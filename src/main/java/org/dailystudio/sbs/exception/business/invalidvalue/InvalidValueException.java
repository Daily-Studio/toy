package org.dailystudio.sbs.exception.business.invalidvalue;

import org.dailystudio.sbs.enums.ErrorCode;
import org.dailystudio.sbs.exception.business.BusinessException;

public class InvalidValueException extends BusinessException {
    public InvalidValueException(ErrorCode errorCode){
        super(errorCode);
    }
}
