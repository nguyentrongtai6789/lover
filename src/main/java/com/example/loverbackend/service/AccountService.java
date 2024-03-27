package com.example.loverbackend.service;

import com.example.loverbackend.dto.AccountDTO;
import com.example.loverbackend.mapper.AccountMapper;
import com.example.loverbackend.model.Account;
import com.example.loverbackend.repository.AccountRepository;
import com.example.loverbackend.security.AccountPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class AccountService extends BaseService<AccountRepository, AccountDTO, Account> implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    public static String randomCodeSendToEmail;
    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public AccountDTO getDetails(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (!accountOptional.equals(null)) {
            return accountMapper.toDto(accountOptional.get());
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if(!accountOptional.equals(null)){
            accountOptional.get().setIsActive(2);
            return true;
        }
        return false;
    }

    @Override
    public List<AccountDTO> findAll() {
        return accountMapper.toDto(accountRepository.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Account> accounts =accountRepository.findAll();
        for(Account account: accounts){
            if(Objects.equals(account.getUsername(), username)){
                return AccountPrinciple.build(account);
            }
        }
        return null;
    }
    public  AccountDTO findByUsername(String username){
        Optional<Account> userOptional = accountRepository.findByUsername(username);
        return accountMapper.toDto(userOptional.get());
    }
}
