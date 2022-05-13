package com.letscode.store.service;


import com.letscode.store.dto.AuthenticationDTO;
import com.letscode.store.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

//@Service
//public class TokenService {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private String expirationJwt;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    public UsernamePasswordAuthenticationToken getAuthenticate(String token) {
//        boolean isValid = this.isTokenValid(token);
//        if(isValid){
//            return this.authenticate(token);
//        }
//        else {
//            return null;
//        }
//    }
//
//    private UsernamePasswordAuthenticationToken authenticate(String token) {
//        String user = this.getTokenUser(token);
//        Optional<com.letscode.store.model.User> userDBOptional = userRepository.findUserByUsername(user);
//        if (userDBOptional.isPresent()){
//            com.letscode.store.model.User userDB = userDBOptional.get();
//            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//            userDB.getRoles()
//                    .forEach(role -> {
//                        grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
//                    });
//             return new UsernamePasswordAuthenticationToken(userDB, null, grantedAuthorities);
//
//        }else {
//            return null;
//        }
//    }
//
//    public String generateToken(Authentication authentication){
//        User user = (User) authentication.getPrincipal();
//
//        Date expiration = new Date( new Date().getTime() + Long.parseLong(expirationJwt));
//        return Jwts.builder()
//                .setIssuer("StoreApp")
//                .setSubject(user.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }
//    public boolean isTokenValid(String token) {
//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
//    public String getTokenUser(String token){
//       Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//       return claims.getSubject();
//    }
//
//    public String getToken(String token) {
//        if (token == null || !token.startsWith("Bearer")) {
//            return null;
//        }
//
//        return token.substring(7);
//    }
//}


@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expirationJwt;

    @Autowired
    private UserRepository userRepository;


    public AuthenticationDTO getAuthenticate(String token) {
        boolean isValid = this.isTokenValid(token);
        if(isValid){
            UsernamePasswordAuthenticationToken usertoken = this.authenticate(token);
            AuthenticationDTO authenticationDTO = new AuthenticationDTO();
            if ( usertoken != null){
                authenticationDTO.setAuthenticated(true);
                authenticationDTO.setRoles(List.copyOf(usertoken.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())));
                return authenticationDTO;
            }
            else{
                authenticationDTO.setAuthenticated(false);
                authenticationDTO.setRoles(null);
                return authenticationDTO;
            }
        }
        else {
            return null;
        }
    }

    public UsernamePasswordAuthenticationToken authenticate(String token) {
        String user = this.getTokenUser(token);
        Optional<com.letscode.store.model.User> userDBOptional = userRepository.findUserByUsername(user);
        if (userDBOptional.isPresent()){
            com.letscode.store.model.User userDB = userDBOptional.get();
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            userDB.getRoles()
                    .forEach(role -> {
                        grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
                    });
            return new UsernamePasswordAuthenticationToken(userDB, null, grantedAuthorities);

        }else {
            return null;
        }
    }

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

    public String getToken(String token) {
        if (token == null || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7);
    }
}
