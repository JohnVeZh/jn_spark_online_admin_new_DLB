package com.easecom.common.util;


import java.text.NumberFormat;


/**
 * @功能描述：	补零操作工具类
 *
 * @作者：zhangpj		@创建时间：2015年3月18日
 */
public class AddZero {
	  public AddZero()
	  {

	  }
	  

	/**
	 * 描述:		在前面补零
	 *
	 * @date 	2014年7月22日 
	 * @author	zhangpj
	 * @param testStr
	 * @param strLength
	 * @return
	 */
	public static String addZero(String testStr,int strLength)
	  {
	    String str = testStr.trim();
	    while (str.length() < strLength)
	    {
	      str = "0" + str;
	    }
	    return str;
	  }
	  //在前面补零
	  public static String addZero(int testInt,int strLength)
	  {
	    String str = String.valueOf(testInt);
	    while (str.length() < strLength)
	    {
	      str = "0" + str;
	    }
	    return str;
	  }
	  //小数点后补零
	  public static String strFormat(String inStr,int strLength)
	  {
	    String outStr = inStr.trim();

	    if (outStr.equals(""))
	    {
	      outStr = "0.00";
	    }

	    if (strLength <= 0)
	    {
	      return outStr;
	    }

	    if (outStr.lastIndexOf(".") == -1)
	    {
	      outStr = outStr + ".";
	    }

	    int len = outStr.lastIndexOf(".") + strLength;

	    int i = outStr.length();

	    if (i > len)
	    {
	      return outStr.substring(0,len+1);
	    }

	    while (i <= len)
	    {
	      outStr += "0";
	      i = outStr.length();
	    }

	    return outStr;
	  }

	  //小数点后零补齐(若小数点后面有多于两位的小数,将舍掉多于的小数,不会四舍五入)
	  public static String format(String inStr,int strLength)
	  {
	    String outStr = strFormat(inStr,strLength);

	    return outStr;
	  }

	  //小数点后零补齐
	  public static String format(double inStr,int strLength)
	  {
	    String outStr = "" + inStr;
	    outStr = strFormat(outStr,strLength);

	    return outStr;
	  }

	  //小数点后零补齐
	  public static String format(float inStr,int strLength)
	  {
	    String outStr = "" + inStr;
	    outStr = strFormat(outStr,strLength);

	    return outStr;

	  }
	  //小数点后零补齐
	  public static String format(int inStr,int strLength)
	  {
	    String outStr = "" + inStr;
	    outStr = strFormat(outStr,strLength);

	    return outStr;

	  }

	  //小数点后零补齐
	  public static String format(long inStr,int strLength)
	  {
	    String outStr = "" + inStr;
	    outStr = strFormat(outStr,strLength);

	    return outStr;

	  }

	  //格式化数据:在数字中加入分隔符（，号）并取得指定位数的小数(自动四舍五入)
	  public static String moneyFormat(double tonosplitnum,int strLength)
	  {
	    NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(strLength);

	    return nf.format(tonosplitnum);
	  }

	  public static String moneyFormat(float tonosplitnum,int strLength)
	  {
	    NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(strLength);

	    return nf.format(tonosplitnum);
	  }


	  public static String moneyFormat(long tonosplitnum,int strLength)
	  {
	    NumberFormat nf = NumberFormat.getInstance();

	    return nf.format(tonosplitnum);
	  }

	  public static String moneyFormat(String tonosplitnum,int strLength)
	  {
	    try
	    {
	      if(tonosplitnum.equals(""))
	      {
	        tonosplitnum = "0.00";
	      }
	      NumberFormat nf = NumberFormat.getInstance();
	      nf.setMaximumFractionDigits(strLength);

	      return nf.format(Double.parseDouble(tonosplitnum));
	    }
	    catch(Exception e)
	    {
	      return "Err:"+tonosplitnum;
	    }
	  }

		//保留指定位数小数(四舍五入)
		public static String formatRound(String inStr,int strLength)
		{
			double outStrD = 0.0;
			String outStrS = "";
			if(!inStr.equals(""))
			{
				outStrD = Double.parseDouble(inStr);
			}
			outStrS = round(outStrD,2);
			String outStr = strFormat(outStrS,strLength);

			return outStr;
		}

		//保留指定位数小数(四舍五入)
		public static String formatRound(double inStr,int strLength)
		{
			String outStrS = "";
			outStrS = round(inStr,2);
			String outStr = "" + outStrS;
			outStr = strFormat(outStr,strLength);

			return outStr;
	  }

	  //保留指定位数小数(四舍五入)
			public static String formatRound(float inStr,int strLength)
			{
				String outStrS = "";
				outStrS = round(inStr,2);
				String outStr = "" + outStrS;
				outStr = strFormat(outStr,strLength);

				return outStr;
	  }

	  //四舍五入
	  public static String round(double source,double n)
	  {
	    double des = 0;
	    des = Math.round(Math.pow(10,n) * source) / Math.pow(10,n);
	    return "" + des;

	  }

	  public static double roundDou(double source,double n)
	  {
	    double des = 0;
	    des = Math.round(Math.pow(10,n) * source) / Math.pow(10,n);
	    return des;
	  }
		public static double roundDou(float source,double n)
		{
			double des = 0;
			des = Math.round(Math.pow(10,n) * source) / Math.pow(10,n);
			return des;
		}
	  //求和
	  public static double addSum(String num1,String num2)
	  {
			double dNum1 = 0;
			double dNum2 = 0;
			double dSum = 0;
			if(!num1.equals(""))
			{
				dNum1 = Double.parseDouble(num1);
			}
			if(!num2.equals(""))
			{
				dNum2 = Double.parseDouble(num2);
			}
			dSum = dNum1 + dNum2;
			return dSum;
		}
}

