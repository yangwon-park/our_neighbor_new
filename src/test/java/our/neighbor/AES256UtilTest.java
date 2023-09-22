package our.neighbor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import our.neighbor.app.core.util.AES256Util;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class AES256UtilTest {

    @Autowired
    AES256Util aes256Util;

    @Test
    void should_ExecuteAES256() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String testText = "가나다라마바사아자차카타파하";
        String aesEncrypt = aes256Util.aesEncrypt(testText);
        String aesDecrypt = aes256Util.aesDecrypt(aesEncrypt);

        System.out.println("aesEncrypt = " + aesEncrypt);
        System.out.println("aesDecrypt = " + aesDecrypt);

        assertThat(aesDecrypt).isEqualTo(testText);
    }
}
