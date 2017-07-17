package com.business.JQrMp; 


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.MD5;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.business.JQrMp.JQrMp;
import com.business.JQrMp.JQrMpActionForm;
import com.business.JQrMp.JQrMpMgr;
import com.business.JQrMpth.JQrMpth;
import com.business.JQrMpth.JQrMpthMgr;
import com.business.JQrMpths.JQrMpths;
import com.business.JQrMpths.JQrMpthsMgr;
import com.business.JQrMpthsc.JQrMpthsc;
import com.business.JQrMpthsc.JQrMpthscMgr;
import com.business.JQrMptht.JQrMptht;
import com.business.JQrMptht.JQrMpthtMgr;
import com.business.MatchedPapers.MatchedPapers;
import com.business.MatchedPapers.MatchedPapersActionForm;
import com.business.MatchedPapersTopicHearing.MatchedPapersTopicHearing;
import com.business.MatchedPapersTopicHearingSubject.MatchedPapersTopicHearingSubject;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoice;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingType;
import com.business.MatchedPapersType.MatchedPapersType;

public class JQrMpAction extends BaseAction{
	 JQrMpMgr mgr = new JQrMpMgr();
	 JQrMpthMgr mpthMgr = new JQrMpthMgr();
	 JQrMpthtMgr mpthtMgr = new JQrMpthtMgr();
	 JQrMpthsMgr mpthsMgr = new JQrMpthsMgr();
	 JQrMpthscMgr mpthscMgr = new JQrMpthscMgr();
	 
