package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.dto.AccountDTO;
import com.example.bookstorebackend.model.Account;
import com.example.bookstorebackend.model.Translation;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try {
            accountService.save(account);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/find-all-account")
    public ResponseEntity<List<AccountDTO>> findAll() {
        try {
            return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    @GetMapping("/demo-translation/{lng}")
//    public Object getTranslations(@PathVariable String lng) {
//        if (lng.equals("vi")) {
//            Object language = new Object() {
//                public String username = "Tên đăng nhập";
//                public String password = "Mật khẩu";
//                public String selectSubsystem = "Chọn phân hệ";
////                public String logIn = "Đăng nhập";
//                public String KHAITHAC_SOHOA = "Hệ thống khai thác hồ sơ CMND 9 số số hóa";
//                public String XLHS_SOHOA = "Hệ thống quản lý hồ sơ CMND 9 số số hóa";
//
//
//            };
//            return language;
//        } else {
//            Object language = new Object() {
//                public String username = "Username";
//                public String password = "Password";
//                public String selectSubsystem = "SelectSubsystem";
////                public String logIn = "Log in";
//                public String KHAITHAC_SOHOA = "Digital 9-digit ID card file mining system";
//                public String XLHS_SOHOA = "Digital 9-digit ID card file management system";
//
//
//            };
//            return language;
//        }
//
//    }


    @GetMapping("/demo-translation/{lng}/{ns}")
    public Object getDemo(@PathVariable String lng, @PathVariable String ns) {
        if (ns.equals("login")) {
            if (lng.equals("vi")) {
                Object language = new Object() {
                    public String username = "Tên đăng nhập";
                    public String password = "Mật khẩu";
                    public String selectSubsystem = "Chọn phân hệ";
                    public String KHAITHAC_SOHOA = "Hệ thống khai thác hồ sơ CMND 9 số số hóa";
                    public String XLHS_SOHOA = "Hệ thống quản lý hồ sơ CMND 9 số số hóa";
                    public String loginSuccess = "Đăng nhập thành công";
                    public String wrongPasswordOrUsername = "Sai tài khoản hoặc mật khẩu";
                    public String networkError = "Sai tài khoản hoặc mật khẩu";


                };
                return language;
            } else {
                Object language = new Object() {
                    public String username = "Username";
                    public String password = "Password";
                    public String selectSubsystem = "SelectSubsystem";
                    public String KHAITHAC_SOHOA = "Digital 9-digit ID card file mining system";
                    public String XLHS_SOHOA = "Digital 9-digit ID card file management system";
                    public String loginSuccess = "Login successfully";
                    public String wrongPasswordOrUsername = "Wrong password or username";
                    public String networkError = "Network error";


                };
                return language;
            }
        }
        if (ns.equals("header")) {
            if (lng.equals("vi")) {
                Object language = new Object() {
                    public String username = "Tên cán bộ";
                    public String unit = "Đơn vị";
                    public String language = "Ngôn ngữ";
                    public String logOut = "Đăng xuất";

                };
                return language;
            } else {
                Object language = new Object() {
                    public String username = "Username";
                    public String unit = "Unit";
                    public String language = "Language";
                    public String logOut = "Log out";
                };
                return language;
            }
        }
        if (ns.equals("sidebarMenu")) {
            if (lng.equals("vi")) {
                Object language = new Object() {
                    public String expandMenu = "Mở rộng menu";
                    public String collapseMenu = "Thu gọn menu";
                };
                return language;
            } else {
                Object language = new Object() {
                    public String expandMenu = "Expand menu";
                    public String collapseMenu = "Collapse menu";

                };
                return language;
            }
        }
        if (ns.equals("quanLyHoSoCmnd9So")) {
            if (lng.equals("vi")) {
                Object language = new Object() {
                    public String searchCondition = "Điều kiện tìm kiếm";
                    public String unit = "Đơn vị";
                    public String identityCardNumber = "Số cmnd 9 số";
                    public String fullName = "Họ và tên";
                    public String dateOfBirth = "Ngày sinh";
                    public String gender = "Giới tính";
                    public String searchRange = "Phạm vi tìm kiếm";
                    public String fatherName = "Họ tên cha";
                    public String motherName = "Họ tên mẹ";
                    public String husbandWifeName = "Họ tên vợ (Chồng)";
                    public String dateInput = "Ngày nhập";
                    public String listed = "Tìm kiếm";
                    public String reset = "Đặt lại";
                };
                return language;
            } else {
                Object language = new Object() {
                    public String searchCondition = "Search condition";
                    public String unit = "Unit";
                    public String identityCardNumber = "Identity card number";
                    public String fullName = "Fullname";
                    public String dateOfBirth = "Date of birth";
                    public String gender = "Gender";
                    public String searchRange = "Search range";
                    public String fatherName = "Father's name";
                    public String motherName = "Mother's name";
                    public String husbandWifeName = "Husband (or wife)'s name";
                    public String dateInput = "Date input";
                    public String listed = "Listed";
                    public String reset = "Reset";
                };
                return language;
            }
        }
        return null;

    }
}
