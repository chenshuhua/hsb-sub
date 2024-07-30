package com.yinhai.hsbsub.listener.event;

import com.yinhai.hsbsub.entity.UpdateLog;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;

/**
 * @author almare
 * @date 2023-01-31 13:37
 */
public class LogChangeEvent extends ApplicationEvent {
    /** 日志详细信息 **/
    private UpdateLog updateLog;


    public LogChangeEvent(Object source, @NonNull UpdateLog updateLog) {
        super(source);
        this.updateLog = updateLog;
    }

    public UpdateLog getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(UpdateLog updateLog) {
        this.updateLog = updateLog;
    }
}
