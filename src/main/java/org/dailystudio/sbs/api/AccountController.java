package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.account.*;
import org.dailystudio.sbs.dto.common.APIResponseDto;
import org.dailystudio.sbs.dto.common.ResponseDto;
import org.dailystudio.sbs.enums.ErrorCode;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/save/user")
    public ResponseEntity<APIResponseDto> signUp(@RequestBody AccountSaveReqDto accountSaveReqDto){
        accountService.signUp(accountSaveReqDto);
        return ResponseEntity.ok().body(APIResponseDto.ofSuccess());
    }

    @PostMapping("/signin")
    public ResponseEntity<APIResponseDto> signIn(@RequestBody AccountLoginReqDto accountLoginReqDto){
        accountService.signIn(accountLoginReqDto);
        return ResponseEntity.ok().body(APIResponseDto.ofSuccess());
    }


    @GetMapping("/find/user")
    public ResponseEntity<APIResponseDto> findUser(@RequestParam("email") String email){
        AccountFindReqDto accountFindReqDto = new AccountFindReqDto(email);
        APIResponseDto apiResponseDto = APIResponseDto.ofSuccess(accountService.findUserByEmail(accountFindReqDto));
        return ResponseEntity.ok().body(apiResponseDto);

    }

    @GetMapping("/find/users")
    public ResponseEntity<APIResponseDto> findUserAll(){
        APIResponseDto apiResponseDto = APIResponseDto.ofSuccess(accountService.findUserAll());
        return ResponseEntity.ok().body(apiResponseDto);
    }

    @PutMapping("/update/user/name")
    public ResponseEntity<APIResponseDto> updateUserName(@RequestBody AccountUpdateNameDto accountUpdateNameDto){
        accountService.UpdateUserName(accountUpdateNameDto);
        return ResponseEntity.ok().body(APIResponseDto.ofSuccess());
    }

}
