package org.dailystudio.sbs.service;

import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.AccountReq;
import org.dailystudio.sbs.dto.LoginReq;
import org.dailystudio.sbs.dto.SignUpReq;
import org.dailystudio.sbs.dto.UserReq;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountService(AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean signUp(SignUpReq signUpReq) {
        Account account = signUpReq.toEntit(bCryptPasswordEncoder);
        accountRepository.save(account);
        return true;
    }

    public UserReq findUser(String email) throws NoSuchFieldException {
        Account account = accountRepository.findByEmail(email);
        account = accountRepository.findByUser(email);
        if (account == null)
            throw new NoSuchFieldException();
        else {
            UserReq userReq = new UserReq(account.getEmail(), account.getName());
            return userReq;
        }
    }

    public String login(LoginReq loginReq) {
        Account account = accountRepository.findByEmail(loginReq.getEmail());

        if (bCryptPasswordEncoder.matches(loginReq.getPassword(), account.getPassword())) {
            return "success";
        }
        return "fail";
    }

/*    public UserReq findAllUser() {
         ArrayList arrayList = new ArrayList(accountRepository.findAll());

    }*/
}
