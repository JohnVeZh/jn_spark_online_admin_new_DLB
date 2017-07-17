package com.business.MatchedPapersTopicHearingType; 


import java.io.File;
import java.io.FileInputStream;
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
import com.easecom.common.util.AddZero;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.MD5;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.business.MatchedPapers.MatchedPapers;
import com.business.MatchedPapers.MatchedPapersActionForm;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingType;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingTypeActionForm;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingTypeMgr;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyric;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyricActionForm;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyricMgr;
import com.business.MatchedPapersTopicTranslate.MatchedPapersTopicTranslate;
import com.business.NetworkVideo.NetworkVideo;
import com.business.ProductOrder.ProductOrder;
import com.business.ProductOrderDetails.ProductOrderDetails;
import com.business.Users.Users;

public class MatchedPapersTopicHearingTypeAction extends BaseAction{
	 MatchedPapersTopicHearingTypeMgr mgr = new MatchedPapersTopicHearingTypeMgr();
	 MatchedPapersTopicLyricMgr mptlMgr = new MatchedPapersTopicLyricMgr();

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
			String mpthId = ParamUtils.getParameter(request, "mpthId", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("MatchedPapersTopicHearingType.mpthId", "String", "=", mpthId));
			queryConds.add(new QueryCond("MatchedPapersTopicHearingType.isDel", "String", "=", "0"));
		 
			 
			//
			//queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("mpthId", mpthId);
			//request.setAttribute("name", name);
			//request.setAttribute("loginName", loginName);
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

			request.setAttribute("MatchedPapersTopicHearingTypeActionForm", mgr.view(ID));

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

