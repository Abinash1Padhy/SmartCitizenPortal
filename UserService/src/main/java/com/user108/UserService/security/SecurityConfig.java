package com.user108.UserService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/user/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .exceptionHandling(excHandling -> excHandling
            		.authenticationEntryPoint((req,resp,authExc)-> {
            			resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            			resp.setContentType(MediaType.APPLICATION_JSON.toString());
            			resp.getWriter().write("{\"error\":\"unauthorized\",\"message\":\"You must provide a valid token to access this resource\"}");
            		})
            		.accessDeniedHandler(accessDeniedHandler())
            		)
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            )
            ;

        return http.build();
    }

// for spring mvc
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return converter;
    }
    
    private AccessDeniedHandler accessDeniedHandler() {
    	return (request, response, authException) ->{
    		response.setStatus(HttpStatus.FORBIDDEN.value());
    		response.setContentType(MediaType.APPLICATION_JSON.toString());
    		response.getWriter().write("{\"error\":\"forbidden\",\"message\":\"You do not have permission to access this resource\"}");
    	};
    }
    
    
}