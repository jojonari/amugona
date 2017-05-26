package me.whiteship.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jojonari on 2017. 5. 26..
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
