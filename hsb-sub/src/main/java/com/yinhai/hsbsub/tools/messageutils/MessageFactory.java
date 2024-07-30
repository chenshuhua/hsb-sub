package com.yinhai.hsbsub.tools.messageutils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.Date;

/**
 * @author chenyl
 */
public class MessageFactory {

    private MessageFactory() {}

    private static JsonConfig jsonConfig = new JsonConfig();
    static {
        jsonConfig.registerJsonValueProcessor(Date.class, new MessageValueProcessor());
    }

    private static JsonConfig jsonConfigD = new JsonConfig();
    static {
        jsonConfigD.registerJsonValueProcessor(Date.class, new MessageValueProcessor(MessageValueProcessor.DAY));
    }

    public static String getMessageObj(Object object) {
        return getMessageObj(object, "");
    }

    public static String getMessageObj(Object object, String model) {
        JsonConfig jConfig;
        if(MessageValueProcessor.DAY.equals(model)) {
            jConfig = jsonConfigD;
        }else {
            jConfig = jsonConfig;
        }
        Message message = null;
        try {
            message = Message.successMessage(object);
        }catch ( Exception e) {
            message = Message.errorMessage();
            return JSONObject.fromObject(message, jConfig).toString();
        }
        return JSONObject.fromObject(message, jConfig).toString();
    }

    public static String getMessage(Message msg) {
        return JSONObject.fromObject(msg, jsonConfig).toString();
    }

    public static String getMessageObj(Object obj, ResponseCode e){
        Message message = null;
        try {
            message =new Message(e.getCode(), obj, e.getDescription());
        } catch (Exception e1) {
            message = Message.errorMessage();
            return JSONObject.fromObject(message, jsonConfig).toString();
        }
        return JSONObject.fromObject(message, jsonConfig).toString();
    }

    public static String getObjJsonStr(Object object) {
        return JSONObject.fromObject(object, jsonConfig).toString();
    }
}
