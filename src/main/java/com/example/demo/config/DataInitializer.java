package com.example.demo.config;

import com.example.demo.dao.AuthDao;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 数据初始化类：应用启动后自动插入测试数据
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AuthDao authDao;

    @Override
    public void run(String... args) throws Exception {
        // 检查是否已有数据，避免重复插入
        if (authDao.count() == 0) {
            // 插入测试用户
            User admin = new User("admin", "123456");
            User user = new User("user", "password");
            
            authDao.save(admin);
            authDao.save(user);
            
            System.out.println("初始化数据完成：已插入测试用户 admin/123456 和 user/password");
        }
    }
}
