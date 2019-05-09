package org.dailystudio.sbs.service;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.AccountFindReqDto;
import org.dailystudio.sbs.dto.AccountFindResDto;
import org.dailystudio.sbs.dto.AccountLoginReqDto;
import org.dailystudio.sbs.dto.AccountReqDto;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private  final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public boolean SignUp(final AccountReqDto accountReqDto){

        if (accountRepository.findByEmail(accountReqDto.getEmail()) != null)
            return false;

        Account account = accountReqDto.toEntity(bCryptPasswordEncoder);
        accountRepository.save(account);
        return true;
    }

    @Transactional
    public String SignIn(final AccountLoginReqDto accountLoginReqDto){
        Account account = accountRepository.findByEmail(accountLoginReqDto.getEmail());
        if (bCryptPasswordEncoder.matches(accountLoginReqDto.getPassword(), account.getPassword())) {
            return "success";
        }
        return "fail";
    }

    @Transactional
    public AccountFindResDto FindUserByEmail(final AccountFindReqDto accountFindReqDto){
        Account account = accountRepository.findByEmail(accountFindReqDto.getEmail());
        AccountFindResDto accountFindResDto = new  AccountFindResDto(account);
        return accountFindResDto;

    }

    @Transactional
    public List<AccountFindResDto> FindUserAll(){
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream()
                .map(account -> new AccountFindResDto(account))
                .collect(Collectors.toList());

    }

    public boolean UpdateUser(){



    }


}
