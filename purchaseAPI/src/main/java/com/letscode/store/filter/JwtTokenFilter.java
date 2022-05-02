package com.letscode.store.filter;

import com.letscode.store.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    // private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        boolean isValid = tokenService.isTokenValid(token);
        if(isValid){
            this.authenticate(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        String user = tokenService.getTokenUser(token);
        if (user != null){
            UsernamePasswordAuthenticationToken userToken = tokenService.getUsernamePasswordAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(userToken);
        }
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || token.startsWith("Bearer")){
            return null;
        }

        return token.substring(7);
    }


}
