package weather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import weather.response.TokenResponse;
import weather.security.jwt.JwtTokenGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenServiceImplTest {

    @Mock
    private JwtTokenGenerator jwtTokenGenerator;
    @InjectMocks
    JwtTokenServiceImpl jwtTokenService;

    @Test
    @DisplayName("should generate token")
    public void shouldGenerateToken() {
        // given
        String username = "Username";
        String token = "Token";
        when(jwtTokenGenerator.generate(username)).thenReturn(token);

        // when
        TokenResponse tokenResponse = jwtTokenService.generateToken(username);

        // then
        verify(jwtTokenGenerator, times(1)).generate(username);
        assertNotNull(tokenResponse);
        assertEquals(token, tokenResponse.getToken());
    }

}