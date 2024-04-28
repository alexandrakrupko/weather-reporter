package weather.security.jwt;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenValidator jwtTokenValidator;

    @Override
    public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {
        if (authentication.getPrincipal() instanceof String token) {
            boolean validated = jwtTokenValidator.validate(token);
            if (validated) {
                authentication.setAuthenticated(true);
                return authentication;
            } else {
                throw new BadCredentialsException("Invalid token");
            }
        }
        throw new IllegalArgumentException("Invalid token type, String expected");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }
}
