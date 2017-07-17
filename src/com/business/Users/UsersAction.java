package com.business.Users; 


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.FontImg;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.MD5;
import com.easecom.common.util.Md5Util;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.business.Users.Users;
import com.business.Users.UsersActionForm;
import com.business.Users.UsersMgr;

public class UsersAction extends BaseAction{
	 UsersMgr mgr = new UsersMgr();

/**
	 * 得到列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			int currentPageInt = ParamUtils.getIntParameter(request,
					"currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request,
					"totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils
					.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem",
						15);
			}
			String action = ParamUtils
					.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			 
			// 接收传值
			
//			String accountStr = ParamUtils.getParameter(request, "accountStr", false);
			String starttime = ParamUtils.getParameter(request, "starttime", false);
			String endtime = ParamUtils.getParameter(request, "endtime", false);
			
			String nameStr=ParamUtils.getParameter(request, "nameStr",false);
			String phoneStr=ParamUtils.getParameter(request, "phoneStr",false);
			String emailStr=ParamUtils.getParameter(request, "emailStr", false);
			String provinceIdStr=ParamUtils.getParameter(request, "provinceIdStr",false);
			String cityIdStr=ParamUtils.getParameter(request, "cityIdStr",false);
			String areaIdStr=ParamUtils.getParameter(request, "areaIdStr",false);
			String fromType = ParamUtils.getParameter(request, "fromType", false);//用户注册来源
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
//			queryConds.add(new QueryCond("Users.account", "String", "like", accountStr));
			if(null!=fromType && !"".equals(fromType)){
				queryConds.add(new QueryCond("Users.fromType", "String", "=", fromType));
			}
			queryConds.add(new QueryCond("Users.createTime", "String", ">=", starttime));
			queryConds.add(new QueryCond("Users.createTime", "String", "<=", endtime+" 23:59:59"));
			queryConds.add(new QueryCond("Users.username","String","like",nameStr));
		    queryConds.add(new QueryCond("Users.mobile","String","like",phoneStr));
			queryConds.add(new QueryCond("Users.email","String","like",emailStr)); 
			queryConds.add(new QueryCond("Users.provinceId","String","=",provinceIdStr));
			queryConds.add(new QueryCond("Users.cityId","String","=",cityIdStr));
			queryConds.add(new QueryCond("Users.areaId","String","=",areaIdStr));
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			request.setAttribute("provinceList", Tool.getList("select provinceID,pname from province", "pname", "provinceID"));
			List<Users> userList = lc.getList();
			for (Users users : userList) {
				if(null != users.getIcon() && !"".equals(users.getIcon()) && !users.getIcon().startsWith("http")){
					if(FontImg.fontFile("http://114.55.73.238:9090/"+users.getIcon())){
						users.setIcon("http://114.55.73.238:9090/"+users.getIcon());
					}else if(FontImg.fontFile("http://114.55.87.86:8988/"+users.getIcon())){
						users.setIcon("http://114.55.87.86:8988/"+users.getIcon());
					}else if(FontImg.fontFile("http://114.55.87.86:9090/"+users.getIcon())){
						users.setIcon("http://114.55.87.86:9090/"+users.getIcon());
					}else{
						users.setIcon("");
					}
				}
			}
			lc.setList(userList);
			
			request.setAttribute("nameStr", nameStr);
			request.setAttribute("starttime", starttime);
			request.setAttribute("endtime", endtime);
			request.setAttribute("phoneStr", phoneStr);
			request.setAttribute("emailStr", emailStr);
			request.setAttribute("provinceIdStr", provinceIdStr);
			request.setAttribute("cityIdStr", cityIdStr);
			request.setAttribute("areaIdStr", areaIdStr);
			request.setAttribute("fromType", fromType);
			request.setAttribute("lc", lc);
			
			 
			return mapping.findForward("list");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}


/**
	 * 得到详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "id", true);

			request.setAttribute("UsersActionForm", mgr.view(ID));

			return mapping.findForward("view");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	 

	/**
	 * 修改信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UsersActionForm vo = (UsersActionForm) form;
		try {
			UsersActionForm po = mgr.view(vo.getId());
			po.setUsername(vo.getUsername());
			po.setMobile(vo.getMobile());
			po.setRealname(vo.getRealname());
			po.setSex(vo.getSex());
			po.setAlipayId(vo.getAlipayId());
			po.setWx(vo.getWx());
			po.setEmail(vo.getEmail());
			po.setQq(vo.getQq());
			po.setIsDisable(vo.getIsDisable());
			po.setIosOff(vo.getIosOff());
			po.setProvinceId(vo.getProvinceId());
			po.setCityId(vo.getCityId());
			po.setAreaId(vo.getAreaId());
			po.setZipcode(vo.getZipcode());
			po.setAddress(vo.getAddress());
			po.setMapy(vo.getMapy());
			po.setMapx(vo.getMapx());
			
			mgr.update(po);
			
			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"修改用户,用户名称:"+vo.getUsername(), "1", ipaddress);
			

			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}


/**
	 * 预修改信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);

			UsersActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			//获取省份城市等信息
			//获取省份城市等信息
			String provinceList = Tool.getList("select provinceID,pname from province ",
							"pname", "provinceID",vo.getProvinceId());
			String cityList = Tool.getList("select cityID,city from city where provinceID='"+vo.getProvinceId()+"'",
					"city", "cityID",vo.getCityId());
			String citysList = Tool.getList("select areaID,area from area where cityID='"+vo.getCityId()+"'",
					"area", "areaID",vo.getAreaId());
			request.setAttribute("provinceList", provinceList);
			request.setAttribute("cityList", cityList);
			request.setAttribute("citysList", citysList);
			
			request.setAttribute("UsersActionForm", vo);

			return mapping.findForward("update");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 增加信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			UsersActionForm vo = (UsersActionForm) form;			  
			vo.setCreateTime(DateUtils.getCurrDateTimeStr());
			vo.setIosOff("NO");
			//获取随机数
			Random random = new Random();
			vo.setIcon("/uploadFile/header/"+random.nextInt(20)+".png");
			Tool.testReflect_admin(vo);
			MD5 md5= MD5.getInstance();
			if(vo.getPassword()== null || vo.getPassword().equals("null") || vo.getPassword().equals("")||vo.getPassword() == ""){
				vo.setPassword(md5.getMD5ofStr("111111").toLowerCase());
			}else{
				vo.setPassword(md5.getMD5ofStr(vo.getPassword()).toLowerCase());
			}

			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"添加用户,用户名称:"+vo.getUsername(), "1", ipaddress);
			mgr.add(vo);
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 预增加信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			UsersActionForm vo = new UsersActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			//获取省份城市等信息
			request.setAttribute("provinceList", Tool.getList("select provinceID,pname from province", "pname", "provinceID"));
			request.setAttribute("UserAddressActionForm", vo);
			request.setAttribute("UsersActionForm", vo);
			
			return mapping.findForward("add");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}


	/**
	 * 删除信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				 
				String username = "admin";
				
				String name = Tool.getValue("select username from users where id='"+ids[i]+"'");
				
				Tool.AddLog(sessionContainer.getUserId(), username,
						"删除用户,用户名称:"+name, "1", ipaddress);
				
			Tool.execute("delete from users where id = '"+ids[i]+"'");
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	

	/**
	 * 查询是否已存在该号码
	 * 方法功能说明：  
	 * 创建：2015年12月22日 by Zzc   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 * @参数： @param mapping
	 * @参数： @param form
	 * @参数： @param request
	 * @参数： @param response      
	 * @return void     
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public ActionForward checkPhone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String mobile = ParamUtils.getParameter(request, "mobile", true);
		
		int count = Tool.getIntValue("select count(id) from users where mobile = '"+mobile+"'");
		if(count>0){
			 JsonUtils.outputJsonResponse(response, "result","false");
		}else{
			JsonUtils.outputJsonResponse(response, "result","true");
		}
		return null;
	}
	
	/**
	 * 异步上传图片
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateImgPath(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UsersActionForm vo = (UsersActionForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFileImage().getFileSize() > 0){
				String type = ParamUtils.getParameter(request, "type", true);
				String value = FileUploadUtil.uploadFile(vo.getFileImage(),DictionaryUtils.USER_ICON_PATH,request);
			  
			  map.put("result", true);
			  map.put("imgPath", value);
			  JsonUtils.outputJsonResponse(response, map);
			}
		} catch (Exception ex) {
			  map.put("result", false);
			  JsonUtils.outputJsonResponse(response, map);
		}
		return null;
	}
	/**
	 * 批量导入会员信息
	 * 从Excel文件中读取数据，并导入到数据库中
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ActionForward getUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取excel 文件  
		UsersActionForm fm = (UsersActionForm) form;
		FormFile formfile = fm.getFile();
		InputStream inputstream = formfile.getInputStream();
		fm.clear();// 清空
		ArrayList list = new ArrayList();
		int input = 0; // 导入记数
		String realname = null;
		String account = null;
		String mobile = null;
		String sex = null;
		String email = null;// 邮箱
		String createTime = null;// 创建时间
		String loginTime = null;// 最后一次在线时间
		String IsDisable = null;// 签收标准
		String token = null;
		String code = null;// 送达方联系人电话
		String address = null;
		String qq = null;
		try {
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
			HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
			HSSFRow hssfrow = hssfsheet.getRow(0);// 第一行

			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < hssfworkbook.getNumberOfSheets(); i++) {
				hssfsheet = hssfworkbook.getSheetAt(i);
				// 遍历该表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
				for (int j = 1; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
					hssfrow = hssfsheet.getRow(j);
					// 判断是否还存在需要导入的数据
					if (hssfrow == null) {
						System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
						break;
					}
					/** */
					/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
					if (hssfrow.getCell((short) 0) == null) {
						realname = "";
					} else if (hssfrow.getCell((short) 0).getCellType() == 0) {
						BigDecimal db = new BigDecimal(new Double(hssfrow.getCell((short) 0)
								.getNumericCellValue()));
						realname = db.toPlainString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						realname = hssfrow.getCell((short) 0).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第二列的值插入到实例中 */
					if (hssfrow.getCell((short) 15) == null) {
						account = "";
					} else if (hssfrow.getCell((short) 15).getCellType() == 0) {
						account = new Double(hssfrow.getCell((short) 15)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						account = hssfrow.getCell((short) 15).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第三列的值插入到实例中 */
					if (hssfrow.getCell((short) 16) == null) {
						mobile = "";
					} else if (hssfrow.getCell((short) 16).getCellType() == 0) {
						mobile = new Double(hssfrow.getCell((short) 16)
								.getNumericCellValue()).toString();
						System.out.println(mobile+"=======totalWeight");
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						mobile = hssfrow.getCell((short) 16).getStringCellValue()
								.trim();
						System.out.println(mobile+"=======totalWeight");
					}
					/** 将EXCEL中的第 j 行，第四列的值插入到实例中 */
					if (hssfrow.getCell((short) 17) == null) {
						sex = "";
					} else if (hssfrow.getCell((short) 17).getCellType() == 0) {
						sex = new Double(hssfrow.getCell((short) 17)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						sex = hssfrow.getCell((short) 17).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第五列的值插入到实例中 */
					if (hssfrow.getCell((short) 28) == null) {
						email = "";
					} else if (hssfrow.getCell((short) 28).getCellType() == 0) {
						email = new Double(hssfrow.getCell((short) 18)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						email = hssfrow.getCell((short) 28).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第六列的值插入到实例中 */
					if (hssfrow.getCell((short) 30) == null) {
						createTime = "";
					} else if (hssfrow.getCell((short) 30).getCellType() == 0) {
						createTime = new Double(hssfrow.getCell((short) 30)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						createTime = hssfrow.getCell((short) 30).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第七列的值插入到实例中 */
					if (hssfrow.getCell((short) 31) == null) {
						loginTime = "";
					} else if (hssfrow.getCell((short) 31).getCellType() == 0) {
						loginTime = new Double(hssfrow.getCell((short) 31)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						loginTime = hssfrow.getCell((short) 31).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第八列的值插入到实例中 */
					if (hssfrow.getCell((short) 43) == null) {
						IsDisable = "";
					} else if (hssfrow.getCell((short) 43).getCellType() == 0) {
						IsDisable = new Double(hssfrow.getCell((short) 43)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						IsDisable = hssfrow.getCell((short) 43).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第九列的值插入到实例中 */
					if (hssfrow.getCell((short) 41) == null) {
						token = "";
					} else if (hssfrow.getCell((short) 41).getCellType() == 0) {
						token = new Double(hssfrow.getCell((short) 41)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						token = hssfrow.getCell((short)41).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第十列的值插入到实例中 */
					if (hssfrow.getCell((short) 32) == null) {
						code = "";
					} else if (hssfrow.getCell((short) 32).getCellType() == 0) {
						code = new Double(hssfrow.getCell((short) 32)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						code = hssfrow.getCell((short) 32).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第十一列的值插入到实例中 */
					if (hssfrow.getCell((short) 22) == null) {
						address = "";
					} else if (hssfrow.getCell((short) 22).getCellType() == 0) {
						address = new Double(hssfrow.getCell((short) 22)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						address = hssfrow.getCell((short) 22).getStringCellValue()
								.trim();
					}
					
					/** 将EXCEL中的第 j 行，第十二列的值插入到实例中 */
					if (hssfrow.getCell((short) 25) == null) {
						qq = "";
					} else if (hssfrow.getCell((short) 25).getCellType() == 0) {
						qq = new Double(hssfrow.getCell((short) 25)
								.getNumericCellValue()).toString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						qq = hssfrow.getCell((short) 25).getStringCellValue()
								.trim();
					}
					//实例化对象
					Users user=new Users();
					user.setAccount(account);
					user.setAddress(address);
					user.setCreateTime(DateUtils.getCurrDateTimeStr());
					user.setCode(code);
					user.setEmail(email);
					user.setMobile(mobile);
					user.setQq(qq);
					user.setLoginTime(loginTime);
					user.setIsDisable(Integer.valueOf(IsDisable));
					user.setToken(token);
					String userName = Tool.getValue("select name from sys_user where id='"+sessionContainer.getUserId()+"'");
					user.setUsername(userName);
					user.setRealname(realname);
					user.setSex(Integer.parseInt(sex));
					
					// 插入对象,首先判断单号是否存在
					String l=Tool.getValue("select id from users where account='"+account+"'");
					System.out.println(l);
					if("".equals(l)||null==l){
						String id=mgr.add(user);
						if(!"".equals(id)&&null!=id){
							// 导入成功加1
							input++;
						}
					}else{
						UsersActionForm userForm = mgr.view(l);
						if(userForm!=null){
							user.setId(userForm.getId());
							user.setAccount(userForm.getAccount());
							if(null!=userForm.getUsername()){
								user.setUsername(userForm.getUsername());
							}
							if(null!=userForm.getRealname()){
								user.setRealname(userForm.getRealname());
							}
						}
						
						mgr.update(user);
						// 导入成功加1
						input++;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("total", input);
		return list(mapping, form, request, response);
		
	}
	
	
	public ActionForward province_city_area_List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SystemException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String type = ParamUtils.getParameter(request, "type");
			String Fid = ParamUtils.getParameter(request, "Fid");
			System.out.println(type);
			System.out.println(Fid);
			if(type.equals("city"))
				map.put("dataList", Tool.getList("select cityID,city from city where provinceID = '"+Fid+"'", "city", "cityID"));
			else if(type.equals("area"))
				map.put("dataList", Tool.getList("select areaID,area from area where cityID = '"+Fid+"'", "area", "areaID"));
			
			map.put("succeed","000");
			map.put("sucInfo", "操作成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put("succeed","001");
			map.put("sucInfo", "操作失败");
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}
