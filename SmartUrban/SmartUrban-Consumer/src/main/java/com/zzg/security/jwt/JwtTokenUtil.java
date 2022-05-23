package com.zzg.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class JwtTokenUtil {
    /**
     * 密钥secret
     */
    private static String secret = "meta";

    /**
     * 过期时间，单位为秒
     */
    private static long expire = 7 * 24 * 60 * 60;

    /**
     * 解析token
     */
    public static Claims parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return claims;
        }

        return claims;
    }

    /**
     * 新建token
     *
     * @return
     */
    public static String createToken(Map<String, Object> paramter) {
        if (Objects.isNull(paramter)) {
            paramter = new HashMap<>();
        }
        // 过期时间
        Date expireDate = new Date(System.currentTimeMillis() + expire * 1000);

        return Jwts.builder().setHeaderParam("typ", "JWT") // 设置头部信息
                .setClaims(paramter) // 装入自定义的用户信息
                .setExpiration(expireDate) // token过期时间
                .signWith(SignatureAlgorithm.HS512, secret) // 密钥
                .compact();

    }

    /**
     * 刷新token
     * @return
     */
    public static String referToken(Map<String, Object> paramter) {
        if (Objects.isNull(paramter)) {
            paramter = new HashMap<>();
        }
        // 过期时间
        Date expireDate = new Date(System.currentTimeMillis() + expire * 1000);

        return Jwts.builder().setHeaderParam("typ", "JWT") // 设置头部信息
                .setClaims(paramter) // 装入自定义的用户信息
                .setExpiration(expireDate) // token过期时间
                .signWith(SignatureAlgorithm.HS512, secret) // 密钥
                .compact();

    }

}
