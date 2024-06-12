package org.zhangziqi.filter;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.zhangziqi.utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!exchange.getRequest().getURI().getPath().equals("/userService/loginCheck")) {
            // 提取 JWT 令牌
            List<String> headers = exchange.getRequest().getHeaders().get("Authorization");
            if (headers == null || headers.isEmpty()) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String token = headers.get(0).startsWith("Bearer ") ? headers.get(0).substring(7) : headers.get(0);

            try {
                Claims claims = JwtUtil.validateToken(token);
                System.out.println(claims);
                // 将 username 和 id 加到请求头
                exchange.getRequest().mutate()
                        .header("userName", claims.get("username", String.class))
                        .header("userId", claims.get("userId", Integer.class).toString()) // 修改此处的键名称
                        .build();
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;  // Ensure this filter is applied early in the chain
    }
}
