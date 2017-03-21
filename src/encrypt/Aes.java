package encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by ning on 2017/3/12.
 */
public class Aes {
    private static String src = "AES,下雨天!";

    public static void main(String[] args){
        jdkAES();
    }
    public static void jdkAES(){
        try{
            KeyGenerator keyGenerator =  KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            SecretKey key = new SecretKeySpec(keyBytes,"AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println(Base64.getEncoder().encodeToString(result));

            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] result1 = cipher.doFinal(result);
            System.out.println(new String(result1));


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
