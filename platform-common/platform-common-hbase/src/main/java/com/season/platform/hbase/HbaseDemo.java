package com.season.platform.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by jiyc on 2017/5/24.
 */
public class HbaseDemo {
	private static Configuration conf =null;
	static{
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.0.252");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
	}

	//建表
	public static void createTable(String tableName){
		HBaseAdmin admin=null;
		try {
			admin = new HBaseAdmin(conf);
			if(admin.tableExists(tableName)){
				admin.disableTable(tableName);
				admin.deleteTable(tableName);
				System.out.println("delete exixt table");
			}
			HTableDescriptor table = new HTableDescriptor(tableName);
			table.addFamily(new HColumnDescriptor(Bytes.toBytes("info")));
			table.addFamily(new HColumnDescriptor(Bytes.toBytes("other")));
			admin.createTable(table);
			System.out.println("create table success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(admin!=null){
					admin.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//删除表
	public static void dropTable(String tableName){
		HBaseAdmin admin=null;
		try {
			admin = new HBaseAdmin(conf);
			if(admin.tableExists(tableName)){
				admin.disableTable(tableName);
				admin.deleteTable(tableName);
				System.out.println("delete table success");
			}else{
				System.out.println("table not exist");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//添加数据
	public static void addData(String tableName,String row){
		HTable table=null;
		try {
			table = new HTable(conf, tableName);
			Put put = new Put(Bytes.toBytes(row));
			put.add(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("java"));
			put.add(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("20"));
			put.add(Bytes.toBytes("other"), Bytes.toBytes("other"), Bytes.toBytes("123"));
			table.put(put);
			table.flushCommits();
			System.out.println("add data success");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(table!=null){
					table.close();
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//得到单条数据
	public static void getDate(String tableName,String row){
		HTable table = null;
		try {
			table=new HTable(conf, tableName);
			Get get = new Get(Bytes.toBytes(row));
			Result result = table.get(get);
			for(Cell cell:result.rawCells()){
				System.out.print(new String(CellUtil.cloneRow(cell))+",");
				System.out.print(new String(CellUtil.cloneFamily(cell))+",");
				System.out.print(new String(CellUtil.cloneQualifier(cell))+",");
				System.out.print(new String(CellUtil.cloneValue(cell)));
				System.out.println();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(table!=null){
					table.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//得到表所有数据
	public static void getAllData(String tableName){
		HTable table = null;
		try {
			table = new HTable(conf, tableName);
			Scan scan = new Scan();
			ResultScanner rs = table.getScanner(scan);
			for(Result r:rs){
				for(Cell cell:r.rawCells()){
					System.out.print(new String(CellUtil.cloneRow(cell))+",");
					System.out.print(new String(CellUtil.cloneFamily(cell))+",");
					System.out.print(new String(CellUtil.cloneQualifier(cell))+",");
					System.out.print(new String(CellUtil.cloneValue(cell)));
					System.out.println();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//删除一条数据
	public static void deleteData(String tableName,String row){
		HTable table = null;
		try {
			table = new HTable(conf, tableName);
			Delete delete = new Delete(Bytes.toBytes(row));
			table.delete(delete);
			System.out.println("delete success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		createTable("hbaseDemo");
//		addData("hbaseDemo", "row1");
//		addData("hbaseDemo", "row2");
//		addData("hbaseDemo", "row3");
//		getAllData("hbaseDemo");
//		getDate("hbaseDemo", "row1");
//		getDate("hbaseDemo", "row2");
//		getDate("hbaseDemo", "row3");
//		deleteData("hbaseDemo", "row1");
//		getAllData("hbaseDemo");
//		dropTable("hbaseDemo");
	}
}