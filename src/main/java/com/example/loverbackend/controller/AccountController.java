package com.example.loverbackend.controller;

import com.example.loverbackend.dto.AccountDTO;
import com.example.loverbackend.dto.RoleDTO;
import com.example.loverbackend.mapper.RoleMapper;
import com.example.loverbackend.model.Account;
import com.example.loverbackend.model.Role;
import com.example.loverbackend.security.jwt.JwtResponse;
import com.example.loverbackend.security.jwt.JwtService;
import com.example.loverbackend.service.extend.AccountService;
import com.example.loverbackend.service.extend.ProfileUserService;
import com.example.loverbackend.service.extend.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ProfileUserService profileUserService;

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
    @GetMapping("/findAllAccounts")
    public ResponseEntity<List<AccountDTO>> getAllAccount() {
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/sendCodeToEmail/{email}")
    public ResponseEntity<?> registerNewAccount(@PathVariable String email) {
        if (accountService.checkEmailExisted(email)) {
            return new ResponseEntity<>("Email này đã được sử dụng!", HttpStatus.OK);
        }
        accountService.sendEmail(email);
        return new ResponseEntity<>("Mã xác nhận đã gửi đến email của bạn!", HttpStatus.OK);
    }

    @PostMapping("/sendCodeToEmail2/{email}")
    public ResponseEntity<?> sendCode2(@PathVariable String email) {
        accountService.sendEmail(email);
        return new ResponseEntity<>("Mã xác nhận đã gửi đến email của bạn!", HttpStatus.OK);
    }

    @PostMapping("/changePassword/{codeEmailVerification}")
    public ResponseEntity<?> changePassword(@RequestBody Account account, @PathVariable String codeEmailVerification) {
        if (codeEmailVerification.equals(AccountService.randomCodeSendToEmail)) {
            Account account1 = accountService.findByEmail(account.getEmail());
            account1.setPassword(passwordEncoder.encode(account.getPassword()));
            accountService.save(account1);
            return new ResponseEntity<>("Đổi mật khẩu thành công!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mã xác nhận không đúng!", HttpStatus.OK);
        }
    }

    @PostMapping("/createNewAccount/{codeEmailVerification}")
    public ResponseEntity<?> createNewAccount(@RequestBody Account account, @PathVariable String codeEmailVerification) {
        if (codeEmailVerification.equals(AccountService.randomCodeSendToEmail)) {
            if (accountService.checkNicknameExisted(account.getNickname())) {
                return new ResponseEntity<>("1", HttpStatus.OK);
            }
            if (accountService.checkUsernameExisted(account.getUsername())) {
                return new ResponseEntity<>("2", HttpStatus.OK);
            }
            if (accountService.checkEmailExisted(account.getEmail())) {
                return new ResponseEntity<>("3", HttpStatus.OK);
            }
            // set default role for new acc is ROLE_USER
            Set<Role> roleSet = new HashSet<>();
            List<RoleDTO> roleDTOs = roleService.findAll();
            List<Role> roles = roleMapper.toEntity(roleDTOs);
            for (Role role : roles) {
                if (role.getName().equals("ROLE_USER")) {
                    roleSet.add(role);
                    break;
                }
            }
            account.setRoles(roleSet);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            //create new account:
            accountService.save(account);
            account.setCreatedAt(LocalDateTime.now());
            // create new profile user:
            profileUserService.createProfileUserWhenCreateAccount(account);
            return new ResponseEntity<>("4", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("5", HttpStatus.OK);
        }
    }
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

