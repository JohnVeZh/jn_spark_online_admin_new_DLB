package com.business.BookActivationCode;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.business.MatchedPapers.MatchedPapers;
import com.business.MatchedPapers.MatchedPapersActionForm;
import com.business.MatchedPapersTopicWriting.MatchedPapersTopicWriting;
import com.easecom.common.util.*;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.system.exception.SystemException;
import com.business.BookActivationCode.BookActivationCode;
import com.business.BookActivationCode.BookActivationCodeActionForm;
import com.business.BookActivationCode.BookActivationCodeMgr;

public class BookActivationCodeAction extends BaseAction {
	BookActivationCodeMgr mgr = new BookActivationCodeMgr();

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
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
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
			String useRegion = ParamUtils.getParameter(request, "region",
					false);
			String userId = ParamUtils.getParameter(request, "userid", false);
			String productId = ParamUtils.getParameter(request, "productid",
					false);
			String createtime = ParamUtils.getParameter(request, "creatime",
					false);// 开始时间
			String useTime = ParamUtils.getParameter(request, "usetime", false);
			String code = ParamUtils.getParameter(request, "code");
			String codeMd5 = "";
			if(null != code && !code.equals("")){
				codeMd5 = MD5.getInstance().getMD5ofStr(code);
			}
			// 设置查询条件
			Collection queryConds = new ArrayList();

			queryConds.add(new QueryCond("BookActivationCode.useRegion",
					"String", "=", useRegion));
			queryConds.add(new QueryCond("BookActivationCode.userId", "String",
					"=", userId));
			queryConds.add(new QueryCond("BookActivationCode.productId",
					"String", "=", productId));
			queryConds.add(new QueryCond("BookActivationCode.createtime",
					"String", "like", createtime));
			queryConds.add(new QueryCond("BookActivationCode.useTime",
					"String", "like", useTime));
			queryConds.add(new QueryCond("BookActivationCode.isDel", "number", "=", "0"));
			queryConds.add(new QueryCond("BookActivationCode.code", "String", "=", codeMd5));
			// queryConds.add(new QueryCond("user.name", "String", "like",
			// name));
			// queryConds.add(new QueryCond("user.loginName", "String", "=",
			// loginName));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("region", useRegion);
			request.setAttribute("userid", userId);
			request.setAttribute("productid", productId);
			request.setAttribute("createtime", createtime);
			request.setAttribute("usetime", useTime);
			request.setAttribute("code", code);
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
			BookActivationCodeActionForm vo = mgr.view(ID);
			request.setAttribute("userName", Tool.getValue("select username from users where id='" + vo.getUserId() + "'"));
			request.setAttribute("sysuser", Tool.getValue("select name from sys_user where id='" + vo.getSysUserId() + "'"));
			request.setAttribute("title", Tool.getValue("select p_name from product where id='" + vo.getProductId() + "'"));
			request.setAttribute("BookActivationCodeActionForm", vo);

