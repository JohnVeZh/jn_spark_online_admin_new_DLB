package com.business.Dlb.ActivationCode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;




import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

/**
 * 大礼包
 * @author sparke
 *
 */
public class ActivationCodeAction extends BaseAction{
	
	ActivationCodeActionMgr mgr=new ActivationCodeActionMgr();
	
	/**
	 * 激活码列表+激活用户信息
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
			String code = ParamUtils.getParameter(request, "code", false);
			String section = ParamUtils.getParameter(request, "section", false);
			String instarttime = ParamUtils.getParameter(request, "instarttime", false);
			String inendtime = ParamUtils.getParameter(request, "inendtime", false);
			String userstarttime = ParamUtils.getParameter(request, "userstarttime", false);
			String userendtime = ParamUtils.getParameter(request, "userendtime", false);
			String mobile = ParamUtils.getParameter(request, "mobile", false);
			String isActivatedType = ParamUtils.getParameter(request, "isActivatedType", false);
			String address = ParamUtils.getParameter(request, "address", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("a.code", "String", "=", code));
			queryConds.add(new QueryCond("a.section", "String", "=", section));
			queryConds.add(new QueryCond("a.create_date", "String", ">=", instarttime));
			queryConds.add(new QueryCond("a.create_date", "String", "<=", inendtime));
			queryConds.add(new QueryCond("a.activate_time", "String", ">=", userstarttime));
			queryConds.add(new QueryCond("a.activate_time", "String", "<=", userendtime));
			queryConds.add(new QueryCond("b.mobile", "String", "=", mobile));
			queryConds.add(new QueryCond("a.is_activated", "String", "=", isActivatedType));
			queryConds.add(new QueryCond("a.address", "String", "=", address));
			

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("code", code);
			request.setAttribute("section", section);
			request.setAttribute("instarttime", instarttime);
			request.setAttribute("inendtime", inendtime);
			request.setAttribute("userstarttime", userstarttime);
			request.setAttribute("userendtime", userendtime);
			request.setAttribute("mobile", mobile);
			request.setAttribute("isActivatedType", isActivatedType);
			request.setAttribute("address", address);
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
	 *  去添加用户批改机会次数
	 * @param mapping
	 * @param 
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws  SQLException {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		try {
			// 接收传值
			String code = ParamUtils.getParameter(request, "code", false); 
			String type = ParamUtils.getParameter(request, "type", false);
			String user_id = Tool.getValue("SELECT activate_user_id FROM dlb_activation_code WHERE code='"+code+"'");
			String username = Tool.getValue("SELECT username FROM users WHERE id='"+user_id+"'");
			request.setAttribute("code", code);
			request.setAttribute("type", type);
			request.setAttribute("user_id", user_id);
			request.setAttribute("username", username);
			return mapping.findForward("update");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 增加用户批改次数机会
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ActionForward Add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws  SQLException {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
			// 接收传值
		String code = ParamUtils.getParameter(request, "code", false); 
		String userId = ParamUtils.getParameter(request, "user_id", false); 
		String type = ParamUtils.getParameter(request, "type", false);
		String code_user = Tool.getValue("SELECT code FROM dlb_user_objective_submit_count WHERE code='"+code+"'");
		if(!"".equals(code_user)&&code_user!=null){
			String type_total = Tool.getValue("SELECT "+type+" FROM dlb_user_objective_submit_count WHERE code='"+code+"'");
			if(type_total.equals("1")){
				return list(mapping, form, request, response);
			}else{
				Tool.execute("UPDATE dlb_user_objective_submit_count SET "+type+"=1 WHERE code='"+code+"'");
			}
		}else{
			String codeId = Tool.getValue("SELECT id FROM dlb_activation_code WHERE code='"+code+"'");
			String section = Tool.getValue("SELECT section FROM dlb_activation_code WHERE code='"+code+"'");
			String sql="INSERT INTO dlb_user_objective_submit_count (id,section,user_id,code_id,code,"+type+",create_date)VALUES('"+UUID.randomUUID().toString().replace("-", "")+"','"+section+"','"+userId+"','"+codeId+"','"+code+"',1,'"+DateUtils.getCurrDateTimeStr()+"')";
			Tool.execute(sql);
		}
		request.setAttribute("msg", "200");
		return list(mapping, form, request, response);
	}
	
	/**
	 * 批量导入激活码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward mpAnalysis(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ActivationCode vo = (ActivationCode) form;
		String section = vo.getSection();
		Map map = new HashMap();
		try {
			String encoding="GBK";
			FormFile file = vo.getFile();
			InputStream inputstream = file.getInputStream();
			int file_size = inputstream.available();
			if(file_size==0){
				 map.put("result", "000");
			}else{
				InputStreamReader read = new InputStreamReader(  inputstream,encoding);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				//拼接sql添加方法
				StringBuilder sql=new StringBuilder("INSERT INTO dlb_activation_code (id,code,section,create_date) VALUES ");
				int a=0;
				while((lineTxt = bufferedReader.readLine()) != null){
					//拼接
					sql.append("('"+UUID.randomUUID().toString().replace("-", "")+"','"+lineTxt+"','"+section+"','"+DateUtils.getCurrDateTimeStr()+"'),");
				}
				//读取结束
				read.close();
				//截取最后一个","
				String sqls = sql.toString().substring(0, sql.length()-1);
				//运行sql
				Tool.execute(sqls);
			}
		} catch (Exception ex) {
			  map.put("result", "001");
			  JsonUtils.outputJsonResponse(response, map);
		}
		map.put("result", "200");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}
