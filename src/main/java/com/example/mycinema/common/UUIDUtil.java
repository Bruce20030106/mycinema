package com.example.mycinema.common;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDUtil {

    public static Long generateUUIDAs8DigitLong() {
        UUID uuid = UUID.randomUUID();
        return extract8DigitLongFromUUID(uuid);
    }

    private static Long extract8DigitLongFromUUID(UUID uuid) {
        long leastSignificantBits = uuid.getLeastSignificantBits();
        return Math.abs(leastSignificantBits % 100000000L); // 取模生成8位数字
    }

    public static void main(String[] args) {
        Long uuidAs8DigitLong = generateUUIDAs8DigitLong();
        System.out.println("Generated UUID as 8-digit long: " + uuidAs8DigitLong);
    }
}

