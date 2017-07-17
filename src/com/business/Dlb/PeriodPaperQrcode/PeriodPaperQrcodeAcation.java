package com.business.Dlb.PeriodPaperQrcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
























import com.business.NetworkCourse.NetworkCourse;
import com.business.NetworkCourseTeacher.NetworkCourseTeacher;
import com.business.NetworkVideo.NetworkVideoActionForm;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.easecom.system.web.SysConfigForm;

/**
 * 试卷列表及答案
 * @author 许森森
 *
 */
public class PeriodPaperQrcodeAcation extends BaseAction{
	
	PeriodPaperQrcodeActionMgr mgr=new PeriodPaperQrcodeActionMgr();
	
	/**
	 * 二维码列表
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
			String action = ParamUtils
					.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			 
			// 接收传值
			String name = ParamUtils.getParameter(request, "name", false);
			String section = ParamUtils.getParameter(request, "section", false);
			String starttime = ParamUtils.getParameter(request, "starttime", false);
			String endtime = ParamUtils.getParameter(request, "endtime", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("a.name", "String", "like", name));
			queryConds.add(new QueryCond("a.section", "String", "=", section));
			queryConds.add(new QueryCond("a.create_date", "String", ">=", starttime));
			queryConds.add(new QueryCond("a.create_date", "String", "<=", endtime));
			

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("name", name);
			request.setAttribute("section", section);
			request.setAttribute("starttime", starttime);
			request.setAttribute("endtime", endtime);
			request.setAttribute("lc", lc);
			request.setAttribute("msg", "200");
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
	 * 添加扫码做题的试卷及答案
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PeriodPaperQrcode vo = (PeriodPaperQrcode) form;			
		try {
			SessionContainer sessionContainer = (SessionContainer) request .getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){ sessionContainer = new SessionContainer();}
			String userId = sessionContainer.getUserId();
			//试卷选项答案拼接  拆分添加 
			String hearingValues = ParamUtils.getParameter(request, "hearingValues", false);
			//写作答案
			String writing = ParamUtils.getParameter(request, "writing", false);
			//翻译答案
			String translate = ParamUtils.getParameter(request, "translate", false);
			//试卷二维码ID 添加答案
			String ID=UUID.randomUUID().toString().replace("-", "");
			System.err.println(vo.toString());
			System.err.println(hearingValues);
			System.err.println(writing);
			System.err.println(translate);
			//添加试卷二维码
			String sql="INSERT INTO dlb_period_paper_qrcode (id,section,period,name,qrcode_content,qrcode_url,user_id,create_date)"
					+ "VALUES('"+ID+"','"+vo.getSection()+"','"+vo.getPeriod()+"','"+vo.getName()+"','"+vo.getQrcodeContent()+"','"+vo.getQrcodeUrl()+"','"+userId+"','"+DateUtils.getCurrDateTimeStr()+"')";
			boolean b = Tool.execute(sql);
			if(b){
				//添加试卷答案
				StringBuilder hearingSql=new StringBuilder("INSERT INTO dlb_period_paper_answer (id,paper_qrcode_id,section,period,question_type,question_no,answer,score,create_date)VALUES ");
				String[] hearings = hearingValues.substring(0, hearingValues.length()-1).split(",");
				int questionType=0;
				String score =null;
				for (String answers : hearings) {
					String[] answer = answers.split("\\.");
					int parseInt = Integer.parseInt(answer[0]);
					if(parseInt<16){
						score="7.1";
						questionType=1;
					}else if( parseInt >15 && parseInt<26){
						score="14.2";
						questionType=1;
					}else if(parseInt>25 && parseInt<36){
						score="3.55";
						questionType=2;
					}else if(parseInt>35 && parseInt<46){
						score="7.1";
						questionType=2;
					}else if(parseInt>45 && parseInt<56) {
						score="14.2";
						questionType=2;
					}
						hearingSql.append("('"+UUID.randomUUID().toString().replace("-", "")+"','"+ID+"','"+vo.getSection()+"',"+vo.getPeriod()+","+questionType+","+answer[0]+",'"+answer[1]+"','"+score+"','"+DateUtils.getCurrDateTimeStr()+"'),");
				}
				hearingSql.append("('"+UUID.randomUUID().toString().replace("-", "")+"','"+ID+"','"+vo.getSection()+"',"+vo.getPeriod()+",'4','0','"+writing+"','106.5','"+DateUtils.getCurrDateTimeStr()+"'),");
				hearingSql.append("('"+UUID.randomUUID().toString().replace("-", "")+"','"+ID+"','"+vo.getSection()+"',"+vo.getPeriod()+",'3','0','"+translate+"','106.5','"+DateUtils.getCurrDateTimeStr()+"')");
				Tool.execute(hearingSql.toString());
				System.err.println(hearingSql.toString());
			}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "添加课程,课程名称:"+vo.getName(), "1", ipaddress);
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 去修改页面，预览信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, Exception {
		String id = ParamUtils.getParameter(request, "id", false);
		//获取二维码信息
		List<PeriodPaperQrcode> codeList = mgr.findPeriodPaperQrcodeListBySql("SELECT a.id,a.section,period,a.name,a.qrcode_content,qrcode_url,a.create_date,a.del_flag,a.user_id, b.username username FROM dlb_period_paper_qrcode a LEFT JOIN users b on a.user_id=b.id WHERE a.del_flag=0 and a.id ='"+id+"'");
		//获取试卷听力全部答案
		List<PeriodPaperAnswer> answerHearing = Tool.findListByHql("from PeriodPaperAnswer where paperQrcodeId='"+id+"' and questionType=1 ORDER BY questionNo");
		//获取试卷阅读全部答案
		List<PeriodPaperAnswer> answerRead = Tool.findListByHql("from PeriodPaperAnswer where paperQrcodeId='"+id+"' and questionType=2  ORDER BY questionNo");
		//获取试卷翻译答案
		String translate = Tool.getValue("SELECT answer FROM dlb_period_paper_answer WHERE paper_qrcode_id='"+id+"' and question_type=3");
		//获取试卷写作答案
		String writing = Tool.getValue("SELECT answer FROM dlb_period_paper_answer WHERE paper_qrcode_id='"+id+"' and question_type=4");
		
		request.setAttribute("answerHearing", answerHearing);
		request.setAttribute("answerRead", answerRead);
		request.setAttribute("translate", translate);
		request.setAttribute("writing", writing);
		request.setAttribute("PeriodPaperQrcode", codeList.get(0));
		return mapping.findForward("view");
	}
	
	/**
	 * 修改详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PeriodPaperQrcode vo = (PeriodPaperQrcode) form;			
		try {
			SessionContainer sessionContainer = (SessionContainer) request .getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){ sessionContainer = new SessionContainer();}
			String userId = sessionContainer.getUserId();
			String hearingValues = ParamUtils.getParameter(request, "hearingValues", false);
			String writing = ParamUtils.getParameter(request, "writing", false);
			String translate = ParamUtils.getParameter(request, "translate", false);
			//修改试卷二维码信息
			String sqls="UPDATE dlb_period_paper_qrcode SET name='"+vo.getName()+"',section='"+vo.getSection()+"',period="+vo.getPeriod()+",qrcode_content='"+vo.getQrcodeContent()+"',qrcode_url='"+vo.getQrcodeUrl()+"' WHERE id ='"+vo.getId()+"'";
			boolean b = Tool.execute(sqls);
			if(b){
				//删除试卷所有选项答案
				boolean c = Tool.execute("DELETE FROM dlb_period_paper_answer WHERE paper_qrcode_id='"+vo.getId()+"'");
				if(c){
					//修改及重新添加试卷所有选项答案
					StringBuilder hearingSql=new StringBuilder("INSERT INTO dlb_period_paper_answer (id,paper_qrcode_id,section,period,question_type,question_no,answer,score,create_date)VALUES ");
					String[] hearings = hearingValues.substring(0, hearingValues.length()-1).split(",");
					int questionType=0;
					String score =null;
					for (String answers : hearings) {
						String[] answer = answers.split("\\.");
						int parseInt = Integer.parseInt(answer[0]);
						if(parseInt<16){
							score="7.1";
							questionType=1;
						}else if( parseInt >15 && parseInt<26){
							score="14.2";
							questionType=1;
						}else if(parseInt>25 && parseInt<36){
							score="3.55";
							questionType=2;
						}else if(parseInt>35 && parseInt<46){
							score="7.1";
							questionType=2;
						}else if(parseInt>45 && parseInt<56) {
							score="14.2";
							questionType=2;
						}
							hearingSql.append("('"+UUID.randomUUID().toString().replace("-", "")+"','"+vo.getId()+"','"+vo.getSection()+"',"+vo.getPeriod()+","+questionType+","+answer[0]+",'"+answer[1]+"','"+score+"','"+DateUtils.getCurrDateTimeStr()+"'),");
					}
					hearingSql.append("('"+UUID.randomUUID().toString().replace("-", "")+"','"+vo.getId()+"','"+vo.getSection()+"',"+vo.getPeriod()+",'4','0','"+writing+"','106.5','"+DateUtils.getCurrDateTimeStr()+"'),");
					hearingSql.append("('"+UUID.randomUUID().toString().replace("-", "")+"','"+vo.getId()+"','"+vo.getSection()+"',"+vo.getPeriod()+",'3','0','"+translate+"','106.5','"+DateUtils.getCurrDateTimeStr()+"')");
					Tool.execute(hearingSql.toString());
				}
			}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "添加课程,课程名称:"+vo.getName(), "1", ipaddress);
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 逻辑删除二维码数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SessionContainer sessionContainer = (SessionContainer) request .getSession().getAttribute("SessionContainer");
		if(sessionContainer==null){ sessionContainer = new SessionContainer();}
		String id = ParamUtils.getParameter(request, "id", false);
		try {
			String value = Tool.getValue("SELECT name FROM dlb_period_paper_qrcode WHERE id ='"+id+"'");
			//逻辑删除
			Tool.execute("UPDATE dlb_period_paper_qrcode SET del_flag=1 WHERE id='"+id+"'");
			String ipaddress = IpAddressUtil.getIpAddr(request);
			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "删除试卷二维码,试卷名称:"+value, "1", ipaddress);
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "删除错误，请检查参数", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
		return list(mapping, form, request, response);
	}
	/**
	 * 异步上传图片
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateImgPath(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PeriodPaperQrcode vo = (PeriodPaperQrcode) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String type = ParamUtils.getParameter(request, "type", true);
				String value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.PRODUCT_ICON_PATH_FILESPHONE,request);
			  
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
}



