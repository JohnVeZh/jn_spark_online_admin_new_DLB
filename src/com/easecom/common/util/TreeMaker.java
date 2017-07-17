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
 * 树状结构工具类
 */

package com.easecom.common.util;

import java.util.*;

public class TreeMaker {

	StringBuffer outHTML;
	boolean parent;

	public TreeMaker() {
		outHTML = new StringBuffer();
		parent = true;
	}

	public StringBuffer TreeRootInit(String rootName) {
		return TreeRootInit("", rootName, "", "");
	}

	public StringBuffer TreeRootInit(String rootId, String rootName) {
		return TreeRootInit(rootId, rootName, "", "");
	}

	public StringBuffer TreeRootInit(String rootName, String Icon,
			String openIcon) {
		return TreeRootInit("", rootName, Icon, openIcon);
	}

	public StringBuffer TreeRootInit(String rootId, String rootName,
			String Icon, String openIcon) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node" + rootId + " = new WebFXTree('" + rootId
				+ "','" + rootName + "','','','" + Icon + "','" + openIcon
				+ "');\n");
		outHTML.append("node" + rootId + ".setBehavior('classic');\n");
		return outHTML;
	}
	public StringBuffer TreeRootInit4(String rootId, String rootName,
			String Icon, String openIcon) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node = new WebFXTree('" + rootId
				+ "','" + rootName + "','','','" + Icon + "','" + openIcon
				+ "');\n");
		outHTML.append("node.setBehavior('classic');\n");
		return outHTML;
	}
	public StringBuffer TreeRootInit(String rootId, String rootName,
			String Icon, String openIcon, String url, String target) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node = new WebFXTree2('" + rootId + "','"
				+ rootName + "','" + url + "\" target=\"" + target + "\"','','"
				+ Icon + "','" + openIcon + "');\n");
		outHTML.append("node.setBehavior('classic');\n");
		return outHTML;
	}

	public StringBuffer TreeRootInit2(String rootId, String rootName,
			String Icon, String openIcon, String url, String target) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node = new WebFXTree2(\"" + rootId + "\",\""
				+ rootName + "\",\"" + url + "\",\"\",\"" + Icon + "\",\""
				+ openIcon + "\");\n");
		outHTML.append("node.setBehavior('classic');\n");
		return outHTML;
	}

	public StringBuffer TreeRootInit3(String rootId, String rootName,
			String Icon, String openIcon, String url, String target) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node = new WebFXTree3('" + rootId + "','"
				+ rootName + "','" + url + "\" target=\"" + target + "\"','','"
				+ Icon + "','" + openIcon + "');\n");
		outHTML.append("node.setBehavior('classic');\n");
		return outHTML;
	}

	public StringBuffer TreeRootInit4(String rootId, String rootName,
			String Icon, String openIcon, String url) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node = new WebFXTree3(\"" + rootId + "\",\""
				+ rootName + "\",\"" + url + "\",\"" + Icon + "\",\""
				+ openIcon + "\");\n");
		outHTML.append("node.setBehavior('classic');\n");
		return outHTML;
	}

	public StringBuffer TreeListApp(Collection tree) {
		return TreeListApp(tree, "", "", "", "", "");
	}

	public StringBuffer TreeListApp(Collection tree, String multiple) {
		return TreeListApp(tree, multiple, "", "", "", "");
	}

	public StringBuffer TreeListApp(Collection tree, String Icon,
			String openIcon) {
		return TreeListApp(tree, "", Icon, openIcon, "", "");
	}

	public StringBuffer TreeListApp(Collection tree, String multiple,
			String Icon, String openIcon) {
		return TreeListApp(tree, multiple, Icon, openIcon, "", "");
	}

	public StringBuffer TreeListApp(Collection tree, String Icon,
			String openIcon, String action, String target) {
		return TreeListApp(tree, "", Icon, openIcon, action, target);
	}

	public StringBuffer TreeListApp2(Collection tree, String Icon,
			String openIcon, String action, String target) {
		return TreeListApp2(tree, "", Icon, openIcon, action, target);
	}

	public StringBuffer TreeListApp(Collection tree, String multiple,
			String Icon, String openIcon, String action, String target) {
		try {
			String multipleStr = "";
			String tempmul = "";
			multipleStr = getClassName(multiple);
			Iterator it = (tree == null ? null : tree.iterator());
			TreeNode treeNode = null;
			while (it != null && it.hasNext()) {
				treeNode = (TreeNode) it.next();
				String tempUrl = "";
				if (treeNode != null) {
					// 处理action的url
					if (isEmpty(action)) {
						tempUrl = treeNode.getTreeNodeURL() == null ? ""
								: treeNode.getTreeNodeURL() + "\" target=\""
										+ target + "\"";
					} else {// 如果最后一个字符是"＝"，则在＝号后加上父ID
						tempUrl = ((action.substring(action.length() - 1,
								action.length()).equals("=")) ? action
								+ treeNode.getTreeNodeID() : action)
								+ "\" target=\"" + target + "\"";
					}
					// 处理节点
					if (outHTML == null || outHTML.length() < 1) {// 如果根结点没有初始化的话，初始化根节点
						TreeRootInit(treeNode.getTreeNodeID(), treeNode
								.getTreeNodeName(), Icon, openIcon);
					} else { // 处理树节点
						if (isEmpty(multiple)) {
							multipleStr = getClassName(treeNode.getMultiple());
						}
						if (isEmpty(treeNode.getParentTreeNodeID())) {// 如果父节点的ID是空的话
							outHTML
									.append("var node"
											+ treeNode.getTreeNodeID()
											+ "= new "
											+ multipleStr
											+ "('"
											+ treeNode.getTreeNodeID()
											+ "','"
											+ treeNode.getTreeNodeName()
											+ "','"
											+ tempUrl
											+ "',"
											+ (multipleStr
													.equals("WebFXTreeItem") ? ""
													: (treeNode.isSelected() + ","))
											+ "'"
											+ treeNode.getParentTreeNodeID()
											+ "','"
											+ (isEmpty(treeNode.getIcon()) ? Icon
													: treeNode.getIcon())
											+ "','"
											+ (isEmpty(treeNode.getOpenIcon()) ? openIcon
													: treeNode.getOpenIcon())
											+ "');\n");
							outHTML.append("node.add(node"
									+ treeNode.getTreeNodeID() + ");\n");
						} else {
							outHTML
									.append("var node"
											+ treeNode.getTreeNodeID()
											+ "= new "
											+ multipleStr
											+ "('"
											+ treeNode.getTreeNodeID()
											+ "','"
											+ treeNode.getTreeNodeName()
											+ "','"
											+ tempUrl
											+ "',"
											+ (multipleStr
													.equals("WebFXTreeItem") ? ""
													: (treeNode.isSelected() + ","))
											+ "'"
											+ treeNode.getParentTreeNodeID()
											+ "','"
											+ (isEmpty(treeNode.getIcon()) ? Icon
													: treeNode.getIcon())
											+ "','"
											+ (isEmpty(treeNode.getOpenIcon()) ? openIcon
													: treeNode.getOpenIcon())
											+ "');\n");
							outHTML.append("node"
									+ treeNode.getParentTreeNodeID()
									+ ".add(node" + treeNode.getTreeNodeID()
									+ ");\n");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			return outHTML;
		}
	}

	public StringBuffer TreeListApp2(Collection tree, String multiple,
			String Icon, String openIcon, String action, String target) {
		try {
			String multipleStr = "";
			String tempmul = "";
			multipleStr = getClassName(multiple);
			Iterator it = (tree == null ? null : tree.iterator());
			TreeNode treeNode = null;
			while (it != null && it.hasNext()) {
				treeNode = (TreeNode) it.next();
				// url
				String tempUrl = "";
				if (treeNode != null) {
					if (isEmpty(action)) {
						tempUrl = treeNode.getTreeNodeURL() == null ? ""
								: treeNode.getTreeNodeURL();
					} else {
						tempUrl = ((action.substring(action.length() - 1,
								action.length()).equals("=")) ? action
								+ treeNode.getTreeNodeID() : action)
								+ "\" target=\"" + target + "\"";
					}
					if (outHTML == null || outHTML.length() < 1) {
						TreeRootInit(treeNode.getTreeNodeID(), treeNode
								.getTreeNodeName(), Icon, openIcon);
					} else {
						if (isEmpty(multiple)) {
							multipleStr = getClassName(treeNode.getMultiple());
						}
						if (isEmpty(treeNode.getParentTreeNodeID())) {
							outHTML
									.append("var node"
											+ treeNode.getTreeNodeID()
											+ "= new "
											+ multipleStr
											+ "(\""
											+ treeNode.getTreeNodeID()
											+ "\",\""
											+ treeNode.getTreeNodeName()
											+ "\",\""
											+ tempUrl
											+ "\","
											+ (multipleStr
													.equals("WebFXTreeItem") ? ""
													: (treeNode.isSelected() + ","))
											+ "\""
											+ treeNode.getParentTreeNodeID()
											+ "\",\""
											+ (isEmpty(treeNode.getIcon()) ? Icon
													: treeNode.getIcon())
											+ "\",\""
											+ (isEmpty(treeNode.getOpenIcon()) ? openIcon
													: treeNode.getOpenIcon())
											+ "\");\n");
							outHTML.append("node.add(node"
									+ treeNode.getTreeNodeID() + ");\n");
						} else {
							outHTML
									.append("var node"
											+ treeNode.getTreeNodeID()
											+ "= new "
											+ multipleStr
											+ "(\""
											+ treeNode.getTreeNodeID()
											+ "\",\""
											+ treeNode.getTreeNodeName()
											+ "\",\""
											+ tempUrl
											+ "\","
											+ (multipleStr
													.equals("WebFXTreeItem") ? ""
													: (treeNode.isSelected() + ","))
											+ "\""
											+ treeNode.getParentTreeNodeID()
											+ "\",\""
											+ (isEmpty(treeNode.getIcon()) ? Icon
													: treeNode.getIcon())
											+ "\",\""
											+ (isEmpty(treeNode.getOpenIcon()) ? openIcon
													: treeNode.getOpenIcon())
											+ "\");\n");
							outHTML.append("node"
									+ treeNode.getParentTreeNodeID()
									+ ".add(node" + treeNode.getTreeNodeID()
									+ ");\n");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			return outHTML;
		}
	}

	public String getClassName(String multiple) {
		String strClassName = "WebFXTreeItem";
		if (multiple != null && multiple.equalsIgnoreCase("c")) {
			// 复选项
			strClassName = "WebFXCheckBoxTreeItem";
		}
		if (multiple != null && multiple.equalsIgnoreCase("r")) {
			// 单选项
			strClassName = "WebFXRadioTreeItem";
		}
		return strClassName;
	}

	public boolean isEmpty(String str) {
		if (str == null || str.equals(""))
			return true;
		return false;
	}
}
