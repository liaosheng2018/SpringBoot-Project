package com.zzg.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zzg.common.vo.Resp;
import com.zzg.common.vo.enums.CodeMsgEnum;
import com.zzg.components.SnowflakeComponent;
import com.zzg.exception.BizException;
import com.zzg.model.OaForm;
import com.zzg.param.OaFormParam;
import com.zzg.service.OaFormService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/designer/form")
@RestController
public class DesignerFormController {
    private static Logger logger= LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private OaFormService oaFormService;

    @Autowired
    private SnowflakeComponent snowflake;

    /**
     * 表单生成器首页
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("redirect:/designerForm.html");
    }

    /**
     * 自定义表单分页查询
     * @param request
     * @return
     */
    @GetMapping("/page")
    public Resp<Page> page(HttpServletRequest request){
        Page<OaForm> page = getOaFormPage(request);
        Wrapper<OaForm> query = getOaFormWrapper(request);
        Page<OaForm> data = oaFormService.selectPage(page, query);
        return Resp.OK(data);

    }

    /**
     * 自定义表单新增
     * @param param
     * @return
     */
    @PostMapping("/insert")
    public Resp<String> insert(@RequestBody OaFormParam param){
        OaForm entity = new OaForm();
        BeanUtil.copyProperties(param,entity);
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(String.valueOf(snowflake.snowflakeId()));
        }
        boolean flag = oaFormService.insert(entity);
        if (flag) {
            return  Resp.OK_WITHOUT_DATA();
        }
        return Resp.ERROR(CodeMsgEnum.ERROR);

    }

    @GetMapping("/delete/{id}")
    public Resp delete(@PathVariable String id){
        if (StringUtils.isEmpty(id)) {
            throw new BizException(CodeMsgEnum.NO_MATCH);
        }
        boolean flag = oaFormService.deleteById(id);
        if (flag) {
            return Resp.OK_WITHOUT_DATA();
        }
        return Resp.ERROR(CodeMsgEnum.NO_MATCH);
    }

    @PostMapping(value ="/update", produces = "application/json;charset=utf-8")
    public Resp update(@RequestBody OaFormParam param){
        OaForm entity = new OaForm();
        BeanUtil.copyProperties(param,entity);
        if (StringUtils.isEmpty(entity.getId())) {
            throw new BizException(CodeMsgEnum.NO_MATCH);
        }
        boolean flag = oaFormService.updateById(entity);
        if (flag) {
            Resp.OK_WITHOUT_DATA();
        }
        return Resp.ERROR(CodeMsgEnum.ERROR);
    }

    private Wrapper<OaForm> getOaFormWrapper(HttpServletRequest request) {
        String name = request.getParameter("name");
        Wrapper<OaForm>  query  = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            query.like("name", name);
        }
        return query;
    }

    private Page<OaForm> getOaFormPage(HttpServletRequest request) {
        String pageParame = request.getParameter("page");
        String sizeParame = request.getParameter("size");

        Integer page = StringUtils.isNotEmpty(pageParame) ? Integer.valueOf(pageParame) : 1;
        Integer size = StringUtils.isNotEmpty(sizeParame) ? Integer.valueOf(sizeParame) : 10;

        return new Page<>(page, size);
    }

}
