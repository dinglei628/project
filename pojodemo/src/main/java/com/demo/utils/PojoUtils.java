package com.demo.utils;

import com.demo.bean.Column;
import com.demo.bean.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PojoUtils {

    private static DatabaseMetaData dbmd;

    /**
     * 获取数据库中的所有表
     * @return
     */
    public static List<Table> getTables() {
        Connection conn = null;
        List<Table> tables = new ArrayList<Table>();
        try {
            conn = DataBaseUtils.getConnection();
            //获取数据库的元数据
            dbmd = conn.getMetaData();
            //获取数据库的所有表的rs
            ResultSet rsTables = dbmd.getTables(null, null, null, new String[]{"TABLE"});
            //遍历rs
            while (rsTables.next()){
                //获取表名
                String tableName = rsTables.getString("TABLE_NAME");
                //获取列
                List<Column> columns = getColumns(tableName);

                Table table = new Table();
                table.setTableName(tableName);
                table.setColumns(columns);
                //根据表名获取类名
                table.setClassName(GenerateUtils.getClassNameByTableName(tableName));
                tables.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtils.closeConnection(conn);
        }
        return tables;
    }

    /**
     * 获取表中的所有列
     * @param tableName
     * @return
     */
    private static List<Column> getColumns(String tableName){
       List<Column> columns = new ArrayList<Column>();
       try {
           //获取每个表的列
           ResultSet rsColumns = dbmd.getColumns(null, "%", tableName, "%");
           while (rsColumns.next()){
               String columnName = rsColumns.getString("COLUMN_NAME");
               String columnType = rsColumns.getString("TYPE_NAME");
               String comment = rsColumns.getString("REMARKS");

               Column column = new Column();
               column.setColumnName(columnName);
               column.setColumnType(columnType);
               column.setComment(comment);
               //根据列名获取属性名
               column.setPropertyName(GenerateUtils.getPropertyNameByColumnName(columnName));
               column.setUpperPropertyName(GenerateUtils.getUpperPropertyNameByColumnName(columnName));
               //根据列类型获取对应的java类型
               column.setJavaType(GenerateUtils.getJavaTypeByColumnType(columnType));

               columns.add(column);
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return columns;
    }
}
