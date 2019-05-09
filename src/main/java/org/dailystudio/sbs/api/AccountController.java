package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.AccountLoginReqDto;
import org.dailystudio.sbs.dto.AccountResDto;
import org.dailystudio.sbs.dto.AccountSignUpReqDto;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")     //경로
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<AccountResDto> loginAccount(@RequestBody AccountLoginReqDto accountLoginReqDto) {
        AccountResDto accountResDto = accountService.login(accountLoginReqDto);
        return ResponseEntity.ok().body(accountResDto);
    }


    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signAccount(@RequestBody AccountSignUpReqDto accountSignUpReqDto) {

        boolean flag = accountService.signUp(accountSignUpReqDto);
        return ResponseEntity.ok(flag);
    }

    @GetMapping("/findByUsingEmail")
    public ResponseEntity<Boolean> findEmailAccount(@RequestBody AccountSignUpReqDto accountSignUpReqDto) {

        boolean flag = accountService.signUp(accountSignUpReqDto);
        return ResponseEntity.ok(flag);
    }

    @PutMapping("/changName")
    public ResponseEntity<Boolean> changeNameAccount(@RequestBody AccountSignUpReqDto accountSignUpReqDto) {

        boolean flag = accountService.signUp(accountSignUpReqDto);
        return ResponseEntity.ok(flag);
    }

    @GetMapping("/returnAllUser")
    public ResponseEntity<Boolean> returnAllUserAccount(@RequestBody AccountSignUpReqDto accountSignUpReqDto) {

        boolean flag = accountService.signUp(accountSignUpReqDto);
        return ResponseEntity.ok(flag);
    }



}
