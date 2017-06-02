package com.fast87.amugona.security;

import com.fast87.amugona.accounts.Account;
import com.fast87.amugona.accounts.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by jojonari on 2017. 6. 2..
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null)
            throw new UsernameNotFoundException(username);
        return new UserDetailsImpl(account);
    }
}
