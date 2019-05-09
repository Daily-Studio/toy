package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.account.AccountLoginReqDto;
import org.dailystudio.sbs.dto.account.AccountNameChangeReqDto;
import org.dailystudio.sbs.dto.account.AccountResDto;
import org.dailystudio.sbs.dto.account.AccountSaveReqDto;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody AccountSaveReqDto saveReqDto) {
        return ResponseEntity.ok(accountService.saveAccount(saveReqDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody AccountLoginReqDto loginReqDto) {
        return ResponseEntity.ok(accountService.login(loginReqDto));
    }

    @GetMapping("")
    public ResponseEntity<AccountResDto> findByEmail(@RequestParam(value = "email") String email) {
        return ResponseEntity.ok(accountService.findAccountByEmail(email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountResDto>> findAll() {
        return ResponseEntity.ok(accountService.findAllAccount());
    }

    @PutMapping("/email/change")
    public ResponseEntity<Boolean> changeName(@RequestBody AccountNameChangeReqDto changeReqDto) {
        return ResponseEntity.ok(accountService.changeAccountName(changeReqDto));
    }
}
