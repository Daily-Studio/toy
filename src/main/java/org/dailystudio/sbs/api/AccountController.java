package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.*;
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

    @GetMapping("/findByUsingEmail/{email}")
    public ResponseEntity<AccountResDto> findEmailAccount(@PathVariable("email") String email) {

        AccountResDto accountResDto = accountService.findAccountByUsingEmail(email);
        return ResponseEntity.ok().body(accountResDto);
    }


    @PutMapping("/changName/{modifiedUserName}")
    public ResponseEntity<AccountChangeNameResDto> changeNameAccount(@PathVariable String modifiedUserNAME, @RequestBody AccountChangeNameReqDto accountChangeNameReqDto) {

        AccountChangeNameResDto accountChangeNameResDto = accountService.changeName(modifiedUserNAME, accountChangeNameReqDto);
        //성공적으로 바뀌었으면 바뀐이름 리턴
        return ResponseEntity.ok().body(accountChangeNameResDto);
    }

    //요청할게 없어서? 안에 안넣었습니다
    @GetMapping("/returnAllUser")
    public ResponseEntity<AccountFindAllUserDto> returnAllUserAccount() {

        AccountFindAllUserDto accountFindAllUserDto = accountService.findAllUser();
        return ResponseEntity.ok().body(accountFindAllUserDto);
    }



}
