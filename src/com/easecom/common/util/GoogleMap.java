package com.easecom.common.util;
/**
 * 通过经纬度获取两点之间的距离
 * @author 孙晓宇
 *Aug 3, 20133:11:05 PM
 */
public class GoogleMap {
	 
	 
	  private static final double EARTH_RADIUS = 6378.137;
	  private static double rad(double d)
	  {
	     return d * Math.PI / 180.0;
	  }
	 /**
	  * @param x1 纬度
	  * @param y1 经度
	  * @param x2
	  * @param y2
	  * @return
	  */
	  public static double GetDistance(double x1, double y1, double x2, double y2)
	  {
	     double radx1 = rad(x1);
	     double radx2 = rad(x2);
	     double a = radx1 - radx2;
	     double b = rad(y1) - rad(y2);
	     double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
	      Math.cos(radx1)*Math.cos(radx2)*Math.pow(Math.sin(b/2),2)));
	     s = s * EARTH_RADIUS;
	     s = Math.round(s * 10000) / 10000;
	     return s;
	  }
	  public static double D_jw(double wd1,double jd1,double wd2,double jd2)
	  {
	      double x,y,out;
	      double PI=3.14159265;
	      double R=6.371229*1e6;

	      x=(jd2-jd1)*PI*R*Math.cos( ((wd1+wd2)/2) *PI/180)/180;
	      y=(wd2-wd1)*PI*R/180;
	      out=Math.hypot(x,y);
	      return out/1000;
	  }
	  
	  
	  
	  public static void main(String[] args) {
		  //mapx>>>>117.062268<<<<<mapy>>>>36.684014=======mapx。。。。36.683894....mapy117.062393
		  System.out.println(D_jw(117.062268,36.684014,  117.062393,36.683894));
	  }
	}