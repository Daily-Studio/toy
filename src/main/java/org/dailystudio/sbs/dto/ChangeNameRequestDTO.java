package org.dailystudio.sbs.dto;

import lombok.Getter;

@Getter
public class ChangeNameRequestDTO {
    private String email;
    private String name;

    public ChangeNameRequestDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
