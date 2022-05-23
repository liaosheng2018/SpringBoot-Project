package com.zzg.security.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.zzg.common.vo.Response;
import com.zzg.security.jwt.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理类
 *
 * @author zzg
 *
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, Object> paramter = new HashMap<String, Object>();
        paramter.put("username", userDetails.getUsername());
        String jwtToken = JwtTokenUtil.createToken(paramter);

        Response<String> responseBody  = Response.error(HttpStatus.HTTP_OK, "Login Success", jwtToken);
        response.getWriter().write(JSON.toJSONString(responseBody));

    }
}
