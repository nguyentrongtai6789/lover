package com.example.loverbackend.controller;

import com.example.loverbackend.dto.AccountDTO;
import com.example.loverbackend.model.Account;
import com.example.loverbackend.model.Role;
import com.example.loverbackend.model.RoleName;
import com.example.loverbackend.model.StatusAccount;
import com.example.loverbackend.service.AccountService;
import com.example.loverbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Account account1 = accountService.findByUsername(account.getUsername());
        if (account1.equals(null)) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        } else if (account1.getUsername().equals(account.getUsername()) && account1.getPassword().equals(account.getPassword())) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

    @PostMapping("/createNewAccount")
    public ResponseEntity<?> createNewAccount(@RequestBody Account account) {
        accountService.save(account);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AccountDTO>> findAll() {
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

//    @PostMapping("/saveRole")
//    public ResponseEntity<?> saveRole() {
//        Role role = new Role();
//        role.setRoleName(RoleName.USER);
//        roleService.save(role);
//        Role role1 = new Role();
//        role1.setRoleName(RoleName.ADMIN);
//        roleService.save(role1);
//        Role role2 = new Role();
//        role2.setRoleName(RoleName.LOVER);
//        roleService.save(role2);
//        return new ResponseEntity<>(true, HttpStatus.OK);
//    }
}
