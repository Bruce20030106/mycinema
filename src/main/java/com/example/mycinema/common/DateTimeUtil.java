package com.example.mycinema.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    // 定义日期时间格式
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 获取当前时间，格式为"yyyy-MM-dd HH:mm"
     * @return 格式化后的当前时间字符串
     */
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(FORMATTER);
    }
}
