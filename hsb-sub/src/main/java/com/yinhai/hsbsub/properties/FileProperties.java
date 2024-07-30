package com.yinhai.hsbsub.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="hsb.file")
@PropertySource("classpath:application.yml")
public class FileProperties {

    private String  programFile;

    private String imgFile;

    private String fileHost;

    private String logBackup;

    public String getProgramFile() {
        return programFile;
    }

    public void setProgramFile(String programFile) {
        this.programFile = programFile;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getFileHost() {
        return fileHost;
    }

    public void setFileHost(String fileHost) {
        this.fileHost = fileHost;
    }

    public String getLogBackup() {
        return logBackup;
    }

    public void setLogBackup(String logBackup) {
        this.logBackup = logBackup;
    }
}
