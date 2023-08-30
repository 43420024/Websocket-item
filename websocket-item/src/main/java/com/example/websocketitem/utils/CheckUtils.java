package com.example.websocketitem.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtils {
    /**
     * 校验手机号
     * */
    public static Boolean phoneNumber(String phone){
        if(phone.length()==11){
            Pattern compile = Pattern.compile("^1[3456789]\\d{9}$");
            Matcher matcher = compile.matcher(phone);
            boolean matches = matcher.matches();
            return matches;
        }else {
            return false;
        }
    }
}
