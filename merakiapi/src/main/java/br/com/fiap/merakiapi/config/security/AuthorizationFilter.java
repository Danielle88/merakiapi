package br.com.fiap.merakiapi.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.merakiapi.service.TokenService;

public class AuthorizationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //pegar o header da requisicao
        String header = request.getHeader("Authorization");

        //validar o token
        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) return; //sai fora do método
        var service = new TokenService();
        String token = header.substring(7);

        if (service.validate(token)){
            // autenticar
            SecurityContextHolder.getContext().setAuthentication(service.getAuthenticationToken(token));
        }


        filterChain.doFilter(request, response);
        
    }
    
}
