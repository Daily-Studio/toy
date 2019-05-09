package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
}
