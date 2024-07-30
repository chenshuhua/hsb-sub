package com.yinhai.hsbsub.controller;

import com.yinhai.hsbsub.entity.UpdateLog;
import com.yinhai.hsbsub.service.UpdateLogService;
import com.yinhai.hsbsub.tools.messageutils.MessageFactory;
import com.yinhai.hsbsub.tools.messageutils.ResponseCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author almare
 */

@RestController
@RequestMapping("/updateLog")
public class UpdateLogController {
    @Resource
    UpdateLogService updateLogService;

    @PostMapping("addLog")
    public String addUpdateLog(@RequestBody UpdateLog log) {
        try {
            return MessageFactory.getMessageObj(updateLogService.addUpdateLog(log));
        }catch (Exception e) {
            return MessageFactory.getMessageObj(e.getMessage(), ResponseCode.CODE_ERROR);
        }
    }

//    @DeleteMapping("delLog/{id}")
    public String deleteLogById(@PathVariable String id) {
        return MessageFactory.getMessageObj(updateLogService.deleteLogById(id));
    }

    /**
     * @des 查询所有日志(包括操作手册)的预览信息
     */
    @GetMapping("queryAllLogPre")
    public String queryAllLogPre() {

        return MessageFactory.getMessageObj(updateLogService.queryAllLogPre());
    }

    /**
     * @des 查询所有升级日志(不包括操作手册)的预览信息
     */
    @GetMapping("queryAllUpdateLogPre")
    public String queryAllUpdateLogPre()  {
        return MessageFactory.getMessageObj(updateLogService.queryAllUpdateLogPre());
    }

    @GetMapping("queryLogInfoById/{id}")
    public String queryLogInfoById(@PathVariable String id) {
        return MessageFactory.getMessageObj(updateLogService.queryLogInfoById(id));
    }

    @PostMapping("updateLogById/{id}")
    public String updateLogById(@PathVariable String id, @RequestBody UpdateLog newInfo) {
        try {
            return MessageFactory.getMessageObj(updateLogService.updateLogById(id, newInfo));
        }catch (Exception e) {
            return MessageFactory.getMessageObj(e.getMessage(), ResponseCode.CODE_ERROR);
        }
    }

    @GetMapping("checkVersionIsExist/{version}")
    public String checkVersionIsExist(@PathVariable String version) {
        return MessageFactory.getMessageObj(updateLogService.checkVersionIsExist(version));
    }
}
