package com.business.JCommonWords; 


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
import com.easecom.common.util.AddZero;
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
import com.business.JCommonType.JCommonType;
import com.business.JCommonWords.JCommonWords;
import com.business.JCommonWords.JCommonWordsActionForm;
import com.business.JCommonWords.JCommonWordsMgr;
import com.business.JQrSubtitleListening.JQrSubtitleListening;
import com.business.JQrSubtitleListeningLyric.JQrSubtitleListeningLyric;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingTypeActionForm;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyric;

public class JCommonWordsAction extends BaseAction{
	 JCommonWordsMgr mgr = new JCommonWordsMgr();

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
			String nameStr = ParamUtils.getParameter(request, "nameStr", false);
			String ctId = ParamUtils.getParameter(request, "ctId", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("JCommonWords.word", "String", "like", nameStr));
			queryConds.add(new QueryCond("JCommonWords.ctId", "String", "=", ctId));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("ctId", ctId);
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

			request.setAttribute("JCommonWordsActionForm", mgr.view(ID));

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
		JCommonWordsActionForm vo = (JCommonWordsActionForm) form;
		try {
			JCommonWordsActionForm po = mgr.view(vo.getId());
			if(null!=po){
				po.setWord(vo.getWord());
				po.setContent(vo.getContent());
				po.setSort(vo.getSort());
				mgr.update(po);
			}

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

			String ID = ParamUtils.getParameter(request, "id", true);

			JCommonWordsActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("JCommonWordsActionForm", vo);

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
			JCommonWordsActionForm vo = (JCommonWordsActionForm) form;			  
			//vo.setType("1");
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

			JCommonWordsActionForm vo = new JCommonWordsActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("JCommonWordsActionForm", vo);
			String ctId = ParamUtils.getParameter(request, "ctId", true);
			request.setAttribute("ctId", ctId);
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
				 
			Tool.execute("delete from j_common_words where id = '"+ids[i]+"'");
				 
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
			String ID = ParamUtils.getParameter(request, "wId", true);
				 
			Tool.execute("delete from j_common_words where id = '"+ID+"'");
				 
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
	
	// 获取excel 文件
	JCommonWordsActionForm fm = (JCommonWordsActionForm) actionForm;
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
		String col1 = ""; //时间
		String col2 = ""; //字幕
		String col3 = "";
		String col4 = "";
		String tId = "";
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
				/** */
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 0)!=null){
					col0 = hssfrow.getCell((short) 0).getStringCellValue().trim();
				}

				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(hssfrow.getCell((short) 1)!=null){
					col1 = hssfrow.getCell((short) 1).getStringCellValue()
							.trim();
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
				
				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(hssfrow.getCell((short) 4)!=null){
					col4 = hssfrow.getCell((short) 4).getStringCellValue()
							.trim();
				}
				
				
				
				if(!"".equals(col0) &&!"".equals(col1)){
					tId = Tool.getValue("select id from j_common_type where type_name='"+col1+"'");
					
					JCommonType jt = new JCommonType();
					if("".equals(tId)){
						jt.setTypeName(col1);
						tId = mgr.add(jt);
					}
					
					if(!"".equals(tId) && !"".equals(col2)){
						JCommonWords jw = new JCommonWords();
						jw.setCtId(tId);
						jw.setWord(col2);
						jw.setContent(col3);
						jw.setPhonogram("");
						mgr.add(jw);
					}
				}else{
					if(!"".equals(tId) && !"".equals(col2)){
						JCommonWords jw = new JCommonWords();
						jw.setCtId(tId);
						jw.setWord(col2);
						jw.setContent(col3);
						jw.setPhonogram("");
						mgr.add(jw);
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
	
}
