package com.business.NetworkVideo; 


import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.IpAddressUtil;
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
import com.business.NetworkVideo.NetworkVideo;
import com.business.NetworkVideo.NetworkVideoActionForm;
import com.business.NetworkVideo.NetworkVideoMgr;
import com.business.NetworkVideoCatalog.NetworkVideoCatalog;
import com.business.NetworkVideoTeaher.NetworkVideoTeaher;
import com.business.News.NewsActionForm;
import com.business.PreferentialCodeProduct.PreferentialCodeProduct;
import com.business.ProductCollocation.ProductCollocation;
import com.business.ProductMatchedPaper.ProductMatchedPaper;
import com.business.ProductNetworkVideo.ProductNetworkVideo;
import com.business.ProductType.ProductType;

public class NetworkVideoAction extends BaseAction{
	 NetworkVideoMgr mgr = new NetworkVideoMgr();

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
			String nvNamelike = ParamUtils.getParameter(request, "nvNamelike", false);
			String stateStr = ParamUtils.getParameter(request, "stateStr", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("NetworkVideo.networkName", "String", "like", nvNamelike));
			queryConds.add(new QueryCond("NetworkVideo.networkType", "String", "=", stateStr));
			

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("nvNamelike", nvNamelike);
			request.setAttribute("stateStr", stateStr);
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