		MatchedPapersTopicHearingTypeActionForm vo = (MatchedPapersTopicHearingTypeActionForm) form;
		try {
			
			MatchedPapersTopicHearingTypeActionForm mptht = mgr.view(vo.getId());
			if(null != mptht){
				mptht.setName(vo.getName());
				mptht.setUrl(vo.getUrl());
				mptht.setSort(vo.getSort());
				mptht.setContent(vo.getContent());
				mgr.update(mptht);
				
				//修改或新增听力歌词
				String[] arrbills = ParamUtils.getParameterValues(request, "arrbills", true);
				for (String string : arrbills) {
					if(null != string && !"".equals(string)){
						String[] arrb = string.split("/");
						//	System.out.println(arrb.length);
						if(null!=arrb[4] && !"".equals(arrb[4]) && !"noId".equals(arrb[4])){
							MatchedPapersTopicLyricActionForm nqpt= mptlMgr.view(arrb[4]);
							if(null!= nqpt){
								if(null!=arrb[0])
									nqpt.setLyricText(arrb[0]);
								if(null!=arrb[1])
									nqpt.setStatrTime(Double.parseDouble(AddZero.format(arrb[1], 2)));
								if(null!=arrb[2])
									nqpt.setEndTime(Double.parseDouble(AddZero.format(arrb[2], 2)));
								if(null!=arrb[3] && !"".equals(arrb[3]));
								nqpt.setSort(Integer.parseInt(arrb[3]));
								
								mptlMgr.update(nqpt);
							}
						}else{
							MatchedPapersTopicLyricActionForm nqpt = new MatchedPapersTopicLyricActionForm();
							nqpt.setMpthtId(vo.getId());
							if(null!=arrb[0])
								nqpt.setLyricText(arrb[0]);
							if(null!=arrb[1])
								nqpt.setStatrTime(Double.parseDouble(AddZero.format(arrb[1], 2)));
							if(null!=arrb[2])
								nqpt.setEndTime(Double.parseDouble(AddZero.format(arrb[2], 2)));
							if(null!=arrb[3] && !"".equals(arrb[3]));
							nqpt.setSort(Integer.parseInt(arrb[3]));
							
							mptlMgr.add(nqpt);
							
						}
					}
				}
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
	//异步修改
	public ActionForward ajaxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		MatchedPapersTopicHearingTypeActionForm vo = (MatchedPapersTopicHearingTypeActionForm) form;
		try {
			String ID = ParamUtils.getParameter(request, "id", true);
			String name = ParamUtils.getParameter(request, "name", true);
			String url = ParamUtils.getParameter(request, "url", true);
			String sort = ParamUtils.getParameter(request, "sort", true);
			String content = ParamUtils.getParameter(request, "content", true);
			
			MatchedPapersTopicHearingTypeActionForm mptht = mgr.view(ID);
			if(null != mptht){
				mptht.setName(name);
				mptht.setUrl(url);
				if(null != sort && !"".equals(sort)){
					mptht.setSort(Integer.parseInt(sort));
				}
				mptht.setContent(content);
				mgr.update(mptht);
				
				//修改或新增听力歌词
				String arrbills = ParamUtils.getParameter(request, "arrbills", true);
				String[] arls = arrbills.split("&");
				for (String string : arls) {
					if(null != string && !"".equals(string)){
						String[] arrb = string.split("#");
						if(null!=arrb[4] && !"".equals(arrb[4]) && !"noId".equals(arrb[4])){
							MatchedPapersTopicLyricActionForm nqpt= mptlMgr.view(arrb[4]);
							
							if(null!= nqpt){
								if(null!=arrb[0])
									nqpt.setLyricText(arrb[0]);
								if(null!=arrb[1])
									nqpt.setStatrTime(Double.parseDouble(AddZero.format(arrb[1], 2)));
								if(null!=arrb[2])
									nqpt.setEndTime(Double.parseDouble(AddZero.format(arrb[2], 2)));
								if(null!=arrb[3] && !"".equals(arrb[3]));
								nqpt.setSort(Integer.parseInt(arrb[3]));
								
								mptlMgr.update(nqpt);
							}
						}else{
							MatchedPapersTopicLyricActionForm nqpt = new MatchedPapersTopicLyricActionForm();
							nqpt.setMpthtId(vo.getId());
							if(null!=arrb[0])
								nqpt.setLyricText(arrb[0]);
							if(null!=arrb[1])
								nqpt.setStatrTime(Double.parseDouble(AddZero.format(arrb[1], 2)));
							if(null!=arrb[2])
								nqpt.setEndTime(Double.parseDouble(AddZero.format(arrb[2], 2)));
							if(null!=arrb[3] && !"".equals(arrb[3]));
							nqpt.setSort(Integer.parseInt(arrb[3]));
							
							mptlMgr.add(nqpt);
							
						}
					}
				}
			}
			JsonUtils.outputJsonResponse(response, "result","200");
			return null;
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
			String mpthId = ParamUtils.getParameter(request, "mpthId", false);
			request.setAttribute("mpthId", mpthId);
			MatchedPapersTopicHearingTypeActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("MatchedPapersTopicHearingTypeActionForm", vo);

			//查询选项
			List<MatchedPapersTopicLyric> choices = Tool.findListByHql("from MatchedPapersTopicLyric where mpthtId = '"+vo.getId()+"' order by sort ");
			request.setAttribute("choices", choices);
			
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
			MatchedPapersTopicHearingTypeActionForm vo = (MatchedPapersTopicHearingTypeActionForm) form;			  
			MatchedPapersTopicHearingType mpth = new MatchedPapersTopicHearingType();
			this.copyProperties(mpth, vo);
			String id = mgr.add(mpth);
			
			String[] arrbills = ParamUtils.getParameterValues(request, "arrbills", true);
			for (String string : arrbills) {
				String[] arrb = string.split("/");
				MatchedPapersTopicLyric mpthsu = new MatchedPapersTopicLyric();
				mpthsu.setMpthtId(id);
				if(null!=arrb[0])
					mpthsu.setLyricText(arrb[0]);
				if(null!=arrb[1])
					mpthsu.setStatrTime(Double.parseDouble(AddZero.format(arrb[1],2)));
				if(null!=arrb[2] && !"".equals(arrb[2]));
					mpthsu.setEndTime(Double.parseDouble(AddZero.format(arrb[2],2)));
				if(null!=arrb[3] && !"".equals(arrb[3]));
					mpthsu.setSort(Integer.parseInt(arrb[3]));
				
				mptlMgr.add(mpthsu);	
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

			MatchedPapersTopicHearingTypeActionForm vo = new MatchedPapersTopicHearingTypeActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String mpthId = ParamUtils.getParameter(request, "mpthId", false);
			request.setAttribute("mpthId", mpthId);
			request.setAttribute("MatchedPapersTopicHearingTypeActionForm", vo);
			
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

			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				 
				Tool.execute("update matched_papers_topic_hearing_type set is_del = 1 where id = '"+ids[i]+"'");
				 
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
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "tId", true);
			Tool.execute("update matched_papers_topic_hearing_type set is_del = 1 where id = '"+ID+"'");
				 
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
	 * 删除歌词列
	 * 方法功能说明：  
	 * 创建：2016年5月31日 by Zzc   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 * @参数： @param mapping
	 * @参数： @param form
	 * @参数： @param request
	 * @参数： @param response
	 * @参数： @return      
	 * @return ActionForward     
	 * @throws
	 */
	public ActionForward AjasRealdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = ParamUtils.getParameter(request, "id");	
		if (id != null && !"".equals(id)) {
			try {
				boolean isSuc = Tool.execute("delete from matched_papers_topic_lyric where id ='"+id+"'");
				
				if (isSuc) {
					// 成功
					map.put("succeed", "000");
				} else {
					// 失败
					map.put("succeed", "001");
				}
			} catch (Exception e) {
				// 异常
				map.put("succeed", "003");
				e.printStackTrace();
			}
		} else {
			// 传值为空
			map.put("succeed", "501");
		}
		map.put("interface_name", "matched_papers_topic_lyric_AjasRealdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	
	/**
	 * 从Excel文件中读取数据，并导入到数据库中,字幕导入
	 */
	public ActionForward lyricExl(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
	if (null == sessionContainer)
		sessionContainer = new SessionContainer();
	Map m = new HashMap();
	String mpthId = ParamUtils.getParameter(request, "mpthId", false);
	
	// 获取excel 文件
	MatchedPapersTopicHearingTypeActionForm fm = (MatchedPapersTopicHearingTypeActionForm) actionForm;
	FormFile formfile = fm.getFile();
	InputStream inputstream = formfile.getInputStream();
	fm.clear();;// 清空
	ArrayList list = new ArrayList();
	int input = 0; // 导入记数

	try {
		// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
		HSSFRow hssfrow = hssfsheet.getRow(0);// 第一行
		String col0 = ""; //passage名称
		String col2 = ""; //时间
		String col3 = ""; //字幕
		String mpthtId = "";
		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
		for (int i = 0; i < hssfworkbook.getNumberOfSheets(); i++) {
			hssfsheet = hssfworkbook.getSheetAt(i);
			// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
			for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
				hssfrow = hssfsheet.getRow(j);
				// 判断是否还存在需要导入的数据
				if (hssfrow == null) {
					System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
					break;
				}
				/** */
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 0)!=null){
					col0 = hssfrow.getCell((short) 0).getStringCellValue().trim();
				}

				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(hssfrow.getCell((short) 2)!=null){
					col2 = hssfrow.getCell((short) 2).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(hssfrow.getCell((short) 3)!=null){
					col3 = hssfrow.getCell((short) 3).getStringCellValue()
							.trim();
				}
				if(!"".equals(col0)){
					mpthtId = Tool.getValue("select id from matched_papers_topic_hearing_type where name='"+col0+"' and mpth_id='"+mpthId+"'");
					if(!"".equals(mpthtId) && !"".equals(col2)){
						String[] str = col2.split(":");
						int dd = Integer.parseInt(str[0].trim().toString());
						double time = Double.parseDouble(AddZero.format(dd*60+Double.parseDouble(str[1].trim().toString()),2));
						MatchedPapersTopicLyric mptl = new MatchedPapersTopicLyric();
						mptl.setLyricText(col3);
						mptl.setStatrTime(time);
						mptl.setMpthtId(mpthtId);
						mptl.setSort(1);
						mptlMgr.add(mptl);
					}
				}else{
					if(!"".equals(mpthtId)){
						if(!"".equals(col2)){
							String[] str = col2.split(":");
							int dd = Integer.parseInt(str[0].trim().toString());
							double time = Double.parseDouble(AddZero.format(dd*60+Double.parseDouble(str[1].trim().toString()),2));
							MatchedPapersTopicLyric mptl = new MatchedPapersTopicLyric();
							mptl.setLyricText(col3);
							mptl.setStatrTime(time);
							mptl.setMpthtId(mpthtId);
							mptl.setSort(1);
							mptlMgr.add(mptl);
						}
					}
				}
				
					// 导入成功加1
					input++;
				}
		}
		
		m.put("result", true);
		JsonUtils.outputJsonResponse(response, m);

	} catch (Exception e) {
		e.printStackTrace();
		m.put("false", true);
		JsonUtils.outputJsonResponse(response, m);
	}

	//request.setAttribute("total", input);
	//return list(actionMapping, actionForm, request, response);
	return null;
	}
	
	
	public ActionForward prelyricExl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mpthId = ParamUtils.getParameter(request, "mpthId", false);
		request.setAttribute("mpthId", mpthId);
		 
		return mapping.findForward("lyricExl");
	}
	/**================导入原订单===============*/
	/**
	 * 从Excel文件中读取数据，并导入到数据库中 导入旧订单
	 */
	public ActionForward orderExl(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取excel 文件
		MatchedPapersTopicHearingTypeActionForm fm = (MatchedPapersTopicHearingTypeActionForm) actionForm;
		FormFile formfile = fm.getFile();
		InputStream inputstream = formfile.getInputStream();
		fm.clear();
		;// 清空
		ArrayList list = new ArrayList();
		int input = 0; // 导入记数

		try {
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
			HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
			HSSFRow hssfrow = hssfsheet.getRow(0);// 第一行
			String account = ""; // 用户名
			String time = ""; // 时间
			String price = ""; // 订单价格
			String name = "";// 图书名称
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < hssfworkbook.getNumberOfSheets(); i++) {
				hssfsheet = hssfworkbook.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
				for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
					hssfrow = hssfsheet.getRow(j);
					// 判断是否还存在需要导入的数据
					if (hssfrow == null) {
						System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
						break;
					}
					/** */
					/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
					if (hssfrow.getCell((short) 1) == null) {
						account = "";
					} else if (hssfrow.getCell((short) 1).getCellType() == 0) {
						BigDecimal db = new BigDecimal(new Double(hssfrow.getCell((short) 1)
								.getNumericCellValue()));
						account = db.toPlainString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						account = hssfrow.getCell((short) 1).getStringCellValue()
								.trim();
					}
					
					/**=======================*/
					if (hssfrow.getCell((short) 2) == null) {
						time = "";
					} else if (hssfrow.getCell((short) 2).getCellType() == 0) {
						BigDecimal db = new BigDecimal(new Double(hssfrow.getCell((short)2)
								.getNumericCellValue()));
						time = db.toPlainString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						time = hssfrow.getCell((short) 2).getStringCellValue()
								.trim();
					}
					/**=======================*/
					if (hssfrow.getCell((short) 6) == null) {
						price = "";
					} else if (hssfrow.getCell((short) 6).getCellType() == 0) {
						BigDecimal db = new BigDecimal(new Double(hssfrow.getCell((short)6)
								.getNumericCellValue()));
						price = db.toPlainString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						price = hssfrow.getCell((short) 6).getStringCellValue()
								.trim();
					}/**=======================*/
					if (hssfrow.getCell((short) 8) == null) {
						name = "";
					} else if (hssfrow.getCell((short) 8).getCellType() == 0) {
						BigDecimal db = new BigDecimal(new Double(hssfrow.getCell((short)8)
								.getNumericCellValue()));
						name = db.toPlainString();
					}
					// 如果EXCEL表格中的数据类型为字符串型
					else {
						name = hssfrow.getCell((short) 8).getStringCellValue()
								.trim();
					}
					
					// 首先获取用户ID
					if (!"".equals(account) && null != account
							&& !"".equals(price) && null != price
							&& !"".equals(time) && null != time) {
						System.out.println(account);
						System.out.println("select * from users where account='"
										+ account + "' or qqOpenId='" + account
										+ "' and 1=1");
						// 获取
						Users u =(Users) mgr
								.getObjectByHql("from Users where account='"
										+ account + "' or qqOpenId='" + account
										+ "' and 1=1");
						if (!"".equals(u) && null != u) {// 用户存在
							// 通过网课名称判断网课是否存在
							NetworkVideo nv = (NetworkVideo) mgr
									.getObjectByHql("from NetworkVideo where networkName='"
											+ name + "' and 1=1");
							System.out.println("select * from network_video where network_name='"
											+ name + "' and 1=1");
							if (!"".equals(nv) && null != nv) {
								DecimalFormat   df   =new   DecimalFormat("#.00"); 
								// 创建订单
								String orderTime = DateUtils.getCurrDateS();// 订单号
								String orderCode = getOrderCode(orderTime);
								ProductOrder po = new ProductOrder();
								po.setAddress("");
								po.setAdminDel(0);
								po.setAreaId("");
								po.setCityId("");
								po.setConsignee("");
								po.setIsPostage(1);
								po.setLogisticscode("");
								po.setOrderCode(orderCode);
								po.setCreatetime(time.replace("/", "-"));
								po.setOrderState("completed");
								po.setPostage(0);
								po.setProvinceId("");
								System.out.println(price);
								po.setPrice(Double.valueOf(price));
								po.setRemark("原网站订单");
								po.setZipcode("");
								po.setUserId(u.getId());
								po.setUserDel(0);
								po.setTelephone("");
								po.setType(1);
								po.setDistrictCn("");
								po.setPayType(1);
								String oid = mgr.add(po);
								System.out.println(oid);
								if (!"".equals(oid) && null != oid) {
									ProductOrderDetails pod = new ProductOrderDetails();
									pod.setMoney(nv.getNetworkMoney());// 价格
									pod.setPcount(1);
									pod.setProductId(nv.getId());
									pod.setProductOrderId(oid);
									pod.setProductType(1);
									pod.setStatus("really");
									mgr.add(pod);
									// 导入成功加1
									input++;
								}
							}
						}
					}
				}
			}
			System.out.println(input);
			m.put("result", true);
			JsonUtils.outputJsonResponse(response, m);

		} catch (Exception e) {
			e.printStackTrace();
			m.put("false", true);
			JsonUtils.outputJsonResponse(response, m);
		}

		// request.setAttribute("total", input);
		// return list(actionMapping, actionForm, request, response);
		return null;
	}

	// 查询订单号,返回不存在单号
	public String getOrderCode(String orderTime) {
		int code = new Random().nextInt(1000000);
		int count = Tool
				.getIntValue("select count(id) from product_order where logisticsCode = '"
						+ orderTime + code + "'");
		if (count > 0) {
			getOrderCode(orderTime);
		}
		return orderTime + code;
	}
}
