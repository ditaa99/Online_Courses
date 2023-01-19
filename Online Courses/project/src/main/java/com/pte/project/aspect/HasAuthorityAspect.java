package com.pte.project.aspect;

import java.util.Collection;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class HasAuthorityAspect {
    
    private static Logger log = Logger.getLogger("com.pte.shop.aspect");
    
    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(authorities)")
    public void hasAuthorities(final JoinPoint joinPoint, final HasAuthorities authorities) throws Exception {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (!Objects.isNull(securityContext)) {
            final Authentication authentication = securityContext.getAuthentication();
            if (!Objects.isNull(authentication)) {
                final String username = authentication.getName();

 

                final Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();

 

                if (Stream.of(authorities.authorities()).noneMatch(authorityName -> userAuthorities.stream().anyMatch(userAuthority ->
                        authorityName.name().equals(userAuthority.getAuthority())))) {

 

                    log.log(Level.WARNING, "User {} does not have the correct authorities required by endpoint", username);
                    throw new Exception("FORBIDDEN");
                }
            } else {
                log.log(Level.WARNING, "The authentication is null when checking endpoint access for user request");
                throw new Exception("UNAUTHORIZED");
            }
        } else {
            log.log(Level.WARNING, "The security context is null when checking endpoint access for user request");
            throw new Exception("FORBIDDEN");
        }
    }
 
    
}
