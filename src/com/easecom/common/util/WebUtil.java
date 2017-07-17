package com.easecom.common.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
                                           
public class WebUtil {
    
    /**
     * 保存在session中的验证码
     */
    public static final String VERIFIED_CODE_KEY = "cn.easecom.verified"; 
    
    public static final String REQUIRE_VERIFIED_CODE_FLAG_KEY = "cn.easecom.requireVerifiedCodeFlag"; 
    
    /**
     * 文件类型配置文件
     */
    private static Properties FILE_TYPE_PROPERTIES;
    
    /**
     * 取得响应头文件的文件类型
     * 
     * @param request
     * @param fileType
     * @return
     */
    public static String getResponseContentType(HttpServletRequest request, String fileType){
        String result = "APPLICATION/OCTET-STREAM";
        
        if(request == null || StringUtils.isEmpty(fileType))
            return result;
        
        fileType = fileType.trim().toLowerCase();
        
        try {
            Properties properties = null;
            if (FILE_TYPE_PROPERTIES == null) {
                FILE_TYPE_PROPERTIES = new Properties();
                FileInputStream fileInputStream = new FileInputStream(getWebRoot(request) + "/BM_FILE_TYPE.properties");
                FILE_TYPE_PROPERTIES.load(fileInputStream);
            } 
            
            properties = FILE_TYPE_PROPERTIES;
            
            String outPutType = (String) properties.get(fileType);
            
            if(StringUtils.isNotEmpty(outPutType)){
                result = outPutType;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    /**
     * 判断是否需要登录验证码
     * 
     * @param request
     * @return
     */
    public static boolean isNeedVerifiedCode(HttpServletRequest request){
        Object result = getSessionAttribute(request, REQUIRE_VERIFIED_CODE_FLAG_KEY);
        if(result == null)
            return false;
        
        return Boolean.parseBoolean(result.toString());
    }
    
    /**
     * 设置需要登录验证码
     * 
     * @param request
     */
    public static void setNeedVerifiedCodeFlag(HttpServletRequest request){
        setSessionAttribute(request, REQUIRE_VERIFIED_CODE_FLAG_KEY, true);
    }
    
    /**
     * 清除验证码标识
     * 
     * @param request
     */
    public static void clearVerifiedCodeFlag(HttpServletRequest request){
        removeSessionAttribute(request, REQUIRE_VERIFIED_CODE_FLAG_KEY);
    }

     
     /**
      * 清除验证码
      *
      * @param request
      */
     public static void clearVerfiedCode(HttpServletRequest request){
         removeSessionAttribute(request, VERIFIED_CODE_KEY);
     }   
    



    /**
     * 保存到session中
     *
     * @param request
     * @param key
     * @param obj
     */
    public static void setSessionAttribute(HttpServletRequest request, String key,
                                           Object obj) {
        request.getSession().setAttribute(key, obj);
    }

    /**
     * 从session中取值
     *
     * @param request
     * @param key
     * @return
     */
    public static Object getSessionAttribute(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    /**
     * 从session中移除值
     *
     * @param request
     * @param key
     */
    public static void removeSessionAttribute(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }


    /**
     * 取得request中所有的参数名
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> getRequestParamNames(HttpServletRequest request) {

        Enumeration<String> paramNameEnum = request.getParameterNames();
        List<String> result = new ArrayList<String>();

        //先将Enumeration中参数转成LIST
        while (paramNameEnum.hasMoreElements()) {
            result.add(paramNameEnum.nextElement().trim());
        }

        return result;
    }
    
    /**
     * 取得请求中的所有参数的键值对
     * 
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParamNameValueMap(HttpServletRequest request) {
        List<String> paramNameList = getRequestParamNames(request);
        
        Map<String, String> result = new HashMap<String, String>();
        
        if(paramNameList == null || paramNameList.size() == 0)
            return result;
        
        for(String paramName : paramNameList){
            result.put(paramName, request.getParameter(paramName));
        }
        
        
        return result;
    }

    /**
     * 判断某个参数名称是否在request中
     *
     * @param request
     * @param paramName
     * @return
     */
    public static boolean hasParamName(HttpServletRequest request, String paramName) {

        if (StringUtils.isEmpty(paramName))
            return false;

        List<String> paramNameList = getRequestParamNames(request);

        return paramNameList.contains(paramName);
    }

    /**
     * 取得Web的根路径
     *
     * @param request
     * @return
     */
    public static String getWebRoot(HttpServletRequest request) {
        //这里在最后添加"/"，如果不加的话，在linux环境下会有问题，会跟后面的目录连在一起
        return request.getSession().getServletContext().getRealPath("/").replace('\\', '/') + "/";
    }


    /**
     *   是否是当前的服务器URL
     * @param url
     * @param serverName
     * @param serverPort
     * @param contextPath
     * @return
     */
    private static boolean isCurrServerUrl(String url, String serverName, int serverPort, String contextPath) {
         if(url == null){
             return false;
         }
         if(serverPort != 80 && url.indexOf(":"+serverPort) == -1){
              return false;
         }
         if(!"/".equals(contextPath) && url.indexOf(contextPath) == -1){
             return false;
         }
         String currUrl = "";
         currUrl ="http://"+serverName;
         if(serverPort != 80){
             currUrl +=":"+serverPort;
         }
         if(!"/".equals(contextPath)){
             currUrl +=contextPath;
         }
         return url.indexOf(currUrl) > -1;
    }
    
    /*
	 * 得到请求的参数 isEmptyString=true:转化null为""
	 */
	public static String getRequestParameter(HttpServletRequest request,
			String paramName, boolean isEmptyString) {
		String param = request.getParameter(paramName);
		if (param != null) {
			return param;
		} else {
			if (isEmptyString)
				return "";
			else
				return null;
		}
	}

	public static String getRequestParameter(HttpServletRequest request,
			String paramName) {
		return getRequestParameter(request, paramName, true);
	}

	public static String getRequestParameter(HttpServletRequest request,
			String paramName, String defaultString) {
		String str = getRequestParameter(request, paramName, true);
		if (str.equals(""))
			str = defaultString;
		return str;
	}
}
