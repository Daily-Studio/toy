package org.dailystudio.sbs.controller;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.LoginRequestDTO;
import org.dailystudio.sbs.dto.SignupRequestDTO;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public HashMap<String, String> postLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        HashMap<String,String> result = accountService.login(loginRequestDTO);
        return result;
    }

    @PostMapping("/signup")
    public HashMap<String, String> postSignup(@RequestBody SignupRequestDTO signupRequestDTO){
        HashMap<String,String> result = accountService.signup(signupRequestDTO);

        return result;
    }
}
