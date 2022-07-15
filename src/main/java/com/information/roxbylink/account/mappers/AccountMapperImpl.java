package com.information.roxbylink.account.mappers;

import com.information.roxbylink.account.dto.AccountDto;
import com.information.roxbylink.account.models.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountDto accountToDto(Account account) {
        return new AccountDto((account));
    }

    @Override
    public List<AccountDto> accountListToDto(List<Account> accounts) {
        List<AccountDto> accountList = new ArrayList<>(accounts.size());

        accounts.forEach(account -> accountList.add(accountToDto(account)));
        return accountList;
    }
}
