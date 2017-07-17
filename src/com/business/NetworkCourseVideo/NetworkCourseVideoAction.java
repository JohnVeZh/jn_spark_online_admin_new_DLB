package com.business.NetworkCourseVideo;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.business.NetworkCourse.NetworkCourse;
import com.business.NetworkCourseTeacher.NetworkCourseTeacher;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;


public class NetworkCourseVideoAction extends BaseAction{
	NetworkCourseVideoMgr mgr=new NetworkCourseVideoMgr();
	
	/**
	 * 得到网课视频列表列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
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
			String nvId = ParamUtils.getParameter(request, "nvId", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("NetworkCourseVideo.nvId", "String", "=", nvId));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			List<NetworkCourseVideo> list=lc.getList();
			for (NetworkCourseVideo networkCourseVideo : list) {
				String name="";
				String t_name="";
				String teacherId = networkCourseVideo.getTeacherId()==null?"":networkCourseVideo.getTeacherId();
				String[] teacher_id = teacherId.split(",");
				for(int i=0;i<teacher_id.length;i++){
					t_name = Tool.getValue("select name from network_course_teacher where id='"+teacher_id[i]+"'");
					name=name+","+t_name;
				}
				networkCourseVideo.setTeacherName(name.substring(1));
				Timestamp startTime = networkCourseVideo.getNcvStartTime();
				if(networkCourseVideo.getNcvType().toString().equals("0")){
					networkCourseVideo.setVideoState("看回放");
				}else{
					if(startTime!=null ){
						Timestamp newTime = new Timestamp(System.currentTimeMillis());
						Timestamp timestamp = mgr.timestamp(startTime,networkCourseVideo.getNcvDuration());
						if(newTime.before(startTime)){
							networkCourseVideo.setVideoState("未开始");
						}else if(newTime.after(startTime) && newTime.before(timestamp)){
							networkCourseVideo.setVideoState("直播中");
						}else if(newTime.after(timestamp)){
							networkCourseVideo.setVideoState("看回放");
						}							
					}
				}
			}
			request.setAttribute("lc", lc);
			request.setAttribute("nvId", nvId);
			 
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
	 * 预增加信息
	 * 
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {

			NetworkCourseVideo vo = new NetworkCourseVideo();			
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String nvId = ParamUtils.getParameter(request, "nvId", false);
			request.setAttribute("nvId", nvId);
			request.setAttribute("NetworkVideoCatalogVideoActionForm", vo);
			List<NetworkCourse> networkCourse = Tool.findListByHql("from NetworkCourse where isDelete=0 and id='"+nvId+"'");
			if(networkCourse.size()>0){
				String teacherId = networkCourse.get(0).getTeacherId();
				String[] teacherIds = teacherId.split(",");
				List<NetworkCourseTeacher> nvtList = new ArrayList<>();
				List<NetworkCourseTeacher> teacher =null;
				for (int i = 0; i < teacherIds.length; i++) {
					teacher = Tool.findListByHql("from NetworkCourseTeacher where isDelete=0 and id='"+teacherIds[i]+"'");
					nvtList.add(teacher.get(0));
				}
				request.setAttribute("teachers", nvtList);
			}
			//查询老师
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			NetworkCourseVideo vo = (NetworkCourseVideo) form;	
			String ncvStartTimes = ParamUtils.getParameter(request, "ncvStartTimes", true);
			vo.setId(UUID.randomUUID().toString().replace("-", ""));
			//拼接老师Id
			String tckbox[] = request.getParameterValues("tckbox");
			String tIds = "";
			for (int i = 0; i < tckbox.length; i++) {
				tIds = tIds+","+tckbox[i];
			}
			vo.setTeacherId(tIds.substring(1));
			//排序处理
			mgr.SQLExecute("update network_course_video set sort=sort+1 where sort>='"+vo.getSort()+"' ");
			
			String sql="INSERT INTO network_course_video( id,nv_id,catalog_id,ncv_name,ncv_type,ncv_start_time,ncv_duration, ncv_img,ncv_preview_link,"
					+ " ncv_live_link,ncv_record_link_id,teacher_id,is_delete, ncv_live_room_id,sort,create_time ) "
					+ "values"
					+ "("+getFormatField(vo.getId())+","+getFormatField(vo.getNvId())+","+getFormatField(vo.getCatalogId())+","+getFormatField(vo.getNcvName())+","+getFormatField(vo.getNcvType())+""
					+ ","+getFormatField(ncvStartTimes)+","+getFormatField(vo.getNcvDuration())+","+getFormatField(vo.getNcvImg())+","+getFormatField(vo.getNcvPreviewLink())+""
					+ ","+getFormatField(vo.getNcvLiveLink())+","+getFormatField(vo.getNcvRecordLinkId())+","+getFormatField(vo.getTeacherId())+","+getFormatField(vo.getIsDelete())+""
					+ ","+getFormatField(vo.getNcvLiveRoomId())+","+getFormatField(vo.getSort())+",'"+DateUtils.getCurrDateTimeStr()+"')";
			mgr.SQLExecute(sql);
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
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "id", true);
			NetworkCourseVideo vo = (NetworkCourseVideo) mgr.getObjectByHql("from NetworkCourseVideo where id='"+ID+"' and isDelete=0");
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			List<NetworkCourse> networkCourse = Tool.findListByHql("from NetworkCourse where isDelete=0 and id='"+vo.getNvId()+"'");
			if(networkCourse.size()>0){
				String teacherId = networkCourse.get(0).getTeacherId();
				String[] teacherIds = teacherId.split(",");
				List<Map> nvtLm = new ArrayList<Map>();
				List<NetworkCourseTeacher> nvtList = new ArrayList<>();
				List<NetworkCourseTeacher> teacher =null;
				for (int i = 0; i < teacherIds.length; i++) {
					teacher = Tool.findListByHql("from NetworkCourseTeacher where isDelete=0 and id='"+teacherIds[i]+"'");
					nvtList.add(teacher.get(0));
				}
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
			}
			request.setAttribute("NetworkCourseVideo", vo);
			return mapping.findForward("update");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 修改信息
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			NetworkCourseVideo vo = (NetworkCourseVideo) form;
			String ncvStartTimes = ParamUtils.getParameter(request, "ncvStartTimes", true);
			String tckbox[] = request.getParameterValues("tckbox");
			String tIds = "";
			for (int i = 0; i < tckbox.length; i++) {
				tIds = tIds+","+tckbox[i];
			}
			vo.setTeacherId(tIds.substring(1));
			
			//处理排序
			String sorts = Tool.getValue("select sort from network_course_video where id='"+vo.getId()+"'");
			int sort=Integer.parseInt(sorts);
			if(vo.getSort()!=sort){
				mgr.SQLExecute("update network_course_video set sort=sort+1 where sort>='"+vo.getSort()+"' ");
			}
			String sql="UPDATE network_course_video SET catalog_id="+getFormatField(vo.getCatalogId())+",ncv_name="+getFormatField(vo.getNcvName())+",ncv_type="+getFormatField(vo.getNcvType())+",ncv_start_time="+getFormatField(ncvStartTimes)+""
					+ ",ncv_duration="+getFormatField(vo.getNcvDuration())+", ncv_img="+getFormatField(vo.getNcvImg())+",ncv_preview_link="+getFormatField(vo.getNcvPreviewLink())+",ncv_live_room_id="+getFormatField(vo.getNcvLiveRoomId())+","
					+ " ncv_live_link="+getFormatField(vo.getNcvLiveLink())+",ncv_record_link_id="+getFormatField(vo.getNcvRecordLinkId())+",teacher_id="+getFormatField(vo.getTeacherId())+", sort="+getFormatField(vo.getSort())+""
							+ " where id='"+vo.getId()+"'";
			mgr.SQLExecute(sql);
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	
	/**
	 * 删除
	 * @param mapping
	 * @param xss
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);
				 
			Tool.execute("update network_course_video set is_delete=1 where id = '"+ID+"'");
				 
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/*批量删除*/
	public ActionForward realdelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				Tool.execute("update network_course_video set is_delete=1 where id = '"+ids[i]+"'");
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
	 * 批量导入跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward batchAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String nvId = ParamUtils.getParameter(request, "id", false);
			request.setAttribute("nvId", nvId);
			return mapping.findForward("batchAdd");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回","javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	
	public ActionForward mpAnalysis(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取excel 文件
		NetworkCourseVideo fm = (NetworkCourseVideo) actionForm;
		String nvId = fm.getNvId();
		FormFile formfile = fm.getFile();
		InputStream inputstream = formfile.getInputStream();
		fm.clear();;// 清空
		ArrayList list = new ArrayList();
		int input = 0; // 导入记数
		
		try {
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			XSSFWorkbook hssfworkbook = new XSSFWorkbook(inputstream);
			XSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
			XSSFRow hssfrow = hssfsheet.getRow(0);// 第一行
			String ncvName = ""; //等级
			String teacherName = "";
			String teacherId = "";
			String ncvType = "";
			String ncvDuration = "";
			String ncvStartTime = "";
			String ncvPreviewLink = "";
			String ncvRecordLinkId = "";
			String ncvLiveLink = "";
			String ncvImg = "";
			String sort = "";
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < hssfworkbook.getNumberOfSheets(); i++) {
				hssfsheet = hssfworkbook.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
				
				for (int j = 1; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
					hssfrow = hssfsheet.getRow(j);
					// 判断是否还存在需要导入的数据
					if (hssfrow == null) {
						System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
						break;
					}
					/** */
					
					/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
					if(hssfrow.getCell((short) 0)!=null){
						ncvName =  hssfrow.getCell((short) 0).getStringCellValue().trim();
					}
					/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
					if(hssfrow.getCell((short) 1)!=null){
						teacherName =  hssfrow.getCell((short) 1).getStringCellValue().trim();
						String[] split =teacherName.split(",");
						String tname="";
						for (int t = 0; t < split.length; t++) {
							List<NetworkCourseTeacher> teacher = Tool.findListByHql("from NetworkCourseTeacher where name='"+split[t]+"'");
							if(teacher.size()>0){
								tname=tname+","+teacher.get(0).getId();
							}
						}
						teacherId=tname.substring(1);
					}
					if(hssfrow.getCell((short) 2)!=null){
						ncvType = 	String.valueOf(hssfrow.getCell((short) 2).getNumericCellValue()); 
					}
					if(hssfrow.getCell((short) 3)!=null){
						ncvDuration = new SimpleDateFormat("HH:mm:ss").format(hssfrow.getCell((short) 3).getDateCellValue());
					}
					if(hssfrow.getCell((short) 4)!=null){
						
						ncvStartTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hssfrow.getCell((short) 4).getDateCellValue());
					}
					if(hssfrow.getCell((short) 5)!=null){
						ncvPreviewLink =  hssfrow.getCell((short) 5).getStringCellValue().trim();
					}
					if(hssfrow.getCell((short) 6)!=null){
						ncvRecordLinkId =  hssfrow.getCell((short) 6).getStringCellValue().trim();
					}
					if(hssfrow.getCell((short) 7)!=null){
						ncvLiveLink =  hssfrow.getCell((short) 7).getStringCellValue().trim();
					}
					if(hssfrow.getCell((short) 8)!=null){
						sort = String.valueOf(hssfrow.getCell((short) 8).getNumericCellValue());
					}
					if(hssfrow.getCell((short) 9)!=null){
						ncvImg = String.valueOf(hssfrow.getCell((short) 9).getStringCellValue().trim());
					}
					String id=UUID.randomUUID().toString().replace("-", "");
					if(ncvType.equals("0")){
						ncvStartTime=null;
					}
					String sql="INSERT INTO network_course_video( id,nv_id,catalog_id,ncv_name,ncv_type,ncv_start_time,ncv_duration, ncv_img,ncv_preview_link,"
							+ " ncv_live_link,ncv_record_link_id,teacher_id,is_delete, sort,create_time ) "
							+ "values"
							+ "("+getFormatField(id)+","+getFormatField(nvId)+","+getFormatField(null)+","+getFormatField(ncvName)+","+getFormatField(ncvType)+""
							+ ","+getFormatField(ncvStartTime)+","+getFormatField(ncvDuration)+","+getFormatField(ncvImg)+","+getFormatField(ncvPreviewLink)+""
							+ ","+getFormatField(ncvLiveLink)+","+getFormatField(ncvRecordLinkId)+","+getFormatField(teacherId)+","+getFormatField("0")+""
							+ ","+getFormatField(sort)+",'"+DateUtils.getCurrDateTimeStr()+"')";
					mgr.SQLExecute(sql);
					
						// 导入成功加1
						input++;
					}
			}
			m.put("result", true);
			JsonUtils.outputJsonResponse(response, m);

		} catch (Exception e) {
			e.printStackTrace();
			m.put("false", true);
			JsonUtils.outputJsonResponse(response, m);
		}
		return null;
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

		NetworkCourseVideo vo = (NetworkCourseVideo) form;
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
