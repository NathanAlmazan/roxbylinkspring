package com.information.roxbylink.account;

import com.information.roxbylink.account.dto.AccountDto;
import com.information.roxbylink.account.services.AccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountServices accountServices;

    @PostMapping("/register")
    public ResponseEntity<AccountDto> registerUser(@RequestBody @Valid AccountDto credentials) {
        return new ResponseEntity<>(accountServices.registerUser(credentials), HttpStatus.CREATED);
    }
}
