package com.example.loverbackend.service.extend;

import com.example.loverbackend.dto.AccountDTO;
import com.example.loverbackend.mapper.AccountMapper;
import com.example.loverbackend.model.Account;
import com.example.loverbackend.repository.AccountRepository;
import com.example.loverbackend.security.AccountPrinciple;
import com.example.loverbackend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;


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
        if (!accountOptional.equals(null)) {
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
    public void sendEmail(String email) {
        Random randomCode = new Random();
        int randomNum = randomCode.nextInt(900000) + 100000;
        String to = email;
        String subject = "ĐĂNG KÍ TÀI KHOẢN ỨNG DỤNG DUAL";
        String text = String.valueOf(randomNum);
        randomCodeSendToEmail = text;
        String content = "Xin Chào ...!\n" +
                "Bạn hoặc ai đó đã dùng email này để đăng ký tài khoản ở Couple Fake\n" +
                "\n" +
                "MÃ XÁC NHẬN CỦA BẠN LÀ  : " + text + "\n" +
                "\n" +
                "--------------------------------------\n" +
                " + Phone  : (+084)088.888.888\n" +
                " + Email  : doanlinh1510@gmail.com\n" +
                " + Address: Nền văn minh vũ trụ cấp 7 - vành đai Andromeda\n";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }
    public boolean checkEmailExisted(String email) {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            if (account.getEmail().equals(email)) {
                return true; // nếu email đã tổn tại trả về true
            }
        }
        return false;
    }

    public boolean checkUsernameExisted(String username) {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNicknameExisted(String nickname) {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            if (account.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
    public Account findById(Long id) {
        return accountRepository.findById(id).get();
    }
}
