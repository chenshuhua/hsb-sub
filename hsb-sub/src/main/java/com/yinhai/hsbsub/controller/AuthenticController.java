package com.yinhai.hsbsub.controller;

import com.yinhai.hsbsub.properties.SysAuthCodeProperties;
import com.yinhai.hsbsub.tools.messageutils.MessageFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author chenyl
 */

@RestController
@RequestMapping("/auth")
public class AuthenticController {
    @Resource
    SysAuthCodeProperties authCodeProperties;
    @PostMapping("/authCheck")
    public String authCheck(@RequestBody Map<String, String> authCode) {
        return MessageFactory.getMessageObj(authCodeProperties.isLegalAuthCode(authCode.get("authCode")));
    }
}
