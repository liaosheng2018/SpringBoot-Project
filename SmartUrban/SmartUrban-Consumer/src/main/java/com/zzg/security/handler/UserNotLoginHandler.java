package com.zzg.security.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.zzg.common.vo.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 未登录处理类
 *
 * @author zzg
 *
 */
@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Response<String> responseBody  = Response.error(HttpStatus.HTTP_UNAUTHORIZED, "Unauthorized", "用户未认证");
        response.getWriter().write(JSON.toJSONString(responseBody));

    }
}
