package com.ucomponent.utils;

import java.security.MessageDigest;

/**
 * 2018年6月20日
 * 代码老哥
 * NAME:MD5加密类
 * Descp:
**/
public class MD5Util {
	public final static String MD5(String s) {
		char hexDigits[] = { '!', 'E', 'B', 'v', 'X', '8', 'i', 'o', 'c', 'L', '9', '7', '1', '3', 'D', 'P' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String get2MD5(String s){
		String md1 = MD5Util.MD5(s);
		String md2 = MD5Util.MD5(md1);
		return md2;
	}
	public final static String lowwerMD5(String s) {
		char hexDigits[] = {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
		};
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			byte[] btInput = s.getBytes();
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;

			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	public static String getMD5(String message) {
		String md5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象
			byte[] messageByte = message.getBytes("UTF-8");
			byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位
			md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5;
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for(int b : bytes){
			if (b < 0){
				b += 256;
			}
			if (b < 16){
				builder.append("0");
			}
			builder.append(Integer.toHexString(b));
		}
		return builder.toString();
	}
}
