package weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weather.response.TokenResponse;
import weather.security.BasicAuthentication;
import weather.service.JwtTokenService;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtTokenService tokenService;

    @GetMapping("/token")
    @BasicAuthentication
    public ResponseEntity<TokenResponse> getToken(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                tokenService.generateToken(userDetails.getUsername()));
    }
}
