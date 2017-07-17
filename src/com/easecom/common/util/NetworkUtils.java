package com.easecom.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.StringTokenizer;
import org.apache.commons.codec.digest.DigestUtils;

public class NetworkUtils {

	private final static int MACADDR_LENGTH = 17;
	private final static String WIN_OSNAME = "Windows";
	private final static String LINUX_OSNAME = "Linux";
	private final static String OSX_OSNAME = "Mac OS X";
	private final static String MACADDR_REG_EXP = "^[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}$";
	private final static String WIN_MACADDR_EXEC = "ipconfig /all";
	private final static String LINUX_MACADDR_EXEC = "ifconfig";
	private final static String OSX_MACADDR_EXEC = "ifconfig";

	public final static String getLisence(String data){
		try{
			return DigestUtils.md5Hex(data);
		}catch(Exception e){
			return null;
		}
	}
	
	public final static String getLocalCode(){
		String code="";
		try{
			code=DigestUtils.shaHex(getMacAddress().replace("-", ""));
			
		}catch(Exception e){
			return null;
		}
		return code;
	}
	
	public final static String getMacAddress() throws IOException {
		String os = System.getProperty("os.name");
		try {
			if (os.startsWith(WIN_OSNAME)) {
				return windowsParseMacAddress(windowsIpConfigCommand());
			} else if (os.startsWith(LINUX_OSNAME)) {
				return linuxParseMacAddress(linuxRunIfConfigCommand());
			} else if (os.startsWith(OSX_OSNAME)) {
				return osxParseMacAddress(osxRunIfConfigCommand());
			} else {
				throw new IOException("OS not supported : " + os);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * OSX stuff
	 */
	private final static String osxParseMacAddress(String ipConfigOutput)
			throws ParseException {
		String localHost = null;
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch (java.net.UnknownHostException ex) {
			ex.printStackTrace();
			throw new ParseException(ex.getMessage(), 0);
		}
		StringTokenizer tokenizer = new StringTokenizer(ipConfigOutput, "\n");
		String lastMacAddress = null;
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			boolean containsLocalHost = line.indexOf(localHost) >= 0;
			// see if line contains IP address
			if (containsLocalHost && lastMacAddress != null) {
				return lastMacAddress;
			}
			// see if line contains MAC address
			int macAddressPosition = line.indexOf("ether");
			if (macAddressPosition != 0) {
				continue;
			}
			String macAddressCandidate = line.substring(macAddressPosition + 6)
					.trim();
			if (osxIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}
		ParseException ex = new ParseException("cannot read MAC address for "
				+ localHost + " from [" + ipConfigOutput + "]", 0);
		ex.printStackTrace();
		throw ex;

	}

	private final static boolean osxIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != MACADDR_LENGTH) {
			return false;
		}
		if (!macAddressCandidate.matches(MACADDR_REG_EXP)) {
			return false;
		}
		return true;
	}

	private final static String osxRunIfConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec(OSX_MACADDR_EXEC);
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1) {
				break;
			}
			buffer.append((char) c);
		}
		String outputText = buffer.toString();
		stdoutStream.close();
		return outputText;
	}

	/**
	 * Linux stuff
	 */
	private final static String linuxParseMacAddress(String ipConfigOutput)
			throws ParseException {
		String localHost = null;
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ParseException(e.getMessage(), 0);
		}
		StringTokenizer tokenizer = new StringTokenizer(ipConfigOutput, "\n");
		String lastMacAddress = null;
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			boolean containsLocalHost = line.indexOf(localHost) >= 0;
			// see if line contains IP address
			if (containsLocalHost && lastMacAddress != null) {
				return lastMacAddress;
			}
			// see if line contains MAC address
			int macAddressPosition = line.indexOf("HWaddr");
			if (macAddressPosition <= 0) {
				continue;
			}
			String macAddressCandidate = line.substring(macAddressPosition + 6)
					.trim();
			if (linuxIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}
		ParseException ex = new ParseException("cannot read MAC address for "
				+ localHost + " from [" + ipConfigOutput + "]", 0);
		ex.printStackTrace();
		throw ex;
	}

	private final static boolean linuxIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != MACADDR_LENGTH) {
			return false;
		}
		if (!macAddressCandidate.matches(MACADDR_REG_EXP)) {
			return false;
		}
		return true;
	}

	private final static String linuxRunIfConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec(LINUX_MACADDR_EXEC);
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1) {
				break;
			}
			buffer.append((char) c);
		}
		String outputText = buffer.toString();
		stdoutStream.close();
		return outputText;
	}

	/**
	 * Windows stuff
	 */
	private final static String windowsParseMacAddress(String ipConfigOutput)
			throws ParseException {
		String localHost = null;
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ParseException(e.getMessage(), 0);
		}
		StringTokenizer tokenizer = new StringTokenizer(ipConfigOutput, "\n");
		String lastMacAddress = null;
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			// see if line contains IP address
			if (line.endsWith(localHost) && lastMacAddress != null) {
				return lastMacAddress;
			}
			// see if line contains MAC address
			int macAddressPosition = line.indexOf(":");
			if (macAddressPosition <= 0) {
				continue;
			}
			String macAddressCandidate = line.substring(macAddressPosition + 1)
					.trim();
			if (windowsIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}
		ParseException ex = new ParseException("cannot read MAC address from ["
				+ ipConfigOutput + "]", 0);
		ex.printStackTrace();
		throw ex;
	}

	private final static boolean windowsIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != MACADDR_LENGTH) {
			return false;
		}
		if (!macAddressCandidate.matches(MACADDR_REG_EXP)) {
			return false;
		}
		return true;
	}

	private final static String windowsIpConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec(WIN_MACADDR_EXEC);
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1) {
				break;
			}
			buffer.append((char) c);
		}
		String outputText = buffer.toString();
		stdoutStream.close();
		return outputText;
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	// 转化十六进制编码为字符串
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
						i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	public static String getHdSerialInfo() {

		  String line = "";
		  String HdSerial = "";//定义变量 硬盘序列号

		  try {

		   Process proces = Runtime.getRuntime().exec("cmd /c dir c:");//获取命令行参数
		   BufferedReader buffreader = new BufferedReader(
		     new InputStreamReader(proces.getInputStream()));

		   while ((line = buffreader.readLine()) != null) {

		    if (line.indexOf("卷的序列号是 ") != -1) {  //读取参数并获取硬盘序列号

		     HdSerial = line.substring(line.indexOf("卷的序列号是 ")
		       + "卷的序列号是 ".length(), line.length());
		     break;
		     // System.out.println(HdSerial);
		    }
		   }

		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }

		  return HdSerial;
		 }
	
	
	public static  void  main(String args[]){
		
		//NetworkUtils.getHdSerialInfo();
		//NetworkUtils.getLisence("22");
		NetworkUtils.getLocalCode();
//		try {
//			//NetworkUtils.getMacAddress();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
