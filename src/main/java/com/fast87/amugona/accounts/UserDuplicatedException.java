package com.fast87.amugona.accounts;

import lombok.Getter;

/**
 * Created by jojonari on 2017. 5. 28..
 */
@Getter
public class UserDuplicatedException extends RuntimeException {

    private String username;


    public UserDuplicatedException(String username) {
        this.username = username;
    }
}
