配置刷新

有时候，我们需要对配置内容做一些实时更新的场景，那么Spring Cloud Config是否可以实现呢？答案显然是可以的。下面，我们看看如何进行改造来实现配置内容的实时更新。

在改造程序之前，我们先将config-server和config-client都启动起来，并访问客户端提供的REST APIhttp://localhost:7002/from来获取配置信息，可以获得返回内容为：git-dev-1.0。接着，我们可以尝试使用Git工具修改当前配置的内容，比如，将config-repo/didispace-dev.properties中的from的值从from=git-dev-1.0修改为from=git-dev-2.0，再访问http://localhost:7002/from，可以看到其返回内容还是git-dev-1.0。

下面，我们将在config-client端增加一些内容和操作以实现配置的刷新：

在config-clinet的pom.xml中新增spring-boot-starter-actuator监控模块，其中包含了/refresh刷新API。

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
重新启动config-clinet，访问一次http://localhost:7002/from，可以看到当前的配置值
修改Git仓库config-repo/didispace-dev.properties文件中from的值
再次访问一次http://localhost:7002/from，可以看到配置值没有改变
通过POST请求发送到http://localhost:7002/refresh，我们可以看到返回内容如下，代表from参数的配置内容被更新了

[
  "from"
]
再次访问一次http://localhost:7002/from，可以看到配置值已经是更新后的值了
通过上面的介绍，大家不难想到，该功能还可以同Git仓库的Web Hook功能进行关联，当有Git提交变化时，就给对应的配置主机发送/refresh请求来实现配置信息的实时更新。但是，当我们的系统发展壮大之后，维护这样的刷新清单也将成为一个非常大的负担，而且很容易犯错，那么有什么办法可以解决这个复杂度呢？后续我们将继续介绍如何通过Spring Cloud Bus来实现以消息总线的方式进行通知配置信息的变化，完成集群上的自动化更新。