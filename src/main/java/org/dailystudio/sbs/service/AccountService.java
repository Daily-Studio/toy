package org.dailystudio.sbs.service;

import com.sun.tools.javac.util.List;
import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.AccountFindReqDto;
import org.dailystudio.sbs.dto.AccountLoginDto;
import org.dailystudio.sbs.dto.AccountSaveReqDto;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public boolean SignUp(@RequestBody AccountSaveReqDto accountSaveReqDto){

        String email = accountSaveReqDto.getEmail();
        if(accountRepository.findByEmail(email)!=null)
            return false;

        Account account = accountSaveReqDto.toEntity(bCryptPasswordEncoder);

        accountRepository.save(account);
        return true;
    }

    public String LogIn(@RequestBody AccountLoginDto accountLoginDto){
        Account account = accountRepository.findByEmail(accountLoginDto.getEmail());

        if (bCryptPasswordEncoder.matches( accountLoginDto.getPassword(), account.getPassword()))
            return "Success";
        return "fail";
    }

    public String find(@RequestBody AccountFindReqDto accountFindReqDto){
        Account account = accountRepository.findByEmail((accountFindReqDto.getEmail()));

        if (account == null)
            return "None";

        return "Email> " + account.getEmail() + "\nName> " + account.getName();

    }

    public String findAll(){
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        for(Account account: accounts){
            
        }

    }
}
