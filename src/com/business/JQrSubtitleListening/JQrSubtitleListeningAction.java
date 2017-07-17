package com.business.JQrSubtitleListening; 


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import bsh.StringUtil;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.AddZero;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.MD5;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.business.JQrSubtitleListening.JQrSubtitleListening;
import com.business.JQrSubtitleListening.JQrSubtitleListeningActionForm;
import com.business.JQrSubtitleListening.JQrSubtitleListeningMgr;
import com.business.JQrSubtitleListeningLyric.JQrSubtitleListeningLyric;
import com.business.JQrSubtitleListeningLyric.JQrSubtitleListeningLyricActionForm;
import com.business.JQrSubtitleListeningLyric.JQrSubtitleListeningLyricMgr;
import com.business.MatchedPapersTopicHearingType.MatchedPapersTopicHearingTypeActionForm;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyric;
import com.business.MatchedPapersTopicLyric.MatchedPapersTopicLyricActionForm;

public class JQrSubtitleListeningAction extends BaseAction{
	 JQrSubtitleListeningMgr mgr = new JQrSubtitleListeningMgr();
	 JQrSubtitleListeningLyricMgr jqrslMgr = new JQrSubtitleListeningLyricMgr();

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
			//String name = ParamUtils.getParameter(request, "name", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("JQrSubtitleListening.isDel", "String", "=", "0"));
			
			//queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			//request.setAttribute("orgId", orgId);
			//request.setAttribute("name", name);
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

