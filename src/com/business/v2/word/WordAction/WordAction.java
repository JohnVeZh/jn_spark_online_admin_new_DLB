package com.business.v2.word.WordAction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.hibernate.Query;
import org.hibernate.Session;

import com.business.v2.question.TbQuestionTranslation;
import com.business.v2.questionTranslation.TbQuestionTranslationActionForm;
import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.word.TbWord;
import com.business.v2.word.TbWordQuestion;
import com.business.v2.word.TbWordQuestionOption;
import com.easecom.common.framework.hibernate.BaseModel;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class WordAction extends BaseAction{
	private static LogUtils logger = LogUtils.getLogger(TbWord.class);
	WordMgr mgr=new WordMgr();
	/**
	 * 生成功能树
	 * 
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward treelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String sql="";
			
			List treelist = null;
			treelist = mgr.getFuntree(sql);

			request.setAttribute("treelist", treelist);
			request.setAttribute("Pid", "010101");
			return mapping.findForward("tree");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取功能树时出错", "返回", "javascript:window.history.back()");

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
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
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
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			
			// 接收传值
			String pid = ParamUtils.getParameter(request, "Pid", true);
			String wordName = ParamUtils.getParameter(request, "wordName", true);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("c.word", "String", "like", wordName));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage,pid);

			request.setAttribute("lc", lc);
			request.setAttribute("wordName", wordName);
			request.setAttribute("parentid", pid);
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
	 * 查看选项
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preOption(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			// 接收传值
			String id = ParamUtils.getParameter(request, "id", true);
			String word = Tool.getValue("select word from tb_word where id='"+id+"'" );
			Session ses = HibernateSessionFactory.openSession();

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
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			
			Collection queryConds = new ArrayList();

			ListContainer lc = mgr.listByObj(queryConds, currentPageInt,
					itemsInPage, action, jumpPage,id);

			request.setAttribute("lc", lc);
			request.setAttribute("word", word);
			request.setAttribute("wordId", id);
			return mapping.findForward("questionOptionList");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	/**
	 * 单词详情
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			// 接收传值
			String id = ParamUtils.getParameter(request, "id", true);
			
			Session ses = HibernateSessionFactory.openSession();
			Query query = ses.createSQLQuery("select a.*,b.sort_order sortOrder from  tb_word a LEFT JOIN tb_word_section b ON a.id=b.word_id where a.id='"+id+"'").addEntity(TbWord.class);
			List list = query.list();
			Query querys = ses.createSQLQuery("SELECT b.* FROM tb_word_section a LEFT JOIN tb_special_catalog b ON a.catalog_id=b.id where a.word_id='"+id+"'").addEntity(TbSpecialCatalog.class);
			if(querys.list().size()>0){
				TbSpecialCatalog tsc = (TbSpecialCatalog) querys.list().get(0);
				String name = tsc.getName();
				request.setAttribute("name", name);
			}
			if(list.size()>0){
				request.setAttribute("TbWord", list.get(0));
			}else{
				request.setAttribute("TbWord", null);
			}
			return mapping.findForward("WordView");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("WordView");
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	
	/**
	 * 预修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			// 接收传值
			String id = ParamUtils.getParameter(request, "id", true);
			
			Session ses = HibernateSessionFactory.openSession();
			Query query = ses.createSQLQuery("select a.*,b.sort_order sortOrder from  tb_word a LEFT JOIN tb_word_section b ON a.id=b.word_id where a.id='"+id+"'").addEntity(TbWord.class);
			List list = query.list();
			Query querys = ses.createSQLQuery("SELECT b.* FROM tb_word_section a LEFT JOIN tb_special_catalog b ON a.catalog_id=b.id where a.word_id='"+id+"'").addEntity(TbSpecialCatalog.class);
			if(querys.list().size()>0){
				TbSpecialCatalog tsc = (TbSpecialCatalog) querys.list().get(0);
				String name = tsc.getName();
				String n=tsc.getId();
				if(n.length()>6){
					String ids = tsc.getId();
					if(ids.substring(0, 4).equals("0101")){
						request.setAttribute("type", "四级");
					}else if(ids.substring(0, 4).equals("0201")){
						request.setAttribute("type", "六级");
						
					}
					if(ids.substring(4, 6).equals("01")){
						request.setAttribute("types", "大纲词");
					}else if(ids.substring(4, 6).equals("02")){
						request.setAttribute("types", "核心词");
					}else if(ids.substring(4, 6).equals("03")){
						request.setAttribute("types", "高频词");
					}
				}
				request.setAttribute("name", name);
			}
			if(list.size()>0){
				request.setAttribute("TbWord", list.get(0));
			}
			return mapping.findForward("update");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}	
	
	/**
	 * 修改单词
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			TbWordActionForm vo = (TbWordActionForm) form;	
			String sql="UPDATE tb_word SET word="+getFormatField(vo.getWord())+",phonetic_symbol="+getFormatField(vo.getPhoneticSymbol())+""
					+ ",pronunciation_url="+getFormatField(vo.getPronunciationUrl())+",paraphrase="+getFormatField(vo.getParaphrase())+""
					+ ",example_sentence="+getFormatField(vo.getExampleSentence())+",example_reference="+getFormatField(vo.getExampleReference())+""
					+ ",example_url="+getFormatField(vo.getExampleUrl())+" where id="+getFormatField(vo.getId())+"";
			
			mgr.SQLExecute(sql);
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username, "修改单词,单词名称:"+vo.getWord(), "1", ipaddress);
			
			request.setAttribute("msg", "200");
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 预添加
	 * 
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			// 接收传值
			String Pid = ParamUtils.getParameter(request, "Pid", true);
			
			Session ses = HibernateSessionFactory.openSession();
			Query querys = ses.createSQLQuery("SELECT * FROM tb_special_catalog where id='"+Pid+"'").addEntity(TbSpecialCatalog.class);
			if(querys.list().size()>0){
				TbSpecialCatalog tsc = (TbSpecialCatalog) querys.list().get(0);
				String type = tsc.getName();
				String n=tsc.getPId();
				String id = Tool.getValue("SELECT p_id from tb_special_catalog where id='"+n+"'");
				String types = Tool.getValue("SELECT name from tb_special_catalog where id='"+id+"'");
				request.setAttribute("type", type);
				request.setAttribute("types", types);
				request.setAttribute("ids", id);
			}
			request.setAttribute("Pid", Pid);
			return mapping.findForward("add");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	
	/**
	 * 添加
	 * 
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){
			sessionContainer=new SessionContainer();
		}
		try {
			Session ses = HibernateSessionFactory.openSession();
			String Pid = ParamUtils.getParameter(request, "Pid", true);//eg:010102
			String ids = ParamUtils.getParameter(request, "ids", true);//eg:010102
			if(Pid==null || Pid.equals("")){
				WebDialogBox dialog = new WebDialogBox(1, "错误", "添加获不到值", "返回", "javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
			if(ids==null || ids.equals("")){
				WebDialogBox dialog = new WebDialogBox(1, "错误", "添加获不到值", "返回", "javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
			TbWordActionForm vo = (TbWordActionForm) form;
			vo.setId(UUID.randomUUID().toString().replace("-", ""));
			String phoneticSymbol = vo.getPhoneticSymbol();
			String phoneticSymbols="\""+phoneticSymbol+"\"";
			Tool.testReflect_admin(vo);
			String addWordSql="INSERT INTO tb_word(id,word,phonetic_symbol,pronunciation_url,paraphrase,example_sentence,example_reference,example_url,create_time)"
					+ "values("+getFormatField(vo.getId())+","+getFormatField(vo.getWord())+","+phoneticSymbols+","
					+ ""+getFormatField(vo.getPronunciationUrl())+","+getFormatField(vo.getParaphrase())+","+getFormatField(vo.getExampleSentence())+","
					+ ""+getFormatField(vo.getExampleReference())+","+getFormatField(vo.getExampleUrl())+",'"+DateUtils.getCurrDateTimeStr()+"')";
			//分组的最大ID
			String MaxTSCId = Tool.getValue("select max(id) from tb_special_catalog where id like '"+Pid+"%' and id != '"+Pid+"'");
			if(MaxTSCId==null || "".equals(MaxTSCId)){
				String catalogId=Pid+"0001";
				String groudName="第1组";
				mgr.SQLExecute("INSERT INTO tb_special_catalog(id,p_id,name,icon_url,sort_order,remarks,is_del,create_time)VALUES('"+catalogId+"','"+Pid+"','"+groudName+"',null,"+1+",null,0,'"+DateUtils.getCurrDateTimeStr()+"')");
				mgr.SQLExecute("INSERT INTO tb_word_section(id,word_id,section,catalog_id,sort_order)"
						+ "values("+getFormatField(UUID.randomUUID().toString().replace("-", ""))+","+getFormatField(vo.getId())+",'"+ids+"','"+catalogId+"',"+1+")");
				mgr.SQLExecute(addWordSql);
				request.setAttribute("msg", "200");
				return list(mapping, form, request, response);
			}
			//最大分组里已经存才多少个
			String MaxCount = Tool.getValue("select count(*) from tb_word_section where  catalog_id = '"+MaxTSCId+"'");
			//最大排序号
			String MaxSortOrder = Tool.getValue("select max(sort_order) from tb_word_section where  catalog_id = '"+MaxTSCId+"'");
			//判断最大分组
			int maxID=Integer.parseInt(MaxTSCId);
			//最大分组ID里有多少个单词
			int maxcounts=Integer.parseInt(MaxCount);
			//最大分组ID里单词最大排序
			int SortOrder=Integer.parseInt(MaxSortOrder);
			
			int sortOrders=SortOrder+1;
			if(maxcounts==20 || maxcounts>20){
				//最大分组号
				String MaxSord = Tool.getValue("select max(sort_order) from tb_special_catalog where id like '"+Pid+"%'");
				//判断最大分组号
				int MaxSords=Integer.parseInt(MaxSord);
				int maxAddSord=MaxSords+1;
				int addId=maxID+1;
				String addIds="0"+addId;
				String groudByName="第"+maxAddSord+"组";
				mgr.SQLExecute("INSERT INTO tb_special_catalog(id,p_id,name,icon_url,sort_order,remarks,is_del,create_time)VALUES('"+addIds+"','"+Pid+"','"+groudByName+"',null,"+maxAddSord+",null,0,'"+DateUtils.getCurrDateTimeStr()+"')");
				mgr.SQLExecute("INSERT INTO tb_word_section(id,word_id,section,catalog_id,sort_order)"
						+ "values("+getFormatField(UUID.randomUUID().toString().replace("-", ""))+","+getFormatField(vo.getId())+",'"+ids+"','"+addIds+"',"+sortOrders+")");
				mgr.SQLExecute(addWordSql);
			}else{
				mgr.SQLExecute("INSERT INTO tb_word_section(id,word_id,section,catalog_id,sort_order)"
							+ "values("+getFormatField(UUID.randomUUID().toString().replace("-", ""))+","+getFormatField(vo.getId())+",'"+ids+"','"+MaxTSCId+"',"+sortOrders+")");
				mgr.SQLExecute(addWordSql);
			}
			request.setAttribute("msg", "200");
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	
	/**
	 * 预添加单词选项
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preWordQuestionOptionAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			// 接收传值
			String wordId = ParamUtils.getParameter(request, "wordId", true);
			String wordName = Tool.getValue("select word from tb_word where id='"+wordId+"'");
			request.setAttribute("wordId", wordId);
			request.setAttribute("wordName", wordName);
			return mapping.findForward("preWordQuestionOptionAdd");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}	
	
	/**
	 * 添加单词选项
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward WordQuestionOptionAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 接收传值
			String wordId = ParamUtils.getParameter(request, "wordId", true);
			if(wordId.equals("") || wordId==null){
				WebDialogBox dialog = new WebDialogBox(1, "错误", "获取单词时出错", "返回", "javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
			String prefix = ParamUtils.getParameter(request, "prefix", true);
			String content = ParamUtils.getParameter(request, "content", true);
			String isAnswer = ParamUtils.getParameter(request, "isAnswer", true);
			if(isAnswer.equals("") || isAnswer==null){
				isAnswer="0";
			}
			String wordQuestionOptionID=UUID.randomUUID().toString().replace("-", "");
			String wordQuestionID=UUID.randomUUID().toString().replace("-", "");
			String WordQuestionOptionSQL="INSERT INTO tb_word_question_option(id,question_id,prefix,content,is_answer)values('"+wordQuestionOptionID+"','"+wordQuestionID+"','"+prefix+"','"+content+"','"+isAnswer+"')";
			String WordQuestionSQL="INSERT INTO tb_word_question(id,word_id,sort_order)values('"+wordQuestionID+"','"+wordId+"',1)";
			int i = mgr.SQLExecute(WordQuestionOptionSQL);
			if(i>0){
				int j = mgr.SQLExecute(WordQuestionSQL);
				if(j>0){
					request.setAttribute("mgr", "200");
				}else{
					mgr.SQLExecute("delete from tb_word_question_option where id='"+wordQuestionOptionID+"'");
				}
			}
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}	
	
	/**
	 * 删除单词选项
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			// 接收传值
			String id = ParamUtils.getParameter(request, "id", true);
			int i = mgr.SQLExecute("DELETE FROM tb_word_question_option WHERE id='"+id+"'");
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		} 
	}	
	
	/**
	 * 修改单词选项
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preWordQuestionOptionUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			// 接收传值
			String id = ParamUtils.getParameter(request, "id", true);
			Session ses = HibernateSessionFactory.openSession();
			Query query = ses.createSQLQuery("select * from tb_word_question_option where id='"+id+"'").addEntity(TbWordQuestionOption.class);
			List<TbWordQuestionOption> list = query.list();
			if(list.size()>0){
				request.setAttribute("TbWordQuestionOption", list.get(0));
			}else{
				WebDialogBox dialog = new WebDialogBox(1, "错误", "获取单词信息时出错", "返回", "javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
			request.setAttribute("id",id);
			return mapping.findForward("WordQuestionOptionUpdate");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取单词信息时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}	
	
	
	/**
	 * 修改单词选项
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward WordQuestionOptionUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 接收传值
			String id = ParamUtils.getParameter(request, "id", true);
			if(id.equals("") || id==null){
				WebDialogBox dialog = new WebDialogBox(1, "错误", "获取单词时出错", "返回", "javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
			String prefix = ParamUtils.getParameter(request, "prefix", true);
			String content = ParamUtils.getParameter(request, "content", true);
			String isAnswer = ParamUtils.getParameter(request, "isAnswer", true);
			if(isAnswer.equals("") || isAnswer==null){
				isAnswer="0";
			}
			String wordQuestionOptionID=UUID.randomUUID().toString().replace("-", "");
			String wordQuestionID=UUID.randomUUID().toString().replace("-", "");
			String WordQuestionOptionSQL="UPDATE tb_word_question_option set prefix='"+prefix+"',content='"+content+"',is_answer='"+isAnswer+"' where id='"+id+"'";
			int i = mgr.SQLExecute(WordQuestionOptionSQL);
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}	
	
	/**
	 * 下载导入单词模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void preDown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

            String fileName = "词汇.xls";
            String filePath = "/template/training/词汇.xls";
            filePath = request.getSession().getServletContext().getRealPath("/") + filePath;
            // 判断文件是否存在
            File file = new File(filePath);
            if (!file.exists()) { // 不存在，返回false
                throw new Exception("没找到该文件，该文件可能已被删除");
            }
            String displayFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename="+ displayFileName);// 不是显示是下载
            BufferedInputStream inputStream = null;
            BufferedOutputStream outputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(filePath));
                outputStream = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while ((bytesRead = inputStream.read(buff, 0, buff.length)) != -1) {
                    outputStream.write(buff, 0, bytesRead);
                }
                outputStream.flush();
            } catch (final IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }  finally {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
	}	
	
	/**
	 * 批量导入
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward WordUpload(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		TbWordActionForm fm = (TbWordActionForm) actionForm;
		//文件
		FormFile formfile = fm.getFile();
		//文件名
		String filename = formfile.toString();
		//文件类型 .xls  .xlsx
		String fileType = filename.substring(filename.lastIndexOf(".") + 1);
		
		InputStream inputstream = formfile.getInputStream();
		Workbook wb=null;
		
		String wordId="";
		String questionId="";
		int input = 0; // 导入记数
		String type="";
		String options="";
		try {
			if (fileType.equals("xls")) {
	            wb = new HSSFWorkbook(inputstream);
	        } else if (fileType.equals("xlsx")) {
	            wb = new XSSFWorkbook(inputstream);
	        } else {
	            throw new Exception("读取的不是excel文件");
	        }
			Sheet hssfsheet = wb.getSheetAt(0);// 第一个工作表
			Row hssfrow = hssfsheet.getRow(0);// 第一行
			List<TbWordQuestionOption> optionList=new ArrayList<TbWordQuestionOption>();
			List<TbWordActionForm> wordList=new ArrayList<TbWordActionForm>();
			List<TbWordQuestion> questionList=new ArrayList<TbWordQuestion>();
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				hssfsheet = wb.getSheetAt(i);
				for (int j = 2; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
					System.err.println(j);
					TbQuestionTranslation tqw=new TbQuestionTranslation();
					hssfrow = hssfsheet.getRow(j);
					if(hssfrow.getCell((short) 0)!=null){
						type=hssfrow.getCell((short) 0).getStringCellValue().trim();
					}else{
						type=null;
					}
					if(type!=null && !type.equals("")){
						wordId=UUID.randomUUID().toString().replaceAll("-", "");
						TbWordActionForm word=new TbWordActionForm();
						word.setId(wordId);
						word.setWord(type);
						word.setPhoneticSymbol(hssfrow.getCell((short) 1).getStringCellValue().trim());
						word.setPronunciationUrl(hssfrow.getCell((short) 2).getStringCellValue().trim());
						word.setParaphrase(hssfrow.getCell((short) 3).getStringCellValue().trim());
						word.setExampleSentence(hssfrow.getCell((short) 4).getStringCellValue().trim());
						word.setExampleReference(hssfrow.getCell((short) 5).getStringCellValue().trim());
						if(hssfrow.getCell((short) 10)!=null){
							if(String.valueOf(hssfrow.getCell((short) 10).getNumericCellValue()).equals("1.0")){
								word.setCet("01");
								word.setType("0101");
								word.setTypes("010101");
							}
						}
						if(hssfrow.getCell((short) 12)!=null){
							if(String.valueOf(hssfrow.getCell((short) 12).getNumericCellValue()).equals("1.0")){
								word.setCet("01");
								word.setType("0101");
								word.setTypes("010102");
							}
						}
						if(hssfrow.getCell((short) 14)!=null){
							if(String.valueOf(hssfrow.getCell((short) 14).getNumericCellValue()).equals("1.0")){
								word.setCet("01");
								word.setType("0101");
								word.setTypes("010103");
							}
						}
						if(hssfrow.getCell((short) 16)!=null){
							if(String.valueOf(hssfrow.getCell((short) 16).getNumericCellValue()).equals("1.0")){
								word.setCet("02");
								word.setType("0201");
								word.setTypes("020101");
							}
						}
						if(hssfrow.getCell((short) 18)!=null){
							if(String.valueOf(hssfrow.getCell((short) 18).getNumericCellValue()).equals("1.0")){
								word.setCet("02");
								word.setType("0201");
								word.setTypes("020102");
							}
						}
						if(hssfrow.getCell((short) 20)!=null){
							if(String.valueOf(hssfrow.getCell((short) 20).getNumericCellValue()).equals("1.0")){
								word.setCet("02");
								word.setType("0201");
								word.setTypes("020103");
							}
						}
						wordList.add(word);
					}
					TbWordQuestion question=new TbWordQuestion();
					questionId=UUID.randomUUID().toString().replaceAll("-", "");
					question.setWordId(wordId);
					question.setId(questionId);
					question.setSortOrder(1);
					questionList.add(question);
					TbWordQuestionOption option=new TbWordQuestionOption();
					option.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					option.setQuestionId(questionId);
					option.setPrefix(hssfrow.getCell((short) 7).getStringCellValue());
					option.setContent(hssfrow.getCell((short)8).getStringCellValue());
					option.setIsAnswer(hssfrow.getCell((short) 9)==null?"1":"0");
					optionList.add(option);
					
					// 导入成功加1
					input++;
				}
			}
			mgr.listUpload(wordList,questionList,optionList);
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
	 * sql拼接去''
	 * @param obj
	 * @return
	 */
	public static String getFormatField(Object obj) {
		String resultStr = "NULL";
		if (obj instanceof Integer) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr =  obj.toString() ;
			}
		} else if(obj instanceof String) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Timestamp) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Double) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = obj.toString();
			}
		}
		return resultStr;
	}
}
