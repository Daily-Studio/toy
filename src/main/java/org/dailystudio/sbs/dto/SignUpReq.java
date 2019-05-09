package org.dailystudio.sbs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@NoArgsConstructor
public class SignUpReq {
    private Long id;
    private String email;
    private String password;
    private String name;

    public Account toEntit(PasswordEncoder passwordEncoder) {
        return Account.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .build();
    }
}
