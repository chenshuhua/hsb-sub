package com.yinhai.hsbsub.repository;

import com.yinhai.hsbsub.entity.UpdateLog;
import com.yinhai.hsbsub.repository.base.BaseSupportMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateLogMapper extends BaseSupportMapper {

    boolean addUpdateLog(UpdateLog log);

    boolean deleteLogById(String id);

    List<UpdateLog> queryLogsByCondition(@Param("condition") UpdateLog condition);

    UpdateLog queryLogInfoById(@Param("id") String id);

    boolean updateLogById(@Param("id") String id, @Param("newInfo") UpdateLog newInfo);

    String checkVersionIsExist(@Param("version") String version);

    List<UpdateLog> queryAllLogPre();

    List<UpdateLog> queryAllUpdateLogPre();
}