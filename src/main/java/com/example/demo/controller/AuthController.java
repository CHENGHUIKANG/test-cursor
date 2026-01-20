package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.DeleteUserRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.QueryUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin // 开发阶段允许跨域访问
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        if (token != null) {
            return new LoginResponse(true, token, "登录成功");
        } else {
            return new LoginResponse(false, null, "用户名或密码错误");
        }
    }

    @PostMapping("/user/create")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return new UserResponse(false, "用户名或密码不能为空", null);
        }
        User user = authService.createUser(request.getUsername(), request.getPassword());
        if (user == null) {
            return new UserResponse(false, "用户名已存在或参数不合法", null);
        }
        return new UserResponse(true, "创建成功", user);
    }

    @PostMapping("/user/delete")
    public UserResponse deleteUser(@RequestBody DeleteUserRequest request) {
        boolean deleted = authService.deleteUser(request.getId());
        if (!deleted) {
            return new UserResponse(false, "用户不存在或参数不合法", null);
        }
        return new UserResponse(true, "删除成功", null);
    }

    @PostMapping("/user/update")
    public UserResponse updateUser(@RequestBody UpdateUserRequest request) {
        return authService.updateUser(request.getId(), request.getUsername(), request.getPassword())
                .map(user -> new UserResponse(true, "更新成功", user))
                .orElseGet(() -> new UserResponse(false, "用户不存在或参数不合法", null));
    }

    @PostMapping("/user/query")
    public UserResponse queryUser(@RequestBody QueryUserRequest request) {
        return authService.queryUser(request.getId())
                .map(user -> new UserResponse(true, "查询成功", user))
                .orElseGet(() -> new UserResponse(false, "用户不存在或参数不合法", null));
    }
}