			request.setAttribute("NetworkVideoActionForm", mgr.view(ID));

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
		NetworkVideoActionForm vo = (NetworkVideoActionForm) form;
		try {
			NetworkVideoActionForm video = mgr.view(vo.getId());
			if(video!=null){
				video.setNetworkImgpath(vo.getNetworkImgpath());
				video.setNetworkName(vo.getNetworkName());
				video.setBrief(vo.getBrief());
				video.setOriginalPrice(vo.getOriginalPrice());
				video.setNetworkMoney(vo.getNetworkMoney());
				video.setSort(vo.getSort());
				video.setNetworkType(vo.getNetworkType());
				video.setIsFree(vo.getIsFree());
				video.setIsLimitFree(vo.getIsLimitFree());
				video.setLimitStartTime(vo.getLimitStartTime());
				video.setLimitEndTime(vo.getLimitEndTime());
				video.setNetworkIntroduce(vo.getNetworkIntroduce());
				video.setTeacherIntroduce(vo.getTeacherIntroduce());
				video.setTeacherId(vo.getTeacherId());
				video.setCatalogIntroduce(vo.getCatalogIntroduce());
				video.setIsCatalog(vo.getIsCatalog());
				video.setIsTeacher(vo.getIsTeacher());
				video.setCatalogNum(vo.getCatalogNum());
				video.setLevelType(vo.getLevelType());
				video.setState(vo.getState());
				video.setIsBook(vo.getIsBook());
				video.setIsPublic(vo.getIsPublic());
				if(vo.getNetworkType()==1){
					video.setNetworkLiveTime(vo.getNetworkLiveTime());
					video.setNetworkLiveLink(vo.getNetworkLiveLink());
				}
				video.setBookPrice(vo.getBookPrice());
                video.setLimitCount(vo.getLimitCount());
                video.setEnrolEndTime(vo.getEnrolEndTime());
                video.setTeachStartTime(vo.getTeachStartTime());
                video.setTeachEndTime(vo.getTeachEndTime());
                video.setQrcode(vo.getQrcode());
				
				//拼接老师i
				String tckbox[] = request.getParameterValues("tckbox");
				String tIds = "";
				if(null!=tckbox&&tckbox.length>0){
					tIds = tckbox[0];
					for (int i = 1; i < tckbox.length; i++) {
						tIds = tIds+","+tckbox[i];
					}
				}
				video.setTeacherId(tIds);
				Tool.testReflect_admin(video);
				mgr.update(video);
				
				
				
				String ipaddress = IpAddressUtil.getIpAddr(request);
				SessionContainer sessionContainer = (SessionContainer) request
						.getSession().getAttribute("SessionContainer");
				if (null == sessionContainer) {
					sessionContainer = new SessionContainer();
				}
				String username = "admin";
				Tool.AddLog(sessionContainer.getUserId(), username,
						"修改课程,课程名称:"+vo.getNetworkName(), "1", ipaddress);
				
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

			NetworkVideoActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			//查询老师
			List<Map> nvtLm = new ArrayList<Map>();
			List<NetworkVideoTeaher> nvtList = Tool.findListByHql("from NetworkVideoTeaher where isDel=0");
			for (NetworkVideoTeaher nvt : nvtList) {
				Map tm = new HashMap();
				tm.put("id", nvt.getId());
				tm.put("name", nvt.getName());
				
				if(vo!=null && vo.getTeacherId()!=null && vo.getTeacherId().indexOf(nvt.getId())!=-1)
					tm.put("isCheck", "Y");
				else
					tm.put("isCheck", "N");
				
				nvtLm.add(tm);
			}
			request.setAttribute("teachers", nvtLm);
			//查询级别类型
			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value",vo.getLevelType());
			request.setAttribute("levelTypeStr", levelTypeStr);
			request.setAttribute("NetworkVideoActionForm", vo);

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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			NetworkVideoActionForm vo = (NetworkVideoActionForm) form;			
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			NetworkVideo nv = new NetworkVideo();
			this.copyProperties(nv, vo);
			Tool.testReflect_admin(nv);
			//拼接老师i
			String tckbox[] = request.getParameterValues("tckbox");
			String tIds = "";
			if(tckbox != null && tckbox.length>0){
				tIds = tckbox[0];
			}
			for (int i = 1; i < tckbox.length; i++) {
				tIds = tIds+","+tckbox[i];
			}
			nv.setTeacherId(tIds);
			String id = mgr.add(nv);
		/*	//添加默认课时
			NetworkVideoCatalog nvc = new NetworkVideoCatalog();
			nvc.setNvId(id);
			nvc.setcName("默认课时");
			nvc.setParentId("FFFFFF");
			nvc.setSort(1);
			mgr.add(nvc);*/
			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username, "添加课程,课程名称:"+vo.getNetworkName(), "1", ipaddress);
			
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

			NetworkVideoActionForm vo = new NetworkVideoActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			//查询老师
			List<NetworkVideoTeaher> nvtList = Tool.findListByHql("from NetworkVideoTeaher where isDel=0");
			request.setAttribute("teachers", nvtList);
			//查询级别
			//查询级别类型
			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value");
			request.setAttribute("levelTypeStr", levelTypeStr);
			request.setAttribute("NetworkVideoActionForm", vo);
			
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
	 * 跳转课时管理
	 * 方法功能说明：  
	 * 创建：2016年5月24日 by Zzc   
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
	public ActionForward preAddByCateLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String nvId = ParamUtils.getParameter(request, "nvId", true);

			request.setAttribute("nvId", nvId);
			
			return mapping.findForward("CatalogFrm");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 跳转课时视频管理
	 * 方法功能说明：  
	 * 创建：2016年5月24日 by Zzc   
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
	public ActionForward preAddByCateLogVideo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String nvId = ParamUtils.getParameter(request, "nvId", true);

			request.setAttribute("nvId", nvId);
			
			return mapping.findForward("CatalogVideoFrm");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 删除信息(逻辑删除)
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
				 
			String name = Tool.getValue("select network_name from network_video where id='"+ids[i]+"'");
			Tool.AddLog(sessionContainer.getUserId(), username,
					"删除课程,课程名称:"+name, "1", ipaddress);
			Tool.execute("update  network_video set is_del=1 where id = '"+ids[i]+"'");

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
	 * 逻辑删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "networkId", true);
				 
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			
			String name = Tool.getValue("select network_name from network_video where id='"+ID+"'");
			Tool.AddLog(sessionContainer.getUserId(), username,
					"删除课程,课程名称:"+name, "1", ipaddress);

