package com.example.demo.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static Integer getUserId(HttpSession session) {
        Object uid = session.getAttribute("userId");
        return (uid instanceof Integer) ? (Integer) uid : null;
    }
    public static boolean isLogin(HttpSession session) {
        return getUserId(session) != null;
    }
}
