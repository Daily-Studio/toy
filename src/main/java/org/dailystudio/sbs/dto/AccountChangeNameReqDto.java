package org.dailystudio.sbs.dto;

import lombok.Getter;

@Getter
public class AccountChangeNameReqDto {

    private String email;
    private String name;
    private String pass;
}

