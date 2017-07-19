package com.business.Dlb.PeriodPaperUserAnswer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.business.Dlb.PeriodPaperQrcode.PeriodPaperAnswer;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.ExcelUtil;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;

public class PeriodPaperUserAnswerAction extends BaseAction{

	PeriodPaperUserAnswerActionMgr mgr=new PeriodPaperUserAnswerActionMgr();
	/**
	 * 用户提交答案列表
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
			String mobile = ParamUtils.getParameter(request, "mobile", false);
			String section = ParamUtils.getParameter(request, "section", false);
			String period = ParamUtils.getParameter(request, "period", false);
			//批改状态
			String state = ParamUtils.getParameter(request, "state", false);
			//作业类型（翻译  写作）
			String type = ParamUtils.getParameter(request, "type", false);
			String createstarttime = ParamUtils.getParameter(request, "createstarttime", false);
			String createendtime = ParamUtils.getParameter(request, "createendtime", false);
			String replystarttime = ParamUtils.getParameter(request, "replystarttime", false);
			String replyendtime = ParamUtils.getParameter(request, "replyendtime", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("b.mobile", "String", "=", mobile));
			queryConds.add(new QueryCond("a.section", "String", "=", section));
			queryConds.add(new QueryCond("a.period", "String", "=", period));
			queryConds.add(new QueryCond("a.question_type", "String", "=", type));
			queryConds.add(new QueryCond("a.reply_date", "String", ">=", replystarttime));
			queryConds.add(new QueryCond("a.reply_date", "String", "<=", replyendtime));
			queryConds.add(new QueryCond("a.create_date", "String", ">=", createstarttime));
			queryConds.add(new QueryCond("a.create_date", "String", "<=", createendtime));
			

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage,state);

			request.setAttribute("mobile", mobile);
			request.setAttribute("section", section);
			
			request.setAttribute("type", type);
			request.setAttribute("period", period);
			request.setAttribute("state", state);
			request.setAttribute("replystarttime", replystarttime);
			request.setAttribute("replyendtime", replyendtime);
			request.setAttribute("createstarttime", createstarttime);
			request.setAttribute("createendtime",createendtime);
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
	 * 去查看答案页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		String ID = ParamUtils.getParameter(request, "id", false);
		try {
			//学生试卷详情
			String sql="SELECT a.*, b.mobile mobile, c.username replyName FROM dlb_period_paper_user_answer a LEFT JOIN users b ON a.user_id = b.id LEFT JOIN users c ON c.id = a.reply_user_id WHERE 1 = 1  AND a.is_teacher_evaluate = 1 AND a.reply_user_id is NOT NULL AND a.id='"+ID+"' ORDER BY a.create_date DESC";
			List<PeriodPaperUserAnswer> userAnswerList=mgr.findObjectListBySql(sql);
			if(userAnswerList.size()>0){
				request.setAttribute("PeriodPaperUserAnswer", userAnswerList.get(0));
				//批改规则详情
				String sqls="select a.*,b.name name FROM dlb_period_paper_evaluate_rule_detail a LEFT JOIN dlb_period_paper_evaluate_rule b ON a.rule_id=b.id where b.question_type="+userAnswerList.get(0).getQuestionType()+" ORDER BY b.sort ASC";
				List<PeriodPaperEvaluateRuleDetail> EvaluateRuleList=mgr.findEvaluateRuleListBySql(sqls);
				//批改规则详情
				List<PeriodPaperUserAnswerEvaluate> userAnswerEvaluate = Tool.findListByHql("from PeriodPaperUserAnswerEvaluate where userAnswerId='"+userAnswerList.get(0).getId()+"'");
				for (PeriodPaperEvaluateRuleDetail periodPaperEvaluateRule : EvaluateRuleList) {
					if(periodPaperEvaluateRule.getLevel()==1){
						periodPaperEvaluateRule.setLevelName("好");
					}
					if(periodPaperEvaluateRule.getLevel()==2){
						periodPaperEvaluateRule.setLevelName("中");
					}
					if(periodPaperEvaluateRule.getLevel()==3){
						periodPaperEvaluateRule.setLevelName("差");
					}
				}
				request.setAttribute("EvaluateRuleList", EvaluateRuleList);
				request.setAttribute("userAnswerEvaluate", userAnswerEvaluate);
				return mapping.findForward("view");
			}
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("view");
	}
	/**
	 * 去答案批改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		String ID = ParamUtils.getParameter(request, "id", false);
		try {
			//学生试卷详情
			String sql="SELECT a.*, b.mobile mobile, c.username replyName FROM dlb_period_paper_user_answer a LEFT JOIN users b ON a.user_id = b.id LEFT JOIN users c ON c.id = a.reply_user_id WHERE 1 = 1  AND a.is_teacher_evaluate = 1 AND a.reply_user_id is NULL AND a.id='"+ID+"' ORDER BY a.create_date DESC";
			List<PeriodPaperUserAnswer> userAnswerList=mgr.findObjectListBySql(sql);
			if(userAnswerList.size()>0){
				request.setAttribute("PeriodPaperUserAnswer", userAnswerList.get(0));
				//批改规则详情
				String sqls="select a.*,b.name name FROM dlb_period_paper_evaluate_rule_detail a LEFT JOIN dlb_period_paper_evaluate_rule b ON a.rule_id=b.id where b.question_type="+userAnswerList.get(0).getQuestionType()+" ORDER BY b.sort ASC";
				List<PeriodPaperEvaluateRuleDetail> EvaluateRuleList=mgr.findEvaluateRuleListBySql(sqls);
				for (PeriodPaperEvaluateRuleDetail periodPaperEvaluateRule : EvaluateRuleList) {
					if(periodPaperEvaluateRule.getLevel()==1){
						periodPaperEvaluateRule.setLevelName("好");
					}
					if(periodPaperEvaluateRule.getLevel()==2){
						periodPaperEvaluateRule.setLevelName("中");
					}
					if(periodPaperEvaluateRule.getLevel()==3){
						periodPaperEvaluateRule.setLevelName("差");
					}
				}
				request.setAttribute("EvaluateRuleList", EvaluateRuleList);
				return mapping.findForward("update");
			}
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("update");
	}
	
	/**
	 * 批改学学生答案
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public ActionForward ReplyUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, Exception {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		//答案ID
		String id = ParamUtils.getParameter(request, "id", false);
		//批改详情ID 拼接的
		String ides = ParamUtils.getParameter(request, "ides", false);
		//老师批改内容
		String translate = ParamUtils.getParameter(request, "translate", false);
		//获取用户答案详情
		String sql="SELECT a.*, b.mobile mobile, c.username replyName FROM dlb_period_paper_user_answer a LEFT JOIN users b ON a.user_id = b.id LEFT JOIN users c ON c.id = a.reply_user_id WHERE 1 = 1  AND a.is_teacher_evaluate = 1 AND a.reply_user_id is NULL AND a.id='"+id+"' ORDER BY a.create_date DESC";
		List<PeriodPaperUserAnswer> userAnswerList=mgr.findObjectListBySql(sql);
		PeriodPaperUserAnswer vo=userAnswerList.get(0);
		//得到规则内容
		String sqls="select a.*,b.name name FROM dlb_period_paper_evaluate_rule_detail a LEFT JOIN dlb_period_paper_evaluate_rule b ON a.rule_id=b.id where b.question_type="+userAnswerList.get(0).getQuestionType()+" ORDER BY b.sort ASC";
		List<PeriodPaperEvaluateRuleDetail> EvaluateRuleList=mgr.findEvaluateRuleListBySql(sqls);
		//批改详情ID 解析拆分
		String ruleIds = ides.substring(0, ides.length()-1);
		String[] split = ruleIds.split(",");
		//拼接添加评分规则ID
		StringBuilder user_answer_evaluate_sql=new StringBuilder("INSERT INTO dlb_period_paper_user_answer_evaluate (id,user_answer_id,rule_id,rule_detail_id,score,create_date) VALUES");
		//规则分数
		BigDecimal sumScore=new BigDecimal(0.00);
		//如果写作切题性为差  总分为0
		int pertinence=0;
		for (String string : split) {
			String[] values = string.split("\\.");
			for (PeriodPaperEvaluateRuleDetail rule : EvaluateRuleList) {
				if(values[1].equals(rule.getId())){
					user_answer_evaluate_sql.append("('"+UUID.randomUUID().toString().replace("-", "")+"','"+id+"','"+rule.getRuleId()+"','"+rule.getId()+"','"+rule.getScore()+"','"+DateUtils.getCurrDateTimeStr()+"'),");
					sumScore=sumScore.add(rule.getScore());
				}
				if(values[1].equals("15")){
					pertinence=1;
				}
			}
		}
		String substring = user_answer_evaluate_sql.toString().substring(0, user_answer_evaluate_sql.length()-1);
		String user_answer="";
		String report_sql="";
		String teacher_submit_sql="";
		if(pertinence==0){
			user_answer="UPDATE dlb_period_paper_user_answer SET score='"+sumScore+"',reply_content='"+translate+"',reply_user_id='"+sessionContainer.getUserId()+"',reply_date='"+DateUtils.getCurrDateTimeStr()+"' WHERE id='"+id+"'";
			//同步测评报告分数
			if(vo.getQuestionType()==3){
				//翻译
				report_sql="UPDATE dlb_evaluation_report SET user_total_score=user_total_score+'"+sumScore+"',user_score_translate='"+sumScore+"' WHERE id='"+vo.getReportId()+"'";
				String writeValue = Tool.getValue("SELECT user_score_write FROM dlb_evaluation_report WHERE id='"+vo.getReportId()+"'");
				if(!writeValue.equals("") && writeValue!=null){
					//批改翻译的时候判断写作是否批改，如果批改完成，测测评报告状态改成：2老师评分已提交
					teacher_submit_sql="UPDATE dlb_evaluation_report SET teacher_submit =2 WHERE id='"+vo.getReportId()+"'";
				}
				
			}else if(vo.getQuestionType()==4){
				//写作
				report_sql="UPDATE dlb_evaluation_report SET user_total_score=user_total_score+'"+sumScore+"',user_score_write='"+sumScore+"' WHERE id='"+vo.getReportId()+"'";
				
			}
		}else{
			user_answer="UPDATE dlb_period_paper_user_answer SET score='0',reply_content='"+translate+"',reply_user_id='"+sessionContainer.getUserId()+"',reply_date='"+DateUtils.getCurrDateTimeStr()+"' WHERE id='"+id+"'";
			report_sql="UPDATE dlb_evaluation_report SET user_score_write='0' WHERE id='"+vo.getReportId()+"'";
			String translateValue = Tool.getValue("SELECT user_score_translate FROM dlb_evaluation_report WHERE id='"+vo.getReportId()+"'");
			if(!translateValue.equals("") && translateValue!=null){
				//批改翻译的时候判断写作是否批改，如果批改完成，测测评报告状态改成：2老师评分已提交
				teacher_submit_sql="UPDATE dlb_evaluation_report SET teacher_submit =2 WHERE id='"+vo.getReportId()+"'";
			}
		}
		//添加批改老师信息
		boolean execute = Tool.execute(user_answer);
		if(execute){
			Tool.execute(report_sql);
			if(!teacher_submit_sql.equals("")){
				Tool.execute(teacher_submit_sql);
			}
			//添加批改老师规则信息
			Tool.execute(substring);
		}
		String ipaddress = IpAddressUtil.getIpAddr(request);
		Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "批改学生"+(vo.getQuestionType()==3?"翻译":"写作")+"答案:"+vo.getMobile(), "1", ipaddress);
		return list(mapping, form, request, response);
	}
	
	 public void fileDownload(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response){
			try {
				String fileName = "学生答案.jpg";
				//http://oanafcpi7.bkt.clouddn.com/755696e0c03bcf99f2dfde5edf19883f.jpg
				String filePath = ParamUtils.getParameter(request,"answerUrl");
				//filePath = request.getSession().getServletContext().getRealPath("/") + filePath;
				// 判断文件是否存在
				File file = new File(filePath);
				if (!file.exists()) { // 不存在，返回false
					throw new Exception("没找到该文件，该文件可能已被删除");
				}
				String displayFileName = URLEncoder.encode(fileName, "UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-disposition", "attachment; filename=" + displayFileName);// 不是显示是下载
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
		 * 导出用户提交答案当前页面
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		public void exportPageExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
			SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
			if(null==sessionContainer)
				sessionContainer=new SessionContainer();
			try {
				//导出类型  0 当前页 1 是全部
				String exportID = ParamUtils.getParameter(request, "exportID", false);
				// 接收传值
				String mobile = ParamUtils.getParameter(request, "mobile", false);
				String section = ParamUtils.getParameter(request, "section", false);
				String period = ParamUtils.getParameter(request, "period", false);
				//批改状态
				String state = ParamUtils.getParameter(request, "state", false);
				//作业类型（翻译  写作）
				String type = ParamUtils.getParameter(request, "type", false);
				String createstarttime = ParamUtils.getParameter(request, "createstarttime", false);
				String createendtime = ParamUtils.getParameter(request, "createendtime", false);
				String replystarttime = ParamUtils.getParameter(request, "replystarttime", false);
				String replyendtime = ParamUtils.getParameter(request, "replyendtime", false);
				// 设置查询条件
				Collection queryConds = new ArrayList();
				queryConds.add(new QueryCond("b.mobile", "String", "=", mobile));
				queryConds.add(new QueryCond("a.section", "String", "=", section));
				queryConds.add(new QueryCond("a.period", "String", "=", period));
				queryConds.add(new QueryCond("a.question_type", "String", "=", type));
				queryConds.add(new QueryCond("a.reply_date", "String", ">=", replystarttime));
				queryConds.add(new QueryCond("a.reply_date", "String", "<=", replyendtime));
				queryConds.add(new QueryCond("a.create_date", "String", ">=", createstarttime));
				queryConds.add(new QueryCond("a.create_date", "String", "<=", createendtime));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	            String timestr = sdf.format(new Date());
	            String fileName=new String(("用户提交答案列表-"+timestr).getBytes("gb2312"), "iso8859-1")+ ".xls";
	            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	            response.setCharacterEncoding("utf-8");
	            ServletOutputStream outputStream = response.getOutputStream();
	            List list = new ArrayList ();
	            ExcelUtil eu = new ExcelUtil();
                String[] titles = { "用户手机号 ", "学段","考试", "题目类型", "题号", "正确答案","是否需要老师批改","用户答案","得分","老师批改内容","批改老师","批改时间","做题时间"};
                String[] column = {"mobile","section","period", "questionType","question_no","right_answer","isTeacherEvaluate","user_answer","score","reply_content","replyName","reply_date","create_date"};
                list = mgr.exportPageExcel(queryConds,state,exportID);
                eu.ExportExcelConmon(titles,column,list,fileName,response);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
				WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
						"javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
			}
		}
}
