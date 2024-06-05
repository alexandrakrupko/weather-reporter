package weather.security;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("security")
@Validated
@Getter
@Setter
public class SecurityProperties {

    @NotNull(message = "security.username property is not specified")
    private String username;
    @NotNull(message = "security.password property is not specified")
    private String password;
    @Valid
    private Jwt jwt = new Jwt();

    @Validated
    @Getter
    @Setter
    public static class Jwt {

        @NotNull(message = "security.jwt.secret-key property is not specified")
        private String secretKey;
    }
}
