package com.zzg.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zzg.common.vo.PageList;
import com.zzg.common.vo.Resp;
import com.zzg.common.vo.enums.CodeMsgEnum;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/model")
@RestController
public class ModelController {
    private static Logger logger= LoggerFactory.getLogger(ActivitiController.class);

    /**
     * 流程存储服务
     */
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 模型默认首页
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("redirect:/model.html");
    }

    /**
     * 模型分页查询
     * @param request
     * @return
     */
    @GetMapping("/page")
    public PageList getModels(HttpServletRequest request){
        String pageParame = request.getParameter("page");
        String sizeParame = request.getParameter("size");
        String modelName = request.getParameter("name");
        Integer page = StringUtils.isNotEmpty(pageParame) ? Integer.valueOf(pageParame) : 1;
        Integer size = StringUtils.isNotEmpty(sizeParame) ? Integer.valueOf(sizeParame) : 10;

        // 获取模型查询实例
        ModelQuery modelQuery = repositoryService.createModelQuery();
        // 根据模型名称模糊查询
        Integer total;
        List<Model> models;

        if (StringUtils.isNotEmpty(modelName)) {
            // 获取模型总数
            total = (int) modelQuery.modelNameLike("%" + modelName + "%").count();
            // 获取列表数据
            models = modelQuery
                    .modelNameLike("%" + modelName + "%")
                    .orderByLastUpdateTime()
                    .desc()
                    .listPage((page - 1) * size, size);
        } else {
            // 获取模型总数
            total = (int) modelQuery.count();
            // 获取列表数据
            models = modelQuery
                    .orderByLastUpdateTime()
                    .desc()
                    .listPage((page - 1) * size, size);
        }
        //封装到pageList对象中
        PageList pageList = new PageList(page,total, size);
        pageList.setList(models);

        return pageList;
    }

    /**
     * 模型删除
     * @param request
     */
    @GetMapping("/delete")
    public Resp<String> delete(HttpServletRequest request){
        String processId = request.getParameter("processId");
        System.out.println("processId is:" + processId);
//        if(StringUtils.isNotEmpty(processId)){
//            return Resp.ERROR(CodeMsgEnum.ERROR);
//        }
        Model model = repositoryService.getModel(processId);
        if(model!=null){
            System.out.println("指定模型存在");
            repositoryService.deleteModel(processId);
        }
        return Resp.OK_WITHOUT_DATA();
    }

    /**
     * 新增模型
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping(value = "/insert", produces = "application/json;charset=utf-8")
    public Resp<String> insert(@RequestBody Map map) throws UnsupportedEncodingException {
        System.out.println("model 参数接受:" + map);
        //初始化一个空模型
        Model model = repositoryService.newModel();

        //设置一些默认信息，可以用参数接收
        int revision = 1;
        String key = String.valueOf(map.get("processKey"));
        String name =String.valueOf(map.get("processName"));;
        String description = String.valueOf(map.get("processDesc"));

        //ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace","http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset" , stencilSetNode);


        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);

        String id = model.getId();

        repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
        // return new ModelAndView("redirect:/modeler.html?modelId=" + id);
        return Resp.OK(id);

    }

    /**
     * 根据Model部署流程
     */
    @PostMapping(value = "deploy/{modelId}")
    public Resp<String> deploy(@PathVariable("modelId") String modelId) {
        try {
            // 获取模型
            Model model = repositoryService.getModel(modelId);
            ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(model.getId()));
            BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);

            String processName = model.getName()+".bpmn20.xml";
            byte[] bytes = new BpmnXMLConverter().convertToXML(bpmnModel);
            // 部署流程
            Deployment deployment = repositoryService
                    .createDeployment().name(model.getName())
                    .addString(processName, new String(bytes,"UTF-8"))
                    .deploy();
            System.out.println("流程部署id----"+deployment.getId());
            return Resp.OK(deployment.getId());
        } catch (Exception e) {
            logger.error("根据模型部署流程失败：modelId={}", modelId, e);
            return Resp.ERROR(CodeMsgEnum.ERROR);
        }
    }

    /**
     * 导出指定模型xml 文件
     * @param modelId
     * @param response
     */
    @GetMapping(value = "export/{modelId}")
    public void export(@PathVariable String modelId, HttpServletResponse response) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(in, outputStream);
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn.xml";
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            logger.error("导出model的xml文件失败：{}", e.getMessage(), e);
        }
    }


}
