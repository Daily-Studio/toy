package org.dailystudio.sbs.dto;

import org.dailystudio.sbs.domain.Account;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignupRequestDTO {

    private String email;
    private String password;
    private String name;

    public Account toAccountEntity(PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(password);
        return new Account(email, encodedPassword, name);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
