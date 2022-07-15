package com.information.roxbylink.account.services;

import com.information.roxbylink.account.dto.AccountDto;
import com.information.roxbylink.account.mappers.AccountMapperImpl;
import com.information.roxbylink.account.models.Account;
import com.information.roxbylink.account.repositories.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AccountServices implements UserDetailsService {
    @Autowired private AccountRepo accountRepo;
    @Autowired private AccountMapperImpl accountMapper;
    @Autowired private PasswordEncoder passwordEncoder;

    public AccountDto registerUser(AccountDto account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepo.insertAccount(account.getUsername(), account.getPassword());
        account.setPassword(null);

        return account;
    }

    public AccountDto getAccountByUsername(String username) {
       return accountMapper.accountToDto(
               accountRepo.getAccountByUsername(username)
       );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto account = getAccountByUsername(username);

        if (account == null) throw new UsernameNotFoundException("There is no account connected to " + username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (String role : account.getRoles()) authorities.add(new SimpleGrantedAuthority(role));

        return new User(account.getUsername(), account.getPassword(), authorities);
    }
}
