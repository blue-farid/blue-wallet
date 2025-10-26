package com.snapp.pay.insurance.bluewallet.service.impl;

import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.OtpRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;
import com.snapp.pay.insurance.bluewallet.domain.Customer;
import com.snapp.pay.insurance.bluewallet.domain.Role;
import com.snapp.pay.insurance.bluewallet.domain.Wallet;
import com.snapp.pay.insurance.bluewallet.mapper.CustomerMapper;
import com.snapp.pay.insurance.bluewallet.repository.CustomerRepository;
import com.snapp.pay.insurance.bluewallet.repository.WalletRepository;
import com.snapp.pay.insurance.bluewallet.service.AuthenticationService;
import com.snapp.pay.insurance.bluewallet.util.JwtUtil;
import com.snapp.pay.insurance.bluewallet.util.MailUtil;
import com.snapp.pay.insurance.bluewallet.util.RedisUtil;
import com.snapp.pay.insurance.bluewallet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomerRepository customerRepository;
    private final WalletRepository walletRepository;
    private final CustomerMapper mapper;
    private final RedisUtil redisUtil;
    private final SecurityUtil securityUtil;
    private final MailUtil mailUtil;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public LoginOrSignupResponse login(LoginOrSignupRequest request) {
        String otp = redisUtil.getValue(request.getMail());

        if (!StringUtils.hasText(otp) || !otp.equals(request.getOtp())) {
            //TODO custom exception
            throw new RuntimeException();
        }

        Customer customer = customerRepository.findByMail(request.getMail()).orElse(newCustomer(request));
        return new LoginOrSignupResponse()
                .setCustomer(mapper.entityToDto(customer))
                .setToken(jwtUtil.generateCustomerToken(customer.getId(), customer.getMail(),
                        Role.CUSTOMER.name()));
    }

    @Override
    public void sendOtp(OtpRequest request) {
        String otp = securityUtil.generateOtp();
        redisUtil.insert(request.getMail(), otp);
        mailUtil.sendOtp(request.getMail(), otp);
    }

    private Customer newCustomer(LoginOrSignupRequest request) {
        Customer customer = customerRepository.save(new Customer().setMail(request.getMail()));
        walletRepository.save(
                new Wallet().setCustomerId(customer.getId())
        );
        return customer;
    }
}
