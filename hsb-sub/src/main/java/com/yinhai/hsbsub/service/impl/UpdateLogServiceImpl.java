package com.yinhai.hsbsub.service.impl;

import com.yinhai.hsbsub.entity.UpdateLog;
import com.yinhai.hsbsub.listener.event.LogChangeEvent;
import com.yinhai.hsbsub.properties.SysAuthCodeProperties;
import com.yinhai.hsbsub.repository.UpdateLogMapper;
import com.yinhai.hsbsub.service.UpdateLogService;
import com.yinhai.hsbsub.tools.pagehelper.MyPage;
import com.yinhai.hsbsub.tools.pagehelper.PageParam;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenyl
 */

@Service
public class UpdateLogServiceImpl implements UpdateLogService {

    @Resource
    UpdateLogMapper updateLogMapper;
    @Resource
    SysAuthCodeProperties authCodeProperties;
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public boolean addUpdateLog(UpdateLog log) {
        log.setModifyTime(new Date());
        log.setModifierId(checkModifier(log.getModifierId()));
        if(updateLogMapper.checkVersionIsExist(log.getProgramVersion()) == null) {
            if(updateLogMapper.addUpdateLog(log)) {
                //新增成功就发布时间
                applicationEventPublisher.publishEvent(new LogChangeEvent(this, log));
                return true;
            }
            return false;
        }else {
            throw new IllegalArgumentException("版本号已经存在新增失败");
        }
    }

    @Override
    public boolean deleteLogById(String id) {
        if("".equals(id)) {
            return false;
        }
        return updateLogMapper.deleteLogById(id);
    }

    @Override
    public MyPage<UpdateLog> queryLogsByCondition(UpdateLog condition, PageParam pageParam) {
        if(condition == null) {
            condition = new UpdateLog();
        }
        updateLogMapper.beginPager(pageParam, "id");
        List<UpdateLog> list = updateLogMapper.queryLogsByCondition(condition);
        return updateLogMapper.endPage(list);
    }

    @Override
    public boolean updateLogById(String id, UpdateLog newInfo) {
        newInfo.setModifyTime(new Date());
        newInfo.setModifierId(checkModifier(newInfo.getModifierId()));
        if(updateLogMapper.updateLogById(id, newInfo)) {
            applicationEventPublisher.publishEvent(new LogChangeEvent(this, newInfo));
            return true;
        }
        return false;
    }

    @Override
    public boolean checkVersionIsExist(String version) {
        return updateLogMapper.checkVersionIsExist(version) != null;
    }

    @Override
    public UpdateLog queryLogInfoById(String id) {
        return updateLogMapper.queryLogInfoById(id);
    }

    @Override
    public List<UpdateLog> queryAllLogPre() {
        return updateLogMapper.queryAllLogPre();
    }

    @Override
    public List<UpdateLog> queryAllUpdateLogPre() {
        return updateLogMapper.queryAllUpdateLogPre();
    }

    //暂时用该方法代替鉴权动作，后续修改
    private String checkModifier(String md5Code) {
        String modifierId = authCodeProperties.getAuthCode(md5Code);
        if(modifierId == null) {
            throw new IllegalArgumentException("权限异常操作失败！！！");
        }
        return modifierId;
    }
}
