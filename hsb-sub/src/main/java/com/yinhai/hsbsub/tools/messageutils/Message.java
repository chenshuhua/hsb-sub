package com.yinhai.hsbsub.tools.messageutils;

/**
 * @author chenyl
 */
public class Message {
    private int ret;
    private Object data;
    private String msg;

    public Message(){}

    public Message(Object o) {
        this.ret = ResponseCode.CODE_SUCCESS.getCode();
        this.data = o;
        this.msg = ResponseCode.CODE_SUCCESS.getDescription();
    }

    private Message(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    Message(int ret, Object data, String msg) {
        this.ret = ret;
        this.data = data;
        this.msg = msg;
    }

    /** 返回错误对象 **/
    static Message errorMessage() {  //默认
        return new Message(ResponseCode.CODE_ERROR.getCode(), ResponseCode.CODE_ERROR.getDescription());
    }

    public static Message errorMessage(int ret, String msg) { //自定义
        return new Message(ret, msg);
    }

    /** 返回成功对象 **/
    static Message successMessage(Object object) {
        return new Message(ResponseCode.CODE_SUCCESS.getCode(), object, ResponseCode.CODE_SUCCESS.getDescription());
    }

    public int getRet() {
        return ret;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    @Override
    public String toString() {
        return "{" + "ret=" + ret + ", data=" + data + ", msg='" + msg + '\'' + '}';
    }
}