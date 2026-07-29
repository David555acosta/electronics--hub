package com.curso.expecializacion.config.security.filters;

import com.curso.expecializacion.config.security.jwt.JwtUtils;
import com.curso.expecializacion.config.security.services.UserDetailServiceIMPL;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAutorizathionFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailServiceIMPL userDetailServiceIMPL;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7).trim();

            try {
                String username = jwtUtils.getUsernameFromToken(token);

                if (username != null) {
                    boolean expired = jwtUtils.isTokenExpired(token);
                    boolean renewable = jwtUtils.canBeTokenRenoved(token);

                    // Caso A: Token expirado pero dentro de la ventana de renovación (7 días)
                    if (expired && renewable) {
                        UserDetails userDetails = userDetailServiceIMPL.loadUserByUsername(username);
                        String renewToken = jwtUtils.generateAccessToken(userDetails.getUsername());

                        // Espacio correcto entre Bearer y el token
                        response.setHeader("Authorization", "Bearer " + renewToken);

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                    // Caso B: Token activo y válido
                    else if (!expired) {
                        UserDetails userDetails = userDetailServiceIMPL.loadUserByUsername(username);
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception e) {
                log.error("Error procesando el token JWT: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

}
