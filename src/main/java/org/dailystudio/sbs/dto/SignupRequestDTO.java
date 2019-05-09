package org.dailystudio.sbs.dto;

import lombok.Getter;

@Getter
public class SignupRequestDTO {
    private String password;
    private String email;
    private String name;
}
