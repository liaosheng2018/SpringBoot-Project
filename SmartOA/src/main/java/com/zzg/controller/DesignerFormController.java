package com.zzg.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zzg.common.vo.Resp;
import com.zzg.model.OaForm;
import com.zzg.service.OaFormService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/designer/form")
@RestController
public class DesignerFormController {
    private static Logger logger= LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private OaFormService oaFormService;

    /**
     * 表单生成器首页
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("redirect:/designerForm.html");
    }

    @GetMapping("/page")
    public Resp<Page> page(HttpServletRequest request){
        Page<OaForm> page = getOaFormPage(request);
        Wrapper<OaForm> query = getOaFormWrapper(request);
        Page<OaForm> data = oaFormService.selectPage(page, query);
        return Resp.OK(data);

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
