package edu.zju.cst.AnomalyDetection.utils;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseUtil {

    public static Configuration configuration = HBaseConfiguration.create();

    public static Connection createConnection() throws IOException{

        Connection conn = ConnectionFactory.createConnection(configuration);

        return conn;
    }

    public static void createTable(Connection connection,String tableName,String ...cf1) throws IOException{

        Admin admin = connection.getAdmin();

        try{
        //HTD需要TableName类型的tableName，创建TableName类型的tableName
            TableName tbName = TableName.valueOf(tableName);
            //判断表述否已存在，不存在则创建表
            if(admin.tableExists(tbName)){
                System.err.println("表" + tableName + "已存在！");
                return;
            }
            //通过HTableDescriptor创建一个HTableDescriptor将表的描述传到createTable参数中
            HTableDescriptor HTD = new HTableDescriptor(tbName);
            //为描述器添加表的详细参数
            for(String cf : cf1){
                // 创建HColumnDescriptor对象添加表的详细的描述
                HColumnDescriptor HCD = new HColumnDescriptor(cf);
                HTD.addFamily(HCD);
            }
            //调用createtable方法创建表
            admin.createTable(HTD);
        } finally {

            IOUtils.closeQuietly(admin);

        }
    }

    public void putDate(Connection connection,String tableName,List<String> columnList,List<List<String>> dataList) throws IOException{

        //通过表名获取tbName
        TableName tbname = TableName.valueOf(tableName);
        //通过connection获取相应的表
        Table table =connection.getTable(tbname);
        //hbase支持批量写入数据，创建Put集合来存放批量的数据
        List<Put> batput = new ArrayList<>();
        int i=0;
        for(List<String> list : dataList){
            //实例化put对象，传入行键
            Put put =new Put(Bytes.toBytes("rowkey_"+(i++)));
            //调用addcolum方法，向i簇中添加字段
            int k=0;
            for(String column : columnList){
                put.addColumn(Bytes.toBytes("i"), Bytes.toBytes(column),Bytes.toBytes(list.get(k++)));
            }
            batput.add(put);
        }
        //调用put方法将list中的测试数据写入hbase
        table.put(batput);
        System.err.println("数据插入完成！");
    }
}
