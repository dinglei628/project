package com.demo.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 表
 */
public class Table {
    public Table() {
        columns = new ArrayList<Column>();
    }

    private String tableName;
    private List<Column> columns;
    private String className;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
