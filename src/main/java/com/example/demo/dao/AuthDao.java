package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 认证持久层接口
 */
@Repository
public interface AuthDao extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户对象（如果存在）
     */
    Optional<User> findByUsername(String username);

    /**
     * 根据用户名和密码查找用户（用于登录验证）
     * @param username 用户名
     * @param password 密码
     * @return 用户对象（如果存在）
     */
    Optional<User> findByUsernameAndPassword(String username, String password);
}
