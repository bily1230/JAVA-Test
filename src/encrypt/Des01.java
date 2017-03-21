package encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;

/**
 * 对称加密算法DES
 * Created by ning on 2017/3/12.
 */
public class Des01 {

   private  final static String  src1 = "好好学习";

    public static void main(String[] args){
        jdkDES();
    }
    public static void jdkDES(){

        try {
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory  factory = SecretKeyFactory.getInstance("DES");
            SecretKey convertSecretKey =  factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
             byte[] result = cipher.doFinal(src1.getBytes());
             System.out.println("加密："+ new String(result));

             cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
             byte[] result1 = cipher.doFinal(result);
             System.out.println("解密："+ new String(result1));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

