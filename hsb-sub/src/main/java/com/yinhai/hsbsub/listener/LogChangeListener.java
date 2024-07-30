package com.yinhai.hsbsub.listener;

import com.yinhai.hsbsub.entity.UpdateLog;
import com.yinhai.hsbsub.listener.event.LogChangeEvent;
import com.yinhai.hsbsub.properties.FileProperties;
import com.yinhai.hsbsub.tools.file.FileUtil;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author almare
 * @date 2023-01-31 11:33
 * 监听日志修改动作以便同步备份文件数据
 */

@Component
public class LogChangeListener {

    @Resource
    FileProperties fileProperties;

    @EventListener
    public void backUpLog(LogChangeEvent event) {
        UpdateLog updateLog = event.getUpdateLog();
        String filePath = fileProperties.getLogBackup()+updateLog.getProgramVersion();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("版本号:");
        stringBuilder.append(updateLog.getProgramVersion());
        stringBuilder.append("\n");
        stringBuilder.append("程序包地址:");
        stringBuilder.append(updateLog.getProgramFileUrl());
        stringBuilder.append("\n");
        stringBuilder.append("html文本:");
        stringBuilder.append("\n");
        stringBuilder.append(updateLog.getLogHtml());
        stringBuilder.append("\n");
        stringBuilder.append("md文本:");
        stringBuilder.append("\n");
        stringBuilder.append(updateLog.getLogMd());
        stringBuilder.append("\n");
        FileUtil.addOrUpdateFile(filePath, stringBuilder.toString());
    }
}
