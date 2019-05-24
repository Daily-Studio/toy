package org.dailystudio.sbs.dto.account;

import lombok.Getter;
import org.dailystudio.sbs.domain.Account;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class AccountSaveReqDto {

    private String email;
    private String password;
    private String name;

    public AccountSaveReqDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Account toEntity(PasswordEncoder passwordEncoder) {
        return Account.builder()
                .email(this.email)
                .name(this.name)
                .password(passwordEncoder.encode(this.password))
                .build();
    }
}
