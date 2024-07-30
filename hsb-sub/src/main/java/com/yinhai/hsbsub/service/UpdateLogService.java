package com.yinhai.hsbsub.service;

import com.yinhai.hsbsub.entity.UpdateLog;
import com.yinhai.hsbsub.tools.pagehelper.MyPage;
import com.yinhai.hsbsub.tools.pagehelper.PageParam;

import java.util.List;

public interface UpdateLogService {

    boolean addUpdateLog(UpdateLog log);

    boolean deleteLogById(String id);

    MyPage<UpdateLog> queryLogsByCondition(UpdateLog condition, PageParam pageParam);

    boolean updateLogById(String id, UpdateLog newInfo);

    boolean checkVersionIsExist(String version);

    UpdateLog queryLogInfoById(String id);

    List<UpdateLog> queryAllLogPre();

    List<UpdateLog> queryAllUpdateLogPre();
}
