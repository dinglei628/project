package com.zb.pojo;

import java.io.Serializable;

public class Videotype implements Serializable {
    private Integer typeId;
    private String typeVideoName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeVideoName() {
        return typeVideoName;
    }

    public void setTypeVideoName(String typeVideoName) {
        this.typeVideoName = typeVideoName;
    }
}
