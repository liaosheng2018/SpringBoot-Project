package com.zzg.service;

import java.io.IOException;

public interface ActivitiService {

    /***
     * 创建请假流程,并指定审核部门经理
     * @param manager
     */
    public void createLeaveWorkFlow(String modelId, String manager) throws IOException;

    /**
     * 传入审核管理员名称,查看审核任务列表
     */
    public void queryExcution(String managerName);

    /**
     * 传入请假流程实例Id+ 是否通过状态标记值 + 指定task 任务审核人信息
     * @param processInstanceId
     * @param isAccept
     * @param managerName
     */
    public void auditLeaveWorkFlow(String processInstanceId,int isAccept,String managerName);

    /**
     * 查询流程实例Id的状态
     * @param proccessInstanceId
     */
    public void  queryProccessInstanceState(String proccessInstanceId);
}
