package com.business.MatchedPapers; 


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.business.MatchedPapersTopicHearing.MatchedPapersTopicHearing;
import com.business.MatchedPapersTopicHearing.MatchedPapersTopicHearingMgr;
import com.business.MatchedPapersTopicHearingSubject.MatchedPapersTopicHearingSubject;
import com.business.MatchedPapersTopicHearingSubject.MatchedPapersTopicHearingSubjectMgr;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoice;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoiceMgr;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingType;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingTypeMgr;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyric;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyricMgr;
import com.business.MatchedPapersTopicRead.MatchedPapersTopicRead;
import com.business.MatchedPapersTopicRead.MatchedPapersTopicReadMgr;
import com.business.MatchedPapersTopicReadSubject.MatchedPapersTopicReadSubject;
import com.business.MatchedPapersTopicReadSubject.MatchedPapersTopicReadSubjectMgr;
import com.business.MatchedPapersTopicReadSubjectChoice.MatchedPapersTopicReadSubjectChoice;
import com.business.MatchedPapersTopicReadSubjectChoice.MatchedPapersTopicReadSubjectChoiceMgr;
import com.business.MatchedPapersTopicReadType.MatchedPapersTopicReadType;
import com.business.MatchedPapersTopicReadType.MatchedPapersTopicReadTypeMgr;
import com.business.MatchedPapersTopicTranslate.MatchedPapersTopicTranslate;
import com.business.MatchedPapersTopicTranslate.MatchedPapersTopicTranslateMgr;
import com.business.MatchedPapersTopicWriting.MatchedPapersTopicWriting;
import com.business.MatchedPapersTopicWriting.MatchedPapersTopicWritingMgr;
import com.business.MatchedPapersType.MatchedPapersType;
import com.business.MatchedPapersType.MatchedPapersTypeMgr;
import com.business.ProductMatchedPaper.ProductMatchedPaper;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.AddZero;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StrUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.model.SysConfig;

