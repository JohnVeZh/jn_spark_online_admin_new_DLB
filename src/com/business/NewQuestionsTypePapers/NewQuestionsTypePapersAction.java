package com.business.NewQuestionsTypePapers; 


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
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.FileUploadUtil;
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
import com.business.MatchedPapersTopicHearing.MatchedPapersTopicHearing;
import com.business.MatchedPapersTopicHearingSubject.MatchedPapersTopicHearingSubject;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoice;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingType;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyric;
import com.business.MatchedPapersType.MatchedPapersType;
import com.business.NewQuestionsPapersTopic.NewQuestionsPapersTopic;
import com.business.NewQuestionsPapersTopic.NewQuestionsPapersTopicMgr;
import com.business.NewQuestionsPapersTopicChoice.NewQuestionsPapersTopicChoice;
import com.business.NewQuestionsPapersTopicChoice.NewQuestionsPapersTopicChoiceMgr;
import com.business.NewQuestionsPapersTopicLyric.NewQuestionsPapersTopicLyric;
import com.business.NewQuestionsPapersTopicLyric.NewQuestionsPapersTopicLyricMgr;
import com.business.NewQuestionsPapersTopicType.NewQuestionsPapersTopicType;
import com.business.NewQuestionsPapersTopicType.NewQuestionsPapersTopicTypeMgr;
import com.business.NewQuestionsType.NewQuestionsType;
import com.business.NewQuestionsType.NewQuestionsTypeMgr;
import com.business.NewQuestionsTypePapers.NewQuestionsTypePapers;
import com.business.NewQuestionsTypePapers.NewQuestionsTypePapersActionForm;
import com.business.NewQuestionsTypePapers.NewQuestionsTypePapersMgr;

