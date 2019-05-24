package org.dailystudio.sbs.dto.account;

import lombok.Getter;
import org.dailystudio.sbs.dto.common.ResponseDto;

@Getter
public class AccountSaveResDto implements ResponseDto {

    private String email;
    private String name;
}
