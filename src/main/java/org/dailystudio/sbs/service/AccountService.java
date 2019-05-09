package org.dailystudio.sbs.service;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.account.AccountLoginReqDto;
import org.dailystudio.sbs.dto.account.AccountNameChangeReqDto;
import org.dailystudio.sbs.dto.account.AccountResDto;
import org.dailystudio.sbs.dto.account.AccountSaveReqDto;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public boolean saveAccount(AccountSaveReqDto accountSaveReqDto) {
        if (accountRepository.findByEmail(accountSaveReqDto.getEmail()).isPresent()) {
            return false;
        }

        Account account = accountSaveReqDto.toEntity(bCryptPasswordEncoder);
        accountRepository.save(account);
        return true;
    }

    public boolean login(AccountLoginReqDto loginReqDto) {
        Account account = accountRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(NoSuchElementException::new);
        if (bCryptPasswordEncoder.matches(loginReqDto.getPassword(), account.getPassword())) {
            return true;
        }
        return false;
    }

    public AccountResDto findAccountByEmail(final String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);

        return new AccountResDto(account);
    }

    public List<AccountResDto> findAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
            return new ArrayList<>();
        }

        return accounts.stream()
                .map(account -> new AccountResDto(account))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean changeAccountName(AccountNameChangeReqDto changeReqDto) {
        Account account = accountRepository.findByEmail(changeReqDto.getEmail())
                .orElseThrow(NoSuchElementException::new);
        if (!bCryptPasswordEncoder.matches(changeReqDto.getPassword(), account.getPassword())) {
            return false;
        }
        account.changeName(changeReqDto);
        return true;
    }

}


