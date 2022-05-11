package com.zzg.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zzg.common.activiti.entity.NodeEntity;
import com.zzg.common.activiti.enums.NodeTypeEnum;
import com.zzg.common.vo.Resp;
import org.activiti.bpmn.model.*;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 节点控制层
 */
@RequestMapping("/node")
@RestController
public class NodeController {
    private static Logger logger= LoggerFactory.getLogger(NodeController.class);

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;
    /**
     * 查找指定流程模型Id关联开始节点和用户任务节点
     * @param modelId
     */
    @PostMapping(value = "/findModelAssociationNode/{modelId}")
    public Resp<List<NodeEntity>> findModelAssociationNode(@PathVariable("modelId") String modelId) throws IOException {
        List<NodeEntity> list = new ArrayList<>();
        // 获取模型XML文件
        ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelId));
        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);
        //获得流程模型的所有节点
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        // 过滤序列流
        List<FlowElement> filterFlowElements = flowElements.stream().filter(item -> {
            if(item instanceof  SequenceFlow){
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        for(FlowElement e : filterFlowElements) {
            NodeEntity entity = new NodeEntity();
            String id = null;
            String name = null;
            String formKey = null;
            List<FormProperty> formProperties = null;
            // 判断节点类型-- 用户任务节点
            if (e instanceof UserTask) {
                entity.setNodeTypeEnum(NodeTypeEnum.UserTask);
                // 节点Id
                id =((UserTask)e).getId();
                // 节点名称
                name = ((UserTask)e).getName();
                // 配置表单(外置表单）
                formKey = ((UserTask)e).getFormKey();
                // 配置表单(内置表单）
                formProperties = ((UserTask)e).getFormProperties();
            }
            // 判断节点类型 -- 开始任务
            if(e instanceof  StartEvent) {
                entity.setNodeTypeEnum(NodeTypeEnum.StartEvent);
                // 节点Id
                id =((StartEvent)e).getId();
                // 节点名称
                name = ((StartEvent)e).getName();
                // 配置表单(外置表单）
                formKey = ((StartEvent)e).getFormKey();
                // 配置表单(内置表单）
                formProperties = ((StartEvent)e).getFormProperties();
            }
            // 判断节点类型 -- 结束任务
            if(e instanceof  EndEvent) {
                entity.setNodeTypeEnum(NodeTypeEnum.EndEvent);
                // 节点Id
                id =((EndEvent)e).getId();
                // 节点名称
                name = ((EndEvent)e).getName();
            }
             entity.setId(id);
             entity.setName(name);
             entity.setFormKey(formKey);
             entity.setFormPropertys(formProperties);
             list.add(entity);
        }
        return Resp.OK(list);
    }
}
