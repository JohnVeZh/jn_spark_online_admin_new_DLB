package com.business.NetworkCourseCode;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.business.NetworkCourse.NetworkCourse;
import com.business.NetworkCourseTeacher.NetworkCourseTeacher;
import com.business.ProductOrderRefund.ProductOrderRefundActionForm;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.ExcelUtil;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class NetworkCourseCodeAction extends BaseAction{
	
	NetworkCourseCodeMgr mgr=new NetworkCourseCodeMgr();
	/**
	 * 兑换码列表条件查询
	 * @param mapping
	 * @param xss
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
			String codeId = ParamUtils.getParameter(request, "codeId", false);
			String ncNamelike = ParamUtils.getParameter(request, "ncNamelike", false);
			String type = ParamUtils.getParameter(request, "type", false);
			//0：未使用 1：已使用
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("networkCourse.nc_name", "String", "like", ncNamelike));
			queryConds.add(new QueryCond("networkCourseCode.id", "String", "=", codeId));
			
			Collection queryCond = new ArrayList();
			queryCond.add(new QueryCond("NetworkCourse.ncName", "String", "like", ncNamelike));
			queryCond.add(new QueryCond("NetworkCourseCode.id", "String", "=", codeId));
			

			ListContainer lc = mgr.list(queryConds,queryCond, currentPageInt,
					itemsInPage, action, jumpPage,type);
			if(type==null || type.equals("") || type.equals("0")){
				type="0";
			}
			request.setAttribute("ncNamelike", ncNamelike);
			request.setAttribute("codeId", codeId);
			request.setAttribute("type", type);
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
	 * 禁用或启动兑换码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward disable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id", false);
			String isEnable = Tool.getValue("SELECT is_enable FROM network_course_code WHERE id='"+id+"'");
			int isEnables=1;
			if(isEnable.equals("1")){
				isEnables=0;
			}else{
				isEnables=1;
			}
			Tool.execute("update network_course_code set is_enable= "+isEnables+" where id='"+id+"'");
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 去批量生成兑换码页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<NetworkCourse> nvtList = Tool.findListByHql("from NetworkCourse where isDelete='0'");
			request.setAttribute("networkCourse", nvtList);
			return mapping.findForward("add");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 批量生成兑换码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			NetworkCourseCode vo = (NetworkCourseCode) form;
			List<NetworkCourse> nvtList = Tool.findListByHql("from NetworkCourse where isDelete=0 and id='"+vo.getNvId()+"'");
			String ncName = nvtList.get(0).getNcName();
			BigDecimal big=new BigDecimal (nvtList.get(0).getCurrentPrice());
			vo.setAmount(big);
			vo.setIsEnable(1);
			String sort = ParamUtils.getParameter(request, "sort", false);
			String checkExport = ParamUtils.getParameter(request, "checkExport", false);
			//判断是否导出
			if(checkExport==null){
				vo.setIsExport(0);
			}else{
				vo.setIsExport(1);
			}
			String sql="insert into network_course_code ( id,nv_id,amount,user_id,is_export,is_enable,create_time) values";
			String sqladd="";
			int num=Integer.parseInt(sort);
			List ncc=new ArrayList();
			for (int i = 0; i < num; i++) {
				Map map = new HashMap();
				 UUID uuid = UUID.randomUUID();
			     long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
			     vo.setId(Long.toString(l, Character.MAX_RADIX));
			     String sqls="("+getFormatField(vo.getId())+","+getFormatField(vo.getNvId())+","+getFormatField(vo.getAmount())+","
					+ ""+getFormatField(vo.getUserId())+","
					+ ""+getFormatField(vo.getIsExport())+","+1+","+getFormatField(DateUtils.getCurrDateTimeStr())+"),";
			     map.put("id", vo.getId());
			     ncc.add(map);
			     sqladd=sqladd+sqls;
			}
			sql=sql+sqladd;
			String sqls = sql.substring(0,sql.length()-1);
			mgr.SQLExecute(sqls);
			if(vo.getIsExport()==1){
				exportExcel(ncName,ncc,request,response);
				request.setAttribute("msg", "200");
				return null;
			}
			request.setAttribute("msg", "200");
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	private void exportExcel(String name,List list,HttpServletRequest request, HttpServletResponse response){
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try {
            // 设置查询条件

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestr = sdf.format(new Date());
            String fileName=new String((name+"-"+timestr).getBytes("gb2312"), "iso8859-1")+ ".xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setCharacterEncoding("utf-8");
            ServletOutputStream outputStream = response.getOutputStream();
            ExcelUtil eu = new ExcelUtil();
            String[] titles = { "兑换码"};
            String[] column = {"id"};
            eu.ExportExcelConmon(titles,column,list,fileName,response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);

        }
	}
	/**
	 * sql拼接去''
	 * @param obj
	 * @return
	 */
	public static String getFormatField(Object obj) {
		String resultStr = "NULL";
		if (obj instanceof Integer) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr =  obj.toString() ;
			}
		} else if(obj instanceof String) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Timestamp) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Double) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = obj.toString();
			}
		}
		return resultStr;
	}
}
