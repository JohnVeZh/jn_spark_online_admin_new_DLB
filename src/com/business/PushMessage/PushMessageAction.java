package com.business.PushMessage; 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.JPushClient;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class PushMessageAction extends BaseAction{
	 PushMessageMgr mgr = new PushMessageMgr();

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
			//String name = ParamUtils.getParameter(request, "name", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			String starttime=ParamUtils.getParameter(request,"starttimeStr");//开始时间
			String endtime=ParamUtils.getParameter(request,"endtimeStr");//结束时间
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
		 
			 
			//
			//queryConds.add(new QueryCond("user.name", "String", "like", name));
			//queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			queryConds.add(new QueryCond("PushMessage.pmCreatetime", "String", ">=", starttime));
			queryConds.add(new QueryCond("PushMessage.pmCreatetime", "String", "<=", endtime+" 23:59:59"));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			//request.setAttribute("orgId", orgId);
			//request.setAttribute("name", name);
			//request.setAttribute("loginName", loginName);
			request.setAttribute("starttimeStr",starttime);
			request.setAttribute("endtimeStr",endtime);
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
			PushMessageActionForm vo=mgr.view(ID);
			String value=vo.getLevelType();
			//System.out.println(value);
			String type=Tool.getValue("select name from sys_config where type='MT_TYPE' and value='"+value+"'");
            request.setAttribute("type", type);
			request.setAttribute("PushMessageActionForm", vo);

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

		PushMessageActionForm vo = (PushMessageActionForm) form;
		try {
			mgr.update(vo);

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

			PushMessageActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("PushMessageActionForm", vo);

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
			PushMessageActionForm vo = (PushMessageActionForm) form;	
			vo.setLevelType("99");//全部
			vo.setPmCreatetime(DateUtils.getCurrDateTimeStr());
			PushMessage pm=new PushMessage();
			this.copyProperties(pm, vo);
			pm.setJumpType(1);
			String id=mgr.add(pm);
			String time=DateUtils.getCurrDateTimeStr();
//			String t=DateUtils.getCurrDateStr_();
			//插入每个人的对应
//			Tool.execute("INSERT INTO  push_message_user  (is_look,message_id,user_id,createtime)    SELECT  '0','"+id+"', id,'"+time+"'  FROM users as u where 1=1");
			/**另起线程插入数据，解决后台处理慢的问题*/
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Tool.execute("INSERT INTO push_message_user(is_look,message_id,user_id,createtime) SELECT '0','" + id + "', id,'" + time + "' FROM users");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}).start();
			//判断是否推送
			//if(1==vo.getPmIsPush()){//推送
				//进行推送
//				JPushClientUtil jpush=new JPushClientUtil();
//				jpush.testSend(vo.getPmTitle(), vo.getPmTitle(),id);
			//}
			/**使用新推送接口*/
			Map<String, Object> extras = new HashMap<String, Object>();
			extras.put("id", id);
			JPushClient.push(vo.getPmTitle(), request.getParameter("subTitle"), extras);
			
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
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

			PushMessageActionForm vo = new PushMessageActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("PushMessageActionForm", vo);
			String type =  Tool.getList("select value,name from sys_config where type='MT_TYPE'", "name", "value");
			request.setAttribute("type", type);
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
				 
			Tool.execute("delete from push_message where id = '"+ids[i]+"'");
				 
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
	 * 异步上传图片
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateImgPath(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		PushMessageActionForm vo = (PushMessageActionForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String value = "";
					value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.NEWS_ICON_PATH_PHONEIMG,request);
			  map.put("result", true);
			  map.put("imgPath", value);
			  System.out.println(value+"---------");
			  JsonUtils.outputJsonResponse(response, map);
			}
		} catch (Exception ex) {
			  map.put("result", false);
			  JsonUtils.outputJsonResponse(response, map);
		}
		return null;
	}
	
}
