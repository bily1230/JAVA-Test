package encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

/**
 * Created by ning on 2017/3/12.
 */
public class Des3 {

    private  final static String  src1 = "好好学习11";

    public static void main(String[] args){
        jdk3DES();
    }

    public static void jdk3DES() {
        try {

            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            keyGenerator.init(168);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            DESedeKeySpec sedeKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            SecretKey convertSecretKey = factory.generateSecret(sedeKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src1.getBytes());
            System.out.println("加密：" + new String(result));

            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] result1 = cipher.doFinal(result);
            System.out.println("解密：" + new String(result1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
