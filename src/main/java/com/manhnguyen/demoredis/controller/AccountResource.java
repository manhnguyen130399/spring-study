package com.manhnguyen.demoredis.controller;

import com.manhnguyen.demoredis.domain.Account;
import com.manhnguyen.demoredis.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

@RestController
@RequestMapping(path = { "/api" })
@RequiredArgsConstructor
public class AccountResource {

    private final AccountService accountService;

    @GetMapping(value = "/accounts")
    public List<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/accounts/{id}")
    public Account getById(final @PathVariable Long id) throws InvalidPropertiesFormatException {
        return accountService.getAccountById(id);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(final @PathVariable Long id) throws InvalidPropertiesFormatException {
        accountService.deleteAccount(id);
    }

    @PutMapping("/accounts/{id}")
    public Account update(@PathVariable Long id,@RequestBody Account account) throws InvalidPropertiesFormatException {
        return accountService.updateAccount(id, account);
    }

    @PostMapping("/accounts")
    public Account create(@RequestBody final Account account) {
        return accountService.createAccount(account);
    }

}
