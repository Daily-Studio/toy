package org.dailystudio.sbs.service;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.Account.*;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountRepository accountRepository;

    @Transactional
    public boolean signUp(final AccountSaveReqDto accountSaveReqDto){

        if (accountRepository.findByEmail(accountSaveReqDto.getEmail()) != null)
            return false;

        Account account = accountSaveReqDto.toEntity(bCryptPasswordEncoder);
        accountRepository.save(account);
        return true;
    }

    @Transactional
    public String signIn(final AccountLoginReqDto accountLoginReqDto){
        Account account = accountRepository.findByEmail(accountLoginReqDto.getEmail());
        if (account == null) return "email does not match";
        if (bCryptPasswordEncoder.matches(accountLoginReqDto.getPassword(), account.getPassword())) {
            return "Login success";
        }
        return "password does not match";
    }

    @Transactional
    public AccountFindResDto findUserByEmail(final AccountFindReqDto accountFindReqDto){
        Account account = accountRepository.findByEmail(accountFindReqDto.getEmail());
        if (account == null)
            return null;
        AccountFindResDto accountFindResDto = new  AccountFindResDto(account);
        return accountFindResDto;

    }

    @Transactional
    public List<AccountFindResDto> findUserAll(){
        List<Account> accounts = accountRepository.findAll();
        if (accounts == null)
            return null;
        return accounts.stream()
                .map(account -> new AccountFindResDto(account))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean UpdateUserName(AccountUpdateNameDto accountUpdateNameDto){

        Account account = accountRepository.findByEmail(accountUpdateNameDto.getEmail());
        if (account == null)
            return false;

        account.SetName(accountUpdateNameDto);
        accountRepository.save(account);
        return true;

    }


}
