package sam.model.common.seguranca;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordDigest {

    public static String passwordDigestMD5(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes("UTF8"));
            byte[] s = md.digest();
            String result = "";
            for (int i = 0; i < s.length; i++)
                result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);

            return result;
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
