package org.dailystudio.sbs.dto;

import lombok.Getter;

@Getter
public class AccountInfo {
    private String email;
    private String name;

    public AccountInfo(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
