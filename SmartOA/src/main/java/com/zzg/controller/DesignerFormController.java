package com.zzg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/designer/form")
@RestController
public class DesignerFormController {
    private static Logger logger= LoggerFactory.getLogger(ActivitiController.class);

    /**
     * 表单生成器首页
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("redirect:/designerForm.html");
    }

}
