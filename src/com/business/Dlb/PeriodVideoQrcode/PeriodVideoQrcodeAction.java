package com.business.Dlb.PeriodVideoQrcode;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
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
/**
 * 扫码看课
 * @author sparke
 *
 */
public class PeriodVideoQrcodeAction extends BaseAction{

	PeriodVideoQrcodeActionMgr mgr=new PeriodVideoQrcodeActionMgr();
	
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
			String createstarttime = ParamUtils.getParameter(request, "createstarttime", false);
			String createendtime = ParamUtils.getParameter(request, "createendtime", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("a.title", "String", "=", name));
			queryConds.add(new QueryCond("a.section", "String", "=", section));
			queryConds.add(new QueryCond("a.create_date", "String", ">=", createstarttime));
			queryConds.add(new QueryCond("a.create_date", "String", "<=", createendtime));
			

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("name", name);
			request.setAttribute("section", section);
			
			request.setAttribute("createstarttime", createstarttime);
			request.setAttribute("createendtime",createendtime);
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
	 * 添加二维码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		PeriodVideoQrcode vo=(PeriodVideoQrcode) form;
		String sql="INSERT INTO dlb_period_video_qrcode (id,section,title,qrcode_content,qrcode_url,create_by,create_date) VALUES('"+UUID.randomUUID().toString().replace("-", "")+"','"+vo.getSection()+"','"+vo.getTitle()+"','"+vo.getQrcodeContent()+"','"+vo.getQrcodeUrl()+"','"+sessionContainer.getUserId()+"','"+DateUtils.getCurrDateTimeStr()+"')";
		boolean execute = Tool.execute(sql);String ipaddress = IpAddressUtil.getIpAddr(request);
		if(!execute){
			request.setAttribute("msg", "400");
			return list(mapping, form, request, response);
		}
		Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "添加扫码看课二维码：二维码名称"+vo.getTitle(), "1", ipaddress);
		request.setAttribute("msg", "200");
		return list(mapping, form, request, response);
		
	}
	
	/**
	 * 去修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, Exception {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		String id = ParamUtils.getParameter(request, "id", false);
		List<PeriodVideoQrcode> list=mgr.findObjectListBySql("SELECT a.*,b.username username FROM dlb_period_video_qrcode a LEFT JOIN users b ON a.create_by=b.id  WHERE a.id='"+id+"'");
		request.setAttribute("PeriodVideoQrcode", list.get(0));
		return mapping.findForward("update");
		
	}
	
	/**
	 * 去修改看课的二维码信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		PeriodVideoQrcode vo=(PeriodVideoQrcode) form;
		String sql="UPDATE dlb_period_video_qrcode SET section='"+vo.getSection()+"',title='"+vo.getTitle()+"',qrcode_content='"+vo.getQrcodeContent()+"',qrcode_url='"+vo.getQrcodeUrl()+"' WHERE id='"+vo.getId()+"'";
		System.err.println(sql);
		boolean execute = Tool.execute(sql);
		String ipaddress = IpAddressUtil.getIpAddr(request);
		if(!execute){
			request.setAttribute("msg", "400");
			return list(mapping, form, request, response);
		}
		Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "修改扫码看课二维码：二维码名称"+vo.getTitle(), "1", ipaddress);
		request.setAttribute("msg", "200");
		return list(mapping, form, request, response);
		
	}
}
