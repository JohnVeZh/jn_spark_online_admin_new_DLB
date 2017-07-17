/**
 * @(#)$CurrentFile
 * 版权声明 山东益信通科贸有限公司，版权所有 违者必究
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> Company：山东益信通科贸有限公司
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

package com.easecom.common.framework.struts;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.ValidatorForm;

/**
 * @title: ActionForm基类
 * @description:
 * @author: wanghw
 * @version: 1.0
 * @create Date:2006-3-30
 */
@SuppressWarnings("serial")
public class BaseForm extends ValidatorForm implements Serializable {

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		String parameter = mapping.getParameter();

		if (parameter != null) {
			String method = request.getParameter(parameter);
			MessageResources resources = (MessageResources) request
					.getAttribute(Globals.MESSAGES_KEY);

			String cancel = resources.getMessage("button.cancel");
			String delete = resources.getMessage("button.delete");

			if ((method != null)
					&& (method.equalsIgnoreCase(cancel) || method
							.equalsIgnoreCase(delete))) {
				return null;
			}
		}
		return super.validate(mapping, request);
	}
}