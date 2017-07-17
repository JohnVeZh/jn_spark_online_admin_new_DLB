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
 *构造器模型
 */
public class WebDialogBox {

	private String title;
	private String nextUrl;
	private String function;
	private String message;
	private String buttonName;
	private Exception exception;
	private int sort;

	/**
	 * 构造器
	 * 
	 * @param sort
	 *            信息类别，1错误,2成功,3一般
	 * @param title
	 *            对话框标题
	 * @param msg
	 *            对话框内容
	 * @param buttonName
	 *            对话框显示的的按扭名称
	 * @param nextUrl
	 *            对话框中按扭事件响应后的目录链接
	 */
	public WebDialogBox() {

	}

	public WebDialogBox(int sort, String title, String msg, String buttonName,
			String nextUrl) {
		this(sort, title, msg, buttonName, nextUrl, "");
	}

	public WebDialogBox(int sort, String title, String msg, String buttonName,
			String nextUrl, String function) {
		this.sort = sort;
		this.title = title;
		this.message = msg;
		this.buttonName = buttonName;
		this.nextUrl = nextUrl;
		this.function = function;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}