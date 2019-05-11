package org.dailystudio.sbs.service;


import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.AccountInfo;
import org.dailystudio.sbs.dto.ChangeNameRequestDTO;
import org.dailystudio.sbs.dto.SignupRequestDTO;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean signUp(SignupRequestDTO signupRequestDTO) {

        String email = signupRequestDTO.getEmail();

        if (accountRepository.findByEmail(email) == null) {
            Account account = signupRequestDTO.toAccountEntity(bCryptPasswordEncoder);
            accountRepository.save(account);

            return true;
        }

        return false;
    }

    @Nullable
    public AccountInfo findAccountByEmail(String email) {

        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return new AccountInfo(null, null);
        }
        return account.toAccountInfo();
    }

    //어떻게 더 잘 짤수잇을까...
    public List<AccountInfo> findAllAccount() {
        List<AccountInfo> allAccountInfos = new ArrayList();
        List<Account> allAccounts = accountRepository.findAll();

        for (Account account : allAccounts) {
            allAccountInfos.add(account.toAccountInfo());
        }
        return allAccountInfos;
    }

    public Boolean changeUserName(ChangeNameRequestDTO changeNameRequestDTO) {

        String email = changeNameRequestDTO.getEmail();
        String name = changeNameRequestDTO.getName();

        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return false;
        }
        account.changeUserName(name);
        accountRepository.save(account);
        return true;
    }
}

