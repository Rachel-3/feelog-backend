package com.feelog.feelog_backend.util;

import com.feelog.feelog_backend.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${JWT_SECRET_KEY}")
    private String JWT_SECRET;

    private final long JWT_EXPIRATION = 604800000L; // 토큰 유효 시간 (예: 1주일)

    // JWT 토큰 생성
    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(Integer.toString(user.getId())) // 사용자 ID를 Subject로 설정
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // JWT 토큰에서 사용자 ID 추출
    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }

    // JWT 토큰 유효성 검증
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // 로그: 잘못된 JWT 서명
        } catch (MalformedJwtException ex) {
            // 로그: 잘못된 JWT 구조
        } catch (ExpiredJwtException ex) {
            // 로그: 만료된 JWT
        } catch (UnsupportedJwtException ex) {
            // 로그: 지원되지 않는 JWT
        } catch (IllegalArgumentException ex) {
            // 로그: 비어있는 JWT
        }
        return false;
    }
}