	 /**
		 * 生成功能树
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public ActionForward treelist(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			try {
				String rootid = "FFFFFF";

				List treelist = null;
				treelist = mgr.getMPFuntree(rootid);

				request.setAttribute("treelist", treelist);
				request.setAttribute("rootid", rootid);
				return mapping.findForward("tree");
			} catch (Exception ex) {

				log.error(ex.getMessage(), ex);

				WebDialogBox dialog = new WebDialogBox(1, "错误", "获取功能树时出错", "返回",
						"javascript:window.history.back()");

				request.setAttribute("DialogBox", dialog);

				return mapping.findForward("DialogBox");
			}
		}
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
			String qrtId = ParamUtils.getParameter(request, "qrtId", false);
			String nameStr = ParamUtils.getParameter(request, "nameStr", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("JQrMp.qrtId", "String", "=", qrtId));
			queryConds.add(new QueryCond("JQrMp.mpName", "String", "like", nameStr));
			queryConds.add(new QueryCond("JQrMp.isDel", "String", "=", "0"));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("qrtId", qrtId);
			//request.setAttribute("orgId", orgId);
			request.setAttribute("nameStr", nameStr);
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

			request.setAttribute("JQrMpActionForm", mgr.view(ID));

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
		Map<String, Object> map = new HashMap<String, Object>();
		JQrMpActionForm vo = (JQrMpActionForm) form;
		try {
			JQrMpActionForm jo = mgr.view(vo.getId());
			jo.setMpName(vo.getMpName());
			jo.setSort(vo.getSort());
			jo.setUrl(vo.getUrl());
			jo.setContent(vo.getContent());
			jo.setLevelType(vo.getLevelType());
			mgr.update(jo);

			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
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

			String ID = ParamUtils.getParameter(request, "mpId", true);

			JQrMpActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("JQrMpActionForm", vo);
			//查询级别类型
			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value",vo.getLevelType());
			request.setAttribute("levelTypeStr", levelTypeStr);

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
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JQrMpActionForm vo = (JQrMpActionForm) form;			  
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			mgr.add(vo);
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
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
			String qrtId = ParamUtils.getParameter(request, "qrtId", true);
			JQrMpActionForm vo = new JQrMpActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("JQrMpActionForm", vo);
			request.setAttribute("qrtId", qrtId);
			
			//查询级别类型
			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value");
			request.setAttribute("levelTypeStr", levelTypeStr);
			
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
				Tool.execute("update j_qr_mp set is_del=1 where id = '"+ids[i]+"'");
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

			String mpId = ParamUtils.getParameter(request, "mpId", true);
			Tool.execute("update j_qr_mp set is_del=1 where id = '"+mpId+"'");
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**//*
	 * 从Excel文件中读取数据，并导入到数据库中,听力
	 */
public ActionForward getUpload_hearing(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
	if (null == sessionContainer)
		sessionContainer = new SessionContainer();
	Map m = new HashMap();
	// 获取excel 文件
	JQrMpActionForm fm = (JQrMpActionForm) actionForm;
	FormFile formfile = fm.getFile();
	InputStream inputstream = formfile.getInputStream();
	fm.clear();;// 清空
	ArrayList list = new ArrayList();
	int input = 0; // 导入记数
	
	String mptId = ParamUtils.getParameter(request, "mptId", true);
	
	try {
		// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
		HSSFRow hssfrow = hssfsheet.getRow(0);// 第一行
		String col0 = "";
		String col1 = "";
		String col2 = "";
		String col3 = "";
		
		String col4 = ""; 
		String col5 = ""; 
		String col6 = "";
		String col7 = ""; 
		String col8 = ""; 
		String col9 = "";
		String col10 = ""; 
		String col11 = ""; 
		String col12 = ""; 
		String col13 = ""; 
		String col14 = "";
		
		String mpId = "";
		String mpthId = "";
		String mpthtId = "";
		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
		for (int i = 0; i < hssfworkbook.getNumberOfSheets(); i++) {
			hssfsheet = hssfworkbook.getSheetAt(i);
			// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
			
			for (int j = 1; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
				hssfrow = hssfsheet.getRow(j);
				// 判断是否还存在需要导入的数据
				if (hssfrow == null) {
					System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
					break;
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 0)!=null){
					col0 =  hssfrow.getCell((short) 0).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 1)!=null){
					col1 =  hssfrow.getCell((short) 1).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 2)!=null){
					col2 =  hssfrow.getCell((short) 2).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 3)!=null){
					col3 =  hssfrow.getCell((short) 3).getStringCellValue()
							.trim();
				}
				/** */
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 4)!=null){
					col4 =  hssfrow.getCell((short) 4).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 5)!=null){
					col5 =  hssfrow.getCell((short) 5).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 6)!=null){
					col6 =  hssfrow.getCell((short) 6).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 7)!=null){
					col7 =  hssfrow.getCell((short) 7).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 8)!=null){
					col8 =  hssfrow.getCell((short) 8).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 9)!=null){
					col9 =  hssfrow.getCell((short) 9).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 10)!=null){
					col10 =  hssfrow.getCell((short) 10).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 11)!=null){
					col11 =  hssfrow.getCell((short) 11).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 12)!=null){
					col12 =  hssfrow.getCell((short) 12).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 13)!=null){
					col13 =  hssfrow.getCell((short) 13).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 14)!=null){
					col14 =  hssfrow.getCell((short) 14).getStringCellValue()
							.trim();
				}
				
				if(null!=col5&&!"".equals(col5)){
					//查询整套试卷,添加试卷
					mpId =  Tool.getValue("select id from j_qr_mp where mp_name='"+col5+"' and code='"+col0+col1+"' and qrt_id='"+mptId+"' and is_del = 0");
					if(null == mpId || "".equals(mpId)){
						if(mptId != null && !mptId.equals("")){
							JQrMp mp = new JQrMp();
							mp.setMpName(col5);
							mp.setContent(col7);
//						mp.setUrl(col14);
							mp.setCreatetime(DateUtils.getCurrDateTimeStr());
							mp.setQrtId(mptId);
							mp.setCode(col0+col1);
							mpId = mgr.add(mp);
						}
					}
				}
				
				if(null!=col6&&!"".equals(col6)){
					//查询节点，添加 Section
					mpthId = Tool.getValue("select id from j_qr_mpth where h_name='"+col6+"' and qrmp_id='"+mpId+"' and code='"+col0+col1+col2+"' and is_del = 0");
					if(null == mpthId || "".equals(mpthId)){
						if(!"".equals(mpId)){
							if(!"".equals(col6) ){
								JQrMpth mpth = new JQrMpth();
								mpth.sethName(col6);
								mpth.sethContent(col10);
								mpth.setQrmpId(mpId);
								mpth.setSubjectType(col4);
								mpth.setCode(col0+col1+col2);
								mpthId = mpthMgr.add(mpth);
							}
						}
					}
				}
				if(null!=col8&&!"".equals(col8)){
					//查询pasage ,添加
					mpthtId = Tool.getValue("select id from j_qr_mptht where name='"+col8+"' and qrmpth_id='"+mpthId+"' and code='"+col0+col1+col2+col3+"' and is_del = 0");
					if(null == mpthtId || "".equals(mpthtId)){
						if(null != mpthId && !"".equals(mpthId)){
							if(!"".equals(col8)){
								JQrMptht mptht = new JQrMptht();
								mptht.setName(col8);
								mptht.setQrmpthId(mpthId);
								mptht.setUrl(col14);
								mptht.setCode(col0+col1+col2+col3);
								mpthtId = mpthtMgr.add(mptht);
							}
						}
					}
				}
				//添加题
				if(null != mpthtId && !"".equals(mpthtId)){
					if(!"".equals(col9)){
						String mpthsId =  Tool.getValue("select id from j_qr_mpths where s_number='"+col9+"' and qrmptht_id='"+mpthtId+"' and is_del=0");
						if(null == mpthsId || "".equals(mpthsId)){
							JQrMpths mpths = new JQrMpths();
							if(StringUtils.isNumber(col9)){
								mpths.setsNumber(Integer.parseInt(col9));
							}
							mpths.setQrmpthtId(mpthtId);
							
							mpthsId =  mpthsMgr.add(mpths);
							if(null!=mpthsId && !mpthsId.equals("")){
								JQrMpthsc mpthsc = new JQrMpthsc();
								mpthsc.setcName(col11);
								mpthsc.setcContent(col12);
								if(StringUtils.isNumber(col13)){
									mpthsc.setcIsAnswer(Integer.parseInt(col13));
								}else{
									mpthsc.setcIsAnswer(0);
								}
								mpthsc.setQrmtphcId(mpthsId);
								mpthscMgr.add(mpthsc);
							}
						}else{
							JQrMpthsc mpthsc = new JQrMpthsc();
							mpthsc.setcName(col11);
							mpthsc.setcContent(col12);
							if(StringUtils.isNumber(col13)){
								mpthsc.setcIsAnswer(Integer.parseInt(col13));
							}else{
								mpthsc.setcIsAnswer(0);
							}
							mpthsc.setQrmtphcId(mpthsId);
							mpthscMgr.add(mpthsc);
						}
					}
				}
				System.out.println(input+"--------");
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
	
//	request.setAttribute("total", input);
//	return list(actionMapping, actionForm, request, response);
	return null;

}
/**
 * 预导入
 * 方法功能说明：  
 * 创建：2016年7月26日 by Zzc   
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
public ActionForward preExl(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {

	try {

		String mptId = ParamUtils.getParameter(request, "mptId", true);

		SessionContainer sessionContainer = (SessionContainer) request
		.getSession().getAttribute("SessionContainer");
		if(sessionContainer==null){
			sessionContainer = new SessionContainer();
		}
		
		request.setAttribute("mptId", mptId);

		return mapping.findForward("preExl");
	} catch (Exception ex) {

		log.error(ex.getMessage(), ex);
		WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
				"javascript:window.history.back()");
		request.setAttribute("DialogBox", dialog);
		return mapping.findForward("DialogBox");
	}
}
}
