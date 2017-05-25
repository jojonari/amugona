package me.whiteship.accounts;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jojonari on 2017. 5. 25..
 */
@Entity
@Getter
@Setter
public class Account {
    @Id @GeneratedValue
    private Long id;

    private String loginId;

    private String password;

}
