package weather.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import weather.response.TokenResponse;
import weather.security.jwt.JwtTokenGenerator;

@Service
@RequiredArgsConstructor
class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtTokenGenerator jwtTokenGenerator;

    @Override
    public TokenResponse generateToken(@NonNull String username) {
        return TokenResponse.builder()
                .token(jwtTokenGenerator.generate(username))
                .build();
    }
}
