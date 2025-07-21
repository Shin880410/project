package com.example.demo.securityUtil;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class SecurityUtil {
	 public static String hash(String plain) {
	        return BCrypt.hashpw(plain, BCrypt.gensalt());
	    }
	    public static boolean match(String plain, String hash) {
	        return BCrypt.checkpw(plain, hash);
	    }
}
