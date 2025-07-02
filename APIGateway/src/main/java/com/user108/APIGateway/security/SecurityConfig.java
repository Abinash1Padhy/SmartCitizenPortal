package com.user108.APIGateway.security;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig{

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

		http
		.authorizeExchange(exchanges -> exchanges
//				.pathMatchers("/userService/**").permitAll()
//				.pathMatchers("/userService/**").hasRole("ROLE_ADMIN")
				.anyExchange().permitAll()
//				.authenticated()
				)
//		.exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec
//				.authenticationEntryPoint(unauthorizedEntryPoint())
//				.accessDeniedHandler(accessDeniedHandler())
//				)
//		.oauth2ResourceServer(oauth2 -> oauth2
//				.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
//				)
		;

		return http.build();
	}

	public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
		return jwt -> {
			Collection<GrantedAuthority> authorities = new ArrayList<>();

			Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
			if (resourceAccess != null && resourceAccess.containsKey("apigateway-confidential")) {
				Map<String, Object> clientRoles = (Map<String, Object>) resourceAccess.get("apigateway-confidential");
				List<String> roles = (List<String>) clientRoles.get("roles");

				for (String role : roles) {
					authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
				}
			}

			JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities);
			return Mono.just(token);
		};
	}

	// ✅ Custom handler for 401 Unauthorized (invalid/missing token)
	private ServerAuthenticationEntryPoint unauthorizedEntryPoint() {
		return (exchange, ex) -> {
			String body = "{\"error\":\"unauthorized\",\"message\":\"You must provide a valid token to access this resource\"}";
			return writeJsonResponse(exchange, 401, body);
		};
	}

	// ✅ Custom handler for 403 Forbidden (insufficient roles)
	private ServerAccessDeniedHandler accessDeniedHandler() {
		return (exchange, ex) -> {
			String body = "{\"error\":\"forbidden\",\"message\":\"You do not have permission to access this resource\"}";
			return writeJsonResponse(exchange, 403, body);
		};
	}

	// ✅ Utility method to write JSON responses
	private Mono<Void> writeJsonResponse(ServerWebExchange exchange, int statusCode, String body) {
		byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
		exchange.getResponse().setStatusCode(HttpStatus.valueOf(statusCode));
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		exchange.getResponse().getHeaders().setContentLength(bytes.length);
		return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
	}
}
