package com.example.demo.dto;

import com.example.demo.domain.User;

/**
 * 通用用户操作响应
 */
public class UserResponse {

    private boolean success;
    private String message;
    private User user;

    public UserResponse(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
