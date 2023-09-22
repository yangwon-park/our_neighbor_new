package our.neighbor.app.core.util;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Component
public class AES256Util {

    @Value("${aes.secret-key}")
    private String SECRET_KEY;

    @PostConstruct
    public void init() {
        if (SECRET_KEY.getBytes().length != 32) {
            throw new IllegalArgumentException("INVALID SECRET KEY SIZE!!! 비밀키는 32 bytes여야 합니다.");
        }
    }

    public String aesEncrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {


        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        // 랜덤 IV 생성
        byte[] ivBytes = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);
        IvParameterSpec ivParamSpec =  new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        byte[] encryptedWithIv = new byte[ivBytes.length + encrypted.length];

        System.arraycopy(ivBytes, 0, encryptedWithIv, 0, ivBytes.length);
        System.arraycopy(encrypted, 0, encryptedWithIv, ivBytes.length, encrypted.length);

        return Base64.getEncoder().encodeToString(encryptedWithIv);
    }

    public String aesDecrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedWithIv = Base64.getDecoder().decode(text);

        // IV와 암호화된 데이터를 분리
        byte[] ivBytes = Arrays.copyOfRange(encryptedWithIv, 0, 16);
        byte[] encrypted = Arrays.copyOfRange(encryptedWithIv, 16, encryptedWithIv.length);

        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
