package org.dailystudio.sbs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReq {
    private String email;
    private String name;

    public UserReq(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
