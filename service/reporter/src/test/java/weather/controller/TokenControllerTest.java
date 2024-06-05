package weather.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import weather.response.TokenResponse;
import weather.service.JwtTokenService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TokenController.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser("username")
class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtTokenService jwtTokenService;

    @Test
    @DisplayName("should return TokenResponse")
    public void shouldReturnTokenResponse() throws Exception {
        // given
        TokenResponse tokenResponse = TokenResponse.builder()
                .token("token")
                .build();

        // when, then
        when(jwtTokenService.generateToken("username")).thenReturn(tokenResponse);

        mockMvc.perform(get("/token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token", is(tokenResponse.getToken())));
    }

}