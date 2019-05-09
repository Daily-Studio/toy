package org.dailystudio.sbs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountReq {
    private String email;
    private String password;
    private String name;
}
