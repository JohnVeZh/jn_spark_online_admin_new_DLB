package com.easecom.common.util;

public class RandomCode {
	/**
	 * 随机生成字符，含大写、小写、数字 <b>function:</b> 功能
	 * 
	 * @author 孙晓宇 Jul 3, 20135:53:54 PM
	 * @return
	 */
	public static String getRandomChar(int n) {
		String str = "";
		for (int i = 0; i < n; i++) {
			String randChar = "";
			int index = (int) Math.round(Math.random() * 2);
			switch (index) {
			case 0:// 大写字符
				 randChar = String.valueOf((char)Math.round(Math.random() * 25
				 + 97));
//				randChar = String.valueOf(Math.round(Math.random() * 65));
				break;
			case 1:// 小写字符
				 randChar = String.valueOf((char)Math.round(Math.random() * 25
				 + 97));
//				randChar = String.valueOf(Math.round(Math.random() * 9));
				break;
			default:// 数字
				randChar = String.valueOf(Math.round(Math.random() * 9));
				break;
			}
			str = str + randChar;
		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println(getRandomChar(9));
	}

	/**
	 * 随机生成字符，含大写、小写、数字 <b>function:</b> 功能
	 * 
	 * @author 孙晓宇 Jul 3, 20135:53:54 PM
	 * @return
	 */
	public static String getRandom() {
		String str = "";
		for (int i = 0; i < 3; i++) {
			String randChar = "";
			int index = (int) Math.round(Math.random() * 2);
			switch (index) {
			case 0:// 大写字符
				// randChar = String.valueOf((char)Math.round(Math.random() * 25
				// + 65));
				randChar = String.valueOf(Math.round(Math.random() * 9));
				break;
			case 1:// 小写字符
				// randChar = String.valueOf((char)Math.round(Math.random() * 25
				// + 97));
				randChar = String.valueOf(Math.round(Math.random() * 9));
				break;
			default:// 数字
				randChar = String.valueOf(Math.round(Math.random() * 9));
				break;
			}
			str = str + randChar;
		}
		return str;
	}
	/**
	 * 作者：拓研-孙晓宇
	 * @Title: getCode 
	 * @Description: 获取6为随机数字 
	 * @return String 返回类型 
	 * @throws 
	 * 日期：2015-12-2
	 */
	public static String getCode() {
		String str = "";

		str += (int) (Math.random() * 9 + 1);

		for (int i = 0; i < 5; i++) {

			str += (int) (Math.random() * 10);
		}
		return str;
	}
}
