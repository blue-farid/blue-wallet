package com.snapp.pay.insurance.bluewallet.e2e.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.pay.insurance.bluewallet.api.v1.AuthController;
import com.snapp.pay.insurance.bluewallet.api.v1.model.CustomerDto;
import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.OtpRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;
import com.snapp.pay.insurance.bluewallet.constant.ApiStatus;
import com.snapp.pay.insurance.bluewallet.service.AuthenticationService;
import com.snapp.pay.insurance.bluewallet.util.MessageSourceUtil;
import com.snapp.pay.insurance.bluewallet.util.RateLimitUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AuthenticationService authService;
    @MockitoBean
    private RateLimitUtil rateLimitUtil;
    @MockitoBean
    private MessageSourceUtil messageSourceUtil;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSendOtpSuccessfully() throws Exception {
        when(rateLimitUtil.isOtpRequestAllowed("test@mail.com")).thenReturn(true);
        doNothing().when(authService).sendOtp(any(OtpRequest.class));

        mockMvc.perform(get("/api/auth/otp")
                        .param("mail", "test@mail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(ApiStatus.SUCCESS.name()));
    }

    @Test
    void shouldRejectOtpDueToRateLimit() throws Exception {
        when(rateLimitUtil.isOtpRequestAllowed("limit@mail.com")).thenReturn(false);

        mockMvc.perform(get("/api/auth/otp")
                        .param("mail", "limit@mail.com"))
                .andExpect(status().isTooManyRequests());
    }

    @Test
    void shouldLoginSuccessfully() throws Exception {
        when(rateLimitUtil.isLoginRequestAllowed("user@mail.com")).thenReturn(true);

        LoginOrSignupResponse fakeResponse = new LoginOrSignupResponse()
                .setCustomer(new CustomerDto()
                        .setMail("user@mail.com"))
                .setToken("jwt-token");

        when(authService.login(any(LoginOrSignupRequest.class))).thenReturn(fakeResponse);

        LoginOrSignupRequest req = new LoginOrSignupRequest()
                .setMail("user@mail.com").setOtp("123456");

        mockMvc.perform(post("/api/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(ApiStatus.SUCCESS.name()))
                .andExpect(jsonPath("$.data.customer.mail").value("user@mail.com"))
                .andExpect(jsonPath("$.data.token").value("jwt-token"));
    }
}
