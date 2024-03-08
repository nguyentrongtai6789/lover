package com.example.loverbackend.service;

import com.example.loverbackend.dto.AccountDTO;
import com.example.loverbackend.mapper.AccountMapper;
import com.example.loverbackend.model.Account;
import com.example.loverbackend.model.RoleName;
import com.example.loverbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RoleService roleService;

    public Account findByUsername(String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        return accountOptional.orElse(null);
    }

    public void save(Account account) {
        account.setCreatedAt(LocalDateTime.now());
        account.setRole(roleService.findByRoleName(RoleName.USER));
        accountRepository.save(account);
    }

    public List<AccountDTO> findAll() {
        return accountMapper.toDto(accountRepository.findAll());
    }
}
