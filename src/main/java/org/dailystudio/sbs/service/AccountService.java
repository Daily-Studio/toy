package org.dailystudio.sbs.service;


import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.*;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
    public class AccountService {

    private final AccountRepository accountRepository;


    @Transactional
    public boolean signUp(AccountSignUpReqDto accountSignUpReqDto)
    {

        String email = accountSignUpReqDto.getEmail();
        String pass = accountSignUpReqDto.getPass();
        String name = accountSignUpReqDto.getName();

        //
        if(accountRepository.findByEmail(email) != null)
        {
            return false;
        }
        Account account = new Account(email,pass,name);
        accountRepository.save(account);
                return true;

    }

    @Transactional
    public AccountResDto login(AccountLoginReqDto accountLoginReqDto) {

        String email = accountLoginReqDto.getEmail();
        String pass = accountLoginReqDto.getPass();

        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return null;
        }
        if (!account.getPass().equals(pass)) {
            return null;
        }

        AccountResDto accountResDto = new AccountResDto(account.getEmail(), account.getName());

        return accountResDto;
    }

    @Transactional
    public AccountResDto findAccountByUsingEmail(AccountFindAccountByUsingEmailReqDto accountFindAccountByUsingEmailReqDto) {

        String email = accountFindAccountByUsingEmailReqDto.getEmail();

        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return null;
        }

        AccountResDto accountResDto = new AccountResDto(account.getEmail(), account.getName());

        return accountResDto;
    }

    @Transactional
    public AccountChangeNameResDto changeName(AccountChangeNameReqDto accountChangeNameReqDto) {


        String name = accountChangeNameReqDto.getName();
        String email = accountChangeNameReqDto.getEmail();
        String pass =  accountChangeNameReqDto.getPass();
        String input_name = accountChangeNameReqDto.getInput_name();

        Account account = accountRepository.findByEmail(email);

        if(account == null)
        {
            return null;
        }
        if (!account.getEmail().equals(email)) {
            return null;
        }
        if (!account.getPass().equals(pass)) {
            return null;
        }
        if (!account.getName().equals(name)) {
            return null;
        }
        AccountChangeNameResDto accountChangeNameResDto = new AccountChangeNameResDto(input_name);

        return accountChangeNameResDto;

    }

    /*@Transactional
    public AccountResDto findAllUser(AccountLoginReqDto accountLoginReqDto) {
    }

*/
}
