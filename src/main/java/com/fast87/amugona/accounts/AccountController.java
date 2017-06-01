package com.fast87.amugona.accounts;

import com.fast87.amugona.commons.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by jojonari on 2017. 5. 25..
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService service;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/accounts", method = POST)
    public ResponseEntity createAccount(@RequestBody @Valid  AccountDto.Create account, BindingResult result){
        if (result.hasErrors()){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("잘못된 요청입니다");
            errorResponse.setCode("bad.request");
            return new ResponseEntity(errorResponse, BAD_REQUEST);
        }

        Account newAccount = service.createAccount(account);


        return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), CREATED);
    }


    @RequestMapping(value = "/accounts", method = GET)
    @ResponseStatus(HttpStatus.OK)
    public PageImpl<AccountDto.Response> getAccounts(Pageable pageable){
        Page<Account> page = repository.findAll(pageable);
        List<AccountDto.Response> content = page.getContent().parallelStream()
                .map(account -> modelMapper.map(account, AccountDto.Response.class))
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @RequestMapping(value = "/accounts/{id}", method = GET)
    @ResponseStatus(HttpStatus.OK)
    public AccountDto.Response getAccount(@PathVariable Long id){
        Account account = service.getAccount(id);
        return modelMapper.map(account, AccountDto.Response.class);
    }

    // 전체업데이트(PUT) VS 부분업데이트(PATCH)
    @RequestMapping(value = "/accounts/{id}", method = PUT)
    public ResponseEntity updateAccount(@PathVariable Long id, @RequestBody @Valid AccountDto.Update updateDto,
                                        BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity(BAD_REQUEST);
        }

        Account updateAccount = service.updateAccount(id, updateDto);
        return new ResponseEntity<>(modelMapper.map(updateAccount, AccountDto.Response.class), HttpStatus.OK);
    }


    @RequestMapping(value = "/accounts/{id}", method = DELETE)
    public ResponseEntity deleteAccount(@PathVariable Long id){
        service.deleteAccount(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(UserDuplicatedException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse userDuplicatedException(UserDuplicatedException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getUsername() + "은 중복된 username입니다");
        errorResponse.setCode("duplicated.username.exception");
        return errorResponse;
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleAccountNOtFoundException(AccountNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getId()+"에 해당하는 계정이 없습니다 ");
        errorResponse.setCode("account.not.found.exception");
        return errorResponse;
    }

}
