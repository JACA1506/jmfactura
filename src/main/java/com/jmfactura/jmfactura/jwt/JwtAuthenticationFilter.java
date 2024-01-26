package com.jmfactura.jmfactura.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Expose-Headers", "responseType");
        response.addHeader("Access-Control-Expose-Headers", "observe");
        System.out.println("Request Method: " + request.getMethod());
        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
            try {

                final String token = getTokenFromRequest(request);
                final String username;

                if (token == null) {
                    filterChain.doFilter(request, response);
                    return;
                }
                username = jwtService.getUsernameFromToken(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userdetails = userDetailsService.loadUserByUsername(username);

                    if (jwtService.isTokenValid(token, userdetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userdetails,
                                null, userdetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }

                filterChain.doFilter(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Pre-flight");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"
                    + "Authorization, content-type," +
                    "USERID" + "ROLE" +
                    "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            return authHeader.substring(7);
        }
        return null;
    }

}
