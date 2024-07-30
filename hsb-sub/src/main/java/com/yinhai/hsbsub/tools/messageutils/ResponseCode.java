package com.yinhai.hsbsub.tools.messageutils;

/**
 * @author chenyl
 */

public enum ResponseCode {

    /**
    * @Description 200-成功，401-未授权，402-授权过期，500-服务出错， 601-非法参数
    * @Author  chenyl
    *
    */
    CODE_SUCCESS(200, "SUCCESS"),
    CODE_FORBIDDEN(401, "No Auth Forbidden"),
    CODE_EXPIRED(402, "Access code is expired"),
    CODE_ERROR(500, "System Exception"),
    CODE_SERVER_DOWN(501, "Server is under maintenance"),
    CODE_SQL_ERROR(601, "SQL Exception please enter correct args");

    private int code;
    private String description;

    ResponseCode(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    protected void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StateCode{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}