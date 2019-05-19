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
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean signUp(SignupRequestDTO signupRequestDTO) {

        String email = signupRequestDTO.getEmail();

        if (accountRepository.findByEmail(email).isPresent()) {
            return false;
        }

        Account account = signupRequestDTO.toAccountEntity(bCryptPasswordEncoder);
        accountRepository.save(account);

        return true;
    }

    @Nullable
    public AccountInfo findAccountByEmail(String email) {

        Account account = accountRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        //이러면 이걸 호출했을 때 이메일이 없으면 아무런 반환이 안되겟네 500메세지가 가겠구먼? 확인해보자
        return account.toAccountInfo();
    }

    public List<AccountInfo> findAllAccount() {
        List<Account> allAccounts = accountRepository.findAll();

        List<AccountInfo> allAccountInfos =
                allAccounts.stream()
                        .map(Account::toAccountInfo)
                        .collect(Collectors.toList());

        return allAccountInfos;
    }

    @Transactional
    public Boolean changeUserName(ChangeNameRequestDTO changeNameRequestDTO) {

        String email = changeNameRequestDTO.getEmail();
        String name = changeNameRequestDTO.getName();

        //이러면 이걸 호출했을 때 이메일이 없으면 아무런 반환이 안되겟네 500메세지가 가겠구먼? 확인해보자
        Account account = accountRepository.findByEmail(email).orElseThrow(RuntimeException::new);
//        if (account == null) {
//            return false;
//        }
        account.changeUserName(name);
        return true;
    }
}

