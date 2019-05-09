package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.AccountFindReqDto;
import org.dailystudio.sbs.dto.AccountFindResDto;
import org.dailystudio.sbs.dto.AccountReqDto;
import org.dailystudio.sbs.repository.AccountRepository;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("api/signup")
    public ResponseEntity<Boolean> SignUp(@RequestBody AccountReqDto accountReqDto){
        boolean flag = accountService.SignUp(accountReqDto);
        return ResponseEntity.ok(flag);
    }


    @PostMapping("api/find")
    public ResponseEntity<AccountFindResDto> FindeUser(@RequestBody AccountFindReqDto accountFindReqDto){
        return ResponseEntity.ok(accountService.FindUserByEmail(accountFindReqDto));
    }

    @PostMapping("api/findAll")
    public ResponseEntity<List<AccountFindResDto>> FindUserAll(){
        return ResponseEntity.ok(accountService.FindUserAll());
    }

}
