package com.zzg.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzg.mapper.OaFormMapper;
import com.zzg.model.OaForm;
import com.zzg.service.OaFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OaFormServiceImpl extends ServiceImpl<OaFormMapper, OaForm> implements OaFormService {
    private static Logger logger= LoggerFactory.getLogger(OaFormServiceImpl.class);


}
