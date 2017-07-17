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

/**
 * session 模型
 */

package com.easecom.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class SessionContainer implements Serializable {
	private String cssId;//客服Id
	private String cssUsername;//客服名称
	private String userId; // 用户ID
	private String userName;// 用户名称
	private String loginName;// 用户登陆帐号
	private String orgId; // 组织ID
	private String orgType; // 组织类型
	private String orgName; // 组织名称
	private String styleId; // 样式ID
	private String styleName; // 样式名称
	private String styleUrl; // 样式url
	private List funIds = new ArrayList(); // 功能权限ID集合
	public String getCssId() {
		return cssId;
	}

	public void setCssId(String cssId) {
		this.cssId = cssId;
	}

	public String getCssUsername() {
		return cssUsername;
	}

	public void setCssUsername(String cssUsername) {
		this.cssUsername = cssUsername;
	}

	private String loadMark = "0";// 标志前台用户是否已经登录，1表示已经登录，0表示未登录
	private String styleUser = "qy";// 企业为qy,国税为gs；

	private String topOrgId;// 用户直属的组织id

	private String urltype = ""; // 用户是在前台pad登录的，还是后台登录的

	private String eatplatname = "未开台";
	private String eatplat;
	private String orderid;// 用户名+当前时间
	private String roleId;// 角色ID
	private String roleName;// 角色名称

	/** ************ */
	private String shopId;
	private String shopName;
	private String userIcon;
	private String type;// 类型
	private Map map;
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStyleUser() {
		return styleUser;
	}

	public void setStyleUser(String styleUser) {
		this.styleUser = styleUser;
	}

	public String getLoadMark() {
		return loadMark;
	}

	public void setLoadMark(String loadMark) {
		this.loadMark = loadMark;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStyleUrl() {
		return styleUrl;
	}

	public void setStyleUrl(String styleUrl) {
		this.styleUrl = styleUrl;
	}

	public List getFunIds() {
		return funIds;
	}

	public void setFunIds(List funIds) {
		this.funIds = funIds;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getTopOrgId() {
		return topOrgId;
	}

	public void setTopOrgId(String topOrgId) {
		this.topOrgId = topOrgId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}

	public String getEatplat() {
		return eatplat;
	}

	public void setEatplat(String eatplat) {
		this.eatplat = eatplat;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getEatplatname() {
		return eatplatname;
	}

	public void setEatplatname(String eatplatname) {
		this.eatplatname = eatplatname;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
