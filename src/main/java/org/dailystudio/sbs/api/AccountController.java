package org.dailystudio.sbs.api;

import org.dailystudio.sbs.dto.LoginReq;
import org.dailystudio.sbs.dto.SignUpReq;
import org.dailystudio.sbs.dto.UserReq;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("signUp")
    public boolean signUp(@RequestBody SignUpReq signUpReq) {
        boolean flag = accountService.signUp(signUpReq);
        return flag;
    }

    @GetMapping("findUser")
    public UserReq findUser(String email) throws NoSuchFieldException {
        UserReq userReq = accountService.findUser(email);
        return userReq;
    }

    @PostMapping("login")
    public String login(
            @RequestBody LoginReq loginReq) {
        String result;
        result = accountService.login(loginReq);
        return result;
    }

/*    @GetMapping("findAll")
    public UserReq findAll(){
        UserReq userReq = accountService.findAllUser();
        return userReq;
    }*/
}