			Tool.execute("update  network_video set is_del=1 where id = '"+ID+"'");
				 
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

		NetworkVideoActionForm vo = (NetworkVideoActionForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.NETWORK_PATH_PHONE,request);
			  
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
	 * 搭配图书
	 * 方法功能说明：  
	 * 创建：2016年5月26日 by Zzc   
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
	public ActionForward Colloc_list(ActionMapping mapping, ActionForm form,
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
			String productFid = ParamUtils.getParameter(request, "productFid", false);
			
			String nvNamelike = ParamUtils.getParameter(request, "nvNamelike", false);
			String stateStr = ParamUtils.getParameter(request, "stateStr", false);
			String type = ParamUtils.getParameter(request, "type", false);
			
			//查询已有套餐中的图书
			List<ProductCollocation> lp = Tool.findListByHql("from ProductCollocation where productFid='"+productFid+"' and productType=1 and isDel=0");
			String lpId = "'"+productFid+"'";
			for (ProductCollocation productCollocation : lp) {
				lpId +=",'"+productCollocation.getProductId()+"'";
			}
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("NetworkVideo.networkName", "String", "like", nvNamelike));
			queryConds.add(new QueryCond("NetworkVideo.networkType", "String", "=", stateStr));
			queryConds.add(new QueryCond("NetworkVideo.isDel", "number", "=", "0"));
			queryConds.add(new QueryCond("NetworkVideo.id", "String", "not in", lpId));
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("lc", lc);
			
			request.setAttribute("nvNamelike", nvNamelike);
			request.setAttribute("stateStr", stateStr);
			request.setAttribute("productFid", productFid);
			request.setAttribute("type", type);
			
			
			return mapping.findForward("Colloclist");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 优惠券绑定网课
	 * 方法功能说明：  
	 * 创建：2016年7月29日 by Zzc   
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
	public ActionForward pcp_list(ActionMapping mapping, ActionForm form,
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
			String pcId = ParamUtils.getParameter(request, "pcId", false);
			
			String nvNamelike = ParamUtils.getParameter(request, "nvNamelike", false);
			String stateStr = ParamUtils.getParameter(request, "stateStr", false);
			
			//查询已绑定的网课
			List<PreferentialCodeProduct> lp = Tool.findListByHql("from PreferentialCodeProduct where pcId='"+pcId+"' and type=1 and isDel=0");
			String lpId = "'"+pcId+"'";
			if(lp.size()>0){
				lpId = "'"+lp.get(0).getProductId()+"'";
			}
			for (int i = 1; i < lp.size(); i++) {
				lpId += ",'"+lp.get(i).getProductId()+"'";
			}
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("NetworkVideo.networkName", "String", "like", nvNamelike));
			queryConds.add(new QueryCond("NetworkVideo.networkType", "String", "=", stateStr));
			queryConds.add(new QueryCond("NetworkVideo.isDel", "number", "=", "0"));
			queryConds.add(new QueryCond("NetworkVideo.id", "String", "not in", lpId));
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("lc", lc);
			
