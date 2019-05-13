package org.dailystudio.sbs.service;


import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.*;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
    public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


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
        Account account = accountSignUpReqDto.toAccountEntity(bCryptPasswordEncoder);
        accountRepository.save(account);
        return true;

    }

    @Transactional
    public AccountResDto login(AccountLoginReqDto accountLoginReqDto) {

        String email = accountLoginReqDto.getEmail();

        Account account = accountRepository.findByEmail(email);

        //그런 이메일을 가진 객체가 없다면 null반환
        if (account == null) {
            return null;
        }
        //비밀번호를 복호화한 비밀번호와 같다면 그 객체를 반환
        if(bCryptPasswordEncoder.matches(accountLoginReqDto.getPass(), account.getPass()))
        {
            AccountResDto accountResDto = new AccountResDto(account.getEmail(), account.getName());
            return accountResDto;
        }

        //비번이랑 같지않아요~
        return null;
    }


     @Transactional
    public AccountResDto findAccountByUsingEmail(String email) {

        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return null;
        }

        AccountResDto accountResDto = new AccountResDto(account.getEmail(), account.getName());

        return accountResDto;
    }

    @Transactional
    public AccountChangeNameResDto changeName(String modifiedUserName, AccountChangeNameReqDto accountChangeNameReqDto) {


         String name = accountChangeNameReqDto.getName();
        String email = accountChangeNameReqDto.getEmail();
        String pass =  accountChangeNameReqDto.getPass();
        String input_name = modifiedUserName;

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

       //삭제하고 다시넣어주나?
        accountRepository.deleteById(account.getId());
        //이쪽까지오면 다 같으니거니까!
        Account modified_account = new Account(email,pass,name);
        accountRepository.save(modified_account);

        //바뀐이름만 리턴
        return accountChangeNameResDto;

    }

    @Transactional
    public AccountFindAllUserDto findAllUser() {

        List<Account> accounts = accountRepository.findAll();
        List<AccountResDto> accountResDtoList = new ArrayList<>();

        //AccountFindAllUserDto의 매개변수로 들어갈, ResDtoList를 만들자
        for(Account account_index : accounts)
        {
            AccountResDto accountResDto = new AccountResDto(account_index.getEmail(), account_index.getName());
            accountResDtoList.add(accountResDto);
        }

        AccountFindAllUserDto accountFindAllUserDto = new AccountFindAllUserDto(accountResDtoList);


        return accountFindAllUserDto;
    }

}
