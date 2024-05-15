package weather.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import weather.security.jwt.JwtAuthentication;

@Component
@SuppressWarnings("unused")
@Slf4j
public class AuthenticationResolver {

    public boolean isBasic() {
        log.debug("Basic authentication required");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication instanceof UsernamePasswordAuthenticationToken;
    }

    public boolean isJwt() {
        log.debug("JWT authentication required");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication instanceof JwtAuthentication;
    }
}
