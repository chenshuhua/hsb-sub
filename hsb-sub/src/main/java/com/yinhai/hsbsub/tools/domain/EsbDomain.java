package com.yinhai.hsbsub.tools.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author chenyl
 **/
public class EsbDomain implements Serializable {
    public Map<String, Object> toMap() {
        Field[] fields = this.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>(fields.length);
        Object value = null;
        SqlName annotation;
        for (Field field: fields) {
            try {
                field.setAccessible(true);
                value = field.get(this);
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null) {
                annotation = field.getAnnotation(SqlName.class);
                if(annotation != null) {
                    map.put(annotation.name(), value);
                }
            }
        }
        return map;
    }
}
