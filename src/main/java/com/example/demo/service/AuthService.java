package com.example.demo.service;

import com.example.demo.dao.AuthDao;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;

    /**
     * 用户登录验证
     * @param username 用户名
     * @param password 密码
     * @return token（登录成功）或 null（登录失败）
     */
    public String login(String username, String password) {
        // 通过 AuthDao 查询数据库验证用户名和密码
        Optional<User> userOptional = authDao.findByUsernameAndPassword(username, password);
        
        if (userOptional.isPresent()) {
            // 登录成功，返回 token（示例：实际项目中应生成 JWT）
            return "fake-jwt-token-" + System.currentTimeMillis();
        }
        
        // 登录失败
        return null;
    }
}

