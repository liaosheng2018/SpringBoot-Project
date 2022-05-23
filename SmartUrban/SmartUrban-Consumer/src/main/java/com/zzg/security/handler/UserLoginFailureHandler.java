package com.zzg.security.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.zzg.common.vo.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理类
 *
 * @author zzg
 *
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Response<String> responseBody  = Response.error(HttpStatus.HTTP_BAD_REQUEST, "Bad Request", "请求失败");
        response.getWriter().write(JSON.toJSONString(responseBody));

    }
}
