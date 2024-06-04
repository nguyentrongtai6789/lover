package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.dto.AccountDTO;
import com.example.bookstorebackend.model.Account;
import com.example.bookstorebackend.security.jwt.JwtResponse;
import com.example.bookstorebackend.security.jwt.JwtService;
import com.example.bookstorebackend.service.extend.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Account account) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            AccountDTO accountDTO = accountService.findByUsername(account.getUsername());
            ResponseEntity responseEntity = new ResponseEntity<>(new JwtResponse(jwt, accountDTO.getId(),
                    accountDTO.getUsername(), accountDTO.getUsername(), userDetails.getAuthorities()), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping("/create-new-account")
    public ResponseEntity<?> createNewAccount(@RequestBody Account account) {
        accountService.save(account);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/find-all-account")
    public ResponseEntity<List<AccountDTO>> findAll() {
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }
}
