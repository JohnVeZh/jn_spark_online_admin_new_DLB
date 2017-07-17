package com.business.Product; 


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
import com.easecom.common.util.AddZero;
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
import com.easecom.common.util.RandomUtil;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.business.NetworkVideo.NetworkVideoMgr;
import com.business.PreferentialCodeProduct.PreferentialCodeProduct;
import com.business.Product.Product;
import com.business.Product.ProductActionForm;
import com.business.Product.ProductMgr;
import com.business.ProductCollocation.ProductCollocation;
import com.business.ProductCollocation.ProductCollocationMgr;
import com.business.ProductType.ProductType;
import com.business.ProductType.ProductTypeMgr;

public class ProductAction extends BaseAction{
	 ProductMgr mgr = new ProductMgr();
	 ProductTypeMgr productTypeMgr = new ProductTypeMgr(); 
	 ProductCollocationMgr pcMgr = new ProductCollocationMgr();
	 
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
				treelist = mgr.getFuntree(rootid);

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
			String pTypeId = ParamUtils.getParameter(request, "pTypeId", false);
			String nameStr = ParamUtils.getParameter(request, "nameStr", false);
			String batchTime = ParamUtils.getParameter(request, "batchTime", false);
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("Product.pName", "String", "like", nameStr));
			queryConds.add(new QueryCond("Product.pTypeId", "String", "=", pTypeId));
			queryConds.add(new QueryCond("Product.pIsDel", "number", "=", "0"));
			queryConds.add(new QueryCond("Product.batchTime", "String", "like", batchTime));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
            request.setAttribute("type", Tool.getList("select typeName,id from city where parentId = '"+pTypeId+"'", "typeName", "id"));
			request.setAttribute("lc", lc);
			request.setAttribute("nameStr", nameStr);
			request.setAttribute("pTypeId", pTypeId);
			request.setAttribute("batchTime", batchTime);
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
			String nameStr = ParamUtils.getParameter(request, "nameStr", false);
			String type = ParamUtils.getParameter(request, "type", false);
			//查询已有套餐中的图书
			List<ProductCollocation> lp = Tool.findListByHql("from ProductCollocation where productFid='"+productFid+"' and productType=0 and isDel=0");
			String lpId = "'"+productFid+"'";
			for (ProductCollocation productCollocation : lp) {
				lpId +=",'"+productCollocation.getProductId()+"'";
			}
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("Product.pName", "String", "like", nameStr));
			queryConds.add(new QueryCond("Product.pIsDel", "number", "=", "0"));
			queryConds.add(new QueryCond("Product.id", "String", "not in", lpId));
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("lc", lc);
			request.setAttribute("nameStr", nameStr);
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
	 * 优惠卷绑定商品
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
			String nameStr = ParamUtils.getParameter(request, "nameStr", false);
			//查询已绑定的商品
			List<PreferentialCodeProduct> lp = Tool.findListByHql("from PreferentialCodeProduct where pcId='"+pcId+"' and type=0 and isDel=0");
			String lpId = "'"+pcId+"'";
			if(lp.size()>0){
				lpId = "'"+lp.get(0).getProductId()+"'";
			}
			for (int i = 1; i < lp.size(); i++) {
				lpId += ",'"+lp.get(i).getProductId()+"'";
			}
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("Product.pName", "String", "like", nameStr));
			queryConds.add(new QueryCond("Product.pIsDel", "number", "=", "0"));
			queryConds.add(new QueryCond("Product.id", "String", "not in", lpId));
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("lc", lc);
			request.setAttribute("nameStr", nameStr);
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

			request.setAttribute("ProductActionForm", mgr.view(ID));

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

		ProductActionForm vo = (ProductActionForm) form;
		try {
			vo.setpPresentPrice(Double.parseDouble(AddZero.formatRound(vo.getpPresentPrice(),2)));
			vo.setpOriginalPrice(Double.parseDouble(AddZero.formatRound(vo.getpOriginalPrice(),2)));
			vo.setMarketTime(vo.getBatchTime()+"年"+vo.getMarketTime()+"月");
			mgr.update(vo);
			request.setAttribute("msg", "200");
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"修改产品,产品名称:"+vo.getpName(), "1", ipaddress);
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
			String pTypeId = ParamUtils.getParameter(request, "pTypeId", false);
			String ID = ParamUtils.getParameter(request, "id", true);

			ProductActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("ProductActionForm", vo);
			request.setAttribute("pTypeId", pTypeId);
			//查询级别类型
