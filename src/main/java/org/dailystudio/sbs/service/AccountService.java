package org.dailystudio.sbs.service;


import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.LoginRequestDTO;
import org.dailystudio.sbs.dto.SignupRequestDTO;
import org.dailystudio.sbs.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public HashMap<String, String> signup(SignupRequestDTO signupRequestDTO) {

        HashMap<String, String> result = new HashMap<>();

        String email = signupRequestDTO.getEmail();
        String name = signupRequestDTO.getName();
        String password = signupRequestDTO.getPassword();

        if (accountRepository.findByEmail(email) == null) {

            Account account = new Account(email, password, name);
            accountRepository.save(account);

            result.put("message", "회원가입 성공");
        } else {
            result.put("message", "중복된 이메일 존재");
        }

        return result;
    }

    public HashMap<String, String> login(LoginRequestDTO loginRequestDTO) {

        HashMap<String, String> result = new HashMap<>();

        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();

        Account userAccount = accountRepository.findByEmail(email);

        if (userAccount == null) {
            result.put("message", "로그인 실패 아이디 없음");
        } else {
            if (userAccount.getPassword().equals(password)) {
                result.put("message", "로그인 성공");
            } else {
                result.put("message", "로그인 실패 비밀번호 틀림");
            }
        }
        return result;
    }

}
