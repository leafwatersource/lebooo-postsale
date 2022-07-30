package com.api.lebooo.utils.encryptTools;

import com.api.lebooo.utils.PropertiesUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;

public class AESUtils {

    protected static Logger logger = LoggerFactory.getLogger(AESUtils.class);
    // 密钥
    public static String key(){
         try{
            // 读取配置文件，获取refreshTokenExpireTime属性
            PropertiesUtil.readProperties("config.properties");
            String secret= PropertiesUtil.getProperty("encryptAESKey");
            return new String( new BASE64Decoder().decodeBuffer(secret));
        }catch (Exception e){
            logger.error("-----获取AES秘钥出错",e);
            return null;
        }
    }

    private static String charset = "utf-8";
    // 偏移量
    private static int offset = 16;
  /*  private static String transformation = "AES/ECB/NOPadding";*/   //ECB  CBC
    private static String algorithm = "AES";
    private static String padding="NOPadding";
    private static String pattern="CBC";

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        try {
            return encrypt(content, key());
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        return decrypt(content, key());
    }

    /**
     * 加密
     *
     * @param data
     *            需要加密的内容
     * @param key
     *            加密密码
     * @return
     */
    public static String encrypt(String data, String key) throws Exception {
        try {

            Cipher cipher = Cipher.getInstance(algorithm+"/"+pattern+"/"+padding);//"算法/模式/补码方式"NoPadding PkcsPadding
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), algorithm);
            IvParameterSpec ivspec = new IvParameterSpec(key.getBytes());

           /* cipher.init(Cipher.ENCRYPT_MODE, keyspec);*/    //ECB  模式
            cipher.init(Cipher.ENCRYPT_MODE, keyspec,ivspec);   //CBC  模式
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base64().encodeToString(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES（256）解密
     *
     * @param content
     *            待解密内容
     * @param key
     *            解密密钥
     * @return 解密之后
     * @throws Exception
     */
    public static String decrypt(String content, String key) {
        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), algorithm);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(), 0, offset);
            Cipher cipher = Cipher.getInstance(algorithm+"/"+pattern+"/"+padding);
            cipher.init(Cipher.DECRYPT_MODE, skey, iv);// 初始化   //CBC  模式
           /* cipher.init(Cipher.DECRYPT_MODE, skey);*/// 初始化   //ECB  模式
            byte[] result = cipher.doFinal(new Base64().decode(content));
            return new String(result).trim(); // 解密
        } catch (Exception e) {

        }
        return null;
    }
    /**
     * 生成盐
     * @return 返回base64编码后的盐信息
     */
    public static String generateSalt(){
        byte [] salt=new byte[8];
        Random random=new Random();
        random.nextBytes(salt);
        return new BASE64Encoder().encode(salt);
    }

    public static void main(String[] args) throws Exception {
//
//        System.out.println(new String( new BASE64Decoder().decodeBuffer("RVBmTGtTNjk4MjFXelhkTw==")));


        KeyGenerator kg = KeyGenerator.getInstance("AES");
        //下面调用方法的参数决定了生成密钥的长度，可以修改为128, 192或256
        kg.init(256);
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        String secret = Base64.encodeBase64String(b);
//        System.out.println(secret);

        String k =new String(new BASE64Decoder().decodeBuffer(secret));

//        System.out.println("------------解密： "+k);

       /* String salt= AESUtils.generateSalt();
        System.out.println("------------pass： "+DigestUtils.md5Hex(DigestUtils.md5Hex("123456") + salt));
        System.out.println("------------salt： "+salt);*/
//        System.out.println(code);
/*        String value=Base64Coder.encodeBASE64(code.getBytes());
        System.out.println(value.toUpperCase());
        System.out.println("-----"+new String(Base64Coder.decryptBASE64(value.toUpperCase())));
        System.out.println(new String(Base64Coder.decryptBASE64("RVBmTGtTNjk4MjFXelhkTw==")));
        System.out.println(new String( new BASE64Decoder().decodeBuffer("QWxsUmlzZTYyM05vdGhpbmdzMDQxQ2hhbmdlMTM1TG92ZTdGb3I4WW91OQ==")));*/
    }
}
