package com.snapp.pay.insurance.bluewallet.filter;

import com.snapp.pay.insurance.bluewallet.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
            String mail = jwtUtil.extractMail(token);
            String[] roles = jwtUtil.extractRoles(token);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(mail, null, Arrays.stream(roles)
                            .map(SimpleGrantedAuthority::new).toList())
            );
        }

        filterChain.doFilter(request, response);
    }
}
