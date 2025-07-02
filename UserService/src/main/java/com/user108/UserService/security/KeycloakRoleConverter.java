package com.user108.UserService.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

	private static final String CLIENT_ID = "apigateway-confidential";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess == null || resourceAccess.isEmpty()) {
            return Collections.emptyList();
        }

        Object clientAccessObj = resourceAccess.get(CLIENT_ID);
        if (!(clientAccessObj instanceof Map)) {
            return Collections.emptyList();
        }

        Map<String, Object> clientAccess = (Map<String, Object>) clientAccessObj;
        Object rolesObj = clientAccess.get("roles");
        if (!(rolesObj instanceof List<?> roles)) {
            return Collections.emptyList();
        }

        return roles.stream()
                .filter(Objects::nonNull)
                .map(role -> {
                    // Remove "ROLE_" prefix if your Keycloak already prepends it
                    String roleName = role.toString();
                    if (!roleName.startsWith("ROLE_")) {
                        roleName = "ROLE_" + roleName;
                    }
                    return new SimpleGrantedAuthority(roleName);
                })
                .collect(Collectors.toList());
    }
}
