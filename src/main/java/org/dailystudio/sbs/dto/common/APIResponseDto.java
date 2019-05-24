package org.dailystudio.sbs.dto.common;

import lombok.Getter;
import org.dailystudio.sbs.enums.ErrorCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class APIResponseDto {
    private String code;
    private String message;
    private List result;


    private APIResponseDto(final ErrorCode code){
        this(code,new ArrayList<>());
    }
    private APIResponseDto(final ErrorCode code, final ResponseDto body){
        this.code = code.getCode();
        this.message = code.getMessage();
        this.result = new ArrayList<>(Arrays.asList(body));
    }
    private APIResponseDto(final ErrorCode code, final List body){
        this.code = code.getCode();
        this.message = code.getMessage();
        this.result = body;
    }

    public  static APIResponseDto of(final ErrorCode code){
        return new APIResponseDto(code);
    }

    public  static APIResponseDto of(final ErrorCode code,final List<ResponseDto> result){
        return new APIResponseDto(code,result);
    }
    public  static APIResponseDto ofSuccess(){
        return new APIResponseDto(ErrorCode.SUCCESS);
    }
    public  static APIResponseDto ofSuccess(ResponseDto body){
        return new APIResponseDto(ErrorCode.SUCCESS,body);
    }

    public  static APIResponseDto ofSuccess(List body){ return new APIResponseDto(ErrorCode.SUCCESS,body);
    }
}
