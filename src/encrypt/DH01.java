package encrypt;

import com.sun.deploy.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 非对称加密算法DH
 * Created by ning on 2017/3/12.
 */
public class DH01 {

    private static String src = "你是谁是！新的小伙伴";
    public static void main(String[] args){
        jdkDH();
    }

    public static void jdkDH(){
        try{

            //初始化发送方密匙
            KeyPairGenerator sendKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            sendKeyPairGenerator.initialize(512);
            KeyPair senderKeyPair = sendKeyPairGenerator.generateKeyPair();
            byte[] sendPublicKetEnc = senderKeyPair.getPublic().getEncoded();//发送方公匙，发送给接收方

            // 初始化接收方秘钥
            KeyFactory keyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(sendPublicKetEnc);
            PublicKey receiverPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            DHParameterSpec dhParameterSpec = ((DHPublicKey)receiverPublicKey).getParams();
            KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            receiverKeyPairGenerator.initialize(dhParameterSpec);
            KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();
            byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();

            //秘钥构建
            KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
            receiverKeyAgreement.init(receiverKeyPair.getPrivate());
            receiverKeyAgreement.doPhase(receiverPublicKey,true);
            SecretKey receiverDesKey = receiverKeyAgreement.generateSecret("DES");


            KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
            x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
            PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
            
            KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
            senderKeyAgreement.init(senderKeyPair.getPrivate());
            senderKeyAgreement.doPhase(senderPublicKey,true);
            SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE,senderDesKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println(Base64.getEncoder().encodeToString(result));

            cipher.init(Cipher.DECRYPT_MODE,receiverDesKey);
            result = cipher.doFinal(result);
            System.out.println(new String(result));



        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
