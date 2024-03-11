package com.tavares.appcontatos._0_configuration;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //Extrair token di cabecalho da requisicao        
        //Validadr o token
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")){
            token = token.substring(7);
            //Validar o token
            if(jwtTokenUtil.validateToken(token)){
                //se valido, configurar a autenticacao no contexto
                String username = jwtTokenUtil.getUsernameFromToken(token);
                //criar yna listas de sutorizacao
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USESR"));
                Authentication autenticacao = new UsernamePasswordAuthenticationToken(username,null, authorities);

                SecurityContextHolder.getContext().setAuthentication(autenticacao);
            }
        }
        filterChain.doFilter(request, response);
    }

}
