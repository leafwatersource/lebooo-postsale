package com.api.lebooo.utils.encryptTools;


import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA安全编码组件
 *
 */
public class RSACode {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /** */
    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    private static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64Coder.decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64Coder.encodeBASE64(signature.sign());
    }

    public static String getSign(String data,String privateKey){
        try {
            data = sign(data.getBytes(), privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /** */
    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @param sign
     *            数字签名
     *
     * @return
     * @throws Exception
     *
     */
    private static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64Coder.decryptBASE64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64Coder.decryptBASE64(sign));
    }

    public static boolean getVerify(String data,String publicKey, String sign){
        boolean falg=false;
        try {
            falg = verify(data.getBytes(), publicKey,sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    /** */
    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    private static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64Coder.decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    private static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = Base64Coder.decryptBASE64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    private static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64Coder.decryptBASE64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    private static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64Coder.decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }


    /**
     * 第一种
     * java端公钥加密
     */
    public static String encryptedDataOnJava(String data, String PUBLICKEY) {
        try {
            data = Base64Coder.encodeBASE64(encryptByPublicKey(data.getBytes(), PUBLICKEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 第一种
     * java端私钥解密
     */
    public static String decryptDataOnJava(String data, String PRIVATEKEY) {
        String temp = "";
        try {
            byte[] rs = Base64Coder.decryptBASE64(data);
            temp = new String(RSACode.decryptByPrivateKey(rs, PRIVATEKEY),"UTF-8"); //以utf-8的方式生成字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    /*------------------------------------第二种-----------------------------------*/
  /*  *//**
     * 第二种
     * java端私钥加密
     */
    public static String encryptedPrivateDataOnJava(String data, String PRIVATEKEY) {
        try {
            data = Base64Coder.encodeBASE64(encryptByPrivateKey(data.getBytes(), PRIVATEKEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 第二种
     * java端公钥解密
     *//*
    public static String decryptPublicDataOnJava(String data, String PUBLICKEY) {
        String temp = "";
        try {
            byte[] rs = Base64Coder.decryptBASE64(data);
            temp = new String(RSACode.decryptByPublicKey(rs, PUBLICKEY),"UTF-8"); //以utf-8的方式生成字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }*/

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return Base64Coder.encodeBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return Base64Coder.encodeBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) {
        try{
            Map<String,Object> info=initKey();
            System.out.println("--------私钥："+getPrivateKey(info));
            System.out.println("--------公钥： "+getPublicKey(info));
//            String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOHzoqTf8D10by+/scJaIT2atpptflDmn2o6yG6f+LZj7zPGKvQmkkDIgYQLQztcA8uVKZSKspcibm8S94CoLI0pQpTJLs6bBWf05AxdUVxOzIYdkCy9UT0ExYyD8nEsjtBxSaWWAop9SmjTfbg79ycdpTpvGNHWmOx35u2xN0GQIDAQAB";
//            String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI4fOipN/wPXRvL7+xwlohPZq2mm1+UOafajrIbp/4tmPvM8Yq9CaSQMiBhAtDO1wDy5UplIqylyJubxL3gKgsjSlClMkuzpsFZ/TkDF1RXE7Mhh2QLL1RPQTFjIPycSyO0HFJpZYCin1KaNN9uDv3Jx2lOm8Y0daY7Hfm7bE3QZAgMBAAECgYBP6pdWeo1Pp5opwC+v4CmHZ/rPahY0oypKmjbFlocv58q2E9qaGonsz28n1BqAiU8T1lHwB8DXiWz+p9i9c4ueary5wWCG90YyYGVp31uzOcK8BXTt4leEsykSk05y4Mtov5bGcC/k7OPb5JUgLn8TvkIwpRjWB/NsWTI0VJ1HkQJBAPsgJKsGSj1JS6wS4YtM8BmC96Vbd/DujomC/7hR5+YPa1nX5na3zRo40qf44nwgPlnWKZVhlDczdanCc1aJF5cCQQCQ4XBt59RUcvqpuEkpGTtjg+UTLb5ECTumji19ZlOacFxkzNi86C0drYzeitNTX7kLYzsNIk3BN4p7aeralcfPAkEAqMDI40SuztBaUZ++gOWK0xn3YN8+kmE815t4TFx+AVOLSCyVamHJKj3VEZtmVxMz0xrfhsqz1wR1pUZOS3CEsQJAPhhxsJlxVvSk3MNAkB3hZ2ircQnlB5IRf/RmKN4HrSTFZOqrZmP4mkS9INDNfGmFjQztzVZLjym37+dYonEZ3wJAOiCIMW7hzeI97LN/qQTQTlLsg+3zRfkMHb0CD9zkWDU5opofq6CGeINfY5Kv1lZ+S8Cc2i3L0nw3Xbm+7ij4WA==";
//
//            String value="AllRise623Nothings041Change135Love7For8You9";
//
//            System.out.println("测试一");
//            String encrypte=encryptedDataOnJava(value,publicKey);
//            System.out.println("公钥加密---------"+encrypte);
          /*  System.out.println("------------------"+new String(encryptByPublicKey(value.getBytes(), publicKey)));*/
//            System.out.println("私钥解密---------"+decryptDataOnJava(encrypte,privateKey));
//            System.out.println("测试二");
//            String sign=getSign(value,privateKey);
//            System.out.println("sign---------"+sign);
//            System.out.println("----check---"+getVerify(value,publicKey,sign));
//            String encry=encryptedPrivateDataOnJava(value,privateKey);
//            System.out.println("私钥加密----------"+encry);
           /* System.out.println("公钥解密----------"+decryptPublicDataOnJava(encry,publicKey));*/
        }catch (Exception e){

        }


    }
}
