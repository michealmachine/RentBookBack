package org.zhangziqi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final Key SECRET_KEY;

    static {
        String secretString = "your-secret-string-I-am-your-father-real-realNigga";
        SECRET_KEY = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    // 生成JWT，现在包括username和userId
    public static String generateToken(String username, int userId) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 添加额外的claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims) // 将自定义claims放入JWT
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + 3600000)) // 设置过期时间为1小时
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 验证JWT，并返回Claims
    public static Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            // JwtException 表示JWT验证失败，IllegalArgumentException 表示token为空或格式不正确
            // 在这里可以记录日志或者返回适当的错误信息
            throw new RuntimeException("JWT验证失败：" + e.getMessage());
        }
    }
}