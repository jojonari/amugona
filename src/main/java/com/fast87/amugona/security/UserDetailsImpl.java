package com.fast87.amugona.security;

import com.fast87.amugona.accounts.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jojonari on 2017. 6. 2..
 */
public class UserDetailsImpl extends User{

    public UserDetailsImpl(Account account) {
        super(account.getUsername(), account.getPassword(), authorities(account));
    }

    private static Collection<? extends GrantedAuthority> authorities(Account account) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (account.isAdmin())
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return authorities;
    }
}
