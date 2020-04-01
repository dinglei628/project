package com.demo.bean;

/**
 * 列
 */
public class Column {
    private String columnName;
    private String columnType;
    private String comment;
    private String propertyName;
    private String upperPropertyName;
    private String javaType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getUpperPropertyName() {
        return upperPropertyName;
    }

    public void setUpperPropertyName(String upperPropertyName) {
        this.upperPropertyName = upperPropertyName;
    }
}
