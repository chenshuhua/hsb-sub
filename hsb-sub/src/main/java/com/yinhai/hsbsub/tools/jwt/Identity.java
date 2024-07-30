package com.yinhai.hsbsub.tools.jwt;


/**
* @Description 身份信息
* @Author  chenyl
*
*/
public class Identity {
    public Identity(){}

    public Identity(String id, Integer level) {
        this.id = id;
        this.level = level;
    }

    private String id;
    private Integer level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
