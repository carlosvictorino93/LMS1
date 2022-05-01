package com.letscode.store.controller;

import com.letscode.store.dto.LoginDTO;
import com.letscode.store.dto.TokenDTO;
import com.letscode.store.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public TokenDTO login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken user
                = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(user);

        String token = tokenService.generateToken(authentication);
        return TokenDTO.builder().token(token).build();
    }

    @GetMapping
    public UsernamePasswordAuthenticationToken getAutenthicate(@RequestBody String token){
        return tokenService.getAuthenticate(token);
    }

}
