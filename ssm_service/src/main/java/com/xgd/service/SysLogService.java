package com.xgd.service;

import com.xgd.pojo.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
