package com.snapp.pay.insurance.bluewallet.e2e.auth;

import com.snapp.pay.insurance.bluewallet.api.v1.model.ApiResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.OtpRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;
import com.snapp.pay.insurance.bluewallet.e2e.BaseE2ETest;
import com.snapp.pay.insurance.bluewallet.e2e.data.Constants;
import com.snapp.pay.insurance.bluewallet.util.RedisUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationE2ETest extends BaseE2ETest {

    @Autowired
    private TestRestTemplate rest;

    @SpyBean
    private RedisUtil redisUtil;

    @Test
    @Order(1)
    void shouldSendOtp() {
        OtpRequest request = new OtpRequest()
                .setMail(Constants.FIRST_CUSTOMER_MAIL);
        ResponseEntity<ApiResponse<Void>> response =
                rest.exchange("/auth/otp?mail=" + request.getMail(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", Objects.requireNonNull(response.getBody()).getStatus());
    }

    @Test
    @Order(2)
    void shouldLoginOrSignupSuccessfully() {
        String otp = "123456";
        doReturn(otp)
                .when(redisUtil)
                .getValue(Constants.FIRST_CUSTOMER_MAIL);
        LoginOrSignupRequest request = new LoginOrSignupRequest()
                .setMail(Constants.FIRST_CUSTOMER_MAIL)
                .setOtp(otp);
        HttpEntity<LoginOrSignupRequest> entity = new HttpEntity<>(request);
        ResponseEntity<ApiResponse<LoginOrSignupResponse>> response = rest.exchange(
                "/auth",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", Objects.requireNonNull(response.getBody()).getStatus());
        Assertions.assertEquals(Constants.FIRST_CUSTOMER_MAIL, response.getBody().getData().getCustomer().getMail());
        Assertions.assertNotNull(response.getBody().getData().getToken());
    }
}
