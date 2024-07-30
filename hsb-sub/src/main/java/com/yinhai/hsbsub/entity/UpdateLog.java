package com.yinhai.hsbsub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenyl
 * 更新日志实体类
 */
public class UpdateLog implements Serializable {

    private String id;

    private String programVersion;

    private String programFileUrl;

    private String logMd;

    private String logHtml;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String modifierId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramVersion() {
        return programVersion;
    }

    public void setProgramVersion(String programVersion) {
        this.programVersion = programVersion;
    }

    public String getProgramFileUrl() {
        return programFileUrl;
    }

    public void setProgramFileUrl(String programFileUrl) {
        this.programFileUrl = programFileUrl;
    }

    public String getLogMd() {
        return logMd;
    }

    public void setLogMd(String logMd) {
        this.logMd = logMd;
    }

    public String getLogHtml() {
        return logHtml;
    }

    public void setLogHtml(String logHtml) {
        this.logHtml = logHtml;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }
}
