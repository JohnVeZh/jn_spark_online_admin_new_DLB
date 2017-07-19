package com.business.Dlb.PeriodVideoQrcode;

import java.sql.SQLException;
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

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
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
 * 扫码看课资源管理
 * @author sparke
 *
 */
public class PeriodVideoDetailAction extends BaseAction{

	PeriodVideoDetailActionMgr mgr=new PeriodVideoDetailActionMgr();
	/**
	 * 扫码看课资源列表
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
			String videoQrcodeId = ParamUtils.getParameter(request, "videoQrcodeId", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("a.video_qrcode_id", "String", "=", videoQrcodeId));
			
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			request.setAttribute("videoQrcodeId", videoQrcodeId);
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
	 * 去添加二维码资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		String videoQrcodeId = ParamUtils.getParameter(request, "videoQrcodeId", false);
		request.setAttribute("videoQrcodeId", videoQrcodeId);
		return mapping.findForward("add");
		
	}
	/**
	 * 添加二维码资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		try{
			PeriodVideoDetail vo=(PeriodVideoDetail) form;
			String sql="INSERT INTO dlb_period_video_detail (id,video_qrcode_id,title,sort,detail,create_by,video_ccid,create_date) VALUES('"+UUID.randomUUID().toString().replace("-", "")+"','"+vo.getVideoQrcodeId()+"','"+vo.getTitle()+"',"+vo.getSort()+",'"+vo.getDetail()+"','"+sessionContainer.getUserId()+"','"+vo.getVideoCcid()+"','"+DateUtils.getCurrDateTimeStr()+"')";
			mgr.SQLExecute(sql);
			String ipaddress = IpAddressUtil.getIpAddr(request);
			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "添加扫码看课二维码资源：资源名称"+vo.getTitle(), "1", ipaddress);
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
		
	}
	/**
	 * 删除二维码资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ActionForward preDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer){sessionContainer=new SessionContainer();}
		String id = ParamUtils.getParameter(request, "id", false);
		String title = Tool.getValue("select title from dlb_period_video_detail where id='"+id+"'");
		String sql="UPDATE dlb_period_video_detail SET del_flag=1 WHERE id='"+id+"'";
		boolean execute = Tool.execute(sql);
		String ipaddress = IpAddressUtil.getIpAddr(request);
		Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "添加扫码看课二维码资源：资源名称"+title, "1", ipaddress);
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
		List<PeriodVideoDetail> list=mgr.findObjectListBySql("SELECT a.*,b.username username FROM dlb_period_video_detail a LEFT JOIN users b ON a.create_by =b.id WHERE 1=1 AND a.del_flag!=1 and a.id='"+id+"'");
		request.setAttribute("PeriodVideoDetail", list.get(0));
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
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
			if(null==sessionContainer){sessionContainer=new SessionContainer();}
			PeriodVideoDetail vo=(PeriodVideoDetail) form;
			
			String sql="UPDATE dlb_period_video_Detail SET title='"+vo.getTitle()+"',sort="+vo.getSort()+",detail='"+vo.getDetail()+"',video_ccid='"+vo.getVideoCcid()+"' WHERE id='"+vo.getId()+"' and video_qrcode_id='"+vo.getVideoQrcodeId()+"'";
			boolean execute = Tool.execute(sql);
			String ipaddress = IpAddressUtil.getIpAddr(request);
			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(), "修改扫码看课二维码资源：资源名称"+vo.getTitle(), "1", ipaddress);
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}
