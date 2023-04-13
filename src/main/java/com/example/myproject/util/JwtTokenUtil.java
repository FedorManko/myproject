package com.example.myproject.util;

import com.example.myproject.entity.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtTokenUtil {

    private String jwtAccessSecret;
    private String jwtRefreshSecret;
    private Integer jwtExpirationAccessTokenInMin;
    private Integer jwtExpirationRefreshTokenInMin;

    public JwtTokenUtil(@Value("${jwt.secret.access}") String jwtAccessSecret,
                        @Value("${jwt.secret.refresh}") String jwtRefreshSecret,
                        @Value("${jwt.expiration.access}") int jwtExpirationAccessTokenInMin,
                        @Value("${jwt.expiration.refresh}") int jwtExpirationRefreshTokenInMin) {
        this.jwtAccessSecret = jwtAccessSecret;
        this.jwtRefreshSecret = jwtRefreshSecret;
        this.jwtExpirationAccessTokenInMin = jwtExpirationAccessTokenInMin;
        this.jwtExpirationRefreshTokenInMin = jwtExpirationRefreshTokenInMin;
    }

    public String generateAccessToken(@NonNull Client client) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(jwtExpirationAccessTokenInMin).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setSubject(String.format(client.getMobilePhone()))
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtAccessSecret)
                .claim("clientStatus", client.getClientStatus())
                .claim("idCustomer", client.getId().toString())
                .claim("role",client.getUserProfile().getRole())
                .compact();
    }

    public String generateRefreshToken(@NonNull Client client) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusMinutes(jwtExpirationRefreshTokenInMin).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(String.format(client.getMobilePhone()))
                .setExpiration(refreshExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(@NonNull String token) {
        return validateToken(token, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NonNull String token) {
        return validateToken(token, jwtRefreshSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull String secret) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtAccessSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getLoginFromRefreshToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtRefreshSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
