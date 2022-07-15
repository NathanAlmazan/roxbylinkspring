package com.information.roxbylink.account.dto;

import com.information.roxbylink.account.models.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccountDto  {

    @NotNull @NotBlank
    @Length(max = 30)
    private String username;

    @NotNull @NotBlank
    @Length(max = 30)
    private String password;

    private List<String> roles;

    public AccountDto() {
        roles = new ArrayList<>();
        roles.add("ROLE_USER");
    }

    public AccountDto(Account account) {
        username = account.getUsername();
        password = account.getPassword();
        roles = new ArrayList<>();
        roles.add("ROLE_USER");
    }
}
