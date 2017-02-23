package com.fox.tools.string;

import java.util.Random;

/**
 * Created by lcy on 2017/2/23.
 */
public class StringUtils {
    public static String generateRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789@#*()QAZWSXEDCRFVTGBYHNUJMIKLOP";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
