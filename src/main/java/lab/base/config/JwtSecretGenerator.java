package lab.base.config;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        // Độ dài của khóa bí mật (ví dụ: 256 bit)
        int keyLength = 256;

        // Tạo một mảng byte ngẫu nhiên để làm khóa bí mật
        byte[] secretBytes = new byte[keyLength / 8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secretBytes);

        // Encode khóa bí mật sang dạng Base64 để lưu trữ hoặc sử dụng trong ứng dụng
        String jwtSecret = Base64.getEncoder().encodeToString(secretBytes);
        System.out.println("Generated JWT Secret: " + jwtSecret);
    }
}
