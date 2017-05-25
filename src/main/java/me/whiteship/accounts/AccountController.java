package me.whiteship.accounts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jojonari on 2017. 5. 25..
 */
@RestController
public class AccountController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello Spring Boot";
    }
}
