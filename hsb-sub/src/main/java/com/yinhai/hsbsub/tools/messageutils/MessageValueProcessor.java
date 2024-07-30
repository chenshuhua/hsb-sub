package com.yinhai.hsbsub.tools.messageutils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author chenyl
 */
public class MessageValueProcessor implements JsonValueProcessor {
    public static final String DAY = "DAY";
    private String format;

    MessageValueProcessor() {
        this.format = "yyyy-MM-dd hh:mm:ss";
    }

    MessageValueProcessor(String model) {
        super();
        if (DAY.equals(model)) {
            this.format = "yyyy-MM-dd";
        } else {
            this.format = "yyyy-MM-dd hh:mm:ss";
        }
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    private Object process(Object o) {
        if(o instanceof Date) {
            SimpleDateFormat s = new SimpleDateFormat(format, Locale.CHINA);
            return s.format(o);
        }
        return o == null ? "" : o.toString();
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        return process(o);
    }
}
