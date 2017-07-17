package com.easecom.common.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    /**
     * MD5加密    
     * @param str
     * @return
     */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
        //MD5强效加密
		String nextStr = md5StrBuff.toString();
		nextStr = getMD5Mapping(nextStr);
		return nextStr;
	}
	
	private static String getMD5Mapping(String str){
		String deepStr1 = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') {
				deepStr1 += 'h';
			}else if (str.charAt(i) == '2') {
				deepStr1 += 'x';
			}else if (str.charAt(i) == '4') {
				deepStr1 += 'z';
			}else if (str.charAt(i) == '6') {
				deepStr1 += 'w';
			}else if (str.charAt(i) == '8') {
				deepStr1 += 'y';
			}else if (str.charAt(i) == 'a') {
				deepStr1 += 't';
			}else if (str.charAt(i) == 'c') {
				deepStr1 += 'j';
			}else if (str.charAt(i) == 'e') {
				deepStr1 += 'o';
			}else{
				deepStr1 += str.charAt(i);
			}
		}
		String deepStr2 = "";
		for (int i = 0; i < deepStr1.length(); i++) {
			if (deepStr1.charAt(i) == '1') {
				deepStr2 += 'e';
			}else if (deepStr1.charAt(i) == '3') {
				deepStr2 += 'a';
			}else if (deepStr1.charAt(i) == '5') {
				deepStr2 += 'c';
			}else if (deepStr1.charAt(i) == '7') {
				deepStr2 += '4';
			}else if (deepStr1.charAt(i) == '9') {
				deepStr2 += '6';
			}else if (deepStr1.charAt(i) == 'b') {
				deepStr2 += '8';
			}else if (deepStr1.charAt(i) == 'd') {
				deepStr2 += 'r';
			}else if (deepStr1.charAt(i) == 'f') {
				deepStr2 += 'q';
			}else{
				deepStr2 += deepStr1.charAt(i);
			}
		}
		return deepStr2;
	}
}
