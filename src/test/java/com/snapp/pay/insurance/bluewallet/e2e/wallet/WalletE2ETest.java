package com.snapp.pay.insurance.bluewallet.e2e.wallet;

import com.snapp.pay.insurance.bluewallet.api.v1.model.ApiResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.model.WalletDto;
import com.snapp.pay.insurance.bluewallet.api.v1.request.admin.CreateWalletRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetWalletResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.admin.CreateWalletResponse;
import com.snapp.pay.insurance.bluewallet.constant.AuthoritiesConstant;
import com.snapp.pay.insurance.bluewallet.domain.Customer;
import com.snapp.pay.insurance.bluewallet.domain.Wallet;
import com.snapp.pay.insurance.bluewallet.e2e.BaseE2ETest;
import com.snapp.pay.insurance.bluewallet.e2e.mockdata.Constants;
import com.snapp.pay.insurance.bluewallet.repository.CustomerRepository;
import com.snapp.pay.insurance.bluewallet.repository.WalletRepository;
import com.snapp.pay.insurance.bluewallet.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Objects;
import java.util.Optional;

//TODO Not just happy scenarios.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WalletE2ETest extends BaseE2ETest {
    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletRepository walletRepository;

    @PostConstruct
    void init() {
        customerRepository.deleteAll();
        customerRepository.save(new Customer()
                .setMail(Constants.FIRST_CUSTOMER_MAIL));
    }

    // It is better to each test has Its own 'when then'. Here I just use Order for the interview purposes!
    @Test
    @Order(1)
    void shouldCreateWalletByAdmin() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", jwtUtil.generateCustomerToken(1L, Constants.FIRST_CUSTOMER_MAIL, AuthoritiesConstant.ADMIN));
        CreateWalletRequest req = new CreateWalletRequest()
                .setWallet(
                        new WalletDto()
                                .setBalance(Constants.FIRST_CUSTOMER_BALANCE)
                                .setCustomerId(1L)
                );
        ResponseEntity<ApiResponse<CreateWalletResponse>> res = rest.exchange("/api/wallets",
                HttpMethod.POST,
                new HttpEntity<>(req, new HttpHeaders(httpHeaders)),
                new ParameterizedTypeReference<>() {
                });
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
        Assertions.assertEquals(Constants.FIRST_CUSTOMER_BALANCE, Objects.requireNonNull(res.getBody()).getData().getWallet().getBalance());
        Optional<Wallet> wallet = walletRepository.findByCustomerId(1L);
        Assertions.assertTrue(wallet.isPresent());
        Assertions.assertEquals(0, wallet.get().getBalance().compareTo(Constants.FIRST_CUSTOMER_BALANCE));
    }

    @Test
    @Order(2)
    void shouldGetWalletByCustomer() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtUtil.generateCustomerToken(1L, Constants.FIRST_CUSTOMER_MAIL, AuthoritiesConstant.CUSTOMER));
        ResponseEntity<ApiResponse<GetWalletResponse>> res = rest.exchange("/api/wallets",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                });
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
        Assertions.assertEquals(0, Objects.requireNonNull(res.getBody()).getData().getWallet().getBalance().compareTo(Constants.FIRST_CUSTOMER_BALANCE));
        Assertions.assertEquals(1L, Objects.requireNonNull(res.getBody()).getData().getWallet().getCustomerId());
    }
}
