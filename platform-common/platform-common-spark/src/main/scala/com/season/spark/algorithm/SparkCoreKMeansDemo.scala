import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * 聚类算法是机器学习中的一大重要算法，也是我们掌握机器学习的必须算法，下面对聚类算法中的K-means算法做一个简单的描述：

一、概述

K-means算法属于聚类算法中的直接聚类算法。给定一个对象(或记录)的集合，将这些对象划分为多个组或者“聚簇”，从而使同组内的对象间比较相似而不同组对象间差异比较大；换言之，聚类算法就是将相似的对象放到同一个聚簇中，而将不相似的对象放到不同的聚簇中。由于在聚类过程中不使用到类别标签，所以相似性的概念要基于对象的属性进行定义。应用不同则相似性规则和聚类算法一般不太一样，所以，不同的聚类算法适应于不同的业务场景，因此，“最优”的聚类算法实际上依赖于具体的应用。

k-means算法是一种简单的迭代型聚类算法，将一个给定的数据集分为用户指定的k的聚簇。实现和运行该算法都比较简单，而且运行速度比较快，同时易于修改，所以在实际应用中使用场景比较多，可以说该算法是数据挖掘领域发展史中最为重要的算法之一。



二、算法描述

k-means算法的输入对象是d维向量空间的一些点。因此，该算法是对一个d维向量的点集D={xi|i=1,....,N}进行聚类，其中xi表示第i个对象(数据点), k-means算法会将集合D划分为k个聚簇。也就是说k-means算法会将集合D中的每一个点xi都归于k个聚簇中的一个，所以可以定义一个长度为N的聚簇成员向量m，其分量mi就是xi的聚簇标识。

k值是k-means算法的一个关键输入。但是如何选择k值对于理解k-means算法的运行原理没有任何关系。k值的选择的典型方法一般是根据某些先验知识来进行确定。

在k-means算法中，使用d维向量空间中的一个点来表示每个聚簇，用集合C={cj|j=1,..,k}来表示，这k个聚簇代表也被成为聚簇均值或者聚簇中心点。那么mi属于集合C中的一个点，表示的是xi的聚簇点坐标。

聚类算法通常基于“紧密度”或者“相似度”进行分组。在k-means算法中，默认的紧密度度量标准是用欧几里得距离(dist)来表示的；k-means算法实质是要得到一个最小化的非负代价函数(cost)；公式为：

dist(xi,xj) = sqrt(pow(xi1-xj1, 2) + pow(xi2-xj2, 2) + pow(xi3-xj3, 2) + pow(xi4-xj4, 2) + ..... +  pow(xid-xjd, 2)), 求点xi和xj两点之间的距离pow(x,y)表示xy的值，sqrt(x)表示求x的平方根。

cost=dist(x1,m1) + dist(x2,m2) + dist(x3,m3) + dist(x4,m4) + ...... + dist(xn,mn)

算法步骤：

1）初始化k个聚类重点点，构成集合C

2）重新分配原始数据集合D。计算每个数据点到C中各个点的距离，并将数据点分配到离之最近的那个集合C中的聚簇中心所表示的聚簇中。

3）重新计算每个聚簇中新的聚簇中心点的坐标。聚簇点坐标即所有聚簇中点坐标的均值

4）计算cost代价函数

5）当集合C不再发生变化的时候，算法收敛(cost代价函数值不发生变化)；否则重新回到第二步继续循环计算

6）集合C即最终所需要求得的K个聚类中心点的坐标



三、SparkCore算法实现

这里简单使用SparkCore代码实现k-means算法（仅仅考虑两位的算法计算），没有进行任何优化，另外这里没有采用cost代价函数，
使用的是另外一种变种，考虑两次C集合之间的数据变化大小，当值不进行变化的时候，算法收敛，具体代码如下：（代码仅做参考，对算法的准确性不做保障，哈哈哈.............）
  *
  */



