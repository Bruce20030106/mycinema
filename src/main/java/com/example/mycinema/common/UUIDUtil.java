package com.example.mycinema.common;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUIDUtil {

    public static Long generateUUIDAsLong() {
        UUID uuid = UUID.randomUUID();
        return hashUUIDToPositiveLong(uuid);
    }

    private static Long hashUUIDToPositiveLong(UUID uuid) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] uuidBytes = toByteArray(uuid);
            byte[] hashBytes = messageDigest.digest(uuidBytes);
            ByteBuffer buffer = ByteBuffer.wrap(hashBytes);
            long longValue = buffer.getLong(); // 从哈希字节数组中获取前8个字节转换为Long
            return longValue & Long.MAX_VALUE; // 确保long值是正数
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.", e);
        }
    }

    private static byte[] toByteArray(UUID uuid) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return buffer.array();
    }

    public static void main(String[] args) {
        Long uuidAsLong = generateUUIDAsLong();
        System.out.println("Generated UUID as Long: " + uuidAsLong);
    }
}
