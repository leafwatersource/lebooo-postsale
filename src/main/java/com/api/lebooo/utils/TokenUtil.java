package com.api.lebooo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class TokenUtil {

	private static TokenUtil instance = new TokenUtil();

	private static final String ACTION_TOKEN = "action.TOKEN";

	private static final String PARAM_TOKEN = "param.TOKEN";

	private Long previous;

	protected TokenUtil() {
	}

	public static TokenUtil getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
	}

	// UUID
	public synchronized String generateTokenUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	// MD5
	public synchronized String generateToken(String id) {
		try {
			Long current = System.currentTimeMillis();
			if (current.equals(previous)) {
				current++;
			}
			previous = current;

			// byte now[] = (current+"").toString().getBytes();
			byte now[] = (new Long(current)).toString().getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(id.getBytes());
			md.update(now);
			return toHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}

		return sb.toString();
	}

	public String randomStr() {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			int number = random.nextInt(60);
			sb.append(s.charAt(number));
		}
		return sb.toString();
	}


	/**
	 * 随机生成六位数
	 *
	 * @return
	 */
	public static int nextInt() {
		Random random = new Random();
		int x = random.nextInt(899999);
		x = x + 100000;
		return x;
	}

	/**
	 * 随机生成三位数
	 *
	 * @return
	 */
	public static int nextInt3() {
		Random random = new Random();
		int x = random.nextInt(899);
		x = x + 100;
		return x;
	}

}
