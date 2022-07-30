package com.api.lebooo.utils.encryptTools;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Base64Coder {

/**
 * 使用Base64进行加密
 * @param res 密文
 * @return
 */

    public static String encodeBASE64(byte[] res) {
        return Base64.encode(res);
    }

/**
 * 使用Base64进行解密
 * @param res
 * @return
 */

    public static byte[] decryptBASE64(String res) {
        return Base64.decode(res);
    }
}
