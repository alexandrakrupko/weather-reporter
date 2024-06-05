package weather.service;

import lombok.NonNull;
import weather.response.TokenResponse;

public interface JwtTokenService {

    TokenResponse generateToken(@NonNull String username);
}
