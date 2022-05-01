package com.zzg.service.impl;

import com.zzg.service.ActivitiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ActivitiServiceImpl implements ActivitiService {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;
    @Override
    public void createLeaveWorkFlow(String modelId ,String manager) throws IOException {
        //先判断这个流程文件有没有部署(只需部署一次)
        Model modelData = repositoryService.getModel(modelId);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(bytes);
        byte[] bpmnBytes = null;
        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);

        String processName = modelData.getName() + ".bpmn20.xml";

        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName()).addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();
        //获取流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        //启动流程定义,返回流程实例
        Map<String,Object> map = new HashMap<>();
        map.put("manager",manager);
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId(),map);
        System.out.println("流程定义的ID：" + pi.getProcessDefinitionId());
        System.out.println("流程实例的ID：" + pi.getId());

    }

    @Override
    public void queryExcution(String managerName) {
        //创建正在执行的流程查询对象 (依据流程运行的变量定义,查询流程实例)run_variable 表查询
        List<Execution> executionList = runtimeService.createExecutionQuery().variableValueLike("manager", "%"+managerName+"%")
                .list();  //查询出集合
        for(Execution execution: executionList){
            System.out.println("正在执行的流程对象的id: "+execution.getId());
            System.out.println("所属流程实例的id:"+execution.getProcessInstanceId());
            System.out.println("正在活动的节点的id: "+execution.getActivityId());
        }

    }

    @Override
    public void auditLeaveWorkFlow(String processInstanceId, int isAccept, String managerName) {
        Map<String,Object> map = new HashMap<>();
        //得到当前实例下的task
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.addComment(task.getId(), processInstanceId, "审核人是否同意该请假流程");
        if(isAccept == 0){
            System.out.println("审核人同意了该请求");
            map.put("accept","同意");
            task.setAssignee(managerName);
            map.put("auditor", managerName);
        }else{
            System.out.println("审核人驳回了该请求");
            map.put("accept","驳回");
            //审核驳回后
            task.setAssignee("zzg");
            map.put("auditor","zzg");
        }
        task.setDescription("请假的描述信息");
        // 任务执行签收操作

        //执行当前这个工作流任务
        taskService.saveTask(task);
        taskService.complete(task.getId(), map);


    }

    @Override
    public void queryProccessInstanceState(String proccessInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proccessInstanceId).singleResult();
        if (processInstance == null) {
            System.out.println("当前流程已经完成");
        } else {
            System.out.println("当前流程实例ID：" + processInstance.getId());
            System.out.println("当前流程所处的位置：" + processInstance.getActivityId());
        }

    }
}