			request.setAttribute("JQrSubtitleListeningActionForm", mgr.view(ID));

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
		JQrSubtitleListeningActionForm vo = (JQrSubtitleListeningActionForm) form;
		try {
			String ID = ParamUtils.getParameter(request, "id", true);
			String mpName = ParamUtils.getParameter(request, "mpName", true);
			String qrCode = ParamUtils.getParameter(request, "qrCode", true);
			String hearUrl = ParamUtils.getParameter(request, "hearUrl", true);
			String sort = ParamUtils.getParameter(request, "sort", true);
			String levelType = ParamUtils.getParameter(request, "levelType", true);
			
			
			JQrSubtitleListeningActionForm po = mgr.view(ID);
			if(null!=po){
				po.setHearUrl(hearUrl);
				po.setMpName(mpName);
				po.setQrCode(qrCode);
				po.setLevelType(levelType);
				if(StringUtils.isNumber(sort)){
					po.setSort(Integer.parseInt(sort));
				}
				mgr.update(po);
			}
			
			//修改或新增听力歌词
			String arrbills = ParamUtils.getParameter(request, "arrbills", true);
			String[] arls = arrbills.split("&");
			for (String string : arls) {
				if(null != string && !"".equals(string)){
					String[] arrb = string.split("#");
					if(null!=arrb[4] && !"".equals(arrb[4]) && !"noId".equals(arrb[4])){
						JQrSubtitleListeningLyricActionForm nqpt= jqrslMgr.view(arrb[4]);
						
						if(null!= nqpt){
							if(null!=arrb[0])
								nqpt.setLyricText(arrb[0]);
							if(null!=arrb[1])
								nqpt.setStatrTime(Double.parseDouble(AddZero.format(arrb[1], 2)));
							if(null!=arrb[3] && !"".equals(arrb[3]));
							nqpt.setSort(Integer.parseInt(arrb[3]));
							
							jqrslMgr.update(nqpt);
						}
					}else{
						JQrSubtitleListeningLyric nqpt = new JQrSubtitleListeningLyric();
						nqpt.setQrslId(vo.getId());
						if(null!=arrb[0])
							nqpt.setLyricText(arrb[0]);
						if(null!=arrb[1])
							nqpt.setStatrTime(Double.parseDouble(AddZero.format(arrb[1], 2)));
						if(null!=arrb[3] && !"".equals(arrb[3]));
						nqpt.setSort(Integer.parseInt(arrb[3]));
						
						jqrslMgr.add(nqpt);
						
					}
				}
			}
			map.put("result",true);
		} catch (Exception ex) {
			ex.printStackTrace();
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

			JQrSubtitleListeningActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("JQrSubtitleListeningActionForm", vo);

			//查询选项
			List<MatchedPapersTopicLyric> choices = Tool.findListByHql("from JQrSubtitleListeningLyric where qrslId = '"+vo.getId()+"' order by sort ");
			request.setAttribute("choices", choices);
			//查询级别类型
			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value",vo.getLevelType());
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
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JQrSubtitleListeningActionForm vo = (JQrSubtitleListeningActionForm) form;			  
			JQrSubtitleListening po = new JQrSubtitleListening();
			po.setCreatetime(DateUtils.getCurrDateTimeStr());
			po.setHearUrl(vo.getHearUrl());
			po.setMpName(vo.getMpName());
			po.setQrCode(vo.getQrCode());
			po.setSort(vo.getSort());
			po.setLevelType(vo.getLevelType());
			String id = mgr.add(po);
			
			String[] arrbills = ParamUtils.getParameterValues(request, "arrbills", true);
			for (String string : arrbills) {
				System.out.println(string+"----");
				String[] arrb = string.split("/");
				JQrSubtitleListeningLyric mpthsu = new JQrSubtitleListeningLyric();
				mpthsu.setQrslId(id);
				if(null!=arrb[0])
					mpthsu.setLyricText(arrb[0]);
				if(null!=arrb[1])
					mpthsu.setStatrTime(Double.parseDouble(AddZero.format(arrb[1],2)));
				if(null!=arrb[3] && !"".equals(arrb[3]));
					mpthsu.setSort(Integer.parseInt(arrb[3]));
				
					jqrslMgr.add(mpthsu);	
			}
			
			map.put("result", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("result",false);
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

			JQrSubtitleListeningActionForm vo = new JQrSubtitleListeningActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("JQrSubtitleListeningActionForm", vo);
			//查询级别类型
			String levelTypeStr = Tool.getList("select value,name from sys_config where type='MT_TYPE' ", "name", "value");
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

			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				 
			Tool.execute("update j_qr_subtitle_listening set is_del=1 where id = '"+ids[i]+"'");
				 
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
			String tId = ParamUtils.getParameter(request, "tId");	
			Tool.execute("update j_qr_subtitle_listening set is_del=1 where id = '"+tId+"'");
				 
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
	 * 删除歌词列
	 * 方法功能说明：  
	 * 创建：2016年5月31日 by Zzc   
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
	public ActionForward AjasRealdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = ParamUtils.getParameter(request, "id");	
		if (id != null && !"".equals(id)) {
			try {
				boolean isSuc = Tool.execute("delete from matched_papers_topic_lyric where id ='"+id+"'");
				
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
		map.put("interface_name", "matched_papers_topic_lyric_AjasRealdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	
	/**
	 * 从Excel文件中读取数据，并导入到数据库中,字幕导入
	 */
	public ActionForward lyricExl(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
	if (null == sessionContainer)
		sessionContainer = new SessionContainer();
	Map m = new HashMap();
	String levelType = ParamUtils.getParameter(request, "levelType");	
	// 获取excel 文件
	JQrSubtitleListeningActionForm fm = (JQrSubtitleListeningActionForm) actionForm;
	FormFile formfile = fm.getFile();
	InputStream inputstream = formfile.getInputStream();
	fm.clear();;// 清空
	ArrayList list = new ArrayList();
	int input = 0; // 导入记数

	try {
		// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
		HSSFRow hssfrow = hssfsheet.getRow(0);// 第一行
		String col0 = ""; //passage名称
		String col1 = ""; //时间
		String col2 = ""; //字幕
		String mpthtId = "";
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
					col0 = hssfrow.getCell((short) 0).getStringCellValue().trim();
				}

				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(hssfrow.getCell((short) 1)!=null){
					col1 = hssfrow.getCell((short) 1).getStringCellValue()
							.trim();
				}
				/** 将EXCEL中的第 j 行，第6列的值插入到实例中 */
				if(hssfrow.getCell((short) 2)!=null){
					col2 = hssfrow.getCell((short) 2).getStringCellValue()
							.trim();
				}
				
				
				if(!"".equals(col0)){
					mpthtId = Tool.getValue("select id from j_qr_subtitle_listening where mp_name='"+col0+"' and level_type='"+levelType+"'");
					
					JQrSubtitleListening jsl = new JQrSubtitleListening();
					if("".equals(mpthtId)){
						jsl.setMpName(col0);
						jsl.setQrCode("");
						jsl.setHearUrl("");
						jsl.setLevelType(levelType);
						mpthtId = mgr.add(jsl);
					}
					
					if(!"".equals(mpthtId) && !"".equals(col1)){
						String[] str = col1.split(":");
						int dd = Integer.parseInt(str[0].trim().toString());
						double time = Double.parseDouble(AddZero.format(dd*60+Double.parseDouble(str[1].trim().toString()),2));
						JQrSubtitleListeningLyric mptl = new JQrSubtitleListeningLyric();
						mptl.setLyricText(col2);
						mptl.setStatrTime(time);
						mptl.setSort(1);
						mptl.setQrslId(mpthtId);
						jqrslMgr.add(mptl);
					}
				}else{
					if(!"".equals(mpthtId)){
						if(!"".equals(col1)){
							String[] str = col1.split(":");
							int dd = Integer.parseInt(str[0].trim().toString());
							double time = Double.parseDouble(AddZero.format(dd*60+Double.parseDouble(str[1].trim().toString()),2));
							MatchedPapersTopicLyric mptl = new MatchedPapersTopicLyric();
							mptl.setLyricText(col2);
							mptl.setStatrTime(time);
							mptl.setMpthtId(mpthtId);
							mptl.setSort(1);
							jqrslMgr.add(mptl);
						}
					}
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

	//request.setAttribute("total", input);
	//return list(actionMapping, actionForm, request, response);
	return null;
	}
	
}
