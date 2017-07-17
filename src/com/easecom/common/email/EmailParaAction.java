/**
 * @(#)$CurrentFile
 * 版权声明 山东益信通科贸有限公司，版权所有 违者必究
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> Company：山东益信通科贸有限公司
 *<br> @author YuPeng
 *<br> 2012-05-28
 *<br> @version 1.0
 */

package com.easecom.common.email;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.WebDialogBox;

/**
 * <pre>
 * 邮件参数设置Action
 * </pre>
 * 
 * @author YuPeng
 * 
 * 
 */
public class EmailParaAction extends BaseAction {
	
	/**
	 * 引用邮件参数维护服务类
	 */
	EmailParaMgr mgr = new EmailParaMgr();

	/**
	 * 增加初始化
	 * @param mapping action映射
	 * @param form 表单
	 * @param request 请求
	 * @param response 响应
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward initAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			form = mgr.initAdd();
			request.setAttribute("EmailParaForm", form);			
			return mapping.findForward("initAdd");
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "修改邮件参数时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 
	 * @Description:  邮件参数预修改
	 * @param mapping action映射
	 * @param form 表单
	 * @param request 请求
	 * @param response 响应
	 * @return  
	 * @author: YuPeng
	 * @date 2012-5-24 下午2:27:56
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			EmailParaForm spForm = (EmailParaForm)form;
			if (spForm != null){
				String tfrom = spForm.getTfrom();
				if (tfrom != null && !tfrom.trim().equals("")){
					String[] strArr = tfrom.split("@");
					String userNm = strArr[0];
					spForm.setUserName(userNm);
				}
			}
			mgr.add(spForm);
			EmailParaUtils.getFromDb();
			request.setAttribute("isAdd", "yes");
			return initAdd(mapping, form, request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "修改邮件参数时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
}
