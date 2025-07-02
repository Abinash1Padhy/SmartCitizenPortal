package com.user108.APIGateway.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

	@Override
	public Collection<GrantedAuthority> convert(Jwt jwtToken) {

		Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> resourceAccess = jwtToken.getClaim("resource_access");

        if (resourceAccess != null && resourceAccess.containsKey("apigateway-confidential")) {
            Map<String, Object> clientRoles = (Map<String, Object>) resourceAccess.get("apigateway-confidential");
            List<String> roles = (List<String>) clientRoles.get("roles");

            for (String role : roles) {
                authorities.add(() -> "ROLE_" + role.replace("ROLE_", "")); // double-check prefixing
            }
        }
		
		return authorities;
	}

}
