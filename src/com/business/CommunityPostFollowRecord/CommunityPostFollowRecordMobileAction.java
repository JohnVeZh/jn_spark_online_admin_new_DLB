package com.business.CommunityPostFollowRecord; 


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
import com.easecom.common.mobile.MobilePage;
import com.easecom.common.util.ExcelUtils;
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
import com.business.CommunityPostFollowRecord.CommunityPostFollowRecord;
import com.business.CommunityPostFollowRecord.CommunityPostFollowRecordActionForm;
import com.business.CommunityPostFollowRecord.CommunityPostFollowRecordMgr;

public class CommunityPostFollowRecordMobileAction extends BaseAction{
	 CommunityPostFollowRecordMgr mgr = new CommunityPostFollowRecordMgr();

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
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		Map<String, Object> map = new HashMap<String, Object>();
		
		//String userId = ParamUtils.getParameter(request, "Id");
		
		Collection queryConds = new ArrayList();
		//queryConds.add(new QueryCond("Collect.userId","String","=", userId));
		
		// 分页数据 start
		String currentPage = ParamUtils.getParameter(request, "currentPage");
		String rowCountPerPage = ParamUtils.getParameter(request,
				"rowCountPerPage");
		int rowPerPage = 5;
		int currPage = 1;
		if (null != currentPage && !"".equals(currentPage)
				&& !"null".equals(currentPage))
			currPage = Integer.parseInt(currentPage);
		if (null != rowCountPerPage && !"".equals(rowCountPerPage)
				&& !"null".equals(rowCountPerPage))
			rowPerPage = Integer.parseInt(rowCountPerPage);
		MobilePage mp = new MobilePage(currPage, rowPerPage);
		// 分页数据 end
		
		try {
			List<CommunityPostFollowRecord> collectList = mgr.mlist(queryConds, mp);			
			map.put("dataList", collectList);
			map.put("succeed", "000");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succeed", "001");
		}
	
		map.put("interface_name", "CommunityPostFollowRecord_mlist");
		JsonUtils.outputJsonResponse(response, map);
		return null;
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

		 String id = ParamUtils.getParameter(request, "id", true);
		Map map = new HashMap();
		if (id == null || id == null) {
			map.put("succeed", "001");
		} else {
			try {
				map.put("CommunityPostFollowRecord",mgr.view(id));					
				map.put("succeed", "000");
			} catch (Exception e) {
				map.put("succeed", "001");
			}
		}
		map.put("interface_name", "CommunityPostFollowRecord_view");
		JsonUtils.outputJsonResponse(response, map);
		return null;
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
     String communityPostId = ParamUtils.getParameter(request, "communityPostId", true);
     String createtime = ParamUtils.getParameter(request, "createtime", true);
     String id = ParamUtils.getParameter(request, "id", true);
     String userId = ParamUtils.getParameter(request, "userId", true);
		CommunityPostFollowRecordActionForm vo = new CommunityPostFollowRecordActionForm();
      vo.setCommunityPostId(communityPostId);
      vo.setCreatetime(createtime);
      vo.setId(id);
      vo.setUserId(userId);
		try {
			mgr.update(vo);
			map.put("succeed", "000");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			map.put("succeed", "001");
		}
        map.put("interface_name", "CommunityPostFollowRecord_update");
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

		 String id = ParamUtils.getParameter(request, "id", true);
		Map map = new HashMap();
		if (id == null || id == null) {
			map.put("succeed", "001");
		} else {
			try {
				map.put("CommunityPostFollowRecord",mgr.view(id));					
				map.put("succeed", "000");
			} catch (Exception e) {
				map.put("succeed", "001");
			}
		}
		map.put("interface_name", "CommunityPostFollowRecord_preUpdate");
		JsonUtils.outputJsonResponse(response, map);
		return null;
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
		
     String communityPostId = ParamUtils.getParameter(request, "communityPostId", true);
     String createtime = ParamUtils.getParameter(request, "createtime", true);
     String id = ParamUtils.getParameter(request, "id", true);
     String userId = ParamUtils.getParameter(request, "userId", true);
		CommunityPostFollowRecordActionForm vo = new CommunityPostFollowRecordActionForm();
      vo.setCommunityPostId(communityPostId);
      vo.setCreatetime(createtime);
      vo.setId(id);
      vo.setUserId(userId);
		try {
			mgr.add(vo);
			map.put("succeed", "000");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			map.put("succeed", "001");
		}
        map.put("interface_name", "CommunityPostFollowRecord_update");
        JsonUtils.outputJsonResponse(response, map);
		return null;
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
Map<String, Object> map = new HashMap<String, Object>();
		String dId = ParamUtils.getParameter(request, "id");		 
		if (dId != null && !"".equals(dId)) {
			try {
				boolean isSuc = Tool.execute("delete from community_post_follow_record where id='"+dId+"'");
				
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
		map.put("interface_name", "CommunityPostFollowRecord_realdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}
