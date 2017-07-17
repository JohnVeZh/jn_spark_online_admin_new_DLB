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
 *树状结构模型
 */

import java.util.*;
import java.io.Serializable;

public class TreeNode implements Serializable {

	/* 树节点ID */
	private String treeNodeID;

	/* 树父节点ID */
	private String parentTreeNodeID;

	/* 树节点编码 */
	private String treeNodeCode;

	/* 树节点名称 */
	private String treeNodeName;

	/* 树节点超连接 */
	private String treeNodeURL;

	/* 树节点图标 */
	private String icon;

	/* 树节点展开图标 */
	private String openIcon;

	// 选项，是否为单选、多选项;c表示checkbox
	private String multiple;

	/* 树节点是否被选，在树节点带有多选或单选框时使用 */
	// private boolean folder = false;
	private boolean isSelected = false;

	public TreeNode() {
	}

	public TreeNode(String treeNodeID, String treeNodeName,
			String parentTreeNodeID) {
		this.treeNodeID = treeNodeID;
		this.treeNodeName = treeNodeName;
		this.parentTreeNodeID = parentTreeNodeID;
	}

	public TreeNode(String treeNodeID, String treeNodeName,
			String parentTreeNodeID, String multiple) {
		this.treeNodeID = treeNodeID;
		this.treeNodeName = treeNodeName;
		this.parentTreeNodeID = parentTreeNodeID;
		this.multiple = multiple;
	}

	public TreeNode(String treeNodeID, String treeNodeName,
			String parentTreeNodeID, String icon, String openIcon) {
		this.treeNodeID = treeNodeID;
		this.treeNodeName = treeNodeName;
		this.parentTreeNodeID = parentTreeNodeID;
		this.icon = icon;
		this.openIcon = openIcon;
	}

	public TreeNode(String treeNodeID, String treeNodeName,
			String parentTreeNodeID, String multiple, boolean isSelected) {
		this.treeNodeID = treeNodeID;
		this.treeNodeName = treeNodeName;
		this.parentTreeNodeID = parentTreeNodeID;
		this.multiple = multiple;
		this.isSelected = isSelected;
	}

	public String getParentTreeNodeID() {
		return parentTreeNodeID;
	}

	public String getTreeNodeID() {
		return treeNodeID;
	}

	public String getTreeNodeName() {
		return treeNodeName;
	}

	public void setTreeNodeName(String treeNodeName) {
		this.treeNodeName = treeNodeName;
	}

	public void setTreeNodeID(String treeNodeID) {
		this.treeNodeID = treeNodeID;
	}

	public void setParentTreeNodeID(String parentTreeNodeID) {
		this.parentTreeNodeID = parentTreeNodeID;
	}

	public String getTreeNodeURL() {
		return treeNodeURL;
	}

	public void setTreeNodeURL(String treeNodeURL) {
		this.treeNodeURL = treeNodeURL;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * @return
	 */
	public String getMultiple() {
		return multiple;
	}

	/**
	 * @param string
	 */
	public void setMultiple(String string) {
		multiple = string;
	}

	/**
	 * @return
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @return
	 */
	public String getOpenIcon() {
		return openIcon;
	}

	/**
	 * @param string
	 */
	public void setIcon(String string) {
		icon = string;
	}

	/**
	 * @param string
	 */
	public void setOpenIcon(String string) {
		openIcon = string;
	}

	public String getTreeNodeCode() {
		return treeNodeCode;
	}

	public void setTreeNodeCode(String treeNodeCode) {
		this.treeNodeCode = treeNodeCode;
	}
}
