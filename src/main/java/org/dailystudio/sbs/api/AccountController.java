package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.Account.*;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/signup")
    public ResponseEntity<Boolean> SignUp(@RequestBody AccountSaveReqDto accountSaveReqDto){
        boolean flag = accountService.signUp(accountSaveReqDto);
        return ResponseEntity.ok(flag);

    }
    @PostMapping("/signin")
    public ResponseEntity<String> SignIn(@RequestBody AccountLoginReqDto accountLoginReqDto){
        String msg = accountService.signIn(accountLoginReqDto);
        return ResponseEntity.ok(msg);
    }

    @ResponseBody
    @GetMapping("/find/User")
    public ResponseEntity<AccountFindResDto> FindUser(@RequestParam("email") String email){
        AccountFindReqDto accountFindReqDto = new AccountFindReqDto(email);
        return ResponseEntity.ok().body(accountService.findUserByEmail(accountFindReqDto));
    }

    @GetMapping("/find/UserAll")
    public ResponseEntity<List<AccountFindResDto>> FindUserAll(){
        return ResponseEntity.ok().body(accountService.findUserAll());
    }

    @PutMapping("/Update/UserName")
    public ResponseEntity<Boolean> UpdateUserName(@RequestBody AccountUpdateNameDto accountUpdateNameDto){
        return ResponseEntity.ok().body(accountService.UpdateUserName(accountUpdateNameDto));
    }

}
