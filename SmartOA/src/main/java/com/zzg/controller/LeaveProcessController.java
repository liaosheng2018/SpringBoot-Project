package com.zzg.controller;

import com.zzg.service.ActivitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/leave")
@RestController
public class LeaveProcessController {
    private static Logger logger= LoggerFactory.getLogger(LeaveProcessController.class);

    @Autowired
    private ActivitiService activitiService;

    /**
     * 1.创建一个流程实例
     */
    @GetMapping(value = "/create")
    public void create() throws IOException {
        //1.举个例子，周志刚 需要请假 ，我在页面上添加请假申请,任务申请人是周志刚,
        // 我选择审核人为范总,请范总给我审核这个请假流程。

        String manager = "范总";
        String modeId ="7501";
        activitiService.createLeaveWorkFlow(modeId, manager);
    }

    /**
     * 2.范总查询正在运行的实例
     */
    @GetMapping(value = "/listLeaveFlow")
    public void listLeaveFlow()  {
        String managerName ="范总";
        activitiService.queryExcution(managerName);
    }

    /**
     * 范总审指定请假正在运行的请假流程实例
     */
    @GetMapping(value = "/auditLeaveFlow")
    public void auditLeaveFlow()  {
        //1.当范总 登录系统的时候，他需要审核一些 审核人为他的 请假流程,当然了，我们在创建这个请假流程的时候，
        //我这个审核人就是可以动态赋值的
        //2.这个是流程实例id ,每次创建一个工作流，都会生成，在具体的生产环境中我们需要将这个流程实例id保存起来。（17509 可能需要替换喔 切记。）
        String processInstanceId = "17518";
        //3.这个是审核人是否同意当前这个流程。（0代表同意 1代表不同意）
        int isAcceppt = 0;
        //4.这个是当前审核人
        String managerName = "manager";
        activitiService.auditLeaveWorkFlow(processInstanceId,isAcceppt,managerName);

    }

    /**
     * 3.查询正在运行的实例
     */
    @GetMapping(value = "/getProccessInstanceIdStatus/{instanceId}")
    public void getLeaveFlowByProccessInstanceId(@PathVariable("instanceId") String instanceId)  {
        activitiService.queryProccessInstanceState(instanceId);
    }



}
