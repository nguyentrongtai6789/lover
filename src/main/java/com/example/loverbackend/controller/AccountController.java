package com.example.loverbackend.controller;

import com.example.loverbackend.dto.AccountDTO;
import com.example.loverbackend.model.Account;
import com.example.loverbackend.model.Role;
import com.example.loverbackend.model.RoleName;
import com.example.loverbackend.model.StatusAccount;
import com.example.loverbackend.security.jwt.JwtResponse;
import com.example.loverbackend.security.jwt.JwtService;
import com.example.loverbackend.service.AccountService;
import com.example.loverbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping("/api")
public class AccountController {

    AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AccountDTO accountDTO = accountService.findByUsername(account.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, accountDTO.getId(),
                accountDTO.getUsername(), accountDTO.getUsername(), userDetails.getAuthorities()));
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
