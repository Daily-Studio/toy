package org.dailystudio.sbs.exception;

import org.dailystudio.sbs.dto.common.APIResponseDto;
import org.dailystudio.sbs.enums.ErrorCode;
import org.dailystudio.sbs.exception.business.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIResponseDto> handleBusinessException(final BusinessException e){
        final ErrorCode errorCode = e.getErrorCode();
        final APIResponseDto apiResponseDto = APIResponseDto.of(errorCode);
        return ResponseEntity.ok().body(apiResponseDto);
    }

}
