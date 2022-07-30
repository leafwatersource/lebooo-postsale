package com.api.lebooo.utils.encryptTools;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.UUID;

@Component
public class MD5Util {

	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/** 对字符串进行MD5加密 */
	public static String encodeByMD5(String originString) {
		if (originString != null && originString != "") {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toLowerCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为十六进制字符串
	 *
	 * @b 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	/**
	 * 获取数据指纹
	 * */
	public String dataFingerprint(){
		try{
			String s = UUID.randomUUID().toString();
			// 先进行MD5加密
			MessageDigest md = MessageDigest.getInstance("md5");
			// 对数据进行加密
			byte[] bs = md.digest(s.getBytes());

			/*
			 * BASE64Encoder所在包的引入方式（Eclipse）： 在Java Build Path下的
			 * Libraries中拉开JRE，然后点击 第一个选项Access rules（双击），然后点击add，在上面的框中选择
			 * Accessible，下面输入**，保存即可引入相应的包。
			 *
			 * BASE64Encoder底层实现原理：三字节变四字节
			 */
			// 采用数据指纹进一步加密，拿到数据指纹
			BASE64Encoder base = new BASE64Encoder();
			// 进一步加密
			return base.encode(bs);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String s = UUID.randomUUID().toString();
		MD5Util m=new MD5Util();
		System.out.println(encodeByMD5(s));
		System.out.println(m.dataFingerprint());
	}
}
