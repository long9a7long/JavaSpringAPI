package com.example.demoSpBoot.jwt;
import java.util.Date;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.stereotype.Component;

import com.example.demoSpBoot.service.UsersService;
import com.example.demoSpBoot.model.users;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class JwtTokenProvider {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "lodaaaaaa";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                   .setSubject((user.getUsername()))
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                   .compact();
    }

    // Lấy thông tin user từ jwt
    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	System.out.println("Expired JWT token");
            //log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	System.out.println("Unsupported JWT token");
            //log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	System.out.println("JWT claims string is empty.");
            //log.error("JWT claims string is empty.");
        }
        return false;
    }
}