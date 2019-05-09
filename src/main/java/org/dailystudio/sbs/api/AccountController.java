package org.dailystudio.sbs.api;

import org.dailystudio.sbs.dto.SignupRequestDTO;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public HttpEntity<Boolean> signUp(@RequestBody SignupRequestDTO signupRequestDTO){
        Boolean result = accountService.signUp(signupRequestDTO);
        return ResponseEntity.ok(result);
    }
}
