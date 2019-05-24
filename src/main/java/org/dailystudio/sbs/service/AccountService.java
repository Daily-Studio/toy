package org.dailystudio.sbs.service;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.domain.Movie;
import org.dailystudio.sbs.domain.MovieRate;
import org.dailystudio.sbs.dto.account.*;
import org.dailystudio.sbs.dto.movie.MovieRateSaveReqDto;
import org.dailystudio.sbs.enums.ErrorCode;
import org.dailystudio.sbs.exception.business.entitynotfound.EntityNotFoundException;
import org.dailystudio.sbs.exception.business.invalidvalue.InvalidValueException;
import org.dailystudio.sbs.repository.AccountRepository;
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

        if (accountRepository.findByEmail(accountSaveReqDto.getEmail()).isPresent())
        throw new InvalidValueException(ErrorCode.EMAIL_DUPLICATION);

        Account account = accountSaveReqDto.toEntity(bCryptPasswordEncoder);
        accountRepository.save(account);
        return true;
    }

    @Transactional
    public boolean signIn(final AccountLoginReqDto accountLoginReqDto){
        Account account = accountRepository.findByEmail(accountLoginReqDto.getEmail()).orElseThrow(()->new InvalidValueException(ErrorCode.EMAIL_OR_PASS_NOT_MATCHED));

        if (!bCryptPasswordEncoder.matches(accountLoginReqDto.getPassword(), account.getPassword()) )
            throw new InvalidValueException(ErrorCode.EMAIL_OR_PASS_NOT_MATCHED);
        return true;
    }

    @Transactional
    public AccountFindResDto findUserByEmail(final AccountFindReqDto accountFindReqDto){
        Account account = accountRepository.findByEmail(accountFindReqDto.getEmail()).orElseThrow( () -> new EntityNotFoundException(ErrorCode.EMAIL_NOT_FOUND));
        AccountFindResDto accountFindResDto = new  AccountFindResDto(account);
        return accountFindResDto;
    }

    @Transactional
    public List<AccountFindResDto> findUserAll(){
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) throw new EntityNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND); //어떻게 위아래랑 합치는 방법이 없을까?
        return accounts.stream()
                .map(account -> new AccountFindResDto(account))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean UpdateUserName(AccountUpdateNameDto accountUpdateNameDto){
        Account account = accountRepository.findByEmail(accountUpdateNameDto.getEmail()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.EMAIL_NOT_FOUND));
        account.SetName(accountUpdateNameDto);
        return true;
    }



}
