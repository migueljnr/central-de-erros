package com.projetofinal.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String userName = null;
        String jwt = null;

        if ( (authorizationHeader != null) && authorizationHeader.startsWith("Bearer ") ) {
            jwt = authorizationHeader.substring(7);
            try {
                userName = jwtUtil.extractUserName(jwt);
            } catch (ExpiredJwtException e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }

        if ( (userName != null) && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);

                if (jwtUtil.validateToken(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {

                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            } catch (UsernameNotFoundException e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