/**
  * SparkCore实现KMeans算法
  * Created by gerry
  */
object SparkCoreKMeansDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("spark-core-kmeans")
      .setMaster("local[*]")
    val sc = SparkContext.getOrCreate(conf)
    //=======================================
    val path = "data/k-means/result"
    val fs = FileSystem.get(sc.hadoopConfiguration)
    fs.delete(new Path(path), true)
    val rdd = sc.textFile("data/taxi/taxi.csv").map(line => {
      val arr = line.split(",")
      (arr(1).toDouble, arr(2).toDouble)
    })

    rdd.cache()

    // SparkCore实现K-Means
    val K = 10
    // 获取初始化种子
    var clusters = rdd.takeSample(false, K)
    var totalDistance: Double = 0.0

    do {
      // 将k个聚类中心广播出去
      val clusterPointWithIndex = clusters.zipWithIndex.map(_.swap)
      val seeder = sc.broadcast(clusterPointWithIndex)

      // 开始计算新的聚类中心
      val newClusterInfos = rdd
        .map(point => {
          // 计算当前节点离所有节点最近的节点是那个
          val clusterIndex = seeder.value
            .map {
              case (index, clusterPoint) => {
                val dist = calcDistance(point, clusterPoint)
                (dist, index)
              }
            }
            .minBy(_._1)._2

          // 返回结果
          (clusterIndex, point)
        })
        .aggregateByKey(ClusterPoint(0D, 0D, 1L))(
          (clusterPoint, point) => {
            // 累加
            clusterPoint.x += point._1
            clusterPoint.y += point._2
            clusterPoint.n += 1
            // 返回聚合结果
            clusterPoint
          },
          (clusterPoint1, clusterPoint2) => {
            // 累加
            clusterPoint1.x += clusterPoint2.x
            clusterPoint1.y += clusterPoint2.y
            clusterPoint1.n += clusterPoint2.n

            // 返回聚合结果
            clusterPoint1
          }
        )
        .collect()
        .map {
          case (oldClusterIndex, clusterPoint) => {
            // 计算新的中心点
            val newCluster = (clusterPoint.x / clusterPoint.n, clusterPoint.y / clusterPoint.n)
            // 获取上一个迭代产生的聚簇点
            val oldClusterPoint = clusterPointWithIndex
              .filter(_._1 == oldClusterPoint)
              .toList match {
              case (_, point) :: Nil => point
              case _ => throw new RuntimeException("不应该出现该异常，从之前的聚簇集合中没法找到下标为")
            }

            // 计算老的中心点和新的中心点的距离
            val distance = calcDistance(oldClusterPoint, newCluster)
            // 返回新的节点, 以及距离
            (distance, newCluster)
          }
        }

      // 清空广播变量
      seeder.unpersist(true)

      // 获得总距离(两次C集合之间的变化距离)
      totalDistance = newClusterInfos.map(_._1).sum
      // 新的聚类点集合
      clusters = newClusterInfos.map(_._2)
      println("distance:" + totalDistance + "," + clusters.mkString("{", ",", "}"))
    } while (totalDistance > 1e-7)

    rdd.map(tuple => tuple._1 + "\t" + tuple._2).repartition(1).saveAsTextFile(path)
    println("最终聚簇点:" + clusters.map(_.swap).mkString("{", ",", "}"))

    rdd.unpersist()
  }

  /**
    * 集群间点的表示方式
    *
    * @param x
    * @param y
    * @param n
    */
  case class ClusterPoint(var x: Double, var y: Double, var n: Long) extends Serializable

  /**
    * 计算距离(欧几里得距离)
    *
    * @param point1
    * @param point2
    * @return
    */
  def calcDistance(point1: (Double, Double), point2: (Double, Double)): Double = {
    import scala.math._
    val a = point1._1 - point2._1
    val b = point1._2 - point2._2
    return sqrt(pow(a, 2) + pow(b, 2))
  }
}