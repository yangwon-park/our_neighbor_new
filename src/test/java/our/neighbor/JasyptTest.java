package our.neighbor;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;


public class JasyptTest {

    @Test
    @DisplayName("Jasypt 적용")
    void jasypt() {
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("neighbor_hood");
        jasypt.setAlgorithm("PBEWITHMD5ANDDES");

        String ex = "neighbor1572";

        String encrypt = jasypt.encrypt(ex);
        String decrypt = jasypt.decrypt(encrypt);

        System.out.println("encrypt = " + encrypt);
        System.out.println("decrypt = " + decrypt);
    }
}
