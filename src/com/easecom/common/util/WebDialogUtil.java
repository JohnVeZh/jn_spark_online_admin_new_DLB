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

package com.easecom.common.util;
/**
 *构造器工具类
 */
public class WebDialogUtil {
	private static WebDialogBox dialog;
	static {
		dialog = new WebDialogBox();
		dialog.setTitle("系统消息");
	}

	public static WebDialogBox setError(String message) {
		dialog.setSort(2);
		dialog.setMessage("error: " + message);
		dialog.setNextUrl("");
		dialog.setFunction("history.back();");
		dialog.setButtonName("返回");
		return dialog;
	}

	public static WebDialogBox setMessage(String message, String url) {
		dialog.setSort(1);
		dialog.setMessage(message);
		dialog.setNextUrl(url);
		dialog.setButtonName("确定");
		return dialog;
	}
}
