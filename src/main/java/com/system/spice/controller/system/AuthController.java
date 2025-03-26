package com.system.spice.controller.system;

import com.system.spice.dto.UserDto;
import com.system.spice.model.system.ChangePasswordModel;
import com.system.spice.model.system.Login;
import com.system.spice.service.system.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.system.spice.entity.User;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    //API Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Login login, HttpServletResponse response) {
        Map<String, String> result = userService.verify(login);

        if (result.containsKey("token")) {
            // Lưu token vào cookie HTTP-Only
            Cookie cookie = new Cookie("token", result.get("token"));
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // 1 ngày
            response.addCookie(cookie);

            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        }

    //    API đăng ký tài khoản quản lý
    @PostMapping("/register-manager")
    public UserDto register(@RequestBody Login login) {
        return userService.registerManager(login);
    }

    //    API đổi mật khẩu
    @PutMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody ChangePasswordModel changePasswordModel) {
        Map<String, String> response = userService.changePassword(changePasswordModel);
        return ResponseEntity.ok(response);
    }
}
