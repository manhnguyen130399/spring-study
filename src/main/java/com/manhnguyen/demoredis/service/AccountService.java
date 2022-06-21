package com.manhnguyen.demoredis.service;

import com.manhnguyen.demoredis.domain.Account;
import com.manhnguyen.demoredis.producer.AccountProducer;
import com.manhnguyen.demoredis.repository.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final IAccountRepository accountRepository;

    private final AccountProducer accountProducer;

    @Transactional
    @Cacheable(value = "users")
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true)
    })
    public Account createAccount(final Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    @Caching(evict = @CacheEvict(value = "users", allEntries = true)
            , put = @CachePut(value = "user", key = "#id"))
    public Account updateAccount(final Long id, final Account account) throws InvalidPropertiesFormatException {
        final Account account1 = getAccountById(id);
        account1.setCreatedAt(account.getCreatedAt());
        account1.setIsActive(account.getIsActive());
        account1.setProvider(account.getProvider());
        return account1;
    }

    @Transactional
//    @Cacheable(value = "user", key = "#id", unless = "#result.id<8")
    public Account getAccountById(final Long id) throws InvalidPropertiesFormatException {
        final Optional<Account> old = accountRepository.findById(id);
        if (old.isEmpty()) {
            throw new InvalidPropertiesFormatException("Not found");
        }
        return accountProducer.sendAccountToQueue(old.get());
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "user", key = "#id", allEntries = false),
            @CacheEvict(value = "users", allEntries = true)})
    public void deleteAccount(final Long id) throws InvalidPropertiesFormatException {
        final Account account = getAccountById(id);
        accountRepository.delete(account);
    }
}