			request.setAttribute("nvNamelike", nvNamelike);
			request.setAttribute("stateStr", stateStr);
			request.setAttribute("pcId", pcId);
			
			
			return mapping.findForward("pcplist");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	
	/**
	 * 得到列表,产品相关搭配弹窗显示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward collocationList(ActionMapping mapping, ActionForm form,
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
			String packageId = ParamUtils.getParameter(request, "packageId", false);
			
			Collection queryConds = new ArrayList();
			// 设置查询条件
			queryConds.add(new QueryCond("Product.pName", "String", "like", nameStr));
			
		 
			//查询当前产品
			
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			//查询已搭配的产品
			
			request.setAttribute("lc", lc);
			
			
			// 查询对象
			String hq = "from ProductType as ProductType where 1=1  order by ProductType.id   ";
			List<ProductType> productTypeList = Tool.findListByHql(hq);
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute("nameStr", nameStr);
			request.setAttribute("packageId", packageId);
			
			return mapping.findForward("collocationList");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 首页推荐或取消推荐
	 * 方法功能说明：  
	 * 创建：2016年6月1日 by Zzc   
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
	public ActionForward AjaxUpt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = ParamUtils.getParameter(request, "id");	
		String type = ParamUtils.getParameter(request, "type");	
		if (id != null && !"".equals(id)) {
			try {
				NetworkVideoActionForm nvaf = mgr.view(id);
				List<NetworkVideo> nvList = Tool.findListByHql("from NetworkVideo where isDel=0 and isIndex=1 and levelType='"+nvaf.getLevelType()+"'");
				if(nvList.size()>=2){
					NetworkVideo nv = nvList.get(0);
					nv.setIsIndex(0);
					mgr.update(nv);
				}
				boolean isSuc = Tool.execute("update network_video set is_index = "+type+" where id='"+id+"'");
				
				if (isSuc) {
					// 成功
					map.put("succeed", "000");
				} else {
					// 失败
					map.put("succeed", "001");
				}
			} catch (Exception e) {
				// 异常
				map.put("succeed", "003");
				e.printStackTrace();
			}
		} else {
			// 传值为空
			map.put("succeed", "501");
		}
		map.put("interface_name", "NewQuestionsPapersTopic_realdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	
	/**
	 * 为图书提供配套网课
	 */
	public ActionForward productList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try{
			int currentPageInt=ParamUtils.getIntParameter(request, "currentPage", 1);
			String strItemsInPage=ParamUtils.getParameter(request, "totalItem", false);
			int itemsInPage=Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
			
			if(strItemsInPage != null){
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem",15);
			}
			
			String action=ParamUtils.getParameter(request, "pageAction", true);
			
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			//接收传值
			String productId=ParamUtils.getParameter(request, "productId",false);
			String networkName=ParamUtils.getParameter(request, "networkName",false);
			
			//设置查询条件
			Collection queryConds=new ArrayList();
			
			queryConds.add(new QueryCond("NetworkVideo.isDel", "number", "=", "0"));
			queryConds.add(new QueryCond("NetworkVideo.networkName", "String", "like", networkName));

			//查询已配套的网课,排除掉已经配套的网课
			List<ProductNetworkVideo> pnvList=Tool.findListByHql("from ProductNetworkVideo where productId='"+productId+"'");
			String ids="";
			if(pnvList.size()>0){
				ids="'"+pnvList.get(0).getNvId()+"'";
			}
			for(int i=1; i< pnvList.size();i++){
				ids=ids+",'"+pnvList.get(i).getNvId()+"'";
			}
			
			queryConds.add(new QueryCond("NetworkVideo.id", "String", "not in", ids));
			
			
			ListContainer lc=mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
			
			request.setAttribute("productId", productId);
			request.setAttribute("networkName", networkName);
			request.setAttribute("lc", lc);
			
			return mapping.findForward("productList");
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 为网课轮播图提供绑定网课
	 */
	public ActionForward scList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try{
			int currentPageInt=ParamUtils.getIntParameter(request, "currentPage", 1);
			String strItemsInPage=ParamUtils.getParameter(request, "totalItem", false);
			int itemsInPage=Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
			
			if(strItemsInPage != null){
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem",15);
			}
			
			String action=ParamUtils.getParameter(request, "pageAction", true);
			
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			//接收传值
			String networkName=ParamUtils.getParameter(request, "networkName",false);
			
			//设置查询条件
			Collection queryConds=new ArrayList();
			
			queryConds.add(new QueryCond("NetworkVideo.isDel", "number", "=", "0"));
			queryConds.add(new QueryCond("NetworkVideo.networkName", "String", "like", networkName));

			ListContainer lc=mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
			
			request.setAttribute("networkName", networkName);
			request.setAttribute("lc", lc);
			
			return mapping.findForward("scList");
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
		
		
	}
}
