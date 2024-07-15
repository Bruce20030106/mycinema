package com.example.mycinema.common;

import com.example.mycinema.domain.dto.LoginInfo;

public class UserContextHolder {
    private static final ThreadLocal<LoginInfo> userHolder = new ThreadLocal<>();

    public static void setUser(LoginInfo user) {
        userHolder.set(user);
    }

    public static LoginInfo getUser() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }
}
