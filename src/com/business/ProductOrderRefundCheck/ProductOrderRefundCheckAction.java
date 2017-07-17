package com.business.ProductOrderRefundCheck;


import com.business.NetworkVideo.NetworkVideo;
import com.business.Product.Product;
import com.business.ProductOrder.ProductOrder;
import com.business.ProductOrderDetails.ProductOrderDetails;
import com.business.ProductOrderRefund.ProductOrderRefund;
import com.business.ProductOrderRefund.ProductOrderRefundActionForm;
import com.business.ProductOrderRefund.ProductOrderRefundMgr;
import com.business.Users.Users;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class ProductOrderRefundCheckAction extends BaseAction{
	 ProductOrderRefundMgr mgr = new ProductOrderRefundMgr();
//	 ProductOrderRefundCheckMgr mgr = new ProductOrderRefundCheckMgr();

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
		if(null==sessionContainer) sessionContainer=new SessionContainer();
		try {

			int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
			}
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action)) action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			 
			// 接收传值
			String orderCode=ParamUtils.getParameter(request,"orderCode");//购买订单编号（product_order）
            String userName = ParamUtils.getParameter(request, "userName"); // 用户名
            String mobile = ParamUtils.getParameter(request, "mobile"); // 手机号
            String starttime=ParamUtils.getParameter(request,"starttimeStr");//购买-开始时间
			String endtime=ParamUtils.getParameter(request,"endtimeStr");//购买-结束时间
            String StatrSel = ParamUtils.getParameter(request, "StatrSel", false); // 订单状态

            String init_sql = "select por.id, por.product_order_details_id, por.order_code,por.refund_reason_id, por.fee, por.type, por.check_status, por.product_status, por.product_type, por.timely_refund, por.book_price, por.play_count, por.createtime from product_order_refund as por";
            String initCount_sql = "select count(1) from product_order_refund as por";
            StringBuffer sql = new StringBuffer(init_sql);
            StringBuffer sqlCount = new StringBuffer(initCount_sql);
            if ((null != orderCode && !"".equals(orderCode)) || (null != StatrSel && !"".equals(StatrSel)) || (null != starttime && !"".equals(starttime)) || (null != endtime && !"".equals(endtime)) || (null != userName && !"".equals(userName)) || (null != mobile && !"".equals(mobile))) {
                if (((null != orderCode && !"".equals(orderCode)) || (null != StatrSel && !"".equals(StatrSel)) || (null != starttime && !"".equals(starttime)) || (null != endtime && !"".equals(endtime))) && ((null == userName || "".equals(userName)) && ((null == mobile || "".equals(mobile))))) {
                    sql.append(" ,product_order_details as pod, product_order as po where 1=1 and por.product_type=1");
                    sqlCount.append(" ,product_order_details as pod, product_order as po where 1=1 and por.product_type=1");
                } else if ((null != userName && !"".equals(userName)) || (null != mobile && !"".equals(mobile))) {
                    sql.append(" ,product_order_details as pod, product_order as po, users as u where 1=1");
                    sqlCount.append(" ,product_order_details as pod, product_order as po, users as u where 1=1");
                }
                sql.append(" and por.product_order_details_id = pod.id");
                sqlCount.append(" and por.product_order_details_id = pod.id");
                sql.append(" and pod.product_order_id = po.id");
                sqlCount.append(" and pod.product_order_id = po.id");
                if ((null != orderCode && !"".equals(orderCode)) || (null != StatrSel && !"".equals(StatrSel)) || (null != starttime && !"".equals(starttime)) || (null != endtime && !"".equals(endtime))) {
                    if ((null != orderCode && !"".equals(orderCode))){
                        	 sql.append(" and po.order_code like '%" +orderCode+"%'");
                        sqlCount.append(" and po.order_code like '%" +orderCode+"%'");
                    }
                    if ((null != StatrSel && !"".equals(StatrSel))){
                    		 sql.append(" and po.order_state like '%" +StatrSel+"%'");
                        sqlCount.append(" and po.order_state like '%" +StatrSel+"%'");
                    }
                    if ((null != starttime && !"".equals(starttime))){
                        	 sql.append(" and po.createtime >= '" +starttime+" 00:00:01'");
                        sqlCount.append(" and po.createtime >= '" +starttime+" 00:00:01'");
                    }
                    if ((null != endtime && !"".equals(endtime))){
                        	 sql.append(" and po.createtime <= '" +endtime+" 23:59:59'");
                        sqlCount.append(" and po.createtime <= '" +endtime+" 23:59:59'");
                    }
                }
//                System.out.println("sql: " + sql);
                if ((null != userName && !"".equals(userName)) || (null != mobile && !"".equals(mobile))) {
                    sql.append(" and po.user_id = u.id");
                    sqlCount.append(" and po.user_id = u.id");
                    if ((null != userName && !"".equals(userName)))
                        sql.append(" and u.username like '%" +userName+"%'");
                        sqlCount.append(" and u.username like '%" +userName+"%'");
                    if ((null != mobile && !"".equals(mobile)))
                        sql.append(" and u.mobile like '%" +mobile+"%'");
                        sqlCount.append(" and u.mobile like '%" +mobile+"%'");
                }
                sql.append(" and por.product_type in (0,1,2)");
                sqlCount.append(" and por.product_type in (0,1,2)"); 
            }else{
            	sql.append(",product_order_details AS pod, product_order AS po WHERE  po.id=pod.product_order_id and pod.id=por.product_order_details_id  and por.product_type IN (0, 1, 2)");
                sqlCount.append(",product_order_details AS pod, product_order AS po WHERE  po.id=pod.product_order_id and pod.id=por.product_order_details_id  and por.product_type IN (0, 1, 2)");
            }
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            ses.beginTransaction();
            Query qCount = ses.createSQLQuery(sqlCount.toString());

            // 新建并设置列表容器
            ListContainer lc = new ListContainer();
            lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
            lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
            lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
            lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
            List L = qCount.list();
            int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString() : "0");
            lc.setTotalItem(totalItems); // 设置记录总数
            ses = HibernateSessionFactory.openSession();
            Query query = ses.createSQLQuery(sql.toString());
            query.setMaxResults(lc.getItemsInPage());
            query.setFirstResult(lc.calculateNextPageIndex());

            currentPageInt = lc.getCurrentPage();
            sql.append(" order by por.createtime  desc ");
            sql.append(" limit " + (currentPageInt - 1) * itemsInPage + "," +  itemsInPage);
            List<Map> porList = mgr.SQLQuery(sql.toString());

            //获取订单状态列表
            String orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value");
            if(!"".equals(StatrSel)&&null!=StatrSel){
                orderStatus=Tool.getList("select value,name from sys_config where  type='ORDER_STATUS' order by sort asc", "name", "value",StatrSel);
            }
			List<Map> lm = new ArrayList<>();
			for (Map por : porList) {
				Map m = new HashMap();
                m.put("id", por.get("id"));
                m.put("productOrderDetailsId", por.get("product_order_details_id"));
                m.put("productStatus", por.get("product_status"));
                m.put("refundReasonId", por.get("refund_reason_id"));
                m.put("fee", por.get("fee"));
                m.put("type", por.get("type"));
                m.put("checkStatus", por.get("check_status"));
                if("check_ing".equals(por.get("check_status"))){
                    m.put("checkStatusStr","退款审核中");
                }else if("check_passed".equals(por.get("check_status"))){
                    m.put("checkStatusStr","审核通过");
                }else if("check_refused".equals(por.get("check_status"))){
                    m.put("checkStatusStr","拒绝退款");
                }else if("pay_finished".equals(por.get("check_status"))){
                    m.put("checkStatusStr","已完成打款");
                }
				ProductOrderDetails pod = (ProductOrderDetails)mgr.getObjectByHql("from ProductOrderDetails where id='"+por.get("product_order_details_id")+"'");
                if (null != pod) {
                    // 订单详情product_order_details
                    if(pod.getProductType()==0){ // 图书
                        Product p = (Product)mgr.getObjectByHql("from Product where id='"+pod.getProductId()+"'");
                        m.put("pName", p.getpName());
                        m.put("pId", p.getId());
                    }else{ // 网课
                        NetworkVideo nv = (NetworkVideo)mgr.getObjectByHql("from NetworkVideo where id='"+pod.getProductId()+"'");
                        if (null != nv){

                            m.put("pName", nv.getNetworkName());
                            m.put("pId", nv.getId());
                        }else{
                            m.put("pName", "");
                            m.put("pId", "");
                        }
                    }
                    // 订单product_order
                    ProductOrder po = (ProductOrder) mgr.getObjectByHql("from ProductOrder where id='" + pod.getProductOrderId() + "'");
                    if(po!=null){
	                    m.put("orderCode", po.getOrderCode());
	                    m.put("userName",Tool.getValue("select username from users where id='"+po.getUserId()+"'"));
	                    m.put("consignee",po.getConsignee());
	                    m.put("mobile",Tool.getValue("select mobile from users where id='"+po.getUserId()+"'"));
	                    m.put("userId",po.getUserId());
	                    String proId = Tool.getValue("select pname from province where provinceID = '"+po.getProvinceId()+"'");
	                    String cityId = Tool.getValue("select city from city where cityID = '"+po.getCityId()+"'");
	                    String area = Tool.getValue("select area from area where areaID = '"+po.getAreaId()+"'");
	                    m.put("proName",proId+"-"+cityId+"-"+area);
	                    m.put("cod",po.getOrderState());
	                    if("not_deliver".equals(po.getOrderState())){
	                        m.put("OrderState","待发货");
	                    }else if("not_received".equals(po.getOrderState())){
	                        m.put("OrderState","待收货");
	                    }else if("not_comment".equals(po.getOrderState())){
	                        m.put("OrderState","待评论");
	                    }else if("been_canceled".equals(po.getOrderState())){
	                        m.put("OrderState","已取消");
	                    }else if("completed".equals(po.getOrderState())){
	                        m.put("OrderState","已完成");
	                    }else if("not_paid".equals(po.getOrderState())){
	                        m.put("OrderState","未支付");
	                    }
	                    m.put("price",po.getPrice());
	                    m.put("payPrice",po.getPayPrice());
	                    m.put("createtime",po.getCreatetime());
                    }
                }
				lm.add(m);
			}
			request.setAttribute("lm", lm);
            request.setAttribute("lc", lc);
            request.setAttribute("orderCode", orderCode);
            request.setAttribute("userName", userName);
            request.setAttribute("mobile", mobile);
            request.setAttribute("orderStatus", orderStatus);
            request.setAttribute("starttime", starttime);
            request.setAttribute("endtime", endtime);

			return mapping.findForward("list");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
		finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {
            }
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
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "id", true);
			ProductOrderRefundActionForm poraForm = mgr.view(ID);
            ProductOrderRefund por = (ProductOrderRefund) mgr.getObjectByHql("from ProductOrderRefund where id='" + ID + "'");
            if (por != null) {
                request.setAttribute("refundOrderCode", por.getOrderCode());
			    ProductOrderDetails pod = (ProductOrderDetails)mgr.getObjectByHql("from ProductOrderDetails where id='"+poraForm.getProductOrderDetailsId()+"'");
                if (pod != null) {
                    request.setAttribute("bookPrice", pod.getBookPrice());
                    if(pod.getProductType()==0){
                        Product p = (Product)mgr.getObjectByHql("from Product where id='"+pod.getProductId()+"'");
                        request.setAttribute("pName", p.getpName());
                    }else{
                        NetworkVideo nv = (NetworkVideo)mgr.getObjectByHql("from NetworkVideo where id='"+pod.getProductId()+"'");
                        request.setAttribute("pName", nv.getNetworkName());
                    }
                    ProductOrder po = (ProductOrder) mgr.getObjectByHql("from ProductOrder where id='" + pod.getProductOrderId() + "'");
                    if (po != null) {
                        request.setAttribute("orderCode", po.getOrderCode());
                        request.setAttribute("price", po.getPrice());
                        request.setAttribute("payPrice", po.getPayPrice());
                        if("not_deliver".equals(po.getOrderState())){
                            request.setAttribute("orderState","待发货");
                        }else if("not_received".equals(po.getOrderState())){
                            request.setAttribute("orderState","待收货");
                        }else if("not_comment".equals(po.getOrderState())){
                            request.setAttribute("orderState","待评论");
                        }else if("been_canceled".equals(po.getOrderState())){
                            request.setAttribute("orderState","已取消");
                        }else if("completed".equals(po.getOrderState())){
                            request.setAttribute("orderState","已完成");
                        }else if("not_paid".equals(po.getOrderState())){
                            request.setAttribute("orderState","未支付");
                        }
                        Users u = (Users) mgr.getObjectByHql("from Users where id='" + po.getUserId() + "'");
                        if (u != null) {
                            request.setAttribute("userName", u.getUsername());
                            request.setAttribute("mobile", u.getMobile());
                        }
                    }
                }
            }
            if (poraForm != null) {
                request.setAttribute("refundOrderCode", poraForm.getOrderCode());
                String value= Tool.getValue("select value from sys_config where id='"+poraForm.getRefundReasonId()+"'");
                request.setAttribute("refundReason",value);
                request.setAttribute("ProductOrderRefundActionForm", poraForm);
            }
			return mapping.findForward("view");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
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

		ProductOrderRefundActionForm vo = (ProductOrderRefundActionForm) form;
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

			ProductOrderRefundActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("ProductOrderRefundActionForm", vo);

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
			ProductOrderRefundActionForm vo = (ProductOrderRefundActionForm) form;			  
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

    /*审核退单-跳转页面*/
    public ActionForward check(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            String ID = ParamUtils.getParameter(request, "id", true);
            String checkStatus = ParamUtils.getParameter(request, "check_status", true);
            request.setAttribute("ID", ID);
            //            String selStr = Tool.getList("select value,name from sys_config where type='ORDER_STATUS'", "name", "value",status);
            String checkStatusStr = Tool.getList("select value,name from sys_config where type='ORDER_CHECK_STATUS'", "name", "value",checkStatus);
            request.setAttribute("checkStatusStr", checkStatusStr);

            ProductOrderRefundActionForm poraForm = mgr.view(ID);
            ProductOrderRefund por = (ProductOrderRefund) mgr.getObjectByHql("from ProductOrderRefund where id='" + ID + "'");
            if (por != null) {
                request.setAttribute("refundOrderCode", por.getOrderCode());
                ProductOrderDetails pod = (ProductOrderDetails)mgr.getObjectByHql("from ProductOrderDetails where id='"+poraForm.getProductOrderDetailsId()+"'");
                if (pod != null) {
                    request.setAttribute("bookPrice", pod.getBookPrice());
                    if(pod.getProductType()==0){
                        Product p = (Product)mgr.getObjectByHql("from Product where id='"+pod.getProductId()+"'");
                        request.setAttribute("pName", p.getpName());
                    }else{
                        NetworkVideo nv = (NetworkVideo)mgr.getObjectByHql("from NetworkVideo where id='"+pod.getProductId()+"'");
                        if(nv!=null){
                        	request.setAttribute("pName", nv.getNetworkName());
                        }
                    }
                    ProductOrder po = (ProductOrder) mgr.getObjectByHql("from ProductOrder where id='" + pod.getProductOrderId() + "'");
                    if (po != null) {
                        request.setAttribute("orderCode", po.getOrderCode());
                        request.setAttribute("price", po.getPrice());
                        request.setAttribute("payPrice", po.getPayPrice());
                        int type = po.getType();

                        request.setAttribute("type",String.valueOf(po.getType()));
                        if("not_deliver".equals(po.getOrderState())){
                            request.setAttribute("orderState","待发货");
                        }else if("not_received".equals(po.getOrderState())){
                            request.setAttribute("orderState","待收货");
                        }else if("not_comment".equals(po.getOrderState())){
                            request.setAttribute("orderState","待评论");
                        }else if("been_canceled".equals(po.getOrderState())){
                            request.setAttribute("orderState","已取消");
                        }else if("completed".equals(po.getOrderState())){
                            request.setAttribute("orderState","已完成");
                        }else if("not_paid".equals(po.getOrderState())){
                            request.setAttribute("orderState","未支付");
                        }
                        Users u = (Users) mgr.getObjectByHql("from Users where id='" + po.getUserId() + "'");
                        if (u != null) {
                            request.setAttribute("userName", u.getUsername());
                            request.setAttribute("mobile", u.getMobile());
                        }
                    }
                }
            }
            if (poraForm != null) {
                request.setAttribute("refundOrderCode", poraForm.getOrderCode());
                String value= Tool.getValue("select value from sys_config where id='"+poraForm.getRefundReasonId()+"'");
                request.setAttribute("refundReason",value);
                request.setAttribute("ProductOrderRefundActionForm", poraForm);
            }

            return mapping.findForward("check");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /*审核退单状态*/
    public ActionForward checkSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            String refundOrderId = ParamUtils.getParameter(request, "refundOrderId", true);
            String checkStatus = ParamUtils.getParameter(request, "checkStatus", true);
            // 修改退单审核状态
            System.out.println("update product_order_refund set check_status='"+checkStatus+"' where id='"+refundOrderId+"'");
            Tool.execute("update product_order_refund set check_status='"+checkStatus+"' where id='"+refundOrderId+"'");
            
            //查询订单详情编号
            String puductOrderRefund_details_IDString= Tool.getValue("select product_order_details_id from product_order_refund where id='"+refundOrderId+"'");
            //修改订单详情退款状态
            //退款审核中
            if(checkStatus.equals("check_ing")){
            	System.out.println("update product_order_details set status='refund_audit' where id='"+puductOrderRefund_details_IDString+"'");
            	Tool.execute("update product_order_details set status='refund_audit' where id='"+puductOrderRefund_details_IDString+"'");
            //已经完成退款
            }else if(checkStatus.equals("pay_finished")){
            	System.out.println("update product_order_details set status='refunded_completed' where id='"+puductOrderRefund_details_IDString+"'");
            	Tool.execute("update product_order_details set status='refunded_completed' where id='"+puductOrderRefund_details_IDString+"'");
            	//商品ID
            	String puduct_netWorkCourse_IDString= Tool.getValue("select product_id from product_order_details where id='"+puductOrderRefund_details_IDString+"'");
            	//订单ID
            	String product_order_IDString= Tool.getValue("select product_order_id from product_order_details where id='"+puductOrderRefund_details_IDString+"'");
            	//USER_ID
            	String user_IDString= Tool.getValue("select user_id from product_order where id='"+product_order_IDString+"'");
            	List query = Tool.query("SELECT * FROM network_course_actlog where user_id='"+user_IDString+"' and nc_id='"+puduct_netWorkCourse_IDString+"'");
            	if(query.size()>0){
            		Tool.execute("update network_course_actlog set act_type = 5 where user_id='"+user_IDString+"' and nc_id='"+puduct_netWorkCourse_IDString+"'");
            	}else{
            		Tool.execute("INSERT INTO network_course_actlog(user_id,nc_id,act_type,create_time)values"
            				+ "('"+user_IDString+"','"+puduct_netWorkCourse_IDString+"','5','"+DateUtils.getCurrDateTimeStr()+"')");
            	}
            }
            map.put("result", true);
        } catch (Exception ex) {
            map.put("result", false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
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

			ProductOrderRefundActionForm vo = new ProductOrderRefundActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("ProductOrderRefundActionForm", vo);
			
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
				 
			Tool.execute("delete from product_order_refund where id = '"+ids[i]+"'");
				 
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

			String porId = ParamUtils.getParameter(request, "porId", true);
				 
			Tool.execute("delete from product_order_refund where id = '"+porId+"'");
				 
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
}
