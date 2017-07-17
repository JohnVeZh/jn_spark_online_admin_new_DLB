package com.business.Statistics;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.business.BookActivationCode.BookActivationCode;
import com.business.CommunityPost.CommunityPost;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingTypeActionForm;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyric;
import com.business.NetworkVideo.NetworkVideo;
import com.business.ProductOrder.ProductOrder;
import com.business.ProductOrderDetails.ProductOrderDetails;
import com.business.UserFeedback.UserFeedback;
import com.business.Users.Users;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.AddZero;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class StatisticsUserAction extends BaseAction {
	StatisticsUserMgr mgr = new StatisticsUserMgr();

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 根据不同商户查询 不同时间段查询
			SessionContainer session = (SessionContainer) request.getSession()
					.getAttribute("SessionContainer");
			if (session == null)
				session = new SessionContainer();

			int users = Tool.getIntValue("SELECT COUNT(id) FROM users");
			// 今天的用户访问量
			int userNum = Tool
					.getIntValue("SELECT COUNT(id) FROM users WHERE DATE(login_time) = CURDATE()");
			request.setAttribute("userNum", userNum);
			int num = userNum * 100 / users;

			request.setAttribute("users", num + "%");

			List<UserFeedback> user = (List<UserFeedback>) mgr.getListByHql(
					"from UserFeedback where 1=1 order by createtime desc", 0,
					10);
			List li = new ArrayList();
			for (UserFeedback us : user) {
				Map map = new HashMap();
				map.put("Id", us.getId());
				map.put("content", us.getCreatetime() + ":" + us.getContent());
				li.add(map);
			}
			request.setAttribute("lm", li);

			String id = "";
			String pStr = "";
			List<Map> list = Tool
					.query("select * from product_order where order_state='not_paid' OR order_state='complete_order' AND user_del=0  AND admin_del=0 and 1=1 "
							+ pStr);
			for (int i = 0; i < list.size(); i++) {
				Map map = list.get(i);
				if (i == list.size() - 1) {
					id = id + "'" + map.get("ID").toString() + "'";
				} else {
					id = id + "'" + map.get("ID").toString() + "',";
				}
			}
			String str = "";
			if (!"".equals(id)) {
				str = " and ID in (" + id + ")";
			}

			request.setAttribute("list", mgr.list(id));

			List<ProductOrder> po = (List<ProductOrder>) mgr
					.getListByHql(
							"from ProductOrder where user_del=0  AND admin_del=0 order by createtime desc ",
							0, 11);
			List poList = new ArrayList();
			for (ProductOrder pp : po) {
				Map map = new HashMap();
				map.put("orderId", pp.getId());
				map.put("orderCode", pp.getOrderCode());
				map.put("username",
						Tool.getValue("select username from users where id='"
								+ pp.getUserId() + "'"));
				map.put("consignee", pp.getConsignee());
				map.put("telephone", pp.getTelephone());
				String proId = Tool
						.getValue("select pname from province where provinceID = '"
								+ pp.getProvinceId() + "'");
				String cityId = Tool
						.getValue("select city from city where cityID = '"
								+ pp.getCityId() + "'");
				String area = Tool
						.getValue("select area from area where areaID = '"
								+ pp.getAreaId() + "'");
				map.put("proName", proId + "-" + cityId + "-" + area);
				if ("paid".equals(pp.getOrderState())) {
					map.put("OrderState", "已支付");
				} else if ("delivered".equals(pp.getOrderState())) {
					map.put("OrderState", "已发货");
				} else if ("complete_order".equals(pp.getOrderState())) {
					map.put("OrderState", "已完成");
				} else if ("cancelled".equals(pp.getOrderState())) {
					map.put("OrderState", "已取消");
				} else if ("not_paid".equals(pp.getOrderState())) {
					map.put("OrderState", "未支付");
				}
				map.put("price", pp.getPrice());
				map.put("createtime", pp.getCreatetime());
				poList.add(map);
			}
			request.setAttribute("poList", poList);

			// 返回list页
			return mapping.findForward("list");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取订单统计出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	public ActionForward userlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 根据不同商户查询 不同时间段查询
			SessionContainer session = (SessionContainer) request.getSession()
					.getAttribute("SessionContainer");
			if (session == null)
				session = new SessionContainer();
			String start = ParamUtils.getParameter(request, "start", false);
			String end = ParamUtils.getParameter(request, "end", false);
			String beginTimes = request.getParameter("beginTimes");
			String endTimes = request.getParameter("endTimes");
			// String t=request.getParameter("t");
			String pStr = "";
			String month = request.getParameter("month");
			/*
			 * String month = ""; if(start!=null&&end!=null){ //两者都不为空 month =
			 * " and createtime between '"+start+"'  and  '"+end+"'  "; }
			 */
			/*
			 * if(!"2".equals(t)){
			 * pStr=" and partner_id='"+session.getPartnerUserId()+"'"; }
			 * request.setAttribute("t", t);
			 */
			// String id="";
			// List<Map>
			// list=Tool.query("select id from users where user_type='0' and 1=1 "+pStr);
			// for(int i=0;i<list.size();i++){
			// Map map=list.get(i);
			// if(i==list.size()-1){
			// id=id+"'"+map.get("ID").toString()+"'";
			// }else{
			// id=id+"'"+map.get("ID").toString()+"',";
			// }
			// }
			// String str="";
			// if(!"".equals(id)){
			// str=" and ID in ("+id+")";
			// }
			String sql = "";
			if (!"".equals(beginTimes) && null != beginTimes
					&& !"".equals(endTimes) && null != endTimes) {
				sql = "and createtime>='" + beginTimes
						+ " 00:00:00' and createtime<='" + endTimes
						+ " 23:59:59'";
			}

			request.setAttribute("list", mgr.getuserEr(beginTimes, endTimes));
			request.setAttribute(
					"userNum",
					Tool.getIntValue("select count(id) from users where user_type='0'"));
			List<Users> user = (List<Users>) mgr.getListByHql(
					"from Users where DATE(createtime) = CURDATE() " + sql
							+ " order by createtime asc", 0, 0);
			List li = new ArrayList();
			for (Users us : user) {
				Map map = new HashMap();
				map.put("userName", us.getUsername());
				map.put("mobile", us.getMobile());
				map.put("createtime", us.getCreateTime());
				li.add(map);
			}
			request.setAttribute("li", li);

			request.setAttribute("month", month);
			request.setAttribute("beginTime", beginTimes);
			request.setAttribute("endTime", endTimes);
			// 返回list页
			return mapping.findForward("userlist");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取订单统计出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	// 激活量统计
	public ActionForward userbook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 根据不同商户查询 不同时间段查询
			SessionContainer session = (SessionContainer) request.getSession()
					.getAttribute("SessionContainer");
			if (session == null)
				session = new SessionContainer();
			String month = request.getParameter("month");
			String beginTimes = request.getParameter("beginTimes");
			String endTimes = request.getParameter("endTimes");
			// String t=request.getParameter("t");
			String pStr = "";
			/*
			 * if(!"2".equals(t)){
			 * pStr=" and partner_id='"+session.getPartnerUserId()+"'"; }
			 * request.setAttribute("t", t);
			 */
			// String id="";
			// List<Map>
			// list=Tool.query("select id from book_activation_code where is_use='1' and is_del='0'  and 1=1 "+pStr);
			// for(int i=0;i<list.size();i++){
			// Map map=list.get(i);
			// if(i==list.size()-1){
			// id=id+"'"+map.get("ID").toString()+"'";
			// }else{
			// id=id+"'"+map.get("ID").toString()+"',";
			// }
			// }
			// String str="";
			// if(!"".equals(id)){
			// str=" and ID in ("+id+")";
			// }

			String sql = "";
			if (!"".equals(beginTimes) && null != beginTimes
					&& !"".equals(endTimes) && null != endTimes) {
				sql = "and use_time>='" + beginTimes
						+ " 00:00:00' and use_time<='" + endTimes
						+ " 23:59:59'";
			}

			request.setAttribute("list", mgr.getuserbook(beginTimes, endTimes));
			List<BookActivationCode> bac = (List<BookActivationCode>) mgr
					.getListByHql(
							"from BookActivationCode where   DATE(useTime) = CURDATE()  and is_use='1' and is_del='0' "
									+ sql + " ", 0, 0);
			List li = new ArrayList();
			for (BookActivationCode ba : bac) {
				Map map = new HashMap();
				map.put("code", ba.getCode());
				map.put("useTime", ba.getUseTime());
				map.put("num", ba.getUseNum());
				li.add(map);
			}

			request.setAttribute("li", li);

			request.setAttribute(
					"userNum",
					Tool.getIntValue("select count(id) from book_activation_code where is_use='1' and is_del='0' "));
			request.setAttribute("month", month);
			request.setAttribute("beginTime", beginTimes);
			request.setAttribute("endTime", endTimes);
			// 返回list页
			return mapping.findForward("userbook");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取订单统计出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	// 接口访问次数统计
	/**
	 * 接口分析
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	// @SuppressWarnings("unchecked")
	public ActionForward interfaceAnalyse(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 根据不同商户查询 不同时间段查询
			SessionContainer session = (SessionContainer) request.getSession()
					.getAttribute("SessionContainer");
			if (session == null)
				session = new SessionContainer();
			request.setAttribute("list1", mgr.getInterfaceAnalyselist());
			// 返回list页
			return mapping.findForward("interface");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取订单统计出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}


}
