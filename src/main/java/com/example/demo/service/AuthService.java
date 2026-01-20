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

    /**
     * 创建用户，若用户名已存在则返回 null
     */
    public User createUser(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        if (authDao.findByUsername(username).isPresent()) {
            return null;
        }
        User user = new User(username, password);
        return authDao.save(user);
    }

    /**
     * 根据 id 删除用户
     */
    public boolean deleteUser(Long id) {
        if (id == null || !authDao.existsById(id)) {
            return false;
        }
        authDao.deleteById(id);
        return true;
    }

    /**
     * 更新用户信息（用户名和密码）
     */
    @SuppressWarnings("null")
    public Optional<User> updateUser(Long id, String username, String password) {
        if (id == null) {
            return Optional.empty();
        }
        return authDao.findById(id).flatMap(existing -> {
            if (username != null) {
                existing.setUsername(username);
            }
            if (password != null) {
                existing.setPassword(password);
            }
            User saved = authDao.save(existing);
            return saved != null ? Optional.of(saved) : Optional.empty();
        });
    }

    /**
     * 根据 id 查询用户
     */
    public Optional<User> queryUser(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return authDao.findById(id);
    }
}

