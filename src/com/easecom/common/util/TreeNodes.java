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

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 *树状结构模型
 */
public class TreeNodes implements Serializable {
	/*父节点*/
	private TreeNodes parentTreeNode;
    
    private Set sonTreeNodes = new TreeSet();
    
    private Object treeNode;

	/*树节点超连接*/
	private String treeNodeURL;

	/*节点是否为目录标识*/
	private boolean folder = false;
    
    /** 是否被选中 */
    private boolean isChoose = false;
    
    /** 是否最后一级的节点 */
    private boolean isLast = true;
    
    /** 是否根节点 */
    private boolean isRoot = false;

	public TreeNodes() {
        super();
	}
    
    public TreeNodes(Object o) {
        this.treeNode = o;
    }
  
    public TreeNodes getParent() {
        return this.parentTreeNode;
    }
    
    public void setParent(TreeNodes parentTreeNode) {
        this.parentTreeNode = parentTreeNode;
        Set set = this.parentTreeNode.getSons();
        set.add(this);
        this.parentTreeNode.setSons(set);
    }
    
    public Set getSons() {
        return this.sonTreeNodes;
    }
    
    public void setSons(Set sons) {
        this.sonTreeNodes = sons;
    }
    
    public void setNode(Object node) {
        this.treeNode = node;
    }
    
    public Object getNode() {
        return this.treeNode;
    }
    
    public void setIsChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }
    
    public boolean getIsChoose() {
        return this.isChoose;
    }
    
    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }
    
    public boolean getIsRoot() {
        return this.isRoot;
    }

	public boolean isFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}
}