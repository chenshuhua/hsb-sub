package com.yinhai.hsbsub.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenyl
 */

@PropertySource(value = {"classpath:/application.yml"}, encoding = "utf-8")
@ConfigurationProperties(prefix = "hsb")
@Component
public class SysAuthCodeProperties {

    private Map<String, String> authCodeTable;

    public void setAuthCode(List<String> authCode) {
        if(authCode != null) {
            this.authCodeTable = new HashMap<>(authCode.size());
            for (String code: authCode) {
                authCodeTable.put(DigestUtils.md5DigestAsHex(code.getBytes()) ,code);
            }
        }
    }

    public boolean isLegalAuthCode(String md5Code) {
        return this.authCodeTable.containsKey(md5Code);
    }

    public String getAuthCode(String md5Code) {
        return this.authCodeTable.get(md5Code);
    }
}
