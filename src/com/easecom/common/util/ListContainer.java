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


/*
 * 分页模型
 */
package com.easecom.common.util;
import java.util.ArrayList;
import java.util.List;


public class ListContainer {
    /**列表所有页的记录总数*/
    private int totalItem;
    /**每一页中所显示的记录数*/
    private int itemsInPage;
    /**列表的总页数*/
    private int totalPages;
    /**列表当前页码数*/
    private int currentPage;
    /**列表数据*/
    private List list;
    /**列表分页行为*/
    private PageAction pageAction;
    /**跳页时的目标页码数*/
    private int jumpIndex;
    /**每页第一个记录在总记录集中的位置索引号*/
    private int index;
    /**
     * 默认构造器，为以下参数设默认值
     *1.设置列表为空
     *2.设置每页显示记录数为15条
     *3.设置分页行为为去第一页
     *4.设置记录在记录集中的索引号为0
     */
    public ListContainer() {
        super();
        this.list=new ArrayList();
        this.itemsInPage=24;
        this.pageAction=PageAction.FIRST;
        this.index=0;
    }
    /**
     * 计算下一页的开始记录索引号
     * @return int
     * @throws Exception
     */
    public int calculateNextPageIndex()throws Exception{
        int result=PageAction.FIRST_INT;
        switch (pageAction.getValue()) {
        case PageAction.FIRST_INT:
            result=0;
            this.currentPage=1;
            break;
        case PageAction.CURRENT_INT:
            result=(this.currentPage-1)*this.itemsInPage;
            this.currentPage=this.currentPage;
            break;
        case PageAction.PREVIOUS_INT:
            result=(this.currentPage-2)*this.itemsInPage;
            this.currentPage=this.currentPage-1;
            break;
        case PageAction.NEXT_INT:
            result=this.currentPage*this.itemsInPage;
            this.currentPage=this.currentPage+1;
            break;
        case PageAction.LAST_INT:
            result=(this.totalPages-1)*this.itemsInPage;
            this.currentPage=this.totalPages;
            break;
        case PageAction.JUMP_INT:
            result=(jumpIndex-1)*this.itemsInPage;
            this.currentPage=jumpIndex;
            if(result<0)result=0;//20060711
            break;
        default:
            result=0;
            this.currentPage=1;
            break;
        }
        this.setIndex(result);
        return result;
    }
    /**
     * 根据总记录数和每页的记录数计算出总页数
     */
    private void calcuateTotalPage() {
       int result=1;
       if(this.totalItem <= this.itemsInPage){
           result=1;
       }else if(this.totalItem % this.itemsInPage==0){
           result=this.totalItem/this.itemsInPage;
       }else{
           result=this.totalItem/this.itemsInPage+1;
       }
       this.totalPages=result;
    }
    /**
     * 设置记录总数
     * @param totalItem
     */
    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
        this.calcuateTotalPage();
    }
    /**
     * 设置每页的记录数
     * @param itemsInPage
     */
    public void setItemsInPage(int itemsInPage) {
        this.itemsInPage = itemsInPage;
    }
    /**
     * 设置总页数
     * @param totalPages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    /**
     * 设置当前页码数
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    /**
     * 设置列表数据
     * @param list
     */
    public void setList(List list) {
        this.list = list;
    }
    /**
     * 设置分页行为
     * @param pageAction
     */
    public void setPageAction(PageAction pageAction) {
        this.pageAction = pageAction;
    }
    /**
     * 设置跳页目标页码
     * @param jumpIndex
     */
    public void setJumpIndex(int jumpIndex) {
        this.jumpIndex = jumpIndex;
    }
    /**
     * 获取总记录数
     * @return int
     */
    public int getTotalItem() {
        return totalItem;
    }
    /**
     * 获取每页的记录数
     * @return int
     */
    public int getItemsInPage() {
        return itemsInPage;
    }
    /**
     * 获取总页数
     * @return int
     */
    public int getTotalPages() {
        return totalPages;
    }
    /**
     * 获取当前页页码
     * @return int
     */
    public int getCurrentPage() {
        return currentPage;
    }
    /**
     * 获取当前页的列表数据
     * @return　List
     */
    public List getList() {
        return list;
    }
    /**
     * 获取分页行为
     * @return　PageAction
     */
    public PageAction getPageAction() {
        return pageAction;
    }
    /**
     * 获取跳页的目标页页码
     * @return　int
     */
    public int getJumpIndex() {
        return jumpIndex;
    }
    /**
     * 获取当页第一条记录在总记录的索引号
     * @return int
     */
    public int getIndex() {
        return index;
    }
    /**
     * 设置当页第一条记录在总记录的索引号
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }
}


