package org.dailystudio.sbs.dto;

import lombok.Getter;
import org.dailystudio.sbs.domain.Account;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class AccountSaveReqDto {
    private String email;
    private String password;
    private String name;

    public Account toEntity(PasswordEncoder passwordEncoder){
        return Account.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .name(this.name)
                .build();
    }
}
