package com.business.NetworkCourse;

import java.sql.Timestamp;
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

import com.business.NetworkCourseTeacher.NetworkCourseTeacher;
import com.business.NetworkCourseVideo.NetworkCourseVideo;
import com.business.NetworkVideo.NetworkVideoActionForm;
import com.business.NetworkVideo.NetworkVideoMgr;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class NetworkCourseAction extends BaseAction{
	
	NetworkCourseMgr mgr=new NetworkCourseMgr();
	
	/**
	 * 新网课列表
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
			String ncNamelike = ParamUtils.getParameter(request, "ncNamelike", false);
			String ncType = ParamUtils.getParameter(request, "ncType", false);
			String ncLevel = ParamUtils.getParameter(request, "ncLevel", false);
			String ncLevelType = ParamUtils.getParameter(request, "ncLevelType", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("NetworkCourse.ncName", "String", "like", ncNamelike));
			queryConds.add(new QueryCond("NetworkCourse.ncType", "String", "=", ncType));
			queryConds.add(new QueryCond("NetworkCourse.ncLevel", "String", "=", ncLevel));
			queryConds.add(new QueryCond("NetworkCourse.ncLevelType", "String", "=", ncLevelType));
			

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("ncNamelike", ncNamelike);
			request.setAttribute("ncType", ncType);
			request.setAttribute("ncLevel", ncLevel);
			request.setAttribute("ncLevelType", ncLevelType);
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
	 * 去添加课程
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

			NetworkVideoActionForm vo = new NetworkVideoActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			//查询老师
			List<NetworkCourseTeacher> nvtList = Tool.findListByHql("from NetworkCourseTeacher where isDelete=0");
			request.setAttribute("teachers", nvtList);
			//查询级别
			//查询级别类型
			String ncLevel = Tool.getList("select value,name from sys_config where type='NC_LEVEL'", "name", "value");
			String ncLevelType= Tool.getList("select value,name from sys_config where type='NC_LEVEL_TYPE' ", "name", "value");
			request.setAttribute("ncLevel", ncLevel);
			request.setAttribute("ncLevelType", ncLevelType);
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
	 * 上传课程图片
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateImgPath(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		NetworkCourse vo = (NetworkCourse) form;
		Map map = new HashMap();
		System.err.println(vo.getFile());
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
	 * 添加网课信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			NetworkCourse vo = (NetworkCourse) form;	
			String ncLiveTime = ParamUtils.getParameter(request, "ncLiveTimes", true);
			String ncEndTime = ParamUtils.getParameter(request, "ncEndTimes", true);
			String reserveTime = ParamUtils.getParameter(request, "reserveTimes", true);
			String saleTime = ParamUtils.getParameter(request, "saleTimes", true);
			String saleEndTime = ParamUtils.getParameter(request, "saleEndTimes", true);
			String onShelfTime = ParamUtils.getParameter(request, "onShelfTimes", true);
			String offShelfTime = ParamUtils.getParameter(request, "offShelfTimes", true);
			String limitStartTime = ParamUtils.getParameter(request, "limitStartTimes", true);
			String limitEndTime = ParamUtils.getParameter(request, "limitEndTimes", true);
			if(vo==null){
				WebDialogBox dialog = new WebDialogBox(1, "错误",  "返回", "javascript:window.history.back()", null, null);
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
			vo.setId(UUID.randomUUID().toString().replace("-", ""));
			//拼接老师i
			String tckbox[] = request.getParameterValues("tckbox");
			String tIds = "";
			String tTeacherName="";
			String t_name=null;
			for (int i = 0; i < tckbox.length; i++) {
				tIds = tIds+","+tckbox[i];
			}
			for (int i = 0; i < tckbox.length; i++) {
				t_name = Tool.getValue("select name from network_course_teacher where id='"+tckbox[i]+"'");
				tTeacherName=tTeacherName+" "+t_name;
			}
			vo.setTeacherId(tIds.substring(1));
			vo.setNcTeacher(tTeacherName.substring(1));
			if(vo.getIsFree()==1){
				vo.setOriginalPrice(0.0);
				vo.setCurrentPrice(0.0);
			}
			//修改排列顺序
			mgr.SQLExecute("update network_course set sort=sort+1 where sort>='"+vo.getSort()+"' ");

			String sql="insert into network_course(id, nc_name, nc_brief, nc_introduce,teacher_id,nc_teacher,teacher_introduce,"
					+ "catalog_introduce,catalog_number,nc_logo,nc_img,original_price,current_price,nc_type,"
					+ "nc_live_link,nc_live_time,nc_end_time,nc_preview_link,nc_record_link,reserve_time,"
					+ "sale_time,sale_end_time,on_shelf_time,off_shelf_time,limit_number,reserve_number,"
					+ "sale_number,nc_state,nc_level,nc_level_type,sort,is_free,is_limit_free,limit_start_time,"
					+ "limit_end_time,is_index,is_delete,evaluate_count,is_teacher,is_catalog,is_public,is_gift_book,gift_book_price,create_time,nc_qq_group,nc_live_rome)"
					+ "values"
					+ "("+getFormatField(vo.getId())+","+getFormatField(vo.getNcName())+","+getFormatField(vo.getNcBrief())+","+getFormatField(vo.getNcIntroduce())+","+getFormatField(vo.getTeacherId())+","+getFormatField(vo.getNcTeacher())+","+getFormatField(vo.getTeacherIntroduce())+","
					+ ""+getFormatField(vo.getCatalogIntroduce())+","+getFormatField(vo.getCatalogNumber())+","+getFormatField(vo.getNcLogo())+","+getFormatField(vo.getNcImg())+","+vo.getOriginalPrice()+","+vo.getCurrentPrice()+","+getFormatField(vo.getNcType())+","
					+ ""+getFormatField(vo.getNcLiveLink())+","+getFormatField(ncLiveTime)+","+getFormatField(ncEndTime)+","+getFormatField(vo.getNcPreviewLink())+","+getFormatField(vo.getNcRecordLink())+","+getFormatField(reserveTime)+","
					+ ""+getFormatField(saleTime)+","+getFormatField(saleEndTime)+","+getFormatField(onShelfTime)+","+getFormatField(offShelfTime)+","+getFormatField(vo.getLimitNumber())+","+getFormatField(vo.getReserveNumber())+","
					+ ""+getFormatField(vo.getSaleNumber())+","+getFormatField(vo.getNcState())+","+getFormatField(vo.getNcLevel())+","+getFormatField(vo.getNcLevelType())+","+getFormatField(vo.getSort())+","+getFormatField(vo.getIsFree())+","+getFormatField(vo.getIsLimitFree())+","+getFormatField(limitStartTime)+","
					+ ""+getFormatField(limitEndTime)+","+getFormatField(vo.getIsIndex())+","+getFormatField(vo.getIsDelete())+","+getFormatField(vo.getEvaluateCount())+","+getFormatField(vo.getIsTeacher())+","+getFormatField(vo.getIsCatalog())+","+getFormatField(vo.getIsPublic())+","
					+ ""+getFormatField(vo.getIsGiftBook())+","+getFormatField(vo.getGiftBookPrice())+",'"+DateUtils.getCurrDateTimeStr()+"',"+getFormatField(vo.getNcQqGroup())+","+getFormatField(vo.getNcLiveRome())+")";
				
			mgr.SQLExecute(sql);
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username, "添加课程,课程名称:"+vo.getNcName(), "1", ipaddress);
			
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
	 * 逻辑删除
	 * @param mapping
	 * @param xs
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);
				 
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			
			String name = Tool.getValue("select nc_name from network_course where id='"+ID+"'");
			Tool.AddLog(sessionContainer.getUserId(), username,
					"删除课程,课程名称:"+name, "1", ipaddress);

			Tool.execute("update  network_course set is_delete=1 where id = '"+ID+"'");
				 
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
	 * 去修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);

			NetworkCourse vo = (NetworkCourse) mgr.getObjectByHql("from NetworkCourse where id='"+ID+"' and isDelete=0");
			
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			//查询老师
			List<Map> nvtLm = new ArrayList<Map>();
			List<NetworkCourseTeacher> nvtList = Tool.findListByHql("from NetworkCourseTeacher where isDelete=0");
			for (NetworkCourseTeacher nvt : nvtList) {
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
			String ncLevel = 	Tool.getList("select value,name from sys_config where type='NC_LEVEL'", "name", "value",vo.getNcLevel());
			String ncLevelType= Tool.getList("select value,name from sys_config where type='NC_LEVEL_TYPE' ", "name", "value",vo.getNcLevelType());
			request.setAttribute("ncLevel", ncLevel);
			request.setAttribute("ncLevelType", ncLevelType);
			request.setAttribute("NetworkCourse", vo);

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
	 * 修改信息
	 * 
	 * @param mapping
	 * @param xs
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		NetworkCourse vo = (NetworkCourse) form;
		try {
			String ncLiveTime = ParamUtils.getParameter(request, "ncLiveTimes", true);
			String ncEndTime = ParamUtils.getParameter(request, "ncEndTimes", true);
			String reserveTime = ParamUtils.getParameter(request, "reserveTimes", true);
			String saleTime = ParamUtils.getParameter(request, "saleTimes", true);
			String saleEndTime = ParamUtils.getParameter(request, "saleEndTimes", true);
			String onShelfTime = ParamUtils.getParameter(request, "onShelfTimes", true);
			String offShelfTime = ParamUtils.getParameter(request, "offShelfTimes", true);
			String limitStartTime = ParamUtils.getParameter(request, "limitStartTimes", true);
			String limitEndTime = ParamUtils.getParameter(request, "limitEndTimes", true);
			//拼接老师i
			String tckbox[] = request.getParameterValues("tckbox");
			String tIds = "";
			String tTeacherName="";
			String t_name=null;
			for (int i = 0; i < tckbox.length; i++) {
				tIds = tIds+","+tckbox[i];
			}
			for (int i = 0; i < tckbox.length; i++) {
				t_name = Tool.getValue("select name from network_course_teacher where id='"+tckbox[i]+"'");
				tTeacherName=tTeacherName+" "+t_name;
			}
			vo.setTeacherId(tIds.substring(1));
			vo.setNcTeacher(tTeacherName.substring(1));
			if(vo.getIsFree()==1){
				vo.setOriginalPrice(0.0);
				vo.setCurrentPrice(0.0);
			}
			String sorts = Tool.getValue("select sort from network_course where id='"+vo.getId()+"'");
			int sort=Integer.parseInt(sorts);
			if(vo.getSort()!=sort){
				mgr.SQLExecute("update network_course set sort=sort+1 where sort>='"+vo.getSort()+"' ");
			}
			String sqls="UPDATE network_course SET nc_name="+getFormatField(vo.getNcName())+", "
					+ "nc_brief="+getFormatField(vo.getNcBrief())+", nc_introduce="+getFormatField(vo.getNcIntroduce())+", teacher_id="+getFormatField(vo.getTeacherId())+", nc_teacher="+getFormatField(vo.getNcTeacher())+", teacher_introduce="+getFormatField(vo.getTeacherIntroduce())+", catalog_introduce="+getFormatField(vo.getCatalogIntroduce())+","
					+ "catalog_number="+getFormatField(vo.getCatalogNumber())+",nc_logo="+getFormatField(vo.getNcLogo())+", nc_img="+getFormatField(vo.getNcImg())+", original_price="+vo.getOriginalPrice()+", current_price="+vo.getCurrentPrice()+", "
					+ "nc_type="+getFormatField(vo.getNcType())+", nc_live_link="+getFormatField(vo.getNcLiveLink())+", nc_live_time="+getFormatField(ncLiveTime)+", nc_end_time="+getFormatField(ncEndTime)+", "
					+ "nc_preview_link="+getFormatField(vo.getNcPreviewLink())+", nc_record_link="+getFormatField(vo.getNcRecordLink())+", reserve_time="+getFormatField(reserveTime)+","
					+ "sale_time="+getFormatField(saleTime)+", sale_end_time="+getFormatField(saleEndTime)+", on_shelf_time="+getFormatField(onShelfTime)+", off_shelf_time="+getFormatField(offShelfTime)+","
					+ "limit_number="+getFormatField(vo.getLimitNumber())+", nc_level="+getFormatField(vo.getNcLevel())+", nc_level_type="+getFormatField(vo.getNcLevelType())+", sort="+getFormatField(vo.getSort())+", is_free="+getFormatField(vo.getIsFree())+", is_limit_free="+getFormatField(vo.getIsLimitFree())+","
					+ "limit_start_time="+getFormatField(limitStartTime)+", limit_end_time="+getFormatField(limitEndTime)+",is_teacher="+getFormatField(vo.getIsTeacher())+", "
					+ "is_public="+getFormatField(vo.getIsPublic())+", is_gift_book="+getFormatField(vo.getIsGiftBook())+", gift_book_price="+getFormatField(vo.getGiftBookPrice())+",nc_qq_group="+getFormatField(vo.getNcQqGroup())+",nc_live_rome="+getFormatField(vo.getNcLiveRome())+" WHERE  id='"+vo.getId()+"'";
			mgr.SQLExecute(sqls);
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username, "修改课程,课程名称:"+vo.getNcName(), "1", ipaddress);
			
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
	 * 修改网课状态页面
	 * @param mapping
	 * @param xs
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preCourseStateEdit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);
			String ncState = ParamUtils.getParameter(request, "ncState", true);
			request.setAttribute("ID", ID);
			request.setAttribute("ncState", ncState);

			return mapping.findForward("courseStateEdit");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 修改网课状态
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward CourseStateEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String id = ParamUtils.getParameter(request, "id", true);
			String ncState= ParamUtils.getParameter(request, "ncState", true);
			NetworkCourse vo = (NetworkCourse) mgr.getObjectByHql("from NetworkCourse as NetworkCourse where NetworkCourse.isDelete=0 and NetworkCourse.id='"+id+"'");
			if(vo!=null){
				Timestamp reserveTime = vo.getReserveTime();
				Timestamp saleTime = vo.getSaleTime();
				Timestamp newTime = new Timestamp(System.currentTimeMillis());
				if(reserveTime!=null){
					if(reserveTime.after(newTime)){
						map.put("result", false);
					}else{
						Tool.execute("update network_course set nc_state="+ncState+" where id='"+id+"'");
						map.put("result", true);
					}
				}else{
					if(saleTime!=null){
						if(saleTime.after(newTime)){
							map.put("result", false);
						}else{
							Tool.execute("update network_course set nc_state="+ncState+" where id='"+id+"'");
							map.put("result", true);
						}
					}else{
						Tool.execute("update network_course set nc_state="+ncState+" where id='"+id+"'");
						map.put("result", true);
					}
				}
			}
		} catch (Exception ex) {
			map.put("result", false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
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
			
			queryConds.add(new QueryCond("nc.nc_name", "String", "like", networkName));
			Collection queryCond=new ArrayList();
			
			queryCond.add(new QueryCond("nc.ncName", "String", "like", networkName));

			ListContainer lc=mgr.sclist(queryConds,queryCond, currentPageInt, itemsInPage, action, jumpPage);
			
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
	
    public ActionForward networkCourseSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
		sessionContainer=new SessionContainer();
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
			queryConds.add(new QueryCond("NetworkCourse.ncName", "String", "like", title));
			queryConds.add(new QueryCond("NetworkCourse.isDelete", "number", "=", "0"));
			 
			ListContainer lc = mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
			
			request.setAttribute("lc", lc);
			request.setAttribute("title", title);
			request.setAttribute("srcpage", request.getParameter("srcpage"));
			request.setAttribute("idKey", request.getParameter("idKey"));
			request.setAttribute("valueKey", request.getParameter("valueKey"));
			
			return mapping.findForward("networkCourseSelector");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
}
