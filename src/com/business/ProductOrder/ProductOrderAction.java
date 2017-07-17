package com.business.ProductOrder;


import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.business.Express.ExpressActionForm;
import com.business.Express.ExpressMgr;
import com.business.NetworkVideo.NetworkVideoActionForm;
import com.business.NetworkVideo.NetworkVideoMgr;
import com.business.Product.ProductActionForm;
import com.business.Product.ProductMgr;
import com.business.ProductCollocation.ProductCollocationMgr;
import com.business.ProductOrderDetails.ProductOrderDetails;
import com.business.ProductOrderDetails.ProductOrderDetailsMgr;
import com.business.ProductOrderDetailsCollocation.ProductOrderDetailsCollocation;
import com.business.ProductOrderLogistics.ProductOrderLogistics;
import com.business.ProductOrderLogistics.ProductOrderLogisticsActionForm;
import com.business.ProductOrderLogistics.ProductOrderLogisticsMgr;
import com.business.Users.Users;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.ExcelUtil;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StrUtils;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class ProductOrderAction extends BaseAction{
	ProductOrderMgr mgr = new ProductOrderMgr();
	ExpressMgr expressMgr = new ExpressMgr();
	ProductOrderLogisticsMgr logisticsMgr = new ProductOrderLogisticsMgr();
	ProductMgr productMgr = new ProductMgr();
	ProductOrderDetailsMgr productOrderDetailsMgr = new ProductOrderDetailsMgr();
	ProductCollocationMgr productPackageCollocationMgr = new ProductCollocationMgr();
	NetworkVideoMgr networkVideoMgr = new NetworkVideoMgr();
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



			String proType = ParamUtils.getParameter(request,"proType");
			String StatrSel = ParamUtils.getParameter(request, "StatrSel", false);
			String fromType = ParamUtils.getParameter(request, "fromType", false);//订单来源
			String starttime=ParamUtils.getParameter(request,"starttime");//开始时间
			String endtime=ParamUtils.getParameter(request,"endtime");//结束时间
			String shouName = ParamUtils.getParameter(request, "shouName");//收货人

			// 设置查询条件


			String orderCode = ParamUtils.getParameter(request,"orderCode");//订单号
			String zip = ParamUtils.getParameter(request, "zip");//邮编
			String username = ParamUtils.getParameter(request, "username");//购货人
			String address = ParamUtils.getParameter(request, "address");//地址
			String phone = ParamUtils.getParameter(request, "phone");//手机号
			String provinceIdStr=ParamUtils.getParameter(request, "provinceIdStr",false);
			String cityIdStr=ParamUtils.getParameter(request, "cityIdStr",false);
			String areaIdStr=ParamUtils.getParameter(request, "areaIdStr",false);

			String userId = "";
			Users us = (Users)mgr.getObjectByHql("from Users where username='"+username+"'");
			if(!"".equals(us)&&null!=us){
				userId = us.getId();
			}


			Collection queryConds = new ArrayList();
			if(null!=StatrSel && !"".equals(StatrSel)){
				queryConds.add(new QueryCond("ProductOrder.orderState", "String", "=", StatrSel));
			}
			if(null!=fromType && !"".equals(fromType)){
				queryConds.add(new QueryCond("ProductOrder.fromType", "String", "=", fromType));
			}
			if(null!=starttime&&!"".equals(starttime)&&null!=endtime&&!"".equals(endtime)){
				queryConds.add(new QueryCond("ProductOrder.createtime", "String", ">=", starttime));
				queryConds.add(new QueryCond("ProductOrder.createtime", "String", "<=", endtime));
			}
			queryConds.add(new QueryCond("ProductOrder.consignee", "String", "=", shouName));

			queryConds.add(new QueryCond("ProductOrder.orderCode","String","=",orderCode));
			queryConds.add(new QueryCond("ProductOrder.zipcode","String","=",zip));
			queryConds.add(new QueryCond("ProductOrder.address","String","=",address));
			queryConds.add(new QueryCond("ProductOrder.telephone","String","=",phone));
			//queryConds.add(new QueryCond("ProductOrder.userId","String","=",userId));

			queryConds.add(new QueryCond("ProductOrder.provinceId","String","=",provinceIdStr));
			queryConds.add(new QueryCond("ProductOrder.cityId","String","=",cityIdStr));
			queryConds.add(new QueryCond("ProductOrder.areaId","String","=",areaIdStr));
			queryConds.add(new QueryCond("ProductOrder.type","String","in", "0, 2, 4"));

			String pod = "";
			if(!"".equals(proType)&&null!=proType){
				pod = Tool.getList("select product_order_id from product_order_details where 1=1","product_order_id",proType);
			}

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			List ll = lc.getList();
			List<Map> la = new ArrayList<Map>();
			for (int i = 0; i < ll.size(); i++)
			{
				ProductOrder po = null;
				po = (ProductOrder)ll.get(i);

				Map pp = new HashMap();
				pp.put("Id",po.getId());
				pp.put("orderCode",po.getOrderCode());
				pp.put("userName",Tool.getValue("select username from users where id='"+po.getUserId()+"'"));
				pp.put("consignee",po.getConsignee());
				pp.put("fromType",po.getFromType());
				try {
					if(po.getTelephone().equals("")){
						pp.put("telephone",Tool.getValue("select mobile from users where id='"+po.getUserId()+"'"));
					}else{
						pp.put("telephone",po.getTelephone());
					}
				}catch (Exception e){
					pp.put("telephone",Tool.getValue("select mobile from users where id='"+po.getUserId()+"'"));
				}

				pp.put("userId",po.getUserId());
				String proId = Tool.getValue("select pname from province where provinceID = '"+po.getProvinceId()+"'");
				String cityId = Tool.getValue("select city from city where cityID = '"+po.getCityId()+"'");
				String area = Tool.getValue("select area from area where areaID = '"+po.getAreaId()+"'");
                String couponCodeId = couponCodeId = Tool.getValue("select coupon_code_id from tb_coupon_record where order_id = '"+po.getId()+"'");
				pp.put("proName",proId+"-"+cityId+"-"+area);
				pp.put("cod",po.getOrderState());
				if("not_deliver".equals(po.getOrderState())){
					pp.put("OrderState","待发货");
				}else if("not_received".equals(po.getOrderState())){
					pp.put("OrderState","待收货");
				}else if("not_comment".equals(po.getOrderState())){
					pp.put("OrderState","待评论");
				}else if("been_canceled".equals(po.getOrderState())){
					pp.put("OrderState","已取消");
				}else if("completed".equals(po.getOrderState())){
					pp.put("OrderState","已完成");
				}else if("not_paid".equals(po.getOrderState())){
					pp.put("OrderState","未支付");
				}
				pp.put("price",po.getPrice());
                pp.put("payPrice",po.getPayPrice());
				pp.put("createtime",po.getCreatetime());
				pp.put("logisticscode", StrUtils.null2Str(po.getLogisticscode()));
                pp.put("couponCodeId",couponCodeId);
				String eCode = Tool.getValue("select log.e_code from product_order_logistics log where log.logisticsCode='"+po.getLogisticscode()+"'");
				pp.put("eCode",eCode);

				  /*查询订单商品*/
				List<ProductOrderDetails> detailsList = Tool.findListByHql("from ProductOrderDetails where productOrderId = '"+po.getId()+"'");
				List<Map> mList = new ArrayList<Map>();
				//查询出主商品
				for (ProductOrderDetails productOrderDetails : detailsList) {
					Map map = new HashMap();
					map.put("id", productOrderDetails.getId());
					map.put("pFType", productOrderDetails.getProductType());
					map.put("pcount", productOrderDetails.getPcount());
					map.put("orderPrice", productOrderDetails.getMoney());
					map.put("statusValue", productOrderDetails.getStatus());
					if(productOrderDetails.getStatus()!=null){
						map.put("status", Tool.getValue("select name from sys_config where value='"+productOrderDetails.getStatus()+"'"));
					}else{
						map.put("status", "");
					}

					if(productOrderDetails.getProductType()==0){
						//查询图书
						ProductActionForm pro = productMgr.view(productOrderDetails.getProductId());
						if(null != pro){
							map.put("pFId", pro.getId());
							map.put("pFName", pro.getpName());
							map.put("presentPrice", pro.getpPresentPrice());
							map.put("originalPrice", pro.getpOriginalPrice());
							//查询套餐集合
							List<ProductOrderDetailsCollocation> cList = Tool.findListByHql("from ProductOrderDetailsCollocation where podId='"+productOrderDetails.getId()+"'");
							List<Map> collMap = new ArrayList<Map>();
							for (ProductOrderDetailsCollocation productOrderDetailsCollocation : cList) {
								Map colMap = new HashMap();
								colMap.put("pFType", productOrderDetailsCollocation.getProductType());
								colMap.put("colPrice", productOrderDetailsCollocation.getMoney());
								colMap.put("statusValue",productOrderDetailsCollocation.getStatus());
								if(productOrderDetailsCollocation.getStatus()!=null){
									colMap.put("status", Tool.getValue("select name from sys_config where value='"+productOrderDetailsCollocation.getStatus()+"'"));
								}else{
									colMap.put("status","");
								}
								//图书
								if(productOrderDetailsCollocation.getProductType()==0){
									//查询套餐商品
									ProductActionForm colPro = productMgr.view(productOrderDetailsCollocation.getProductId());
									if(null != colPro){
										colMap.put("pFId", colPro.getId());
										colMap.put("pFName", colPro.getpName());
										colMap.put("presentPrice", colPro.getpPresentPrice());
										colMap.put("originalPrice", colPro.getpOriginalPrice());
				    		    			/*colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));*/
										Double t = Tool.geDoubleValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'");
										if(t!=0&&t!=null){
											colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
										}else{
											colMap.put("colloMoney","0");
										}
									}
								}else{
									//查询搭配套餐的网课
									NetworkVideoActionForm net = networkVideoMgr.view(productOrderDetailsCollocation.getProductId());
									if(null != net){
										colMap.put("pFId", net.getId());
										colMap.put("pFName", net.getNetworkName());
										colMap.put("presentPrice", net.getOriginalPrice());
										colMap.put("originalPrice", net.getNetworkMoney());
				    		    			/*colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));*/
										Double t = Tool.geDoubleValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'");
										if(t!=0&&t!=null){
											colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
										}else{
											colMap.put("colloMoney","0");
										}
									}
								}
								collMap.add(colMap);
							}
							map.put("collMap", collMap);
						}
					}else{
						//查询网课
						NetworkVideoActionForm net = networkVideoMgr.view(productOrderDetails.getProductId());
						map.put("pFId", net.getId());
						map.put("pFName", net.getNetworkName());
						map.put("presentPrice", net.getOriginalPrice());
						map.put("originalPrice", net.getNetworkMoney());
						//查询套餐集合
						List<ProductOrderDetailsCollocation> cList = Tool.findListByHql("from ProductOrderDetailsCollocation where podId='"+productOrderDetails.getId()+"'");
						List<Map> collMap = new ArrayList<Map>();
						for (ProductOrderDetailsCollocation productOrderDetailsCollocation : cList) {
							Map colMap = new HashMap();
							colMap.put("pFType", productOrderDetailsCollocation.getProductType());

							//图书
							if(productOrderDetailsCollocation.getProductType()==0){
								//查询套餐商品
								ProductActionForm colPro = productMgr.view(productOrderDetailsCollocation.getProductId());
								if(null != colPro){
									colMap.put("pFId", colPro.getId());
									colMap.put("pFName", colPro.getpName());
									colMap.put("presentPrice", colPro.getpPresentPrice());
									colMap.put("originalPrice", colPro.getpOriginalPrice());
			    		    			/*colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));*/
									Double t = Tool.geDoubleValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'");
									if(t!=0&&t!=null){
										colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
									}else{
										colMap.put("colloMoney","0");
									}
								}
							}else{
								//查询搭配套餐的网课
								NetworkVideoActionForm collNet = networkVideoMgr.view(productOrderDetailsCollocation.getProductId());
								if(null != collNet){
									colMap.put("pFId", collNet.getId());
									colMap.put("pFName", collNet.getNetworkName());
									colMap.put("presentPrice", collNet.getOriginalPrice());
									colMap.put("originalPrice", collNet .getNetworkMoney());
			    		    			/*colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));*/
									Double t = Tool.geDoubleValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'");
									if(t!=0&&t!=null){
										colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
									}else{
										colMap.put("colloMoney","0");
									}
								}
							}
							collMap.add(colMap);
						}
						map.put("collMap", collMap);
					}
					mList.add(map);
				}
				pp.put("mList",mList);
				la.add(pp);
			}
			request.setAttribute("mList", la);

			String pro = Tool.getList("select id,p_name from product where p_is_del='0' order by level_type asc","p_name","id");
			if(!"".equals(proType)&&null!=proType){
				pro = Tool.getList("select id,p_name from product where p_is_del='0' order by level_type asc","p_name","id",proType);
			}
			request.setAttribute("pro", pro);



			//获取订单状态列表
			String orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value");
			if(!"".equals(StatrSel)&&null!=StatrSel){
				orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value",StatrSel);
			}
			request.setAttribute("provinceIdStr", provinceIdStr);
			request.setAttribute("cityIdStr", cityIdStr);
			request.setAttribute("areaIdStr", areaIdStr);
			request.setAttribute("shouName", shouName);
			request.setAttribute("orderStatus", orderStatus);
			request.setAttribute("starttime", starttime);
			request.setAttribute("endtime", endtime);
			request.setAttribute("fromType", fromType);
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
			ProductOrderActionForm vo = mgr.view(ID);
			request.setAttribute("ProductOrderActionForm", vo);
			request.setAttribute("uName", Tool.getValue("select username from users where id='"+vo.getUserId()+"'"));
			String pca = Tool.getValue("select pname from province where provinceID = '"+vo.getProvinceId()+"'");
			pca = pca +"-"+ Tool.getValue("select city from city where cityID = '"+vo.getCityId()+"'");
			pca = pca +"-"+ Tool.getValue("select area from area where areaID = '"+vo.getAreaId()+"'");
			request.setAttribute("pca",pca);

			String state = Tool.getValue("select name from sys_config where type='ORDER_STATUS' and alias='"+vo.getOrderState()+"'");

		    /*查询订单商品*/
			List<ProductOrderDetails> detailsList = Tool.findListByHql("from ProductOrderDetails where productOrderId = '"+vo.getId()+"'");
			List<Map> mList = new ArrayList<Map>();
			//查询出主商品
			for (ProductOrderDetails productOrderDetails : detailsList) {
				Map map = new HashMap();
				map.put("pFType", productOrderDetails.getProductType());

				if(productOrderDetails.getProductType()==0){
					//查询图书
					ProductActionForm pro = productMgr.view(productOrderDetails.getProductId());
					if(null != pro){
						map.put("pFId", pro.getId());
						map.put("pFName", pro.getpName());
						map.put("presentPrice", pro.getpPresentPrice());
						map.put("originalPrice", pro.getpOriginalPrice());
						//查询套餐集合
						List<ProductOrderDetailsCollocation> cList = Tool.findListByHql("from ProductOrderDetailsCollocation where podId='"+productOrderDetails.getId()+"'");
						List<Map> collMap = new ArrayList<Map>();
						for (ProductOrderDetailsCollocation productOrderDetailsCollocation : cList) {
							Map colMap = new HashMap();
							colMap.put("pFType", productOrderDetailsCollocation.getProductType());
							//图书
							if(productOrderDetailsCollocation.getProductType()==0){
								//查询套餐商品
								ProductActionForm colPro = productMgr.view(productOrderDetailsCollocation.getProductId());
								if(null != colPro){
									colMap.put("pFId", colPro.getId());
									colMap.put("pFName", colPro.getpName());
									colMap.put("presentPrice", colPro.getpPresentPrice());
									colMap.put("originalPrice", colPro.getpOriginalPrice());
									colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
								}
							}else{
								//查询搭配套餐的网课
								NetworkVideoActionForm net = networkVideoMgr.view(productOrderDetailsCollocation.getProductId());
								if(null != net){
									colMap.put("pFId", net.getId());
									colMap.put("pFName", net.getNetworkName());
									colMap.put("presentPrice", net.getOriginalPrice());
									colMap.put("originalPrice", net.getNetworkMoney());
									colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
								}
							}
							collMap.add(colMap);
						}
						map.put("collMap", collMap);
					}
				}else{
					//查询网课
					NetworkVideoActionForm net = networkVideoMgr.view(productOrderDetails.getProductId());
					map.put("pFId", net.getId());
					map.put("pFName", net.getNetworkName());
					map.put("presentPrice", net.getOriginalPrice());
					map.put("originalPrice", net.getNetworkMoney());
					//查询套餐集合
					List<ProductOrderDetailsCollocation> cList = Tool.findListByHql("from ProductOrderDetailsCollocation where podId='"+productOrderDetails.getId()+"'");
					List<Map> collMap = new ArrayList<Map>();
					for (ProductOrderDetailsCollocation productOrderDetailsCollocation : cList) {
						Map colMap = new HashMap();
						colMap.put("pFType", productOrderDetailsCollocation.getProductType());
						//图书
						if(productOrderDetailsCollocation.getProductType()==0){
							//查询套餐商品
							ProductActionForm colPro = productMgr.view(productOrderDetailsCollocation.getProductId());
							if(null != colPro){
								colMap.put("pFId", colPro.getId());
								colMap.put("pFName", colPro.getpName());
								colMap.put("presentPrice", colPro.getpPresentPrice());
								colMap.put("originalPrice", colPro.getpOriginalPrice());
								colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
							}
						}else{
							//查询搭配套餐的网课
							NetworkVideoActionForm collNet = networkVideoMgr.view(productOrderDetailsCollocation.getProductId());
							if(null != collNet){
								colMap.put("pFId", collNet.getId());
								colMap.put("pFName", collNet.getNetworkName());
								colMap.put("presentPrice", collNet.getOriginalPrice());
								colMap.put("originalPrice", collNet .getNetworkMoney());
								colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
							}
						}
						collMap.add(colMap);
					}
					map.put("collMap", collMap);
				}
				mList.add(map);
			}
			request.setAttribute("mList",mList);

			request.setAttribute("state",state);
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
	 * 预发货
	 * 方法功能说明：
	 * 创建：2016年3月31日 by Zzc
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
	public ActionForward preCodes(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "orderId", true);
			ProductOrderActionForm vo = mgr.view(ID);
			if(null!=vo){
				request.setAttribute("orderId", vo.getId());
			}
			String logisticsStr = Tool.getList("select id,e_name from express order by e_order","e_name","id");
			request.setAttribute("logisticsStr", logisticsStr);
			return mapping.findForward("code");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 发货
	 * 方法功能说明：
	 * 创建：2016年3月31日 by Zzc
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
	public ActionForward codes(ActionMapping mapping, ActionForm form,
							   HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "orderId", true);
			String logisticscode = ParamUtils.getParameter(request, "logisticscode", true);
			String logistics = ParamUtils.getParameter(request, "logistics", true);
			ProductOrderActionForm vo = mgr.view(ID);
			if(vo!=null){
				vo.setOrderState(DictionaryUtils.ORDER_STATE_NOT_RECEIVED);
				vo.setLogisticscode(logisticscode);
				vo.setAutoRewardTime(DateUtils.format(new Date(new Date().getTime()+14*24*60*60*1000),"yyyy-MM-dd HH:mm:ss"));
//				vo.setRemark("发货人："+vo.getUserId());
				mgr.update(vo);
				ExpressActionForm ex =  expressMgr.view(logistics);
				if(ex!=null){
					ProductOrderLogistics pol=(ProductOrderLogistics)mgr.getObjectByHql("from ProductOrderLogistics where orderHistory='"+ID+"' and 1=1");
					if(!"".equals(pol)&&null!=pol){
						pol.seteCode(ex.geteCode());
						pol.setLogisticscode(logisticscode);
						pol.seteName(ex.geteName());
						mgr.update(pol);
					}else{
						//添加物流记录
						ProductOrderLogisticsActionForm logins = new ProductOrderLogisticsActionForm();
						logins.seteCode(ex.geteCode());
						logins.seteName(ex.geteName());
						logins.setCreatetime(DateUtils.getCurrDateTimeStr());
						logins.setOrderHistory(vo.getId());
						logins.setLogisticscode(logisticscode);
						logisticsMgr.add(logins);
					}
				}

			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 完成订单
	 * 方法功能说明：
	 * 创建：2016年4月1日 by Zzc
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
	public ActionForward complete(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "orderId", true);
			ProductOrderActionForm vo = mgr.view(ID);
			if(vo!=null){
				vo.setOrderState(DictionaryUtils.ORDER_STATE_NOT_COMPLETED);
				mgr.update(vo);

			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 取消订单
	 * 方法功能说明：
	 * 创建：2016年4月1日 by Zzc
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "orderId", true);
			ProductOrderActionForm vo = mgr.view(ID);
			if(vo!=null){
				vo.setOrderState(DictionaryUtils.ORDER_STATE_BEEN_CANCELED);
				vo.setRemark("取消人："+vo.getUserId());
				mgr.update(vo);

			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	public ActionForward preCancel(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response) {
		try {
			String ID = ParamUtils.getParameter(request, "orderId", true);
			String id = ParamUtils.getParameter(request, "id", true);

			ProductOrderActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			String state = Tool.getValue("select name from sys_config where type='ORDER_STATUS' and alias='"+vo.getOrderState()+"'");


			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("uName", Tool.getValue("select username from users where id='"+vo.getUserId()+"'"));
			request.setAttribute("state",state);
			request.setAttribute("ProductOrderActionForm", vo);

			return mapping.findForward("cancel");
		} catch (Exception ex) {
			ex.printStackTrace();
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

		ProductOrderActionForm vo = (ProductOrderActionForm) form;
		try {
			ProductOrderActionForm poa = mgr.view(vo.getId());
			if(null != poa){
				poa.setCreatetime(DateUtils.getCurrDateTimeStr());
				poa.setConsignee(vo.getConsignee());
				poa.setTelephone(vo.getTelephone());
				poa.setZipcode(vo.getZipcode());
				poa.setPrice(vo.getPrice());
				poa.setPostage(vo.getPostage());
				poa.setAddress(vo.getAddress());
				poa.setRemark(vo.getRemark());
				poa.setPayPrice(vo.getPayPrice());
				mgr.update(poa);
			}

			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"修改订单,订单编号:"+vo.getOrderCode(), "1", ipaddress);

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

			ProductOrderActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			String state = Tool.getValue("select name from sys_config where type='ORDER_STATUS' and alias='"+vo.getOrderState()+"'");


			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("uName", Tool.getValue("select username from users where id='"+vo.getUserId()+"'"));
			request.setAttribute("state",state);
			request.setAttribute("ProductOrderActionForm", vo);

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
			ProductOrderActionForm vo = (ProductOrderActionForm) form;
			//vo.setType("1");
			mgr.add(vo);
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

			ProductOrderActionForm vo = new ProductOrderActionForm();
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}

			request.setAttribute("ProductOrderActionForm", vo);

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
	 * 增加信息
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
							   HttpServletRequest request, HttpServletResponse response) {

		try {

			ProductOrderActionForm vo = (ProductOrderActionForm) form;



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
	 * 查询
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preQuery(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) {

		try {
			ProductOrderActionForm vo = new ProductOrderActionForm();
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("provinceList", Tool.getList("select provinceID,pname from province", "pname", "provinceID"));
			//获取订单状态列表
			String orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value");
			request.setAttribute("orderStatus", orderStatus);



			return mapping.findForward("query");
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

				//Tool.execute("delete from product_order where id = '"+ids[i]+"'");
				Tool.execute("update product_order set admin_del=1 where id='"+ids[i]+"'");
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

			String ID = ParamUtils.getParameter(request, "id", true);
			if(null!=ID && !"".equals(ID)){
				Tool.execute("update product_order set admin_del=1 where id='"+ID+"'");
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
	//退单管理,跳转
	public ActionForward preCollEdit(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);
			String status = ParamUtils.getParameter(request, "status", true);
			request.setAttribute("ID", ID);
			String selStr = Tool.getList("select value,name from sys_config where type='ORDER_REFUND_STATUS'", "name", "value",status);
			request.setAttribute("selStr", selStr);

			String type = Tool.getValue("select type from product_order_refund where product_order_details_id='"+ID+"'");
			request.setAttribute("type", type);
			String order_code = Tool.getValue("select order_code from product_order_refund where product_order_details_id='"+ID+"'");
			request.setAttribute("orderCode", order_code);
			return mapping.findForward("collEdit");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	//退单管理
	public ActionForward collEdit(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String podId = ParamUtils.getParameter(request, "podId", true);
			String status = ParamUtils.getParameter(request, "status", true);
			//修改主订单退单状态
			Tool.execute("update product_order_details set status='"+status+"' where id='"+podId+"'");
			//修改搭配套餐状态
			Tool.execute("update product_order_details_collocation set status='"+status+"' where pod_id='"+podId+"'");
			
			
			if(status.equals("refunded_completed")){
				//product_order_ID
				String product_order_IDString= Tool.getValue("select product_order_id from product_order_details where id='"+podId+"'");
				
				String user_IDString= Tool.getValue("select user_id from product_order where id='"+product_order_IDString+"'");
				//productID
				String product_IDString= Tool.getValue("select product_id from product_order_details where id='"+podId+"'");
				
				Tool.execute("update product_order_refund set check_status='pay_finished' where product_order_details_id='"+podId+"'");
				List query = Tool.query("SELECT * FROM network_course_actlog where user_id='"+user_IDString+"' and nc_id='"+product_IDString+"'");
            	if(query.size()>0){
            		Tool.execute("update network_course_actlog set act_type = 5 where user_id='"+user_IDString+"' and nc_id='"+product_IDString+"'");
            	}else{
            		Tool.execute("INSERT INTO network_course_actlog(user_id,nc_id,act_type,create_time)values"
            				+ "('"+user_IDString+"','"+product_IDString+"','5','"+DateUtils.getCurrDateTimeStr()+"')");
            	}
			}


			map.put("result", true);
		} catch (Exception ex) {
			map.put("result", false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	/*修改订单状态跳转页面*/
	public ActionForward preOrderStateEdit(ActionMapping mapping, ActionForm form,
										   HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);
			String status = ParamUtils.getParameter(request, "status", true);
			request.setAttribute("ID", ID);
			String selStr = Tool.getList("select value,name from sys_config where type='ORDER_STATUS'", "name", "value",status);
			request.setAttribute("selStr", selStr);

			return mapping.findForward("orderStateEdit");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/*修改订单状态*/
	public ActionForward orderStateEdit(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String poId = ParamUtils.getParameter(request, "poId", true);
			String status = ParamUtils.getParameter(request, "status", true);
			//修改订单退单状态
			System.out.println("update product_order set order_state='"+status+"' where id='"+poId+"'");
			Tool.execute("update product_order set order_state='"+status+"' where id='"+poId+"'");

			map.put("result", true);
		} catch (Exception ex) {
			map.put("result", false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}

	/**
	 * 导出图书或者网课订单
	 *
	 *
	 *
	 */

	public void exportExcel(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {

			// 接收传值
			String StatrSel = ParamUtils.getParameter(request, "StatrSel1", false);
			String starttime=ParamUtils.getParameter(request,"starttime1");//开始时间
			String endtime=ParamUtils.getParameter(request,"endtime1");//结束时间
			String shouName = ParamUtils.getParameter(request, "shouName1");//收货人
			String netWorkOrBook = ParamUtils.getParameter(request, "netWorkOrBook");//图书或者网课
			// 设置查询条件
			Collection queryConds = new ArrayList();
			if(null!=StatrSel && !"".equals(StatrSel)){
				queryConds.add(new QueryCond("po.order_state", "String", "=", StatrSel));
			}
			if(null!=starttime&&!"".equals(starttime)&&null!=endtime&&!"".equals(endtime)){
				queryConds.add(new QueryCond("po.createtime", "String", ">=", starttime));
				queryConds.add(new QueryCond("po.createtime", "String", "<=", endtime+" 23:59:59"));
			}
			if(null!=shouName && !"".equals(shouName)){
				queryConds.add(new QueryCond("po.consignee", "String", "=", shouName));
			}


			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String timestr = sdf.format(new Date());
			String fileName=new String((netWorkOrBook+"订单-"+timestr).getBytes("gb2312"), "iso8859-1")+ ".xls";
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setCharacterEncoding("utf-8");
			ServletOutputStream outputStream = response.getOutputStream();
			List list = new ArrayList ();
			ExcelUtil eu = new ExcelUtil();
			if(netWorkOrBook.equals("图书")){
				String[] titles = { "网店订单号", "商品名称","ERP编码", "数量", "订单金额", "实付金额","应付金额","买家昵称","收货人姓名","收货人手机号","收货人电话","收货人邮编","收货人所在省","收货人所在市","收货人所在区","收货人详细地址 ","买家订单留言","商家订单留言","订单运费","下单时间"};
				list = (ArrayList<Object>) mgr.getBookOrderList(queryConds);
				eu.ExportExcel(titles, list, outputStream);
			}else if(netWorkOrBook.equals("网课")){
				String[] titles = { "网店订单号", "商品名称","ERP编码","退款状态", "数量", "支付方式","订单金额", "实付金额","应付金额","买家昵称","收货人姓名","收货人手机号","收货人电话","收货人邮编","收货人所在省","收货人所在市","收货人所在区","收货人详细地址 ","买家订单留言","商家订单留言","订单运费","下单时间"};
				String[] column = {"order_code","p_name",null,"check_status","pcount","payType","pay_price","money",null,"username","consignee","telephone",null,"zipcode","pname","city","area","address","remark",null,"postage","createtime"};
				list =  mgr.getNetWorkOrderList(queryConds);
				eu.ExportExcelConmon(titles,column,list,fileName,response);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);

		}
	}


	public ActionForward coupon(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response){
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try {
            String couponCodeId = ParamUtils.getParameter(request, "couponCodeId", true);
            String sql = "SELECT c.title, c.type, c.`status`, c.max_use_num, c.start_time, c.end_time, " +
                    "c.min_money,c.discount_money, c.discount_rate, c.create_time AS ctime, c.relation_type," +
                    "cc.id as code_id, cc.`code`, cc.coupon_id, cc.use_num, cc.create_time as codetime " +
                    "FROM tb_coupon c INNER JOIN tb_coupon_code cc ON cc.coupon_id = c.id where cc.id='"+couponCodeId+"'" ;
            List lc = mgr.SQLQuery(sql);
            String title = "",type="",status="",max_use_num="",start_time="",end_time="",min_money="",discount_money="",discount_rate = "",ctime="",relation_type="",code="",use_num = "",codetime="";
            for(int i = 0; i<lc.size();i++){
                Map map = (Map) lc.get(i);
                try {title = map.get("title").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {type = map.get("type").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  status = map.get("status").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  max_use_num = map.get("max_use_num").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  start_time = map.get("start_time").toString().substring(0,10); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  end_time = map.get("end_time").toString().substring(0,10); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  min_money = map.get("min_money").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  discount_money = map.get("discount_money").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  discount_rate = map.get("discount_rate").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  ctime = map.get("ctime").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  relation_type = map.get("relation_type").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  code = map.get("code").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  use_num = map.get("use_num").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                try {  codetime = map.get("codetime").toString(); }catch (Exception e){System.out.println(e.getMessage() );};
                if(type.equals("1"))type="折扣券";else type="现金券";
                if(status.equals("0"))status="启用";else status="停用";
                if(max_use_num.equals("-1")) max_use_num="无限制";
                if(relation_type.equals("1"))relation_type="非全场";else if(relation_type.equals("2"))relation_type="全场网课";else if(relation_type.equals("3"))relation_type="全场图书";else if(relation_type.equals("4")) relation_type="全场通用";

            }
            request.setAttribute("title",title);
            request.setAttribute("type",type);
            request.setAttribute("status",status);
            request.setAttribute("max_use_num",max_use_num);
            request.setAttribute("start_time",start_time);
            request.setAttribute("end_time",end_time);
            request.setAttribute("min_money",min_money);
            request.setAttribute("discount_money",discount_money);
            request.setAttribute("discount_rate",discount_rate);
            request.setAttribute("ctime",ctime);
            request.setAttribute("relation_type",relation_type);
            request.setAttribute("code",code);
            request.setAttribute("use_num",use_num);
            request.setAttribute("codetime",codetime);
            return mapping.findForward("coupon");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
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
	public ActionForward bookList(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try {
            //导出类型 ""翻页；"search"查询条件改变，当前页跳转第一页；"all"导出查询所有内容；"current'导出当前页
            String searchType = ParamUtils.getParameter(request, "searchType");
            //快捷查询类型 ""非快捷查询；"all"：全部订单；"not_paid"：未支付；"not_deliver'：待发货，not_received：待收货，not_comment：待评论success：交易成功（包括待评论下的取消退款，拒绝退款，正常；完成状态下的取消退款，拒绝退款，正常）
            String quickSearchType = ParamUtils.getParameter(request, "quickSearchType");
            int currentPageInt = ParamUtils.getIntParameter(request,"currentPage", 1);
            String strItemsInPage = ParamUtils.getParameter(request,"totalItem", false);
            int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
            if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem",15);
            }
            String action = ParamUtils.getParameter(request, "pageAction", true);
            if ("".equals(searchType)||"search".equals(searchType))action = PageAction.FIRST.toString();
            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
            String orderCode = ParamUtils.getParameter(request,"orderCode");
            String telPhone = ParamUtils.getParameter(request, "telPhone", false);
            String starttime=ParamUtils.getParameter(request,"starttime");//开始时间
            String endtime=ParamUtils.getParameter(request,"endtime");//结束时间
            String shouName = ParamUtils.getParameter(request, "shouName");//收货人
            String orderStatusStr = ParamUtils.getParameter(request, "orderStatusStr");//订单状态
            String statusStr = ParamUtils.getParameter(request, "statusStr");//退货状态
            ActionForward actionforward = null;
            Collection queryConds = new ArrayList();
            String whereStr = "";
            if(null != quickSearchType && !"".equals(quickSearchType) ){
				if(null!=starttime&&!"".equals(starttime)&&null!=endtime&&!"".equals(endtime)){
					queryConds.add(new QueryCond("po.createtime", "String", ">=", starttime));
					queryConds.add(new QueryCond("po.createtime", "String", "<=", endtime+" 23:59:59"));
				}
                switch (quickSearchType){
                    case "all":
						whereStr=QueryHelper.toAndQuery(queryConds);
                        break;
                    case "not_paid"://待付款
                        whereStr=QueryHelper.toAndQuery(queryConds)+" and po.order_state = 'not_paid' ";
                        break;
                    case "not_deliver"://待发货
						whereStr=QueryHelper.toAndQuery(queryConds)+" and po.order_state = 'not_deliver' ";
                        break;
                    case "not_received"://待收货
						whereStr=QueryHelper.toAndQuery(queryConds)+" and po.order_state = 'not_received' ";
                        break;
                    case "not_comment"://待评论
						whereStr=QueryHelper.toAndQuery(queryConds)+" and po.order_state = 'not_comment' ";
                        break;
                    case "success"://交易成功（待评论下的正常、取消退款、拒绝退款，已完成状态下的正常、取消退款、拒绝退款）
						whereStr=QueryHelper.toAndQuery(queryConds)+" and (po.order_state = 'not_comment' and pod.status = 'really') or (po.order_state = 'not_comment' and pod.status = 'refunded_refuse') or (po.order_state = 'not_comment' and pod.status = 'refunded_del') or(po.order_state = 'completed' and pod.status = 'really') or (po.order_state = 'completed' and pod.status = 'refunded_refuse') or (po.order_state = 'completed' and pod.status = 'refunded_del') ";
                        break;
                    case "refund"://退货中（退款审核中、审核通过）
						whereStr=QueryHelper.toAndQuery(queryConds)+" and pod.status = 'refund_audit' or  pod.status = 'refund' or pod.status = 'refunded_received'";
                        break;
                    default:
                        break;
                }
            }else{
				if(null!=orderStatusStr && !"".equals(orderStatusStr) && !"all".equals(orderStatusStr)){
					queryConds.add(new QueryCond("po.order_state", "String", "=", orderStatusStr));
				}
				if(null!=statusStr && !"".equals(statusStr) && !"all".equals(orderStatusStr)){
					queryConds.add(new QueryCond("pod.status", "String", "=", statusStr));
				}
				if(null!=starttime&&!"".equals(starttime)&&null!=endtime&&!"".equals(endtime)){
					queryConds.add(new QueryCond("po.createtime", "String", ">=", starttime));
					queryConds.add(new QueryCond("po.createtime", "String", "<=", endtime+" 23:59:59"));
				}
				queryConds.add(new QueryCond("po.consignee", "String", "=", shouName));
				queryConds.add(new QueryCond("po.telephone","String","=",telPhone));
				queryConds.add(new QueryCond("po.order_code", "String", "=", orderCode));
                whereStr = QueryHelper.toAndQuery(queryConds);
            }
            ListContainer lc = mgr.bookList(whereStr, currentPageInt,
                    itemsInPage, action, jumpPage,searchType);
            List ll = lc.getList();
            List<Map> la = ListDataUtil(ll);
            if("current".equals(searchType)||"all".equals(searchType)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String timestr = sdf.format(new Date());
                String fileName=new String(("图书订单报表导出-"+timestr).getBytes("gb2312"), "iso8859-1")+ ".xls";
                    ExcelUtil eu = new ExcelUtil();
                    String[] titles = { "订单号", "订单状态","退货状态", "商品名称", "数量", "总订单金额","总订单实付金额","实付金额","昵称","收货人","联系电话","省-市-区、县","地址","创建时间"};
                    String[] column = {"order_code","order_state","status","p_name","pcount","price","pay_price","money","username","consignee","telephone","pname","address","createtime"};
                    eu.ExportExcelConmon(titles,column,la,fileName,response);
            }else{
                request.setAttribute("mList", la);
                //获取订单状态列表
                String orderStatus="";
                if(!"".equals(orderStatusStr)&&null!=orderStatusStr){
                    orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value",orderStatusStr);
                }else{
                    orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value");
                }
                //获取订单状态列表
                String status="";
                if(!"".equals(statusStr)&&null!=statusStr){
                    status=Tool.getList("select value,name from sys_config where  type='ORDER_REFUND_STATUS' order by sort asc", "name", "value",statusStr);
                }else{
                    status=Tool.getList("select value,name from sys_config where  type='ORDER_REFUND_STATUS' order by sort asc", "name", "value");
                }


                request.setAttribute("shouName", shouName);
                request.setAttribute("orderStatus", orderStatus);
                request.setAttribute("status", status);
                request.setAttribute("starttime", starttime);
                request.setAttribute("endtime", endtime);
                request.setAttribute("telPhone",telPhone);
                request.setAttribute("orderCode",orderCode);
				request.setAttribute("quickSearchType",quickSearchType);
                request.setAttribute("lc", lc);
                actionforward = mapping.findForward("bookList");
            }
            return actionforward;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
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
    public ActionForward netList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try {
            String searchType = ParamUtils.getParameter(request, "searchType");//导出类型 ""翻页；"search"查询条件改变，当前页跳转第一页；"all"导出查询所有内容；"current'导出当前页
            int currentPageInt = ParamUtils.getIntParameter(request,"currentPage", 1);
            String strItemsInPage = ParamUtils.getParameter(request,"totalItem", false);
            int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
            if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem",15);
            }
            String action = ParamUtils.getParameter(request, "pageAction", true);
            if ("".equals(action)||"search".equals(searchType))action = PageAction.FIRST.toString();
            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
            String orderCode = ParamUtils.getParameter(request,"orderCode");
            String telPhone = ParamUtils.getParameter(request, "telPhone", false);
            String starttime=ParamUtils.getParameter(request,"starttime");//开始时间
            String endtime=ParamUtils.getParameter(request,"endtime");//结束时间
            String shouName = ParamUtils.getParameter(request, "shouName");//收货人
            String orderStatusStr = ParamUtils.getParameter(request, "orderStatusStr");//订单状态
            String statusStr = ParamUtils.getParameter(request, "statusStr");//退货状态
            ActionForward actionforward = null;
            Collection queryConds = new ArrayList();
            if(null!=orderStatusStr && !"".equals(orderStatusStr) && !"all".equals(orderStatusStr)){
                queryConds.add(new QueryCond("po.order_state", "String", "=", orderStatusStr));
            }
            if(null!=statusStr && !"".equals(statusStr) && !"all".equals(orderStatusStr)){
                queryConds.add(new QueryCond("pod.status", "String", "=", statusStr));
            }
            if(null!=starttime&&!"".equals(starttime)&&null!=endtime&&!"".equals(endtime)){
                queryConds.add(new QueryCond("po.createtime", "String", ">=", starttime));
                queryConds.add(new QueryCond("po.createtime", "String", "<=", endtime+" 23:59:59"));
            }
            queryConds.add(new QueryCond("po.consignee", "String", "=", shouName));
            queryConds.add(new QueryCond("po.telephone","String","=",telPhone));
            queryConds.add(new QueryCond("po.order_code", "String", "=", orderCode));
            ListContainer lc = mgr.networkList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage,searchType);
            List ll = lc.getList();
            List<Map> la = ListDataUtil(ll);
            if("current".equals(searchType)||"all".equals(searchType)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String timestr = sdf.format(new Date());
                String fileName=new String(("网课订单报表导出-"+timestr).getBytes("gb2312"), "iso8859-1")+ ".xls";
                ExcelUtil eu = new ExcelUtil();
                String[] titles = { "订单号", "订单状态","退货状态", "网课名称", "数量", "总订单金额","总订单实付金额","实付金额","昵称","收货人","联系电话","省-市-区、县","地址","创建时间"};
                String[] column = {"order_code","order_state","status","p_name","pcount","price","pay_price","money","username","consignee","telephone","pname","address","createtime"};
                eu.ExportExcelConmon(titles,column,la,fileName,response);
            }else{
                request.setAttribute("mList", la);
                //获取订单状态列表
                String orderStatus="";
                if(!"".equals(orderStatusStr)&&null!=orderStatusStr){
                    orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value",orderStatusStr);
                }else{
                    orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value");
                }
                request.setAttribute("shouName", shouName);
                request.setAttribute("orderStatus", orderStatus);
                request.setAttribute("starttime", starttime);
                request.setAttribute("endtime", endtime);
                request.setAttribute("telPhone",telPhone);
                request.setAttribute("orderCode",orderCode);
                request.setAttribute("lc", lc);
                actionforward = mapping.findForward("netList");
            }
            return actionforward;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }
    public List<Map> ListDataUtil(List initialList){
        List<Map> la = new ArrayList<Map>();
        for (int i = 0; i < initialList.size(); i++)
        {
            Map pp = new HashMap();
            Object[] order = (Object[]) initialList.get(i);
            pp.put("pname",order[0]+"-"+order[1]+"-"+order[2]);
            pp.put("p_name",order[3]);
            pp.put("id",order[4]);
            pp.put("price",order[5]);
            pp.put("zipcode",order[6]);
            pp.put("consignee",order[7]);
			if(("").equals(order[8])|| ("null").equals(order[8])||null==order[8]){
				pp.put("telephone",order[23]);
			}else{
				pp.put("telephone",order[8]);
			}
            pp.put("address",order[9]);
            pp.put("createtime",order[10]);
            if("not_deliver".equals(order[11])){
                pp.put("order_state","待发货");
            }else if("not_received".equals(order[11])){
                pp.put("order_state","待收货");
            }else if("not_comment".equals(order[11])){
                pp.put("order_state","待评论");
            }else if("been_canceled".equals(order[11])){
                pp.put("order_state","已取消");
            }else if("completed".equals(order[11])){
                pp.put("order_state","已完成");
            }else if("not_paid".equals(order[11])){
                pp.put("order_state","未支付");
            }
            pp.put("remark",order[12]);
            pp.put("order_code",order[13]);
            pp.put("postage",order[14]);
            pp.put("pay_price",order[15]);
            pp.put("from_type",order[16]);
            pp.put("details_id",order[17]);
            pp.put("pcount",order[18]);
            if("really".equals(order[19])){
                pp.put("status","正常");
            }else if("refund_audit".equals(order[19])){
                pp.put("status","退款审核中");
            }else if("refund".equals(order[19])){
                pp.put("status","审核通过");
            }else if("refunded_completed".equals(order[19])){
                pp.put("status","已退款");
            }else if("refunded_refuse".equals(order[19])){
                pp.put("status","拒绝退款");
            }else if("refunded_del".equals(order[19])){
                pp.put("status","用户取消退款");
            }else if("refunded_received".equals(order[19])){
                pp.put("status","商家待收货");
            }
            try{
                pp.put("money",Double.valueOf(order[20].toString())* Double.valueOf(order[18].toString()));
            }catch (Exception e){
                pp.put("money","数据错误");
            }
            pp.put("username",order[21]);
			String couponCodeId = couponCodeId = Tool.getValue("select coupon_code_id from tb_coupon_record where order_id = '"+order[4]+"'");
			pp.put("logisticscode", StrUtils.null2Str(order[22]));
			pp.put("couponCodeId",couponCodeId);
			String eCode = Tool.getValue("select log.e_code from product_order_logistics log where log.logisticsCode='"+order[22]+"'");
			pp.put("eCode",eCode);
            la.add(pp);
        }
        return la;
    }
    
	public ActionForward preExpressImport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("express_import");
	}
	
	public ActionForward expressImport(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取excel 文件
		ProductOrderActionForm fm = (ProductOrderActionForm) actionForm;
		FormFile formfile = fm.getFile();
		InputStream inputstream = formfile.getInputStream();
		fm.clear();// 清空
		ArrayList list = new ArrayList();
		int input = 0; // 导入记数
		
		boolean result = true;
		long succNum = 0;
		try {
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
			HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
			HSSFRow hssfrow = null;
			String orderCode = null;	// 订单code
			String expressName = null;	// 物流商
			String logistics = null;	// 物流单号
			for (int i=1; i<hssfsheet.getPhysicalNumberOfRows(); i++) {
				hssfrow = hssfsheet.getRow(i);
				if(hssfrow == null) {
					continue;
				}
				orderCode = String.valueOf(hssfrow.getCell(0).getStringCellValue()).trim();
				expressName = String.valueOf(hssfrow.getCell(1).getStringCellValue()).trim();
				logistics = String.valueOf(hssfrow.getCell(2).getStringCellValue()).trim();
				if(StringUtils.isEmpty(logistics)) {
					continue;
				}
				try {
					if(mgr.orderExpress(orderCode, expressName, logistics)) {
						succNum++;
					}
				} catch (Exception e) {
					log.error("物流单号导入出错：订单号" + orderCode, e);
				}
			}
			
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			if(inputstream != null) {
				try {
					inputstream.close();
				} catch (Exception e) {}
			}
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {}
		}

		m.put("result", result);
		m.put("succNum", succNum);
		JsonUtils.outputJsonResponse(response, m);
		return null;
	}
}
