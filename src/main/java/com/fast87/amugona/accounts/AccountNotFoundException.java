package com.fast87.amugona.accounts;

/**
 * Created by jojonari on 2017. 5. 31..
 */
public class AccountNotFoundException extends RuntimeException {

    Long id;

    public AccountNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