public class MatchedPapersAction extends BaseAction{
	 MatchedPapersMgr mgr = new MatchedPapersMgr();
	 MatchedPapersTypeMgr matchedPapersTypeMgr = new MatchedPapersTypeMgr();
	 MatchedPapersTopicWritingMgr mptwMgr = new MatchedPapersTopicWritingMgr();
	 MatchedPapersTopicTranslateMgr mpttMgr = new MatchedPapersTopicTranslateMgr();
	 MatchedPapersTopicHearingMgr mpthMgr = new  MatchedPapersTopicHearingMgr();
	 MatchedPapersTopicHearingTypeMgr mpthtMgr = new MatchedPapersTopicHearingTypeMgr();
	 MatchedPapersTopicHearingSubjectMgr mpthsMgr = new MatchedPapersTopicHearingSubjectMgr();
	 MatchedPapersTopicHearingSubjectChoiceMgr mpthscMgr = new MatchedPapersTopicHearingSubjectChoiceMgr();
	 MatchedPapersTopicReadTypeMgr mptrtMgr = new MatchedPapersTopicReadTypeMgr();
	 MatchedPapersTopicReadMgr mptrMgr = new MatchedPapersTopicReadMgr();
	 MatchedPapersTopicReadSubjectMgr mptrsMgr = new MatchedPapersTopicReadSubjectMgr();
	 MatchedPapersTopicReadSubjectChoiceMgr mptrscMgr = new MatchedPapersTopicReadSubjectChoiceMgr();
	 MatchedPapersTopicLyricMgr mptlMgr = new MatchedPapersTopicLyricMgr();
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
				treelist = matchedPapersTypeMgr.getMPFuntree(rootid);

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
			String mptId = ParamUtils.getParameter(request, "mptId", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
		 
			 
			//
			queryConds.add(new QueryCond("MatchedPapers.mptId", "String", "=", mptId));
			queryConds.add(new QueryCond("MatchedPapers.isDel", "number", "=", "0"));
			//queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			String textName=Tool.getValue("select text_type_name from matched_papers_type where id='"+mptId+"'");
			request.setAttribute("textName", textName);
			request.setAttribute("mptId", mptId);
			request.setAttribute("lc", lc);
			request.setAttribute("meg", "200");
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
	 * 为图书配套试卷提供
	 * 方法功能说明：  
	 * 创建：2016年6月2日 by Zzc   
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
	public ActionForward productList(ActionMapping mapping, ActionForm form,
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
			String productId = ParamUtils.getParameter(request, "productId", false);
			String scType = ParamUtils.getParameter(request, "scType", false);
			String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("MatchedPapers.isDel", "number", "=", "0"));
			queryConds.add(new QueryCond("MatchedPapers.mpName", "String", "like", loginName));
			queryConds.add(new QueryCond("MatchedPapers.mptId", "String", "=", scType));
			
			//查询以配套的试卷,排除掉已经配套的试卷
			List<ProductMatchedPaper> pmpList = Tool.findListByHql("from ProductMatchedPaper where productId='"+productId+"'");
			String ids = "";
			if(pmpList.size()>0){
				ids = "'"+pmpList.get(0).getMpId()+"'";
			}
			for (int i = 1; i < pmpList.size(); i++) {
				ids = ids+",'"+pmpList.get(i).getMpId()+"'";
			}
			queryConds.add(new QueryCond("MatchedPapers.id", "String", "not in", ids));
			
			//根据级别类型查询试卷类型
			List<MatchedPapersType> mptList = Tool.findListByHql("from MatchedPapersType where scId='"+scType+"'");
			String mptIds = "";
			if(mptList.size()>0){
				mptIds = "'"+mptList.get(0).getId()+"'";
			}
			for (int i = 1; i < mptList.size(); i++) {
				mptIds = mptIds+",'"+mptList.get(i).getId()+"'";
			}
			queryConds.add(new QueryCond("MatchedPapers.mptId", "String", "in", mptIds));
			
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("productId", productId);
			request.setAttribute("loginName", loginName);
			request.setAttribute("scType", scType);
			request.setAttribute("lc", lc);
			
			//查询级别类型
//			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value",scType);
			List<SysConfig> ltList = Tool.findListByHql("from SysConfig where  type='MT_TYPE'");
			String levelTypeStr = "{\"treeNodes\":[";
			String nodes = "";
			for (SysConfig sysConfig : ltList) {
				if("".equals(nodes)){
					nodes += "{\"id\":\""+sysConfig.getValue()+"\",\"name\":\""+sysConfig.getName()+"\"}";
				}else{
					nodes += ",{\"id\":\""+sysConfig.getValue()+"\",\"name\":\""+sysConfig.getName()+"\"}";
				}
				List<MatchedPapersType> tl = Tool.findListByHql("from MatchedPapersType where parentId='"+sysConfig.getValue()+"'");
				for (MatchedPapersType mpt : tl) {
					nodes += ",{\"id\":\""+mpt.getId()+"\",\"name\":\""+mpt.getTextTypeName()+"\",\"parentId\":\""+mpt.getParentId()+"\"}";
				}
			}
			levelTypeStr = levelTypeStr+nodes+"]}";
			//System.out.println(levelTypeStr);
			request.setAttribute("levelTypeStr", levelTypeStr);
            
			
			
			return mapping.findForward("productList");
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

			request.setAttribute("MatchedPapersActionForm", mgr.view(ID));

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

		MatchedPapersActionForm vo = (MatchedPapersActionForm) form;
		try {
//			MatchedPapersActionForm mp = mgr.view(vo.getId());
			MatchedPapers mp=(MatchedPapers)mgr.getObjectByHql("from MatchedPapers where id='"+vo.getId()+"'");
			if(mp!=null){
				mp.setMpViewImgpath(vo.getMpViewImgpath());
				mp.setMpListImgpath(vo.getMpListImgpath());
				mp.setMpName(vo.getMpName());
				mp.setSort(vo.getSort());
				mp.setQrCode(vo.getQrCode());
				mgr.update(mp);
				
				//添加写作
				String wContent = ParamUtils.getParameter(request, "wContent", true);
				String wTest = ParamUtils.getParameter(request, "wTest", true);
				String mptwId = ParamUtils.getParameter(request, "mptwId", true);
				String wAnalysis = ParamUtils.getParameter(request, "wAnalysis", true);
				MatchedPapersTopicWriting mptw = (MatchedPapersTopicWriting)mptwMgr.getObjectByHql("from MatchedPapersTopicWriting where id='"+mptwId+"'");
				if(null != mptw){
					mptw.setwContent(StrUtils.null2Str(wContent));
					mptw.setwTest(StrUtils.null2Str(wTest));
					mptw.setAnalysis(StrUtils.null2Str(wAnalysis));
					mptwMgr.update(mptw);
					
				}else{
					mptw = new MatchedPapersTopicWriting();
					mptw.setwContent(StrUtils.null2Str(wContent));
					mptw.setwTest(StrUtils.null2Str(wTest));
					mptw.setAnalysis(StrUtils.null2Str(wAnalysis));
					mptw.setCode("");
					mptw.setMpId(mp.getId());
					mptw.setIsDel(0);
					mptwMgr.add(mptw);
				}
				
				//添加翻译
				String tContent = ParamUtils.getParameter(request, "tContent", true);
				String tTest = ParamUtils.getParameter(request, "tTest", true);
				String mpttId = ParamUtils.getParameter(request, "mpttId", true);
				String tAnalysis = ParamUtils.getParameter(request, "tAnalysis", true);
				MatchedPapersTopicTranslate mptt = (MatchedPapersTopicTranslate)mpttMgr.getObjectByHql("from MatchedPapersTopicTranslate where id='"+mpttId+"'");
				if(null != mptt){
					mptt.settContent(StrUtils.null2Str(tContent));
					mptt.settTest(StrUtils.null2Str(tTest));
					mptt.setAnalysis(StrUtils.null2Str(tAnalysis));
					mpttMgr.update(mptt);
					
				}else{
					mptt = new MatchedPapersTopicTranslate();
					mptt.settContent(StrUtils.null2Str(tContent));
					mptt.settTest(StrUtils.null2Str(tTest));
					mptt.setAnalysis(StrUtils.null2Str(tAnalysis));
					mptt.setCode("");
					mptt.setIsDel(0);
					mptt.setMpId(mp.getId());
					mpttMgr.add(mptt);
				}
			}
			
			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"修改试卷,试卷名称:"+vo.getMpName(), "1", ipaddress);
			
			request.setAttribute("meg", "200");
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

			MatchedPapersActionForm vo = mgr.view(ID);
			
			//查询写作
			MatchedPapersTopicWriting mptw = (MatchedPapersTopicWriting)mptwMgr.getObjectByHql("from MatchedPapersTopicWriting where mpId = '"+vo.getId()+"' ");
			request.setAttribute("mptw", mptw);
			//查询翻译
			MatchedPapersTopicTranslate mptt =  (MatchedPapersTopicTranslate)mpttMgr.getObjectByHql("from MatchedPapersTopicTranslate where mpId = '"+vo.getId()+"'");
			request.setAttribute("mptt", mptt);
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String mptId = ParamUtils.getParameter(request, "mptId", false);
			String textName=Tool.getValue("select text_type_name from matched_papers_type where id='"+mptId+"'");
			request.setAttribute("textName", textName);
			request.setAttribute("MatchedPapersActionForm", vo);

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
			//添加套卷
			MatchedPapersActionForm vo = (MatchedPapersActionForm) form;			  
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			MatchedPapers mp = new MatchedPapers();
			this.copyProperties(mp, vo);
			Tool.testReflect_admin(mp);
			String mpId = mgr.add(mp);
			if(!"".equals(mpId)){
				//添加写作
				String wContent = ParamUtils.getParameter(request, "wContent", true);
				String wTest = ParamUtils.getParameter(request, "wTest", true);
				String wAnalysis = ParamUtils.getParameter(request, "wAnalysis", true);
				MatchedPapersTopicWriting mptw = new MatchedPapersTopicWriting();
				mptw.setwContent(wContent);
				mptw.setwTest(wTest);
				mptw.setMpId(mpId);
				mptw.setAnalysis(wAnalysis);
//				Tool.testReflect_admin(mptw);
				mptwMgr.add(mptw);
				
				//添加翻译
				String tContent = ParamUtils.getParameter(request, "tContent", true);
				String tTest = ParamUtils.getParameter(request, "tTest", true);
				String tAnalysis = ParamUtils.getParameter(request, "tAnalysis", true);
				MatchedPapersTopicTranslate mptt = new MatchedPapersTopicTranslate();
				mptt.settContent(tContent);
				mptt.settTest(tTest);
				mptt.setMpId(mpId);
				mptt.setAnalysis(tAnalysis);
//				Tool.testReflect_admin(mptt);
				mpttMgr.add(mptt);
				
				
				String ipaddress = IpAddressUtil.getIpAddr(request);
				SessionContainer sessionContainer = (SessionContainer) request
						.getSession().getAttribute("SessionContainer");
				if (null == sessionContainer) {
					sessionContainer = new SessionContainer();
				}
				String username = "admin";
				Tool.AddLog(sessionContainer.getUserId(), username,
						"添加试卷,试卷名称:"+vo.getMpName(), "1", ipaddress);
				
				
			}
			request.setAttribute("meg", "200");
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

			MatchedPapersActionForm vo = new MatchedPapersActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			String mptId = ParamUtils.getParameter(request, "mptId", false);
			String textName=Tool.getValue("select text_type_name from matched_papers_type where id='"+mptId+"'");
			request.setAttribute("textName", textName);
			request.setAttribute("mptId", mptId);
			request.setAttribute("MatchedPapersActionForm", vo);
			
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
	 * 写作专区预导入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preWritExl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			MatchedPapersActionForm vo = new MatchedPapersActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			String textName = ParamUtils.getParameter(request, "textName", false);
			request.setAttribute("textName", textName);
			
			return mapping.findForward("preWritExl");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 翻译专区预导入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preTranslateExl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			MatchedPapersActionForm vo = new MatchedPapersActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			String textName = ParamUtils.getParameter(request, "textName", false);
			request.setAttribute("textName", textName);
			
			return mapping.findForward("preTranslateExl");
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
			String username = "admin";
			
			
			
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				 
				String name = Tool.getValue("select mp_name from matched_papers where id='"+ids[i]+"' and is_del=1");
				
				Tool.AddLog(sessionContainer.getUserId(), username,
						"删除试卷,试卷名称:"+name, "1", ipaddress);
				
//			Tool.execute("delete from matched_papers where id = '"+ids[i]+"'");
			Tool.execute("update matched_papers set is_del=1 where id = '"+ids[i]+"'");	 
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

			String ID = ParamUtils.getParameter(request, "mId", true);
				 
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			String name = Tool.getValue("select mp_name from matched_papers where id='"+ID+"'");
			
			Tool.AddLog(sessionContainer.getUserId(), username,
					"删除试卷,试卷名称:"+name, "1", ipaddress);
			
			Tool.execute("update matched_papers set is_del=1 where id = '"+ID+"'");
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

		MatchedPapersActionForm vo = (MatchedPapersActionForm) form;
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
	MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
		String col20 = ""; //题，是否正确答案
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
				
				//查询年月类型,添加
				System.out.println(col0+"=========="+col2);
				String mptId = Tool.getValue("select id from matched_papers_type where code='"+col0+col2+"' ");
				if(mptId == null || mptId.equals("")){
					if(!"".equals(col0) && !"".equals(col2)){
						MatchedPapersType mpType = new MatchedPapersType();
						mpType.setTextTypeName(col9);
						mpType.setParentId(col0);
						mpType.setCode(col0+col2);
						mpType.setScId(col0);
						mpType.setCreatetime(DateUtils.getCurrDateTimeStr());
						mptId = matchedPapersTypeMgr.add(mpType);
					}
				}	
				
				//查询整套试卷,添加试卷
				String mpId =  Tool.getValue("select id from matched_papers where code='"+col0+col2+col3+"'");
				if(null == mpId || "".equals(mpId)){
					if(mptId != null && !mptId.equals("")){
						MatchedPapers mp = new MatchedPapers();
						mp.setMpName(col10);
						mp.setCreatetime(DateUtils.getCurrDateTimeStr());
						mp.setCode(col0+col2+col3);
						mp.setMptId(mptId);
						mpId = mgr.add(mp);
					}
				}
				//查询节点，添加 Section
				String mpthId = Tool.getValue("select id from matched_papers_topic_hearing where code='"+col0+col2+col3+col4+"'");
				if(null == mpthId || "".equals(mpthId)){
					if(!"".equals(mpId)){
						if(!"".equals(col0) && !"".equals(col2) && !"".equals(col3) && !"".equals(col4)){
							MatchedPapersTopicHearing mpth = new MatchedPapersTopicHearing();
							mpth.sethName(col11);
							mpth.setSubjectType(col6);
							mpth.sethContent(col12);
							mpth.setCode(col0+col2+col3+col4);
							mpth.setMpId(mpId);
							mpthId = mpthMgr.add(mpth);
						}
					}
				}
				//查询pasage ,添加
				String mpthtId = Tool.getValue("select id from matched_papers_topic_hearing_type where code='"+col0+col2+col3+col4+col5+"'");
				if(null == mpthtId || "".equals(mpthtId)){
					if(null != mpthId && !"".equals(mpthId)){
						if(!"".equals(col0) && !"".equals(col2) && !"".equals(col3) && !"".equals(col4) && !"".equals(col5)){
							MatchedPapersTopicHearingType mptht = new MatchedPapersTopicHearingType();
							mptht.setName(col13);
							mptht.setMpthId(mpthId);
							mptht.setUrl(col20);
							mptht.setContent(col14);
							mptht.setCode(col0+col2+col3+col4+col5);
							mpthtId = mpthtMgr.add(mptht);
						}
					}
				}
				//添加题
				if(null != mpthtId && !"".equals(mpthtId)){
					if(!"".equals(col15)){
						String mpthsId =  Tool.getValue("select id from matched_papers_topic_hearing_subject where mptht_id = '"+mpthtId+"' and s_number='"+col15+"'");
						if(null == mpthsId || "".equals(mpthsId)){
							MatchedPapersTopicHearingSubject mpths = new MatchedPapersTopicHearingSubject();
							mpths.setsNumber(Integer.parseInt(col15));
							mpths.setMpthtId(mpthtId);
							mpths.setcAnalysis(col19);
//						mpths.setCode(code);
							
							mpthsId =  mpthsMgr.add(mpths);
							if(null!=mpthsId && !mpthsId.equals("")){
								MatchedPapersTopicHearingSubjectChoice mpthsc = new MatchedPapersTopicHearingSubjectChoice();
								mpthsc.setcName(col16);
								mpthsc.setcContent(col17);
								if(!"".equals(col18)){
									mpthsc.setcIsAnswer(Integer.parseInt(col18));
								}else{
									mpthsc.setcIsAnswer(0);
								}
								mpthsc.setMtphcId(mpthsId);
								mpthscMgr.add(mpthsc);
							}
						}else{
							MatchedPapersTopicHearingSubjectChoice mpthsc = new MatchedPapersTopicHearingSubjectChoice();
							mpthsc.setcName(col16);
							mpthsc.setcContent(col17);
							if(!"".equals(col18)){
								mpthsc.setcIsAnswer(Integer.parseInt(col18));
							}else{
								mpthsc.setcIsAnswer(0);
							}
							mpthsc.setMtphcId(mpthsId);
							mpthscMgr.add(mpthsc);
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
	
//	request.setAttribute("total", input);
//	return list(actionMapping, actionForm, request, response);
	return null;

}

/**//*
 * 从Excel文件中读取数据，并导入到数据库中,阅读
 */
public ActionForward getUpload_reading(ActionMapping actionMapping,
	ActionForm actionForm, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
SessionContainer sessionContainer = (SessionContainer) request
		.getSession().getAttribute("SessionContainer");
if (null == sessionContainer)
	sessionContainer = new SessionContainer();
Map m = new HashMap();
// 获取excel 文件
MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
	String col0 = ""; //等级
	String col1 = ""; //等级
	String col2 = ""; //年月
	String col3 = ""; //套
	String col4 = ""; //节
	String col5 = ""; //模块
	String col6 = ""; //分组
	String col7 = ""; //月份类型
	String col8 = ""; //月份类型
	String col9 = ""; //级别
	String col10 = ""; //套名
	String col11 = ""; //节名
	String col12 = ""; //节名
	String col13 = ""; //pageage
	String col14 = ""; //pageage
	String col15 = ""; //题
	String col16 = ""; //节内容
	String col17 = ""; //题，选项
	String col18 = ""; //题，选项内容
	String col19 = ""; //题，选项内容
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
			if(hssfrow.getCell((short) 1)!=null){
				col1 =  hssfrow.getCell((short) 1).getStringCellValue()
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
			if(hssfrow.getCell((short) 7)!=null){
				col7 =  hssfrow.getCell((short) 7).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 8)!=null){
				col8 =  hssfrow.getCell((short) 8).getStringCellValue()
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
			
			//查询年月类型,添加
			String mptId = Tool.getValue("select id from matched_papers_type where code='"+col0+col1+"' ");
			if(mptId == null || mptId.equals("")){
				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(!"".equals(col0) && !"".equals(col1) ){
					MatchedPapersType mpType = new MatchedPapersType();
					mpType.setTextTypeName(col7);
					mpType.setParentId(col0);
					mpType.setCode(col0+col1);
					mpType.setScId(col0);
					mpType.setCreatetime(DateUtils.getCurrDateTimeStr());
					mptId = matchedPapersTypeMgr.add(mpType);
				}
			}	
			
			//查询整套试卷,添加试卷
			String mpId =  Tool.getValue("select id from matched_papers where is_del=0 and  code='"+col0+col1+col2+"'");
			if(null == mpId || "".equals(mpId)){
				if(mptId != null && !mptId.equals("")){
					if(!"".equals(col0) && !"".equals(col1) && !"".equals(col2) ){
						MatchedPapers mp = new MatchedPapers();
						mp.setMpName(col8);
						mp.setCreatetime(DateUtils.getCurrDateTimeStr());
						mp.setCode(col0+col1+col2);
						mp.setMptId(mptId);
						mpId = mgr.add(mp);
					}
				}
			}
			//查询节点，添加
			String mptrId = Tool.getValue("select id from matched_papers_topic_read where is_del=0 and code='"+col0+col1+col2+col3+"'");
			if(null == mptrId || "".equals(mptrId)){
				if(!"".equals(mpId)){
					if(!"".equals(col0) && !"".equals(col1) && !"".equals(col2) && !"".equals(col3) ){
						MatchedPapersTopicRead mptr = new MatchedPapersTopicRead();
						mptr.setCode(col0+col1+col2+col3);
						mptr.setrName(col9);
						mptr.setrContent(col10);
						mptr.setMpId(mpId);
						mptrId = mptrMgr.add(mptr);
					}
				}
			}
			
			//查询pasage ,添加
			String mptrtId = Tool.getValue("select id from matched_papers_topic_read_type where code='"+col0+col1+col2+col3+col4+"'");
			if(null == mptrtId || "".equals(mptrtId)){
				if(null != mptrId && !"".equals(mptrId)){
					if(!"".equals(col0) && !"".equals(col2) && !"".equals(col3) && !"".equals(col4) && !"".equals(col5)){
						
						MatchedPapersTopicReadType mptrt = new MatchedPapersTopicReadType();
						mptrt.setCode(col0+col1+col2+col3+col4);
						mptrt.setTypeName(col11);
						mptrt.setContent(col12);
						mptrt.setTranslateContent(col19);
						mptrt.setMptrId(mptrId);
						mptrtId = mptrtMgr.add(mptrt);
					}else{
						MatchedPapersTopicReadType mptrt = new MatchedPapersTopicReadType();
						mptrt.setCode(col0+col1+col2+col3+col4);
						mptrt.setTypeName(col11);
						mptrt.setContent(col12);
						mptrt.setTranslateContent(col19);
						mptrt.setMptrId(mptrId);
						mptrtId = mptrtMgr.add(mptrt);
					}
				}
			}
			
			if(null != mptrtId && !"".equals(mptrtId)){
				
			//添加题
				String mpthsId =  Tool.getValue("select id from matched_papers_topic_read_subject where is_del=0 and mptrt_id = '"+mptrtId+"' and s_number='"+col13+"'");
				if(null == mpthsId || "".equals(mpthsId))
				{
					 MatchedPapersTopicReadSubject mptrs = new MatchedPapersTopicReadSubject();
					 if(col13!=null && !"".equals(col13)){
						 mptrs.setsNumber(Integer.parseInt(col13));
					 }
					 mptrs.setcTitle(col14);
					 mptrs.setcAnalysis(col18);
					 mptrs.setMptrtId(mptrtId);
					 mpthsId =  mptrsMgr.add(mptrs);
					 
					if(null!=mpthsId && !mpthsId.equals("")){
						MatchedPapersTopicReadSubjectChoice mptrsc = new MatchedPapersTopicReadSubjectChoice();
						mptrsc.setcName(col15);
						mptrsc.setcContent(col16);
						if(null!=col17 && !"".equals(col17)){
							mptrsc.setcIsAnswer(Integer.parseInt(col17));
						}
						mptrsc.setMptrsId(mpthsId);
						mptrscMgr.add(mptrsc);
					}
				}else{
					MatchedPapersTopicReadSubjectChoice mptrsc = new MatchedPapersTopicReadSubjectChoice();
					mptrsc.setcName(col15);
					mptrsc.setcContent(col16);
					if(null!=col17 && !"".equals(col17)){
						mptrsc.setcIsAnswer(Integer.parseInt(col17));
					}
					mptrsc.setMptrsId(mpthsId);
					mptrscMgr.add(mptrsc);
				}
			}
			
				// 导入成功加1
				input++;
				System.out.println(input+"---------");
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

/**//*
 * 从Excel文件中读取数据，并导入到数据库中,写作
 */
public ActionForward getUpload_writing(ActionMapping actionMapping,
	ActionForm actionForm, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
SessionContainer sessionContainer = (SessionContainer) request
		.getSession().getAttribute("SessionContainer");
if (null == sessionContainer)
	sessionContainer = new SessionContainer();
Map m = new HashMap();
// 获取excel 文件
MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
	String col0 = ""; //等级
	String col1 = ""; //等级
	String col2 = ""; //年月
	String col3 = ""; //套
	String col4 = ""; //节
	String col5 = ""; //模块
	String col6 = ""; //分组
	String col7 = ""; //月份类型
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
				col0 = hssfrow.getCell((short) 0).getStringCellValue().trim();
			}

			/** 将EXCEL中的第 j 行，第3列的值插入到实例中 */
			if(hssfrow.getCell((short) 1)!=null){
				col1 = hssfrow.getCell((short) 1).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第3列的值插入到实例中 */
			if(hssfrow.getCell((short) 2)!=null){
				col2 = hssfrow.getCell((short) 2).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 4)!=null){
				col4 = hssfrow.getCell((short) 4).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 5)!=null){
				col5 = hssfrow.getCell((short) 5).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 6)!=null){
				col6 = hssfrow.getCell((short) 6).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 7)!=null){
				col7 = hssfrow.getCell((short) 7).getStringCellValue()
						.trim();
			}
			//查询年月类型,添加
			String mptId = Tool.getValue("select id from matched_papers_type where code='"+col0+col1+"' ");
			if(mptId == null || mptId.equals("")){
				if(!"".equals(col0)&&!"".equals(col1)){
					MatchedPapersType mpType = new MatchedPapersType();
					mpType.setTextTypeName(col4);
					mpType.setParentId(col0);
					mpType.setCode(col0+col1);
					mpType.setScId(col0);
					mpType.setCreatetime(DateUtils.getCurrDateTimeStr());
					mptId = matchedPapersTypeMgr.add(mpType);
				}
			}	
			
			//查询整套试卷,添加试卷
			String mpId =  Tool.getValue("select id from matched_papers where code='"+col0+col1+col2+"'");
			if(null == mpId || "".equals(mpId)){
				if(mptId != null && !mptId.equals("")){
					if(!"".equals(col0) && !"".equals(col1) && !"".equals(col2)){
						MatchedPapers mp = new MatchedPapers();
						mp.setMpName(col5);
						mp.setCreatetime(DateUtils.getCurrDateTimeStr());
						mp.setCode(col0+col1+col2);
						mp.setMptId(mptId);
						mpId = mgr.add(mp);
						MatchedPapersTopicWriting mptw = new MatchedPapersTopicWriting();
						mptw.setwContent(col6);
						mptw.setwTest(col7);
						mptw.setMpId(mpId);
						mptwMgr.add(mptw);
					}
				}else{
					MatchedPapersTopicWriting mptw = (MatchedPapersTopicWriting)mptwMgr.getObjectByHql("from MatchedPapersTopicWriting where mpId='"+mpId+"'");
					if(null!=mptw){
						mptw.setwContent(col6);
						mptw.setwTest(col7);
						mptwMgr.update(mptw);
					}else{
						mptw = new MatchedPapersTopicWriting();
						mptw.setwContent(col6);
						mptw.setwTest(col7);
						mptwMgr.add(mptw);
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
/**//*
 * 从Excel文件中读取数据，并导入到数据库中,翻译
 */
public ActionForward getUpload_translate(ActionMapping actionMapping,
	ActionForm actionForm, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
	String textName = ParamUtils.getParameter(request, "textName", false);
//	if(textName==null || textName.equals("")){
//		return null;
//	}
SessionContainer sessionContainer = (SessionContainer) request
		.getSession().getAttribute("SessionContainer");
if (null == sessionContainer)
	sessionContainer = new SessionContainer();
Map m = new HashMap();
// 获取excel 文件
MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
	String col0 = ""; //等级
	String col1 = ""; //等级
	String col2 = ""; //年月
	String col4 = ""; //节
	String col5 = ""; //模块
	String col6 = ""; //分组
	String col7 = ""; //月份类型
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
			if(hssfrow.getCell((short) 1)!=null){
				col1 =  hssfrow.getCell((short) 1).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第3列的值插入到实例中 */
			if(hssfrow.getCell((short) 2)!=null){
				col2 =  hssfrow.getCell((short) 2).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 4)!=null){
				col4 =  hssfrow.getCell((short) 4).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
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
			if(hssfrow.getCell((short) 7)!=null){
				col7 =  hssfrow.getCell((short) 7).getStringCellValue()
						.trim();
			}
			
			//查询年月类型,添加
			String mptId = Tool.getValue("select id from matched_papers_type where code='"+col0+col1+"' ");
			if(mptId == null || mptId.equals("")){
				if(!"".equals(col0) && !"".equals(col1)){
					MatchedPapersType mpType = new MatchedPapersType();
					mpType.setTextTypeName(col4);
					mpType.setParentId("FFFFFF");
					mpType.setCode(col0+col1);
					mpType.setScId(col0);
					mpType.setCreatetime(DateUtils.getCurrDateTimeStr());
					mptId = matchedPapersTypeMgr.add(mpType);
				}
			}	
			
			//查询整套试卷,添加试卷
			String mpId =  Tool.getValue("select id from matched_papers where code='"+col0+col1+col2+"'");
			if(null == mpId || "".equals(mpId)){
				if(mptId != null && !mptId.equals("")){
					if(!"".equals(col0) && !"".equals(col1) && !"".equals(col2) ){
						MatchedPapers mp = new MatchedPapers();
						mp.setMpName(col5);
						mp.setCreatetime(DateUtils.getCurrDateTimeStr());
						mp.setCode(col0+col1+col2);
						mp.setMptId(mptId);
						mpId = mgr.add(mp);
						MatchedPapersTopicTranslate mptt = new MatchedPapersTopicTranslate();
						mptt.settContent(col6);
						mptt.settTest(col7);
						mptt.setMpId(mpId);
						mpttMgr.add(mptt);
					}
				}
			}else{
				MatchedPapersTopicTranslate mptt = (MatchedPapersTopicTranslate)mptwMgr.getObjectByHql("from MatchedPapersTopicTranslate where mpId='"+mpId+"'");
				if(null != mptt){
					mptt.settContent(col6);
					mptt.settTest(col7);
					mpttMgr.update(mptt);
				}else{
					mptt = new MatchedPapersTopicTranslate();
					mptt.settContent(col6);
					mptt.settTest(col7);
					mptt.setMpId(mpId);
					mpttMgr.add(mptt);
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


/**
 * 从Excel文件中读取数据，并导入到数据库中,写作专区
 */
public ActionForward zoneWriting(ActionMapping actionMapping,
	ActionForm actionForm, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
SessionContainer sessionContainer = (SessionContainer) request
		.getSession().getAttribute("SessionContainer");
if (null == sessionContainer)
	sessionContainer = new SessionContainer();
Map m = new HashMap();
// 获取excel 文件
MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
	String col0 = ""; //等级
	String col5 = ""; //套名
	String col6 = ""; //文本
	String col7 = ""; //范文和点评
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
				col0 = hssfrow.getCell((short) 0).getStringCellValue().trim();
			}

			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 5)!=null){
				col5 = hssfrow.getCell((short) 5).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 6)!=null){
				col6 = hssfrow.getCell((short) 6).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 7)!=null){
				col7 = hssfrow.getCell((short) 7).getStringCellValue()
						.trim();
			}
			
			//查询整套试卷,添加试卷
				if("01".equals(col0) ){
						MatchedPapers mp = new MatchedPapers();
						mp.setMpName(col5);
						mp.setCreatetime(DateUtils.getCurrDateTimeStr());
						mp.setCode("");
						mp.setSort(1);
						mp.setMptId("56737d630d2209a429202237");
						String mpId = mgr.add(mp);
						if(!"".equals(mpId)){
							MatchedPapersTopicWriting mptw = new MatchedPapersTopicWriting();
							mptw.setwContent(col6);
							mptw.setwTest(col7);
							mptw.setMpId(mpId);
							mptwMgr.add(mptw);
						}
				}else if("02".equals(col0)){
					MatchedPapers mp = new MatchedPapers();
					mp.setMpName(col5);
					mp.setCreatetime(DateUtils.getCurrDateTimeStr());
					mp.setCode("");
					mp.setSort(1);
					mp.setMptId("402880ee552d89fa01552dc99c510004");
					String mpId = mgr.add(mp);
					if(!"".equals(mpId)){
						MatchedPapersTopicWriting mptw = new MatchedPapersTopicWriting();
						mptw.setwContent(col6);
						mptw.setwTest(col7);
						mptw.setMpId(mpId);
						mptwMgr.add(mptw);
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

/**
 * 从Excel文件中读取数据，并导入到数据库中,翻译专区
 */
public ActionForward zoneTranslate(ActionMapping actionMapping,
	ActionForm actionForm, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
SessionContainer sessionContainer = (SessionContainer) request
		.getSession().getAttribute("SessionContainer");
if (null == sessionContainer)
	sessionContainer = new SessionContainer();
Map m = new HashMap();
// 获取excel 文件
MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
	String col0 = ""; //等级
	String col5 = ""; //套名
	String col6 = ""; //范文和点评
	String col7 = ""; //范文和点评
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
				col0 = hssfrow.getCell((short) 0).getStringCellValue().trim();
			}

			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 5)!=null){
				col5 = hssfrow.getCell((short) 5).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 6)!=null){
				col6 = hssfrow.getCell((short) 6).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 7)!=null){
				col7 = hssfrow.getCell((short) 7).getStringCellValue()
						.trim();
			}
			
			//查询整套试卷,添加试卷
				if("01".equals(col0) ){
					MatchedPapers mp = new MatchedPapers();
					mp.setMpName(col5);
					mp.setCreatetime(DateUtils.getCurrDateTimeStr());
					mp.setCode("");
					mp.setMptId("56737bb50d2209a4291ffecd");
					String mpId = mgr.add(mp);
					if(!"".equals(mpId)){
						MatchedPapersTopicTranslate mptt = new MatchedPapersTopicTranslate();
						mptt.settContent(col6);
						mptt.settTest(col7);
						mptt.setMpId(mpId);
						mpttMgr.add(mptt);
					}
				}else if("02".equals(col0)){
					MatchedPapers mp = new MatchedPapers();
					mp.setMpName(col5);
					mp.setCreatetime(DateUtils.getCurrDateTimeStr());
					mp.setCode("");
					mp.setMptId("402880ee552d89fa01552dc97f5b0003");
					String mpId = mgr.add(mp);
					if(!"".equals(mpId)){
						MatchedPapersTopicTranslate mptt = new MatchedPapersTopicTranslate();
						mptt.settContent(col6);
						mptt.settTest(col7);
						mptt.setMpId(mpId);
						mpttMgr.add(mptt);
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
public ActionForward mpthsList(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
	SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
	if(null==sessionContainer)
		sessionContainer=new SessionContainer();
	try {
		 
		// 接收传值
		String mpId = ParamUtils.getParameter(request, "mpId", false);
		//String loginName = ParamUtils.getParameter(request, "loginName", false);
		// 设置查询条件
		List<Map> lm = new ArrayList<Map>();
		//查询section
		List<MatchedPapersTopicHearing> mpthList = Tool.findListByHql("from MatchedPapersTopicHearing where mpId='"+mpId+"' and isDel=0");
		for (MatchedPapersTopicHearing hear : mpthList) {
			//查询Passage
			List<MatchedPapersTopicHearingType> mpthtList = Tool.findListByHql("from MatchedPapersTopicHearingType where mpthId='"+hear.getId()+"' and isDel=0");
			for (MatchedPapersTopicHearingType htype : mpthtList) {
			  List<MatchedPapersTopicHearingSubject> mpthsList = Tool.findListByHql("from MatchedPapersTopicHearingSubject where mpthtId='"+htype.getId()+"' and isDel=0");
			  for (MatchedPapersTopicHearingSubject mpths : mpthsList) {
				Map m = new HashMap();
				m.put("pasName", htype.getName());
				m.put("mpthsId", mpths.getId());
				m.put("sNumber", mpths.getsNumber());
				String content = Tool.getValue("select c_content from matched_papers_topic_hearing_subject_choice where mtphc_id='"+mpths.getId()+"' and is_del=0 limit 1");
				m.put("content", content);
				lm.add(m);
			  }
			}
		}
		
		request.setAttribute("mpId", mpId);
		request.setAttribute("lm",lm);
		 
		return mapping.findForward("mpthsList");
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
String code = ParamUtils.getParameter(request, "code", false);
Map m = new HashMap();
// 获取excel 文件
MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
			System.out.println(col0+"-------");
			if(!"".equals(col0)){
				String hql = "select mptht.id from matched_papers mp,matched_papers_type mpt,matched_papers_topic_hearing mpth,matched_papers_topic_hearing_type mptht where mp.id=mpth.mp_id and mpth.id=mptht.mpth_id and mpth.is_del=0 and mptht.is_del=0 and mp_name='"+col0+"' and mpt.id = mp.mpt_id and mpt.text_type_name='听力专区' and mpt.sc_id='"+code+"' and mp.is_del=0 and mpth.is_del=0 and mptht.is_del=0  limit 1";
				mpthtId = Tool.getValue(hql);
				System.out.println(mpthtId);
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

return null;

}


/**//*
 * 从Excel文件中读取数据，并导入到数据库中,听力专区
 */
public ActionForward hearArea(ActionMapping actionMapping,
	ActionForm actionForm, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
SessionContainer sessionContainer = (SessionContainer) request
		.getSession().getAttribute("SessionContainer");
if (null == sessionContainer)
	sessionContainer = new SessionContainer();
Map m = new HashMap();
// 获取excel 文件
MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
	String col0 = ""; //等级
	String col1 = "";
	String col2 = "";
	String col3 = "";
	String col10 = ""; //套名
	String col12 = ""; //套名
	String col14 = ""; //套名
	String col20 = ""; //套名
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
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 1)!=null){
				col1 =  hssfrow.getCell((short) 1).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
			if(hssfrow.getCell((short) 3)!=null){
				col3 =  hssfrow.getCell((short) 3).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 10)!=null){
				col10 =  hssfrow.getCell((short) 10).getStringCellValue()
						.trim();
			}
			
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 12)!=null){
				col12 =  hssfrow.getCell((short) 12).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 14)!=null){
				col14 =  hssfrow.getCell((short) 14).getStringCellValue()
						.trim();
			}
			/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
			if(hssfrow.getCell((short) 20)!=null){
				col20 =  hssfrow.getCell((short) 20).getStringCellValue()
						.trim();
			}
			
			if(col0.equals("01")){
				col2 = "06";
			}else if(col0.equals("02")){
				col2 = "03";
			}
			String mptId = Tool.getValue("select id from matched_papers_type where code='"+col0+col2+"' ");
			//查询整套试卷,添加试卷
			String mpId =  Tool.getValue("select id from matched_papers where is_del=0 and code='"+col0+col2+col1+col3+"'");
			if(null == mpId || "".equals(mpId)){
				if(mptId != null && !mptId.equals("")){
					MatchedPapers mp = new MatchedPapers();
					mp.setMpName(col10);
					mp.setCreatetime(DateUtils.getCurrDateTimeStr());
					mp.setCode(col0+col2+col1+col3);
					mp.setMptId(mptId);
					mpId = mgr.add(mp);
					if(!"".equals(mpId)){ //添加secton
						MatchedPapersTopicHearing mpth = new MatchedPapersTopicHearing();
						mpth.sethName("Section A");
						mpth.setSubjectType("01");
						mpth.sethContent(col12);
						mpth.setCode("");
						mpth.setMpId(mpId);
						String mpthId = mpthMgr.add(mpth);
						if(!"".equals(mpthId)){
							MatchedPapersTopicHearingType mptht = new MatchedPapersTopicHearingType();
							mptht.setName("Passage One");
							mptht.setMpthId(mpthId);
							mptht.setUrl(col20);
							mptht.setContent(col14);
							mptht.setCode("");
							mpthtMgr.add(mptht);
						}
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
	/**
	 * 预修改信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preUpdateRead(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
		try {
	
			String ID = ParamUtils.getParameter(request, "id", true);//試卷ID
			//通过试卷ID获取所有的题目ID
			List<MatchedPapersTopicReadSubject> mptrsl=new ArrayList();
			//先获取section的ID
			MatchedPapersTopicRead mptr=(MatchedPapersTopicRead)mgr.getObjectByHql("from MatchedPapersTopicRead where mpId='"+ID+"' and subjectType='02' and 1=1");
			if(!"".equals(mptr)&&null!=mptr){
				//获取passage
				MatchedPapersTopicReadType mptrt=(MatchedPapersTopicReadType)mgr.getObjectByHql("from MatchedPapersTopicReadType where mptrId='"+mptr.getId()+"' and 1=1");
				if(!"".equals(mptrt)&&null!=mptrt){
					//获取所有的题目
					mptrsl=(List<MatchedPapersTopicReadSubject>)mgr.getListByHql("from MatchedPapersTopicReadSubject where mptrtId='"+mptrt.getId()+"' order by sNumber asc", 0, 0);
				}
			}
			request.setAttribute("mptrsl", mptrsl);
			return mapping.findForward("preUpdateRead");
		} catch (Exception ex) {
	
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
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
	public ActionForward updateRead(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
		try {
			//获取对应的数量
			String num=ParamUtils.getParameter(request, "num");
			for(int i=0;i<10;i++){
				String tId=ParamUtils.getParameter(request, "tId"+i);
				String vl=ParamUtils.getParameter(request, "vl"+i);

				if(!"".equals(tId)&&null!=tId&&!"".equals(vl)&&null!=vl&&!"".equals(num)&&null!=num){
					//删除题目对应的选项
					Tool.execute("delete from matched_papers_topic_read_subject_choice where mptrs_id='"+tId+"' and 1=1");
					//循环num生产选项
					for(int x=1;x<=Integer.valueOf(num);x++){
						MatchedPapersTopicReadSubjectChoice m=new MatchedPapersTopicReadSubjectChoice();
						m.setcContent(this.getWord(x));
						m.setcName(this.getWord(x));
						m.setCode(this.getWord(x));
						m.setcSort(x);
						m.setIsDel(0);
						m.setMptrsId(tId);
						if(vl.equals(this.getWord(x))){
							m.setcIsAnswer(1);
						}else{
							m.setcIsAnswer(0);
						}
						mgr.add(m);
					}
				}
			}
			return null;
		} catch (Exception ex) {
	
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	private String getWord(int i){
		String st="A";
		if(i==1){
			st="A";
		}else if(i==2){
			st="B";
		}else if(i==3){
			st="C";
		}else if(i==4){
			st="D";
		}else if(i==5){
			st="E";
		}else if(i==6){
			st="F";
		}else if(i==7){
			st="G";
		}else if(i==8){
			st="H";
		}else if(i==9){
			st="I";
		}else if(i==10){
			st="G";
		}else if(i==11){
			st="K";
		}else if(i==12){
			st="L";
		}else if(i==13){
			st="M";
		}else if(i==14){
			st="N";
		}else if(i==15){
			st="O";
		}else if(i==16){
			st="P";
		}else if(i==17){
			st="Q";
		}else if(i==18){
			st="R";
		}else if(i==19){
			st="S";
		}else if(i==20){
			st="T";
		}else if(i==21){
			st="U";
		}else if(i==22){
			st="V";
		}else if(i==23){
			st="W";
		}else if(i==24){
			st="X";
		}else if(i==25){
			st="Y";
		}else if(i==26){
			st="Z";
		}
		return st;
	}
	
	
	/**//*
	 * 从Excel文件中读取数据，并导入到数据库中,试卷解析修改
	 */
	public ActionForward mpAnalysis(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
	if (null == sessionContainer)
		sessionContainer = new SessionContainer();
	Map m = new HashMap();
	// 获取excel 文件
	MatchedPapersActionForm fm = (MatchedPapersActionForm) actionForm;
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
		String col0 = ""; //等级
		String col1 = "";
		String col2 = "";
		String col3 = "";
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
					col0 =  hssfrow.getCell((short) 0).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(hssfrow.getCell((short) 1)!=null){
					col1 =  hssfrow.getCell((short) 1).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(hssfrow.getCell((short) 2)!=null){
//					col2 =  hssfrow.getCell((short) 2).getStringCellValue()
//							.trim();
					col2 = 	String.valueOf(hssfrow.getCell((short) 2).getNumericCellValue());     
					if(null!=col2&&!"".equals(col2)){
						col2 = col2.substring(0, col2.indexOf("."));
					}
				}
				/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
				if(hssfrow.getCell((short) 3)!=null){
					col3 =  hssfrow.getCell((short) 3).getStringCellValue()
							.trim();
				}
				String mptId="";
				//判断4、6级全真模拟
				if(col0.equals("01"))
					mptId = "f2b7d7d6556bd8a3015572032c1f01d7";
				else if(col0.equals("02"))
					mptId = "f2b7d7d6556bd8a3015572078d7501dc";
				
				String sql ="select mpths.id as ID from matched_papers_type mpt,matched_papers mp,matched_papers_topic_hearing mpth,matched_papers_topic_hearing_type mptht,matched_papers_topic_hearing_subject mpths"
					+" where mpt.id=mp.mpt_id"
					+" and mp.id=mpth.mp_id"
				    +" and mpth.id=mptht.mpth_id"
				    +" and mptht.id = mpths.mptht_id"
				    +" and mpt.sc_id='"+col0+"'"
				    +" and mp.mp_name='"+col1+"'"
				    +" and mpths.s_number = '"+col2+"'"
				    +" and mp.is_del=0"
				    +" and mpth.is_del=0"
				    +" and mpths.is_del=0"
				    +" and mpt.id='"+mptId+"'";
					
				String mpthsId = "";
				List<Map> lm = Tool.query(sql);
				if(lm.size()>0){
					mpthsId = lm.get(0).get("ID").toString();
				}
				System.out.println(mpthsId+"----------"+col2);
				System.out.println(sql);
				MatchedPapersTopicHearingSubject mpths = (MatchedPapersTopicHearingSubject)mgr.getObjectByHql("from MatchedPapersTopicHearingSubject where id='"+mpthsId+"'");
				if(null != mpths){
					mpths.setcAnalysis(col3);
					mpthsMgr.update(mpths);
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
	
	/**
	 * 预维护视频
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward preUpdateVideo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String mpId = ParamUtils.getParameter(request, "mpId", false);
			MatchedPapersVideo item = (MatchedPapersVideo) mgr.getObjectByHql("from MatchedPapersVideo where isDel='0' and mpId='" + mpId + "'");
			if(item == null) {
				item = new MatchedPapersVideo();
				item.setMpId(mpId);
			}
			request.setAttribute("item", item);
			
			return mapping.findForward("updateVideo");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取数据时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			
			return mapping.findForward("DialogBox");
		}
	}
    
    /**
     * 维护视频
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward updateVideo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	String mpId = ParamUtils.getParameter(request, "mpId", false);
        	String videoTitle = ParamUtils.getParameter(request, "videoTitle", false);
        	String videoId = ParamUtils.getParameter(request, "videoId", false);
        	String videoUrl = ParamUtils.getParameter(request, "videoUrl", false);
        	
        	MatchedPapersVideo video = (MatchedPapersVideo) mgr.getObjectByHql("from MatchedPapersVideo where isDel='0' and mpId='" + mpId + "'");
        	if(video == null) {
        		video = new MatchedPapersVideo(UUID.randomUUID().toString().replaceAll("-", ""));
        		video.setMpId(mpId);
        		video.setVideoTitle(videoTitle);
        		video.setVideoId(videoId);
        		video.setVideoUrl(videoUrl);
        		video.setIsDel("0");
            	mgr.save(video);
        	} else {
        		video.setVideoTitle(videoTitle);
        		video.setVideoId(videoId);
        		video.setVideoUrl(videoUrl);
        		mgr.update(video);
        	}
        	
        	String ipaddress = IpAddressUtil.getIpAddr(request);
        	SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
        	if (null == sessionContainer) {
        		sessionContainer = new SessionContainer();
        	}
        	String username = "admin";
        	Tool.AddLog(sessionContainer.getUserId(), username, "维护字幕听力的视频,名称:"+video.getVideoTitle(), "1", ipaddress);
        	
        	map.put("result", true);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            map.put("result",false);
        }

        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
    
    /**
     * 删除视频
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward deleteVideo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		String id = ParamUtils.getParameter(request, "id", false);
    		
    		MatchedPapersVideo video = (MatchedPapersVideo) mgr.getObjectByHql("from MatchedPapersVideo where id='" + id + "'");
    		if(video != null) {
    			video.setIsDel("1");
    			mgr.update(video);
    		}
    		
    		String ipaddress = IpAddressUtil.getIpAddr(request);
    		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
    		if (null == sessionContainer) {
    			sessionContainer = new SessionContainer();
    		}
    		String username = "admin";
    		Tool.AddLog(sessionContainer.getUserId(), username, "删除字幕听力的视频,名称:"+video.getVideoTitle(), "1", ipaddress);
    		
    		map.put("result", true);
    	} catch (Exception ex) {
    		log.error(ex.getMessage(), ex);
    		WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
    		request.setAttribute("DialogBox", dialog);
    		map.put("result",false);
    	}
    	
    	JsonUtils.outputJsonResponse(response, map);
    	return null;
    }
}
