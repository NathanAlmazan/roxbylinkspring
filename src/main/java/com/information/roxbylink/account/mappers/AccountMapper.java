package com.information.roxbylink.account.mappers;

import com.information.roxbylink.account.dto.AccountDto;
import com.information.roxbylink.account.models.Account;

import java.util.List;

public interface AccountMapper {
    AccountDto accountToDto(Account account);
    List<AccountDto> accountListToDto(List<Account> accounts);
}
