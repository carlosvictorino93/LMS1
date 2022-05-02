package com.letscode.store.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expirationJwt;

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        Date expiration = new Date( new Date().getTime() + Long.parseLong(expirationJwt));
        return Jwts.builder()
                .setIssuer("StoreApp")
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public String getTokenUser(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
        WebClient webClient = WebClient.create("http://localhost:8083");

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/login/")
                        .queryParam(token)
                        .build())
                .retrieve()
                .bodyToMono(UsernamePasswordAuthenticationToken.class)
                .block();
    }
}
