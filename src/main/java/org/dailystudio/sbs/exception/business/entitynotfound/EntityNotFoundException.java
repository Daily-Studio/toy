package org.dailystudio.sbs.exception.business.entitynotfound;

import org.dailystudio.sbs.enums.ErrorCode;
import org.dailystudio.sbs.exception.business.BusinessException;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message){
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(ErrorCode errorCode){
        super(errorCode);
    }
    public EntityNotFoundException(){
        super(ErrorCode.ENTITY_NOT_FOUND);
    }
}
