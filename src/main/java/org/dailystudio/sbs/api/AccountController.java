package org.dailystudio.sbs.api;

import org.dailystudio.sbs.dto.AccountInfo;
import org.dailystudio.sbs.dto.ChangeNameRequestDTO;
import org.dailystudio.sbs.dto.SignupRequestDTO;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public HttpEntity<Boolean> signUp(@RequestBody SignupRequestDTO signupRequestDTO) {
        Boolean result = accountService.signUp(signupRequestDTO);
        return ResponseEntity.ok(result);
    }

    //POST 끼리 파일을 묶는게 나은지 아니면 기능별로 Account 에 관련된 api 면 전송방식에 상관없이 기능별로 묶는게 나은지??
    //현재는 후자
    @GetMapping("/find")
    public HttpEntity<AccountInfo> findAccountByEmail(
            @RequestParam(value = "email") String email) {
        AccountInfo responseAccount = accountService.findAccountByEmail(email);
        return ResponseEntity.ok(responseAccount);
    }

    @GetMapping("/find/all")
    public HttpEntity<List<AccountInfo>> findAllAccounts(){
        List<AccountInfo> allAccountInfos = accountService.findAllAccount();
        return ResponseEntity.ok(allAccountInfos);
    }

    @PutMapping("change/name")
    public HttpEntity<Boolean> changeUserName(
            @RequestBody ChangeNameRequestDTO changeNameRequestDTO) {
        Boolean result = accountService.changeUserName(changeNameRequestDTO);
        return ResponseEntity.ok(result);
    }

}
