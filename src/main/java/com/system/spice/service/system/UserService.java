package com.system.spice.service.system;

import com.system.spice.dto.RoleDto;
import com.system.spice.dto.UserDto;
import com.system.spice.entity.Role;
import com.system.spice.entity.User;
import com.system.spice.model.system.ChangePasswordModel;
import com.system.spice.model.system.Login;
import com.system.spice.repository.RoleRepository;
import com.system.spice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Service tạo tài khoản quản lý
    public UserDto registerManager(Login user) {
        User u = new User();
        u.setUsername(user.getUsername());
        
        // Kiểm tra nếu mật khẩu đã được mã hóa rồi thì không mã hóa lại
        if (!user.getPassword().startsWith("$2a$")) {
            u.setPassword(encoder.encode(user.getPassword()));
        } else {
            u.setPassword(user.getPassword());
        }

        u.setCreateAt(Instant.now());
        u.setRole(getRoleByManager("MANAGER"));
        repo.save(u);
        return convertToDto(u);
    }


    //Service đăng nhập
    public Map<String, String> verify(Login user) {
        Map<String, String> response = new HashMap<>();
        try {
            User dbUser = repo.findByUsername(user.getUsername()).orElse(null);
            if (dbUser == null) {
                response.put("message", "Tài khoản không tồn tại!");
                return response;
            }

            // Kiểm tra mật khẩu nhập vào có khớp với mật khẩu đã mã hóa không
            if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
                response.put("message", "Sai mật khẩu!");
                return response;
            }

            // Nếu đúng thì tạo token
            response.put("username", user.getUsername());
            response.put("token", jwtService.generateToken(user.getUsername()));
            response.put("role", getRoleByUsername(user.getUsername()));
            response.put("message", "Đăng nhập thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi đăng nhập: " + e.getMessage());
            response.put("message", "Lỗi đăng nhập!");
        }
        return response;
    }


    //    API đổi mật khẩu
    public Map<String, String> changePassword(ChangePasswordModel model) {
        Map<String, String> response = new HashMap<>();
        try {
            User user = repo.findByUsername(model.getUsername()).orElse(null);
            if (!encoder.matches(model.getOldPassword(), user.getPassword())) {
                response.put("message", "Mật khẩu cũ không chính xác!");
                return response;
            }
            user.setPassword(encoder.encode(model.getNewPassword()));
            user.setUpdateAt(Instant.now());
            repo.save(user);
            response.put("message", "Mật khẩu đã được thay đổi thành công!");
            return response;
        } catch (Exception e) {
            response.put("message", "Có lỗi xảy ra khi thay đổi mật khẩu!");
        }
        return response;
    }


    //Tìm vai trò
    private Role getRoleByManager(String roleName) {
        return roleRepository.findByRoleName(roleName).orElse(null);
    }

    //Tìm vai trò theo username
    private String getRoleByUsername(String username) {
        return roleRepository.findRoleNameByUsername(username);
    }

    private UserDto convertToDto(User user) {
        UserDto u = new UserDto();
        u.setUsername(user.getUsername());
        if (user.getRole() != null) {
            RoleDto r = new RoleDto();
            r.setRoleName(user.getRole().getRoleName());
            u.setRole(r);
        }
        return u;
    }
}
