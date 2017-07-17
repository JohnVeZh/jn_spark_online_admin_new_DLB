package com.business.PreferentialCode; 


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
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.Tool;

public class PreferentialCodeMobileAction extends BaseAction{
	 PreferentialCodeMgr mgr = new PreferentialCodeMgr();

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
			List<PreferentialCode> collectList = mgr.mlist(queryConds, mp);			
			map.put("dataList", collectList);
			map.put("succeed", "000");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("succeed", "001");
		}
	
		map.put("interface_name", "PreferentialCode_mlist");
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
				map.put("PreferentialCode",mgr.view(id));					
				map.put("succeed", "000");
			} catch (Exception e) {
				map.put("succeed", "001");
			}
		}
		map.put("interface_name", "PreferentialCode_view");
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
     String productType = ParamUtils.getParameter(request, "productType", true);
     String deductionMoney = ParamUtils.getParameter(request, "deductionMoney", true);
     String fullMoney = ParamUtils.getParameter(request, "fullMoney", true);
     String rate = ParamUtils.getParameter(request, "rate", true);
     String id = ParamUtils.getParameter(request, "id", true);
     String sort = ParamUtils.getParameter(request, "sort", true);
     String endTime = ParamUtils.getParameter(request, "endTime", true);
     String createtime = ParamUtils.getParameter(request, "createtime", true);
     String startTime = ParamUtils.getParameter(request, "startTime", true);
     String content = ParamUtils.getParameter(request, "content", true);
     String discountType = ParamUtils.getParameter(request, "discountType", true);
     String title = ParamUtils.getParameter(request, "title", true);
		PreferentialCodeActionForm vo = new PreferentialCodeActionForm();
      vo.setProductType(Integer.parseInt(productType));
      vo.setDeductionMoney(Double.parseDouble(deductionMoney));
      vo.setFullMoney(Double.parseDouble(fullMoney));
      vo.setRate(Double.parseDouble(rate));
      vo.setId(id);
      vo.setSort(sort);
      vo.setEndTime(endTime);
      vo.setCreatetime(createtime);
      vo.setStartTime(startTime);
      vo.setContent(content);
      vo.setDiscountType(Integer.parseInt(discountType));
      vo.setTitle(title);
		try {
			mgr.update(vo);
			map.put("succeed", "000");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			map.put("succeed", "001");
		}
        map.put("interface_name", "PreferentialCode_update");
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
				map.put("PreferentialCode",mgr.view(id));					
				map.put("succeed", "000");
			} catch (Exception e) {
				map.put("succeed", "001");
			}
		}
		map.put("interface_name", "PreferentialCode_preUpdate");
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
		
     String productType = ParamUtils.getParameter(request, "productType", true);
     String deductionMoney = ParamUtils.getParameter(request, "deductionMoney", true);
     String fullMoney = ParamUtils.getParameter(request, "fullMoney", true);
     String rate = ParamUtils.getParameter(request, "rate", true);
     String id = ParamUtils.getParameter(request, "id", true);
     String sort = ParamUtils.getParameter(request, "sort", true);
     String endTime = ParamUtils.getParameter(request, "endTime", true);
     String createtime = ParamUtils.getParameter(request, "createtime", true);
     String startTime = ParamUtils.getParameter(request, "startTime", true);
     String content = ParamUtils.getParameter(request, "content", true);
     String discountType = ParamUtils.getParameter(request, "discountType", true);
     String title = ParamUtils.getParameter(request, "title", true);
		PreferentialCodeActionForm vo = new PreferentialCodeActionForm();
      vo.setProductType(Integer.parseInt(productType));
      vo.setDeductionMoney(Double.parseDouble(deductionMoney));
      vo.setFullMoney(Double.parseDouble(fullMoney));
      vo.setRate(Double.parseDouble(rate));
      vo.setId(id);
      vo.setSort(sort);
      vo.setEndTime(endTime);
      vo.setCreatetime(createtime);
      vo.setStartTime(startTime);
      vo.setContent(content);
      vo.setDiscountType(Integer.parseInt(discountType));
      vo.setTitle(title);
		try {
			mgr.add(vo);
			map.put("succeed", "000");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			map.put("succeed", "001");
		}
        map.put("interface_name", "PreferentialCode_update");
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
				boolean isSuc = Tool.execute("delete from preferential_code where id='"+dId+"'");
				
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
		map.put("interface_name", "PreferentialCode_realdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}