			return mapping.findForward("view");
		} catch (Exception ex) {

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

		BookActivationCodeActionForm vo = (BookActivationCodeActionForm) form;
		try {
			mgr.update(vo);
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"修改激活码,激活码:" + vo.getCode(), "1", ipaddress);


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

			BookActivationCodeActionForm vo = mgr.view(ID);
			// vo.setPasswordold(vo.getPassword());

			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (sessionContainer == null) {
				sessionContainer = new SessionContainer();
			}

			request.setAttribute("BookActivationCodeActionForm", vo);

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
			BookActivationCodeActionForm vo = (BookActivationCodeActionForm) form;
			// vo.setType("1");

			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"添加激活码,激活码:" + vo.getCode(), "1", ipaddress);

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

			BookActivationCodeActionForm vo = new BookActivationCodeActionForm();
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (sessionContainer == null) {
				sessionContainer = new SessionContainer();
			}

			request.setAttribute("BookActivationCodeActionForm", vo);

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
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (sessionContainer == null) {
				sessionContainer = new SessionContainer();
			}

			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {

				Tool.execute("update book_activation_code set is_del = 1 where id = '"
						+ ids[i] + "'");
				// 获取ip地址
				String ipaddress = IpAddressUtil.getIpAddr(request);
				//添加操作记录
				Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "删除激活码,Id:" + ids[i], "1", ipaddress);


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
	 * 生成激活码
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jsonAjax(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) {
		int digits = Integer.parseInt(ParamUtils.getParameter(request,
				"digits", true));
		int count = Integer.parseInt(ParamUtils.getParameter(request, "count",
				true));
		String productId = ParamUtils.getParameter(request, "productId", true);
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (sessionContainer == null) {
			sessionContainer = new SessionContainer();
		}
		for (int i = 0; i < count; i = i) {
			String code = RandomCode.getRandomChar(digits);
			//System.out.println(code);
			try {
				BookActivationCode vo = (BookActivationCode) mgr
						.getObjectByHql("from BookActivationCode where code='"
								+ code + "'");
				if (vo != null) {
					continue;
				} else {
					i++;
					vo = new BookActivationCode();
					vo.setSysUserId(sessionContainer.getUserId());
					vo.setProductId(productId);
					vo.setCode(code);
					vo.setCreatetime(DateUtils.getCurrDateTimeStr());
					Tool.testReflect_admin(vo);
					mgr.add(vo);
				}
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
				WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错",
						"返回", "javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
		}
		return list(mapping, form, request, response);
	}

	/**
	 * 导出全部数据
	 */
	public void Findexcl(ActionMapping mapping, ActionForm form,
						 HttpServletRequest request, HttpServletResponse response) {
		// String useRegion= ParamUtils.getParameter(request, "useRegion",
		// false);
		// String userId=ParamUtils.getParameter(request, "userId",false);
		String productId = ParamUtils.getParameter(request, "productId", false);
		// String
		// createtime=ParamUtils.getParameter(request,"createtime",false);//开始时间
		// String useTime=ParamUtils.getParameter(request,"useTime",false);
		String codes = ParamUtils.getParameter(request, "code", false);

		String type = ParamUtils.getParameter(request, "type", true);

		Map m = new HashMap();
		List<BookActivationCodeActionForm> list = new ArrayList<BookActivationCodeActionForm>();
		String excel = DateUtils.getCurrDateStr_()
				+ new Random().nextInt(10000);
		String sheetName = excel + ".xls";
		String path = "/uploadFile/orderxls/dg/" + DateUtils.getCurrDateS()
				+ "/";
		String logoRealPathDir = request.getSession().getServletContext()
				.getRealPath(path);
		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();

		try {
			Collection queryConds = new ArrayList();
			// 必要条件
			if (null != productId && !"".equals(productId)
					&& "productId".equals(productId)) {
				if (null != codes && !"".equals(codes)) {
					this.getCollection(queryConds, "BookActivationCode",
							"code", "String", "in", codes);
				}
			}
			String bookcodeHql = " order by createtime asc";

			List<BookActivationCode> bookCodeList = mgr.mlist(queryConds,
					bookcodeHql);
			if (null != productId && !"".equals(productId)
					&& "productId".equals(productId)) {
				if (null == codes && "".equals(codes)) {
					bookCodeList = new ArrayList();
				}
			}
			for (BookActivationCode bookcode : bookCodeList) {
				// if(orders.getIsExport()==0){
				BookActivationCodeActionForm vo = new BookActivationCodeActionForm();
				super.copyProperties(vo, bookcode);

				list.add(vo);
				// }
			}
			if (type.equals("0"))
				MyExcelTest.excleOrderSta(list, logoRealPathDir + "/"
						+ sheetName);
			else
				MyExcelTest.excleOrder(list, logoRealPathDir + "/" + sheetName);
			// 导出成功后修改状态
			for (BookActivationCode bookcode : bookCodeList) {
				if (bookcode.getIsExport() == 0) {
					// 修改是否导出状态
					bookcode.setIsExport(1);
					mgr.update(bookcode);
				}
			}
			m.put("result", true);
			m.put("path", path + sheetName);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("result", false);
		}
		JsonUtils.outputJsonResponse(response, m);
	}

	/**
	 * 导出Excel，导出选中的数据
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void excl(ActionMapping mapping, ActionForm form,
					 HttpServletRequest request, HttpServletResponse response) {
		Map m = new HashMap();
		String[] ids = ParamUtils.getParameterValues(request, "id", true);
		String type = ParamUtils.getParameter(request, "type", true);
		List<BookActivationCodeActionForm> list = new ArrayList<BookActivationCodeActionForm>();
		String excel = DateUtils.getCurrDateStr_()
				+ new Random().nextInt(10000);
		String sheetName = excel + ".xls";
		String path = "/uploadFile/orderxls/dg/" + DateUtils.getCurrDateS()
				+ "/";
		String logoRealPathDir = request.getSession().getServletContext()
				.getRealPath(path);
		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();
		try {
			for (int i = 0; i < ids.length; i++) {
				BookActivationCodeActionForm bookCode = mgr.view(ids[i]);
				if (null != bookCode) {
					list.add(bookCode);
				}
			}
			if (type.equals("0"))
				MyExcelTest.excleOrderSta(list, logoRealPathDir + "/"
						+ sheetName);
			else
				MyExcelTest.excleOrder(list, logoRealPathDir + "/" + sheetName);
			// 导出成功后再修改状态
			for (int i = 0; i < ids.length; i++) {
				BookActivationCodeActionForm orders = mgr.view(ids[i]);
				if (null != orders) {
					if (orders.getIsExport() == 0) {
						// 修改是否导出状态
						orders.setIsExport(1);
						mgr.update(orders);
					}
				}
			}
			m.put("result", true);
			m.put("path", path + sheetName);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("result", false);
		}
		JsonUtils.outputJsonResponse(response, m);
	}

	private Collection getCollection(Collection queryConds, String className,
									 String fieldName, String dataType, String symbol, String fieldValue) {
		if (!"".equals(fieldName) && null != fieldName
				&& !"".equals(fieldValue) && null != fieldValue) {
			queryConds.add(new QueryCond(className + "." + fieldName, dataType,
					symbol, fieldValue));
			return queryConds;
		} else {
			return null;
		}
	}


	/**
	 *
	 * 从Excel文件中读取数据，并导入到数据库中,四级激活码
	 */
	public ActionForward fourCodeByExcel(ActionMapping actionMapping,
								  ActionForm actionForm, HttpServletRequest request,
								  HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取txt 文件,四六级激活码不在新建实体，直接套用BookActivationCodeActionForm，批次字段直接以useTime字段进行传参
		BookActivationCodeActionForm bc = (BookActivationCodeActionForm) actionForm;
		String batch = bc.getUseTime();
		FormFile formfile = bc.getFormfile();
		InputStream inputstream = formfile.getInputStream();
		bc.clear();;// 清空
		ArrayList list = new ArrayList();
		int totalCount = 0;//处理总数
		int insertCount = 0;//插入总数
		int count = 0;//确定1000条插入一次
		String code = ""; //激活码
		String insertSql = "";
		String id = "";
		FileOutputStream fot = null;
		PrintWriter pw = null;
		InputStreamReader read = null;
		List<String> existList = new ArrayList<String>();//已插入字符串
		List<String> errorList = new ArrayList<String>();//错误集合
		List ls = mgr.SQLQuery("select code from bookcode4 where batch_time = '"+batch+"'");
		String path = this.getServlet().getServletContext().getRealPath("/");
		try {
			File fl1 = new File(path+"/error");
			File fl2 = new File(path+"/error/error4.txt");
			if (!fl1.exists()) {
				fl1.mkdirs();
			}
			if(fl2.exists()){
				fl2.delete();
			}
			fot = new FileOutputStream(fl2);
			pw = new PrintWriter(new OutputStreamWriter(fot,"utf-8"),true);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(ls.size()>0){
			for(int i = 0;i<ls.size();i++){
				Map map = (Map) ls.get(i);
				existList.add(map.get("code").toString());
			}
		}
		try {
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			XSSFWorkbook xssfworkbook = new XSSFWorkbook(inputstream);
			XSSFSheet xssfsheet = xssfworkbook.getSheetAt(0);// 第一个工作表
			XSSFRow xssfrow = xssfsheet.getRow(0);// 第一行
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < xssfworkbook.getNumberOfSheets(); i++) {
				xssfsheet = xssfworkbook.getSheetAt(i);
				id = "";
				for (int j = 0; j < xssfsheet.getPhysicalNumberOfRows(); j++) {
					totalCount++;
					xssfrow = xssfsheet.getRow(j);
					// 判断是否还存在需要导入的数据
					if (xssfrow == null) {
						System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
						break;
					}
					/** */
					/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
					if (xssfrow.getCell((short) 0) != null) {
						code = xssfrow.getCell((short) 0).getStringCellValue().trim();
						errorList.add(code);
					}
					code = code.trim();
                    code = MD5.getInstance().getMD5ofStr(code);
					//插入激活码，每1000条存一次
					if (count == 0) {
						insertSql = "insert into bookcode4(code,inuse,batch_time) values";
					}
					if (existList.size() > 0) {
						if (!existList.contains(code)) {
							insertSql += " ('" + code + "','0','" + batch + "'),";
							count++;
							insertCount++;
						}
					} else {
						insertSql += " ('" + code + "','0','" + batch + "'),";
						count++;
						insertCount++;
					}


					if (count == 1000) {
						if (insertSql.endsWith(",")) {
							insertSql = insertSql.substring(0, insertSql.length() - 1) + ";";
						}
						try {
							int result = mgr.SQLExecute(insertSql);
							pw.println("当前已处理完成第"+totalCount+"行");
						} catch (Exception ex) {
							insertCount = insertCount - count;
							if (null != pw) {
								pw.println("处理第"+totalCount+"行出错，以下数据插入失败：失败原因"+ex.getMessage());
								for (int error = 0; error < errorList.size(); error++) {
									pw.println(errorList.get(error));
								}
								errorList.clear();
							}
						}
						count = 0;
						insertSql = "";
					}
				}
			}
				if(count>0&& count<1000){
					if (insertSql.endsWith(",")) {
						insertSql = insertSql.substring(0, insertSql.length() - 1) + ";";
					}
					try {
						int result = mgr.SQLExecute(insertSql);
					} catch (Exception ex) {
						insertCount = insertCount-count;
						if(null != pw){
							pw.println("处理第"+totalCount+"行出错，以下数据插入失败：失败原因"+ex.getMessage());
							for(int error= 0 ;error<errorList.size();error++){
								pw.println(errorList.get(error));
							}
							errorList.clear();
						}
					}
					count = 0;
					insertSql = "";
				}
				if(null != pw){
					pw.println("共处理数据"+totalCount+"条，除去重复共成功导入"+insertCount+"条");
					pw.close();
				}
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
			}
			read.close();
			return null;
		}

	/**
	 * 从txt 读取文件 去空格
	 */
	public ActionForward fourCodeByTxt(ActionMapping actionMapping,
									   ActionForm actionForm, HttpServletRequest request,
									   HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取txt 文件,四六级激活码不在新建实体，直接套用BookActivationCodeActionForm，批次字段直接以useTime字段进行传参
		BookActivationCodeActionForm bc = (BookActivationCodeActionForm) actionForm;
		String batch = bc.getUseTime();
		FormFile formfile = bc.getFormfile();
		InputStream inputstream = formfile.getInputStream();
		bc.clear();;// 清空
		ArrayList list = new ArrayList();
		int totalCount = 0;//处理总数
		int insertCount = 0;//插入总数
		int count = 0;//确定1000条插入一次
		String code = ""; //激活码
		String insertSql = "";
		String id = "";
		FileOutputStream fot = null;
		PrintWriter pw = null;
		InputStreamReader read = null;
		List<String> existList = new ArrayList<String>();//已插入字符串
		List<String> errorList = new ArrayList<String>();//错误集合
		List ls = mgr.SQLQuery("select code from bookcode4 where batch_time = '"+batch+"'");
		String path = this.getServlet().getServletContext().getRealPath("/");
		try {
			File fl1 = new File(path+"/error");
			File fl2 = new File(path+"/error/error4.txt");
			if (!fl1.exists()) {
				fl1.mkdirs();
			}
			if(fl2.exists()){
				fl2.delete();
			}
			fot = new FileOutputStream(fl2);
			pw = new PrintWriter(new OutputStreamWriter(fot,"utf-8"),true);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(ls.size()>0){
			for(int i = 0;i<ls.size();i++){
				Map map = (Map) ls.get(i);
				existList.add(map.get("code").toString());
			}
		}
		try {
			String encoding = "GBK";
			read = new InputStreamReader(inputstream, encoding);//考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			while ((code = bufferedReader.readLine()) != null && !code.equals("")) {
				totalCount++;
				code = code.trim();
				errorList.add(code);
                code = MD5.getInstance().getMD5ofStr(code);
				//插入激活码，每1000条存一次
				if (count == 0) {
					insertSql = "insert into bookcode4(code,inuse,batch_time) values";
				}
				if( existList.size()>0){
					if(!existList.contains(code)){
						insertSql += " ('" + code + "','0','" + batch + "'),";
						count++;
						insertCount++;
					}
				}else{
						insertSql += " ('" + code + "','0','" + batch + "'),";
						count++;
						insertCount++;
				}


				if (count == 1000) {
					if (insertSql.endsWith(",")) {
						insertSql = insertSql.substring(0, insertSql.length() - 1) + ";";
					}
					try {
						int result = mgr.SQLExecute(insertSql);
						pw.println("当前已处理完成第"+totalCount+"行");
					} catch (Exception ex) {
						insertCount = insertCount-count;
						if(null != pw){
							pw.println("处理第"+totalCount+"行出错，以下数据插入失败：失败原因"+ex.getMessage());
							for(int error= 0 ;error<errorList.size();error++){
								pw.println(errorList.get(error));
							}
							errorList.clear();
						}
					}
					count = 0;
					insertSql = "";
				}
			}
			if(count>0&& count<1000){
				if (insertSql.endsWith(",")) {
					insertSql = insertSql.substring(0, insertSql.length() - 1) + ";";
				}
				try {
					int result = mgr.SQLExecute(insertSql);
				} catch (Exception ex) {
					insertCount = insertCount-count;
					if(null != pw){
						pw.println("处理第"+totalCount+"行出错，以下数据插入失败：失败原因"+ex.getMessage());
						for(int error= 0 ;error<errorList.size();error++){
							pw.println(errorList.get(error));
						}
						errorList.clear();
					}
				}
				count = 0;
				insertSql = "";
			}
			if(null != pw){
				pw.println("共处理数据"+totalCount+"条，除去重复共成功导入"+insertCount+"条");
				pw.close();
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		read.close();
		return null;
	}
	/**
	 *
	 * 从Excel文件中读取数据，并导入到数据库中,四级激活码
	 */
	public ActionForward sixCodeByExcel(ActionMapping actionMapping,
										 ActionForm actionForm, HttpServletRequest request,
										 HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取txt 文件,四六级激活码不在新建实体，直接套用BookActivationCodeActionForm，批次字段直接以useTime字段进行传参
		BookActivationCodeActionForm bc = (BookActivationCodeActionForm) actionForm;
		String batch = bc.getUseTime();
		FormFile formfile = bc.getFormfile();
		InputStream inputstream = formfile.getInputStream();
		bc.clear();;// 清空
		ArrayList list = new ArrayList();
		int totalCount = 0;//处理总数
		int insertCount = 0;//插入总数
		int count = 0;//确定1000条插入一次
		String code = ""; //激活码
		String insertSql = "";
		String id = "";
		FileOutputStream fot = null;
		PrintWriter pw = null;
		InputStreamReader read = null;
		List<String> existList = new ArrayList<String>();//已插入字符串
		List<String> errorList = new ArrayList<String>();//错误集合
		List ls = mgr.SQLQuery("select code from bookcode6 where batch_time = '"+batch+"'");
		String path = this.getServlet().getServletContext().getRealPath("/");
		try {
			File fl1 = new File(path+"/error");
			File fl2 = new File(path+"/error/error6.txt");
			if (!fl1.exists()) {
				fl1.mkdirs();
			}
			if(fl2.exists()){
				fl2.delete();
			}
			fot = new FileOutputStream(fl2);
			pw = new PrintWriter(new OutputStreamWriter(fot,"utf-8"),true);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(ls.size()>0){
			for(int i = 0;i<ls.size();i++){
				Map map = (Map) ls.get(i);
				existList.add(map.get("code").toString());
			}
		}
		try {
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			XSSFWorkbook xssfworkbook = new XSSFWorkbook(inputstream);
			XSSFSheet xssfsheet = xssfworkbook.getSheetAt(0);// 第一个工作表
			XSSFRow xssfrow = xssfsheet.getRow(0);// 第一行
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < xssfworkbook.getNumberOfSheets(); i++) {
				xssfsheet = xssfworkbook.getSheetAt(i);
				id = "";
				for (int j = 0; j < xssfsheet.getPhysicalNumberOfRows(); j++) {
					totalCount++;
					xssfrow = xssfsheet.getRow(j);
					// 判断是否还存在需要导入的数据
					if (xssfrow == null) {
						System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
						break;
					}
					/** */
					/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
					if (xssfrow.getCell((short) 0) != null) {
						code = xssfrow.getCell((short) 0).getStringCellValue().trim();
						errorList.add(code);
					}
					code = code.trim();
                    code = MD5.getInstance().getMD5ofStr(code);
					//插入激活码，每1000条存一次
					if (count == 0) {
						insertSql = "insert into bookcode6(code,inuse,batch_time) values";
					}
					if (existList.size() > 0) {
						if (!existList.contains(code)) {
							insertSql += " ('" + code + "','0','" + batch + "'),";
							count++;
							insertCount++;
						}
					} else {
						insertSql += " ('" + code + "','0','" + batch + "'),";
						count++;
						insertCount++;
					}


					if (count == 1000) {
						if (insertSql.endsWith(",")) {
							insertSql = insertSql.substring(0, insertSql.length() - 1) + ";";
						}
						try {
							int result = mgr.SQLExecute(insertSql);
							pw.println("当前已处理完成第"+totalCount+"行");
						} catch (Exception ex) {
							insertCount = insertCount - count;
							if (null != pw) {
								pw.println("处理第"+totalCount+"行出错，以下数据插入失败：失败原因"+ex.getMessage());
								for (int error = 0; error < errorList.size(); error++) {
									pw.println(errorList.get(error));
								}
								errorList.clear();
							}
						}
						count = 0;
						insertSql = "";
					}
				}
			}
			if(count>0&& count<1000){
				if (insertSql.endsWith(",")) {
					insertSql = insertSql.substring(0, insertSql.length() - 1) + ";";
				}
				try {
					int result = mgr.SQLExecute(insertSql);
				} catch (Exception ex) {
					insertCount = insertCount-count;
					if(null != pw){
						pw.println("处理第"+totalCount+"行出错，以下数据插入失败：失败原因"+ex.getMessage());
						for(int error= 0 ;error<errorList.size();error++){
							pw.println(errorList.get(error));
						}
						errorList.clear();
					}
				}
				count = 0;
				insertSql = "";
			}
			if(null != pw){
				pw.println("共处理数据"+totalCount+"条，除去重复共成功导入"+insertCount+"条");
				pw.close();
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		read.close();
		return null;
	}

	/**
	 * 从txt 读取文件 去空格
	 */
	public ActionForward sixCodeByTxt(ActionMapping actionMapping,
									   ActionForm actionForm, HttpServletRequest request,
									   HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取txt 文件,四六级激活码不在新建实体，直接套用BookActivationCodeActionForm，批次字段直接以useTime字段进行传参
		BookActivationCodeActionForm bc = (BookActivationCodeActionForm) actionForm;
		String batch = bc.getUseTime();
		FormFile formfile = bc.getFormfile();
		InputStream inputstream = formfile.getInputStream();
		bc.clear();;// 清空
		ArrayList list = new ArrayList();
		int totalCount = 0;//处理总数
		int insertCount = 0;//插入总数
		int count = 0;//确定1000条插入一次
		String code = ""; //激活码
		String insertSql = "";
		String id = "";
		FileOutputStream fot = null;
		PrintWriter pw = null;
		InputStreamReader read = null;
		List<String> existList = new ArrayList<String>();//已插入字符串
		List<String> errorList = new ArrayList<String>();//错误集合
		List ls = mgr.SQLQuery("select code from bookcode6 where batch_time = '"+batch+"'");
		String path = this.getServlet().getServletContext().getRealPath("/");
		try {
			File fl1 = new File(path+"/error");
			File fl2 = new File(path+"/error/error6.txt");
			if (!fl1.exists()) {
				fl1.mkdirs();
			}
			if(fl2.exists()){
				fl2.delete();
			}
			fot = new FileOutputStream(fl2);
			pw = new PrintWriter(new OutputStreamWriter(fot,"utf-8"),true);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(ls.size()>0){
			for(int i = 0;i<ls.size();i++){
				Map map = (Map) ls.get(i);
				existList.add(map.get("code").toString());
			}
		}
		try {
			String encoding = "GBK";
			read = new InputStreamReader(inputstream, encoding);//考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			while ((code = bufferedReader.readLine()) != null && !code.equals("")) {
				totalCount++;
				code = code.trim();
				errorList.add(code);
				code = MD5.getInstance().getMD5ofStr(code);
				//插入激活码，每1000条存一次
				if (count == 0) {
					insertSql = "insert into bookcode6(code,inuse,batch_time) values";
				}
				if( existList.size()>0){
					if(!existList.contains(code)){
						insertSql += " ('" + code + "','0','" + batch + "'),";
						count++;
						insertCount++;
					}
				}else{
					insertSql += " ('" + code + "','0','" + batch + "'),";
					count++;
					insertCount++;
				}


				if (count == 1000) {
					if (insertSql.endsWith(",")) {
						insertSql = insertSql.substring(0, insertSql.length() - 1) + ";";
					}
					try {
						int result = mgr.SQLExecute(insertSql);
						pw.println("当前已处理完成第"+totalCount+"行");
					} catch (Exception ex) {
						insertCount = insertCount-count;
						if(null != pw){
							pw.println("处理第"+totalCount+"行出错，以下数据插入失败：失败原因"+ex.getMessage());
							for(int error= 0 ;error<errorList.size();error++){
								pw.println(errorList.get(error));
							}
							errorList.clear();
						}
					}
					count = 0;
					insertSql = "";
				}
			}
			if(count>0&& count<1000){
				if (insertSql.endsWith(",")) {
					insertSql = insertSql.substring(0, insertSql.length() - 1) + ";";
				}
				try {
					int result = mgr.SQLExecute(insertSql);
				} catch (Exception ex) {
					insertCount = insertCount-count;
					if(null != pw){
						pw.println("处理第"+totalCount+"行出错，以下数据插入失败：失败原因"+ex.getMessage());
						for(int error= 0 ;error<errorList.size();error++){
							pw.println(errorList.get(error));
						}
						errorList.clear();
					}
				}
				count = 0;
				insertSql = "";
			}
			if(null != pw){
				pw.println("共处理数据"+totalCount+"条，除去重复共成功导入"+insertCount+"条");
				pw.close();
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		read.close();
		return null;
	}
}
