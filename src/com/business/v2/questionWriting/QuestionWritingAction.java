package com.business.v2.questionWriting;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easecom.common.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.hibernate.Query;
import org.hibernate.Session;

import com.business.v2.question.TbQuestionWriting;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.framework.struts.BaseAction;

public class QuestionWritingAction extends BaseAction{
	private static LogUtils logger = LogUtils.getLogger(TbQuestionWriting.class);
	QuestionWritingMgr mgr=new QuestionWritingMgr();

	/**
	 *  绑定作业时需要的翻译列表
	 */
	public ActionForward questionWriteList (ActionMapping mapping, ActionForm form,
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
			String title = ParamUtils.getParameter(request, "title", true);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			if (!"".equals(title)){
				queryConds.add(new QueryCond("title","String","like",title));
			}

			ListContainer lc = mgr.questionWriteList(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			List<TbQuestionWriting> list = lc.getList();

			request.setAttribute("dlbList",list);
			request.setAttribute("title",title);
			request.setAttribute("lc", lc);
			return mapping.findForward("dlbWriteList");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 生成功能树
	 * 
	 * @param mapping
	 * @param
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward treelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String sql="";
			
			List treelist = null;
			treelist = mgr.getFuntree(sql);

			request.setAttribute("treelist", treelist);
			request.setAttribute("Pid", "0104");
			return mapping.findForward("tree");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取功能树时出错", "返回", "javascript:window.history.back()");

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
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			
			// 接收传值
			String pid = ParamUtils.getParameter(request, "Pid", true);
			// 设置查询条件
			Collection queryConds = new ArrayList();

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage,pid);

			request.setAttribute("lc", lc);
			request.setAttribute("parentid", pid);
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
	 * 逻辑删除
	 * @param mapping
	 * @param
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "id", true);
			if(ID==null || ID.equals("")){
				WebDialogBox dialog = new WebDialogBox(1, "错误", "", "返回", "javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			
			String name = Tool.getValue("select name from tb_special_catalog where id='"+ID+"'");
			Tool.AddLog(sessionContainer.getUserId(), username, "删除专项练习写作,写作:"+name, "1", ipaddress);
			//写作表ID
			String id = Tool.getValue("select c.id from tb_special_catalog a LEFT JOIN tb_special_training b on a.id=b.catalog_id LEFT JOIN tb_question_writing c on b.training_id=c.id where a.id='"+ID+"'");

			Tool.execute("update  tb_special_catalog set is_del=1 where id = '"+ID+"'");
			Tool.execute("update  tb_question_writing set is_del=1 where id = '"+id+"'");
				 
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
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
			if(ID==null || ID.equals("")){
				WebDialogBox dialog = new WebDialogBox(1, "错误", "", "返回", "javascript:window.history.back()");
				request.setAttribute("DialogBox", dialog);
				return mapping.findForward("DialogBox");
			}
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			Session ses = HibernateSessionFactory.openSession();
			String sql="select a.name name,a.id catalogId,a.sort_order sortOrder,c.* from tb_special_catalog a LEFT JOIN tb_special_training b on a.id=b.catalog_id LEFT JOIN tb_question_writing c on b.training_id =c.id where a.id='"+ID+"' and a.is_del=0";
			Query query = ses.createSQLQuery(sql.toString()).addEntity(TbQuestionWriting.class);
			if(query.list().size()>0){
				request.setAttribute("TbQuestionWriting", query.list().get(0)); 
			}
			return mapping.findForward("update");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	/**
	 * 修改信息
	 * 
	 * @param mapping
	 * @param
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		TbQuestionWritingActionForm vo = (TbQuestionWritingActionForm) form;
		try {
			
			String analysis="\""+vo.getAnalysis()+"\"";
			String reference="\""+vo.getReference()+"\"";
			String content="\""+vo.getContent()+"\"";
			String target="\""+vo.getTarget()+"\"";
			String sql="UPDATE tb_question_writing set title="+getFormatField(vo.getTitle())+",content="+content+",reference="+reference+",analysis="+analysis+",target="+target+",analysis_url="+getFormatField(vo.getAnalysisUrl())+" WHERE id='"+vo.getId()+"'";
			String sqls="UPDATE tb_special_catalog SET name ='"+vo.getName()+"' WHERE id ='"+vo.getCatalogId()+"'";
			mgr.SQLExecute(sql);
			mgr.SQLExecute(sqls);
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username, "修改专项练习写作,写作:"+vo.getName(), "1", ipaddress);
			
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
	 * 去添加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			String Pid = ParamUtils.getParameter(request, "Pid", true);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("pid", Pid);
			if(Pid.equals("0105")){
				request.setAttribute("type", "四级");
			}else{
				request.setAttribute("type", "六级");
			}
			return mapping.findForward("add");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	/**
	 * 添加信息
	 * 
	 * @param mapping
	 * @param
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		TbQuestionWritingActionForm vo = (TbQuestionWritingActionForm) form;
		vo.setId(UUID.randomUUID().toString().replace("-", ""));
		String Pid = ParamUtils.getParameter(request, "Pid", true);
		try {
			//最大排序
			String MaxSort = Tool.getValue("select max(sort_order) from tb_special_catalog where p_id ='"+Pid+"'");
			int sortOrder=Integer.parseInt(MaxSort);
			int max_sort_order=sortOrder+1;
			//最大ID
			String MaxID = Tool.getValue("select max(id) from tb_special_catalog where p_id ='"+Pid+"'");
			int maxID=Integer.parseInt(MaxID);
			int maxId=maxID+1;
			String max_id="0"+maxId;
			//级别 01:四级 02:六级
			String p_id = Tool.getValue("select p_id from tb_special_catalog where id ='"+Pid+"'");
			//添加 tb_special_catalog 表信息
			String specialSQL="INSERT INTO tb_special_catalog(id,p_id,name,icon_url,sort_order,remarks,is_del,create_time)VALUES"
					 + "('"+max_id+"','"+Pid+"','"+vo.getName()+"',null,'"+max_sort_order+"',null,0,'"+DateUtils.getCurrDateTimeStr()+"')";
			//添加 tb_special_training 中间表信息
			String trainingSQL="INSERT INTO tb_special_training(id,section,catalog_id,training_type,training_id,sort_order)VALUES"
					 + "('"+UUID.randomUUID().toString().replace("-", "")+"','"+p_id+"','"+max_id+"','4','"+vo.getId()+"','1')";
			//添加 tb_question_translation 翻译题表信息
			String analysis="\""+vo.getAnalysis()+"\"";
			String reference="\""+vo.getReference()+"\"";
			String content="\""+vo.getContent()+"\"";
			String target="\""+vo.getTarget()+"\"";
			String translationSQL="INSERT INTO tb_question_writing(id,title,content,reference,analysis,target,analysis_url,target,is_del,create_time)VALUES"
								+ "('"+vo.getId()+"',"+getFormatField(vo.getTitle())+","+content+","+reference+","+analysis+","+target+","+getFormatField(vo.getAnalysisUrl())+",1,0,'"+DateUtils.getCurrDateTimeStr()+"')";
			mgr.SQLExecute(specialSQL);
			mgr.SQLExecute(trainingSQL);
			mgr.SQLExecute(translationSQL);
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username, "添加专项训练写作,写作:"+vo.getName(), "1", ipaddress);
			
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
	 * 下载导入单词模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void preDown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

            String fileName = "写作.xls";
            String filePath = "/template/training/写作.xls";
            filePath = request.getSession().getServletContext().getRealPath("/") + filePath;
            // 判断文件是否存在
            File file = new File(filePath);
            if (!file.exists()) { // 不存在，返回false
                throw new Exception("没找到该文件，该文件可能已被删除");
            }
            String displayFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename="+ displayFileName);// 不是显示是下载
            BufferedInputStream inputStream = null;
            BufferedOutputStream outputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(filePath));
                outputStream = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while ((bytesRead = inputStream.read(buff, 0, buff.length)) != -1) {
                    outputStream.write(buff, 0, bytesRead);
                }
                outputStream.flush();
            } catch (final IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }  finally {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
	}
	
	/**
	 * 专项批量导入
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward QuestionWritingUpload(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取excel 文件
		TbQuestionWritingActionForm fm = (TbQuestionWritingActionForm) actionForm;
		//文件
		FormFile formfile = fm.getFile();
		//文件名
		String filename = formfile.toString();
		//文件类型 .xls  .xlsx
		String fileType = filename.substring(filename.lastIndexOf(".") + 1);
		
		InputStream inputstream = formfile.getInputStream();
		Workbook wb=null;
		int input = 0; // 导入记数
		String type="";
		try {
			if (fileType.equals("xls")) {
	            wb = new HSSFWorkbook(inputstream);
	        } else if (fileType.equals("xlsx")) {
	            wb = new XSSFWorkbook(inputstream);
	        } else {
	            throw new Exception("读取的不是excel文件");
	        }
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			//XSSFWorkbook hssfworkbook = new XSSFWorkbook(inputstream);
			Sheet hssfsheet = wb.getSheetAt(0);// 第一个工作表
			Row hssfrow = hssfsheet.getRow(0);// 第一行
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				hssfsheet = wb.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
				
				for (int j = 2; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
					TbQuestionWriting tqw=new TbQuestionWriting();
					tqw.setTarget("1");
					hssfrow = hssfsheet.getRow(j);
					// 判断是否还存在需要导入的数据
					if (hssfrow == null) {
						System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
						break;
					}
					/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
					if(hssfrow.getCell((short) 0)!=null){
						type=hssfrow.getCell((short) 0).getStringCellValue().trim();
					}
					if(hssfrow.getCell((short) 1)!=null){
						tqw.setName(hssfrow.getCell((short) 1).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 2)!=null){
						tqw.setTitle(hssfrow.getCell((short) 2).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 3)!=null){
						tqw.setContent(hssfrow.getCell((short) 3).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 4)!=null){
						tqw.setReference(hssfrow.getCell((short) 4).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 5)!=null){
						tqw.setAnalysis(hssfrow.getCell((short) 5).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 6)!=null){
						tqw.setAnalysisUrl(hssfrow.getCell((short) 6).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 7)!=null){
						tqw.setSortOrder(hssfrow.getCell((short) 7).getStringCellValue().trim());
					}
					String pid="";
					if(type.equals("01")){
						pid="0105";
					}else if(type.equals("02")){
						pid="0205";
					}
					if(pid!=null && !pid.equals("")){
						mgr.UploadAdd(pid,tqw);
					}
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
	 * 试卷批量导入
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward QuestionWritingUpload2(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
		Map m = new HashMap();
		// 获取excel 文件
		TbQuestionWritingActionForm fm = (TbQuestionWritingActionForm) actionForm;
		//文件
		FormFile formfile = fm.getFile();
		//文件名
		String filename = formfile.toString();
		//文件类型 .xls  .xlsx
		String fileType = filename.substring(filename.lastIndexOf(".") + 1);

		InputStream inputstream = formfile.getInputStream();
		Workbook wb=null;
		int input = 0; // 导入记数
		String type="";
		try {
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook(inputstream);
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook(inputstream);
			} else {
				throw new Exception("读取的不是excel文件");
			}
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			//XSSFWorkbook hssfworkbook = new XSSFWorkbook(inputstream);
			Sheet hssfsheet = wb.getSheetAt(0);// 第一个工作表
			Row hssfrow = hssfsheet.getRow(0);// 第一行
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				hssfsheet = wb.getSheetAt(i);
				// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数

				for (int j = 2; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
					TbQuestionWriting tqw=new TbQuestionWriting();
					tqw.setTarget("2");
					hssfrow = hssfsheet.getRow(j);
					// 判断是否还存在需要导入的数据
					if (hssfrow == null) {
						System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
						break;
					}
					/** 将EXCEL中的第 j 行，第一列的值插入到实例中 */
					if(hssfrow.getCell((short) 0)!=null){
						type=hssfrow.getCell((short) 0).getStringCellValue().trim();
					}
					if(hssfrow.getCell((short) 1)!=null){
						tqw.setName(hssfrow.getCell((short) 1).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 2)!=null){
						tqw.setTitle(hssfrow.getCell((short) 2).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 3)!=null){
						tqw.setContent(hssfrow.getCell((short) 3).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 4)!=null){
						tqw.setReference(hssfrow.getCell((short) 4).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 5)!=null){
						tqw.setAnalysis(hssfrow.getCell((short) 5).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 6)!=null){
						tqw.setAnalysisUrl(hssfrow.getCell((short) 6).getStringCellValue().trim());
					}
					if(hssfrow.getCell((short) 7)!=null){
						tqw.setSortOrder(hssfrow.getCell((short) 7).getStringCellValue().trim());
					}
					String pid="";
					if(type.equals("01")){
						pid="0105";
					}else if(type.equals("02")){
						pid="0205";
					}
					if(pid!=null && !pid.equals("")){
						mgr.UploadAdd(pid,tqw);
					}
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