public class NewQuestionsTypePapersAction extends BaseAction{
	 NewQuestionsTypePapersMgr mgr = new NewQuestionsTypePapersMgr();
	 NewQuestionsTypeMgr nqtmgr=new NewQuestionsTypeMgr();
	 NewQuestionsPapersTopicTypeMgr nqtptmgr=new NewQuestionsPapersTopicTypeMgr();
	 NewQuestionsPapersTopicMgr nqptmgr=new NewQuestionsPapersTopicMgr();
	 NewQuestionsPapersTopicChoiceMgr nqptcmgr=new NewQuestionsPapersTopicChoiceMgr();
	 NewQuestionsPapersTopicLyricMgr mqptlMgr = new NewQuestionsPapersTopicLyricMgr();
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
				treelist = nqtmgr.getMPFuntree(rootid);

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
			String nqtId = ParamUtils.getParameter(request, "nqtId", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
		 
			 
			//
			queryConds.add(new QueryCond("NewQuestionsTypePapers.nqtId", "String", "=", nqtId));
			queryConds.add(new QueryCond("NewQuestionsTypePapers.isDel", "String", "=", "0"));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("nqtId", nqtId);
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

			request.setAttribute("NewQuestionsTypePapersActionForm", mgr.view(ID));

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

		NewQuestionsTypePapersActionForm vo = (NewQuestionsTypePapersActionForm) form;
		try {
			NewQuestionsTypePapersActionForm nt= mgr.view(vo.getId());
			nt.setMpViewImgpath(vo.getMpViewImgpath());
			nt.setMpListImgpath(vo.getMpListImgpath());
			nt.setMpName(vo.getMpName());
			nt.setSort(vo.getSort());
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			
			mgr.update(nt);

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

			NewQuestionsTypePapersActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("NewQuestionsTypePapersActionForm", vo);

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
			NewQuestionsTypePapersActionForm vo = (NewQuestionsTypePapersActionForm) form;			  
			//vo.setType("1");
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			NewQuestionsTypePapers nt = new NewQuestionsTypePapers();
			this.copyProperties(nt, vo);
			Tool.testReflect_admin(nt);
			mgr.add(nt);
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

			NewQuestionsTypePapersActionForm vo = new NewQuestionsTypePapersActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String nqtId = ParamUtils.getParameter(request, "nqtId", false);
			request.setAttribute("nqtId", nqtId);
			request.setAttribute("NewQuestionsTypePapersActionForm", vo);
			
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
				 
			//Tool.execute("delete from new_Questions_type_papers where id = '"+ids[i]+"'");
			Tool.execute("update new_Questions_type_papers set is_del=1 where id = '"+ids[i]+"'");	 
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

			String ID = ParamUtils.getParameter(request, "id", true);
			
			Tool.execute("update new_Questions_type_papers set is_del=1 where id = '"+ID+"'");
			//Tool.execute("update matched_papers set is_del=1 where id = '"+ID+"'");
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
	 * 异步上传图片
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateImgPath(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NewQuestionsTypePapersActionForm vo = (NewQuestionsTypePapersActionForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String value = "";
					value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.MP_ICON_PATH_PHONEIMG,request);
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
		NewQuestionsTypePapersActionForm fm = (NewQuestionsTypePapersActionForm) actionForm;
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
			String col0 = ""; //等级
			String col2 = ""; //年月
			String col3 = ""; //套
			String col4 = ""; //节
			String col5 = ""; //模块
			String col6 = ""; //分组
			String col9 = ""; //级别
			String col10 = ""; //套名
			String col11 = ""; //节名
			String col12 = ""; //题
			String col13 = ""; //pageage
			String col14 = ""; //pageage
			String col15 = ""; //题
			String col16 = ""; //节内容
			String col17 = ""; //题，选项
			String col18 = ""; //题，选项内容
			String col19 = ""; //题，是否正确答案
			String col20 = ""; //url
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < hssfworkbook.getNumberOfSheets(); i++) {
				hssfsheet = hssfworkbook.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
				for (int j = 2; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
					hssfrow = hssfsheet.getRow(j);
					// 判断是否还存在需要导入的数据
					if (hssfrow == null) {
						System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
						break;
					}
					/** */

					/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
					if(hssfrow.getCell((short) 0)!=null){
						col0 =  hssfrow.getCell((short) 0).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第3列的值插入到实例中 */
					if(hssfrow.getCell((short) 2)!=null){
						col2 =  hssfrow.getCell((short) 2).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第3列的值插入到实例中 */
					if(hssfrow.getCell((short) 3)!=null){
						col3 =  hssfrow.getCell((short) 3).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第4列的值插入到实例中 */
					if(hssfrow.getCell((short) 4)!=null){
						col4 =  hssfrow.getCell((short) 4).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第5列的值插入到实例中 */
					if(hssfrow.getCell((short) 5)!=null){
						col5 =  hssfrow.getCell((short) 5).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 6)!=null){
						col6 =  hssfrow.getCell((short) 6).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 9)!=null){
						col9 =  hssfrow.getCell((short) 9).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 10)!=null){
						col10 =  hssfrow.getCell((short) 10).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 11)!=null){
						col11 =  hssfrow.getCell((short) 11).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 12)!=null){
						col12 =  hssfrow.getCell((short) 12).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 13)!=null){
						col13 =  hssfrow.getCell((short) 13).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 14)!=null){
						col14 =  hssfrow.getCell((short) 14).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 15)!=null){
						col15 =  hssfrow.getCell((short) 15).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 16)!=null){
						col16 =  hssfrow.getCell((short) 16).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 17)!=null){
						col17 =  hssfrow.getCell((short) 17).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 18)!=null){
						col18 =  hssfrow.getCell((short) 18).getStringCellValue()
								.trim();
					}
					
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 19)!=null){
						col19 =  hssfrow.getCell((short) 19).getStringCellValue()
								.trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 20)!=null){
						col20 =  hssfrow.getCell((short) 20).getStringCellValue()
								.trim();
					}

					// 查询年月类型,添加
					String nqtId = Tool 
							.getValue("select id from new_questions_type where code='"
									+ col0 + col2 + "' ");
					//System.out.println(nqtId);
					if (nqtId == null || nqtId.equals("")) {
						/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
						if (!"".equals(col0) && !"".equals(col2)) {
							NewQuestionsType nType = new NewQuestionsType();
							nType.setTextTypeName(col9);
							nType.setParentId(col0);
							nType.setCode(col0 + col2);
							nType.setLevelType(col0);
							nType.setCreatetime(DateUtils.getCurrDateTimeStr());
							nqtId = nqtmgr.add(nType);
						} 
						
					}

					// 查询整套试卷,添加试卷
					String nqtpId = Tool
							.getValue("select id from new_questions_type_papers where is_del=0 and code='"
									+ col0 + col2 + col3 + "'");
					//System.out.println(nqtpId);
					if (null == nqtpId || "".equals(nqtpId)) {
						/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
						if (nqtId != null && !nqtId.equals("")) {
							NewQuestionsTypePapers nqtp = new NewQuestionsTypePapers();
							nqtp.setMpName(col10);
							nqtp.setCreatetime(DateUtils.getCurrDateTimeStr());
							nqtp.setCode(col0 + col2 + col3);
							nqtp.setNqtId(nqtId);
							nqtpId = mgr.add(nqtp);
						}
					}
					// 查询节点，添加
					String nqpttId = Tool
							.getValue("select id from new_Questions_papers_topic_type where is_del=0 and code='"
									+ col0 + col2 + col3 + col4 +col5+ "'");
				//	System.out.println(nqpttId);
					if (null == nqpttId || "".equals(nqpttId)) {
						/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
						
						if (!"".equals(nqtpId)) {
							if(!"".equals(col0) && !"".equals(col2) && !"".equals(col3) && !"".equals(col4)){
								NewQuestionsPapersTopicType nqpt = new NewQuestionsPapersTopicType();
								nqpt.settName(col13);
								nqpt.setSubjectType(col6);
								nqpt.settUrl(col20);
								nqpt.setCode(col0 + col2 + col3 + col4+col5);
								nqpt.setNqtpId(nqtpId);
								nqpttId = nqtptmgr.add(nqpt);
							}
							
						}
					}
					// 添加题
					if (null != nqpttId && !"".equals(nqpttId)) {
						String nqptId = Tool
								.getValue("select id from new_Questions_papers_topic  where is_del=0 and nqptt_id = '"
										+ nqpttId
										+ "' and t_num='"
										+ col15
										+ "'");
						if (null == nqptId || "".equals(nqptId)) {
							NewQuestionsPapersTopic mpths = new NewQuestionsPapersTopic();
							mpths.settNum(Integer.parseInt(col15));
							mpths.settAnalysis(col19);
							mpths.setNqpttId(nqpttId);
							//mpths.setCode(code);

							nqptId = nqptmgr.add(mpths);
							if (null != nqptId && !nqptId.equals("")) {
								NewQuestionsPapersTopicChoice mpthsc = new NewQuestionsPapersTopicChoice();
								mpthsc.setcName(col16);
								mpthsc.setcContent(col17);
								if(null!=col18 && !"".equals(col18)){
									mpthsc.setcIsAnswer(Integer.parseInt(col18));
								}
								mpthsc.setNqptId(nqptId);
								nqptcmgr.add(mpthsc);
							}
						} else {
							NewQuestionsPapersTopicChoice mpthsc = new NewQuestionsPapersTopicChoice();
							mpthsc.setcName(col16);
							mpthsc.setcContent(col17);
							if(null!=col18 && !"".equals(col18)){
								mpthsc.setcIsAnswer(Integer.parseInt(col18));
							}
							mpthsc.setNqptId(nqptId);
							nqptcmgr.add(mpthsc);
						}
					}

					// 导入成功加1
					input++;
					System.out.println(input+"----------");
				}
			}

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

	
	/**
	 * 查询每套试卷所有的题
	 * 方法功能说明：  
	 * 创建：2016年6月24日 by Zzc   
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
	public ActionForward nqpcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			 
			// 接收传值
			String nqtpId = ParamUtils.getParameter(request, "nqtpId", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			List<Map> lm = new ArrayList<Map>();
			//查询section
			List<NewQuestionsPapersTopicType> nqpttList = Tool.findListByHql("from NewQuestionsPapersTopicType where nqtpId='"+nqtpId+"' and isDel=0");
			for (NewQuestionsPapersTopicType tt : nqpttList) {
				//查询Passage
				List<NewQuestionsPapersTopic> nqptList = Tool.findListByHql("from NewQuestionsPapersTopic where nqpttId='"+tt.getId()+"' and isDel=0");
				for (NewQuestionsPapersTopic nqpt : nqptList) {
					Map m = new HashMap();
					m.put("pasName", tt.gettName());
					m.put("mpthsId", nqpt.getId());
					m.put("sNumber", nqpt.gettNum());
					m.put("content", nqpt.gettSubject());
					lm.add(m);
				}
			}
			
			request.setAttribute("nqtpId", nqtpId);
			request.setAttribute("lm",lm);
			 
			return mapping.findForward("newtopic");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
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
	String nptpId = ParamUtils.getParameter(request, "nqtpId", false);
	Map m = new HashMap();
	// 获取excel 文件
	NewQuestionsTypePapersActionForm fm = (NewQuestionsTypePapersActionForm) actionForm;
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
		String nqpttId = "";
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
				System.out.println(col0+"-------");
				if(!"".equals(col0)){
					
					String sql = "select nqptt.id from new_Questions_type_papers nqtp,new_Questions_papers_topic_type nqptt"
							   +" where nqtp.id = nqptt.nqtp_id"
							   +" and nqtp.id = '"+nptpId+"'"
							   +" and nqptt.t_name='"+col0+"'";
					System.out.println(sql);
					nqpttId = Tool.getValue(sql);
					System.out.println(nqpttId+"-----nqpttId");
					if(!"".equals(nqpttId) && !"".equals(col2)){
						String[] str = col2.split(":");
						int dd = Integer.parseInt(str[0].trim().toString());
						double time = Double.parseDouble(AddZero.format(dd*60+Double.parseDouble(str[1].trim().toString()),2));
						NewQuestionsPapersTopicLyric nqptl = new NewQuestionsPapersTopicLyric();
						nqptl.setLyricText(col3);
						nqptl.setStatrTime(time);
						nqptl.setNqpttId(nqpttId);
						nqptl.setSort(1);
						mqptlMgr.add(nqptl);
					}
				}else{
					if(!"".equals(nqpttId)){
						if(!"".equals(col2)){
							String[] str = col2.split(":");
							int dd = Integer.parseInt(str[0].trim().toString());
							double time = Double.parseDouble(AddZero.format(dd*60+Double.parseDouble(str[1].trim().toString()),2));
							NewQuestionsPapersTopicLyric nqptl = new NewQuestionsPapersTopicLyric();
							nqptl.setLyricText(col3);
							nqptl.setStatrTime(time);
							nqptl.setNqpttId(nqpttId);
							nqptl.setSort(1);
							mqptlMgr.add(nqptl);
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

	return null;

	}
	
	public ActionForward prelyricExl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nqpttId = ParamUtils.getParameter(request, "nqtpId", false);
		request.setAttribute("nqtpId", nqpttId);
		 
		return mapping.findForward("lyricExl");
	}
}