//			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value",vo.getLevelType());
			List<Map> levelTypeStr = Tool.query("select value,name from sys_config where type='MT_TYPE'");
			request.setAttribute("levelTypeStr", levelTypeStr);
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
			ProductActionForm vo = (ProductActionForm) form;			  
			vo.setpCreatetime(DateUtils.getCurrDateTimeStr());
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			vo.setpPresentPrice(Double.parseDouble(AddZero.formatRound(vo.getpPresentPrice(),2)));
			vo.setpOriginalPrice(Double.parseDouble(AddZero.formatRound(vo.getpOriginalPrice(),2)));
			vo.setSysUserId(sessionContainer.getUserId());
			mgr.add(vo);
			
			request.setAttribute("msg", "200");
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"添加产品,产品名称:"+vo.getpName(), "1", ipaddress);
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
			String pTypeId = ParamUtils.getParameter(request, "pTypeId", false);
			ProductActionForm vo = new ProductActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("ProductActionForm", vo);
			request.setAttribute("pTypeId", pTypeId);
			//查询级别类型
//			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value");
			List<Map> levelTypeStr = Tool.query("select value,name from sys_config where type='MT_TYPE'");
			request.setAttribute("levelTypeStr", levelTypeStr);
			
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
			
			
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			
			
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				
				String name = Tool.getValue("select p_name product  where id='"+ids[i]+"'");
				Tool.AddLog(sessionContainer.getUserId(), username,
						"删除产品,产品名称:"+name, "1", ipaddress);
//			Tool.execute("delete from product where id = '"+ids[i]+"'");
				Tool.execute("update product set p_is_del = 1 where id='"+ids[i]+"'");
				 
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
			
			
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			String pId = ParamUtils.getParameter(request, "pId", false);
			
				String name = Tool.getValue("select p_name product  where id='"+pId+"'");
				Tool.AddLog(sessionContainer.getUserId(), username,
						"删除产品,产品名称:"+name, "1", ipaddress);
//			Tool.execute("delete from product where id = '"+ids[i]+"'");
				Tool.execute("update product set p_is_del = 1  where id='"+pId+"'");
				 
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
	public ActionForward uploadImgPath(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ProductActionForm vo = (ProductActionForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String value = "";
					value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.PRODUCT_ICON_PATH_PHONE,request);
			  
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
	 *关联图书
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward chose(ActionMapping mapping, ActionForm form,
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
			String pTypeId = ParamUtils.getParameter(request, "pTypeId", false);
			String nameStr = ParamUtils.getParameter(request, "nameStr", false);
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("Product.pName", "String", "like", nameStr));
			queryConds.add(new QueryCond("Product.pTypeId", "String", "=", pTypeId));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("lc", lc);
			request.setAttribute("nameStr", nameStr);
			request.setAttribute("pTypeId", pTypeId);
			
			return mapping.findForward("chose");
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
	 * 搭配试卷
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
	public ActionForward preMatched(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String id = ParamUtils.getParameter(request, "id", false);
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			return mapping.findForward("matched");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

    public ActionForward bookSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
		sessionContainer=new SessionContainer();
		NetworkVideoMgr nmgr = new NetworkVideoMgr();
		try {
			int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
			}
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			
			// 接收传值
			String title = ParamUtils.getParameter(request, "title", false);
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("Product.pName", "String", "like", title));
			queryConds.add(new QueryCond("Product.pIsDel", "number", "=", "0"));
			 
			ListContainer lc = mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
			
			request.setAttribute("lc", lc);
			request.setAttribute("title", title);
			request.setAttribute("srcpage", request.getParameter("srcpage"));
			request.setAttribute("idKey", request.getParameter("idKey"));
			request.setAttribute("valueKey", request.getParameter("valueKey"));
			
			return mapping.findForward("bookSelector");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
}
