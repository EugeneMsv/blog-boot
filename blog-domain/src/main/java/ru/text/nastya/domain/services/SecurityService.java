package ru.text.nastya.domain.services;

import org.apache.commons.lang3.Validate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityService {

    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        Validate.notNull(context, "Security context can't be null");
        return context.getAuthentication();
    }

    public static boolean isUserAuthenticated() {
        Authentication authentication = getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public static boolean isAnonymousUser() {
        Authentication authentication = getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken;
    }
}
