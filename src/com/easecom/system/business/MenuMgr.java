/**
 * @(#)$CurrentFile
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

package com.easecom.system.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.Tool;
import com.easecom.common.util.TreeMaker;
import com.easecom.common.util.TreeNode;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysFunction;

public class MenuMgr {

	private static final Logger logger = Logger.getLogger(MenuMgr.class);

	public MenuMgr() {
		super();
	}

	/**
	 * 生成菜单脚本 session：请求的session list：返回的菜单栏名称列表 ： 
	 * 
	 * @param httpSession
	 * @param list
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getMenus(HttpSession httpSession, List list)
			throws SystemException, Exception {
		String str = "<script type=\"text/javascript\" language=\"javascript\">";
		SessionContainer sessionContainer = (SessionContainer) httpSession
				.getAttribute("SessionContainer");
		try {
			Session session = HibernateSessionFactory.openSession();
			List funIds = new SysUserMgr().getFunction(sessionContainer.getUserId());// 功能ID列表
			Iterator it = funIds.iterator();
			str += "\nvar menu = new Array();";
			str += "\nvar menuText = new Array();";
			str += "\nvar childMenu = new Array();";
			str += "\nvar childMenuText;";
			while (it.hasNext()) {

				String id = (String) it.next();// 功能id
				SysFunction po = (SysFunction) session.load(SysFunction.class, id);
				String name = po.getName();
				String parentid = po.getSysFunction().getId();

				// if (!po.getIsvalid().equalsIgnoreCase("1")) continue;
				if (!po.getIsview().equalsIgnoreCase("1"))
					continue;

				// 第一级菜单（目录）
				if (parentid.equalsIgnoreCase("FFFFFF")
						&& po.getIsdir().equalsIgnoreCase("1")) {
					str += "\n menuText = new Array();";
					str += "\n menuText.push('" + id + "');";
					str += "\n menuText.push('" + name + "');";
					str += "\n menu.push(menuText);";
				} else {
					if (po.getIsdir().equalsIgnoreCase("1")) {// 如果是目录
						str += "\n childMenuText = new Array();";
						str += "\n childMenuText.push('" + id + "');";
						str += "\n childMenuText.push('" + po.getName() + "');";
						str += "\n childMenuText.push('" + parentid + "');";
						str += "\n childMenu.push(childMenuText);";
					} else {

						// 不是目录
						// 处理URL,在URL后加上功能ID参数
						String url = po.getUrl();
						String temp = "";
						if (url != null && !url.equalsIgnoreCase("")) {
							if (url.indexOf("?") > 0)
								temp = url + "&funid=" + id;
							else
								temp = url + "?funid=" + id;
						}
						str += "\n childMenuText = new Array();";
						str += "\n childMenuText.push('" + id + "');";
						str += "\n childMenuText.push('" + po.getName() + "');";
						str += "\n childMenuText.push('" + parentid + "');";
						str += "\n childMenuText.push('" + temp + "');";
						str += "\n childMenu.push(childMenuText);";
					}
				}
			}
			str += "\n</script>";
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return str.toString();
	}
	
	/**
	 * 生成菜单脚本 session：请求的session list：返回的菜单栏名称列表 ： 侯洪涛
	 * 新界面皮肤左侧导航
	 * @param httpSession
	 * @param path
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getNewMenus(String path,HttpSession httpSession,String pid )
			throws SystemException, Exception {
		StringBuffer str_SB = new StringBuffer();
		SessionContainer sessionContainer = (SessionContainer) httpSession
		.getAttribute("SessionContainer");
		if(null == pid ||"".equals(pid)){
			return "";
		}
		try {
			Session session = HibernateSessionFactory.openSession();
			String CloseLeft = " onclick=\"javascript:window.parent.opeLeft();\"";
				//首先判断是否一级目录，
				
				String id = pid;// 功能id
				SysFunction po = (SysFunction) session.load(SysFunction.class, id);
				String parentid = po.getSysFunction().getId(); 
				// 第一级菜单（目录）
				if (parentid.equalsIgnoreCase("FFFFFF")
						&& po.getIsdir().equalsIgnoreCase("1")){
					str_SB.append("<dt><a><h3>"+po.getName()+"</h3></a></dt>");
			//		str_SB.append("<div class=\"menuheader expandable\"><span class=\""+po.getIcon()+"\"></span>"+po.getName()+"</div>");
					//然后把子目录遍历出来
					if(sessionContainer==null)
						sessionContainer = new SessionContainer();
					List<SysFunction> funIdsForSon = new SysUserMgr().getSonFunction(sessionContainer.getUserId(), id);// 功能ID列表
			//		str_SB.append("<dd>");
					for(SysFunction item:funIdsForSon){
						String id_son = (String) item.getId();// 功能id
						SysFunction po_son = (SysFunction) session.load(SysFunction.class, id_son);
						if(po.getId().equals(po_son.getSysFunction().getId())){
							if (po_son!= null){// && po_son.getUrl() != null && !"".equals(po_son.getUrl().trim())
								//	str_SB.append("<li><a href=\""+path+po_son.getUrl()+"\" target=\"frmright\" "+("1".equals(po_son.getIsvalid())?CloseLeft:"")+"><span class=\"text_slice\">"+po_son.getName()+"</span></a></li>");
									str_SB.append("<dd><a href=\""+path+po_son.getUrl()+"\" target=\"frmright\" "+("1".equals(po_son.getIsvalid())?CloseLeft:"")+">"+po_son.getName()+"</a></dd>");							
							}
						}
					}
				//	str_SB.append("</dd>");
				} else {
					 //不是一级菜单，放在子循环里面做
				}
			 
			
			str_SB.append("");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return str_SB.toString();
	}
	
	/**
	 * 生成菜单脚本 session：请求的session list：返回的菜单栏名称列表 ： 
	 * 新界面皮肤左侧导航
	 * @param httpSession
	 * @param path
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getNewLeftMenu(String path,HttpSession httpSession,String pid )
			throws SystemException, Exception {
		StringBuffer str_SB = new StringBuffer();
		SessionContainer sessionContainer = (SessionContainer) httpSession
		.getAttribute("SessionContainer");
		if(null == pid ||"".equals(pid)){
			return "";
		}
		try {
			Session session = HibernateSessionFactory.openSession();
			String CloseLeft = " onclick=\"javascript:window.parent.opeLeft();\"";
				//首先判断是否一级目录，
				String id = pid;// 功能id
				SysFunction po = (SysFunction) session.load(SysFunction.class, id);
				String parentid = po.getSysFunction().getId(); 
				// 第一级菜单（目录）
				if (parentid.equalsIgnoreCase("FFFFFF")
						&& po.getIsdir().equalsIgnoreCase("1")){
					str_SB.append("<div class=\"menuheader expandable\"><span class=\""+po.getIcon()+"\"></span>"+po.getName()+"</div>");
					//然后把子目录遍历出来
					List<SysFunction> funIdsForSon = new SysUserMgr().getSonFunction(sessionContainer.getUserId(), id);// 功能ID列表
					str_SB.append("<ul class=\"categoryitems\">");
					for(SysFunction item:funIdsForSon){
						String id_son = (String) item.getId();// 功能id
						SysFunction po_son = (SysFunction) session.load(SysFunction.class, id_son);
						if(po.getId().equals(po_son.getSysFunction().getId())){
									if (po_son!= null){// && po_son.getUrl() != null && !"".equals(po_son.getUrl().trim())
									str_SB.append("<li><a href=\""+path+po_son.getUrl()+"\" target=\"frmright\" "+("1".equals(po_son.getIsvalid())?CloseLeft:"")+"><span class=\"text_slice\">"+po_son.getName()+"</span></a></li>");
							}
						}
					}
					str_SB.append("</ul>");
				} else {
					 //不是一级菜单，放在子循环里面做
				}
			str_SB.append("");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		System.out.println(str_SB.toString());
		return str_SB.toString();
	}
	
	/**
	 * 
	 * @Description: 获取分类管理树
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return  
	 * @author:  
	 * @date Mar 8, 2012 4:30:06 PM
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	public static String  classTreeList(String path,HttpSession httpSession,String functionId) {
		StringBuffer tree = new StringBuffer();
		try {
			SessionContainer sessionContainer = (SessionContainer) httpSession.getAttribute("SessionContainer");
			List funIds = sessionContainer.getFunIds();// 功能树实体类列表
			List treelist = null;
			treelist =getSysClassTree(funIds,functionId,path);
			String treeurl  = "left_tree.jsp?funid=" + functionId;
			TreeMaker treeMaker = new TreeMaker();
	        tree=treeMaker.TreeRootInit3(functionId,"操作菜单",path+"/style/normal/images/xtree_foldericon.gif",path+"/style/normal/images/xtree_openfoldericon.gif",treeurl,"frmleft");
			tree = treeMaker.TreeListApp(treelist,path+"/style/normal/images/xtree_foldericon.gif",path+"/style/normal/images/xtree_openfoldericon.gif","","frmright");
		} catch (Exception ex) {
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取菜单列表树时出错", "返回",
					"javascript:window.history.back()");
		}
		return tree.toString();
	}
	/**
	 * 
	 * @Description: 获取树根节点名称
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return  
	 * @author: 窦恩虎
	 * @date Mar 8, 2012 4:30:06 PM
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	public static String  TreeName(HttpSession httpSession,String functionId) {
		StringBuffer tree = new StringBuffer();
		try {
			SessionContainer sessionContainer = (SessionContainer) httpSession.getAttribute("SessionContainer");
			List funIds = sessionContainer.getFunIds();// 功能树实体类列表
			Iterator <SysFunction>tempArray = funIds.iterator();
			while (tempArray.hasNext()){
				SysFunction sf = tempArray.next();
				if(functionId.equals(sf.getId())){
					tree.append(sf.getName());
				}
			}
		} catch (Exception ex) {
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取菜单列表树时出错", "返回",
					"javascript:window.history.back()");
		}
		return tree.toString();
	}
	
	
	
	/**
	 * 
	 * @Description: 生成分类管理树
	 * @param parentId
	 * @param SysClass 文档管理、 知识库分类 、法律法规分类的根节点对象
	 * @return
	 * @throws SystemException
	 * @throws Exception  
	 * @author: 窦恩虎
	 * @date Mar 8, 2012 3:51:46 PM
	 */
	@SuppressWarnings({ "nls", "unchecked", "unchecked" })
	public static  List getSysClassTree(List list,String parentId,String path)
			throws SystemException, Exception {
		List <TreeNode>treeNodes = new ArrayList<TreeNode>();
		try {
			//现在出现的是全部菜单，需要对菜单进行过滤
			List listok = new ArrayList();
			listok = getRecursionList(list,parentId);
			//过滤完毕，目前是本菜单下面的全部功能
			Iterator <SysFunction>tempArray = listok.iterator();
			SysFunction sf = null;
			TreeNode treeNode = null;
			while (tempArray.hasNext()){
				sf = (SysFunction) tempArray.next();
				treeNode = new TreeNode(sf.getId(), sf.getName(), sf
						.getSysFunction().getId().equals(parentId) ? "" :  sf
								.getSysFunction().getId());
				if("1".equals(sf.getIsdir())){
					treeNode.setTreeNodeURL(null);
				}else{
					treeNode.setTreeNodeURL(path+sf.getUrl());
				}
				treeNodes.add(treeNode);
			}
			return treeNodes;
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
		}
	}
	
	
	/**
	 * 生成菜单树脚本,获得该节点下面的全部节点树
	 * 新界面皮肤左侧树导航
	 * @author Hou hongtao
	 * @time 2012-6-12
	 * @param httpSession
	 * @param path
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static List getRecursionList(List list ,String fatherId)
			throws SystemException, Exception {
		List listok = new ArrayList();
		try {
			List listcheck = list;
			Iterator<SysFunction> it_check = listcheck.iterator();
			Iterator<SysFunction> it = list.iterator();
			while (it.hasNext()){
				SysFunction po = it.next();
					//把一级子节点都循环出来
					if(fatherId.equals(po.getSysFunction().getId())){
						//继续判断是否有子节点,有的话遍历出来
						String poid = po.getId();
						boolean isHaveSon = false;
						String sons = Tool.getValue("select  count(id)  from sys_function t   where  t.parent_id = '"+poid+"'");
						if (Integer.parseInt(sons)>0)
							isHaveSon = true;
						if(isHaveSon){
							po.setUrl(null);
							listok.add(po);
						}else{
							listok.add(po);
						}
						if(isHaveSon){
							listok.addAll(getRecursionList(list,poid));
						}
					}
				 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} finally {
		}
		return listok;
	}
	
	 

	/**
	 * 根据菜单id，获得友情提示的文字说明
	 * @param httpSession
	 * @param menuid
	 * @return 页面调用:
	 * 1.引入类<%@ page import="com.easecom.system.business.MenuMgr" %>
	 * 2.拷贝并修改menuid  <div class="diverror">友情提示:</br><% out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%></div>
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getMenuRemark(String menuId)
			throws SystemException, Exception {
		StringBuffer str_SB = new StringBuffer();
		 
		if(null == menuId ||"".equals(menuId)){
			return "";
		}
		try {
			Session session = HibernateSessionFactory.openSession();
				SysFunction po = (SysFunction) session.load(SysFunction.class, menuId);
				if (null != po) 
					str_SB.append(po.getRemark());
				
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return str_SB.toString();
	}
	
	
	
	/**
	 * 根据菜单id，获得所在位置
	 * @param httpSession
	 * @param menuid
	 * @return 页面调用:
	 * 1.引入类<%@ page import="com.easecom.system.business.MenuMgr" %>
	 * 2.拷贝并修改menuid     
	 * <div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：<% out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%></span>
				</div>
			</div>
		</div>
	</div>
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getMenuNavigation(String menuId)
			throws SystemException, Exception {
		StringBuffer str_SB = new StringBuffer();
		 
		if(null == menuId ||"".equals(menuId)){
			return "";
		}
		try {
			Session session = HibernateSessionFactory.openSession();
				SysFunction po = (SysFunction) session.load(SysFunction.class, menuId);
				if (null != po) 
					str_SB.append(po.getFullname());
				
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return str_SB.toString();
	}
	
	
	/**
	 * 生成菜单脚本 session：请求的session list：返回的菜单栏名称列表 ： 侯洪涛
	 * 新界面皮肤左侧导航
	 * @param httpSession
	 * @param path
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String get3_3Menus(String path,HttpSession httpSession )
			throws SystemException, Exception {
		StringBuffer str_SB = new StringBuffer();
		SessionContainer sessionContainer = (SessionContainer) httpSession
		.getAttribute("SessionContainer");
		if(null == sessionContainer.getUserId() ||"".equals(sessionContainer.getUserId())){
			return "";
		}
		try {
			Session session = HibernateSessionFactory.openSession();
				//	String CloseLeft = " onclick=\"javascript:window.parent.opeLeft();\"";
			
//			str_SB.append("{ id:-1, parentId:0, name:\"专项讲解\",url:\""+path+"/business/training/explain.do?act=list\", target:\"frmright\",iconOpen:\""+path+"/libs/icons/tree_close.gif\",iconClose:\""+path+"/libs/icons/tree_open.gif\",icon:\"skin/titlebar_arrow.gif\"},");
//			str_SB.append("{ id:-2, parentId:0, name:\"题目纠错\",url:\""+path+"/business/training/feedback.do?act=list\", target:\"frmright\",iconOpen:\""+path+"/libs/icons/tree_close.gif\",iconClose:\""+path+"/libs/icons/tree_open.gif\",icon:\"skin/titlebar_arrow.gif\"},");
//			str_SB.append("{ id:-3, parentId:0, name:\"分析建议\",url:\""+path+"/business/training/suggestion.do?act=list\", target:\"frmright\",iconOpen:\""+path+"/libs/icons/tree_close.gif\",iconClose:\""+path+"/libs/icons/tree_open.gif\",icon:\"skin/titlebar_arrow.gif\"},");
//			str_SB.append("{ id:-3, parentId:0, name:\"2.0首页轮播图\",url:\""+path+"/System/SysConfig.do?act=list&type=INDEX_CAROUSEL2&rs=true&modular=imgs\", target:\"frmright\",iconOpen:\""+path+"/libs/icons/tree_close.gif\",iconClose:\""+path+"/libs/icons/tree_open.gif\",icon:\"skin/titlebar_arrow.gif\"},");
			
			//首先判断是否一级目录，
			//{ id:3002, parentId:0, name:"表格模板",icon:"skin/icons/icon_2.png",iconSkin:"diy01"},
			//{ id:201, parentId:3002, name:"页面中的grid表格",url:"../sample_skin/normal/grid-page.html", target:"frmright",iconOpen:"../libs/icons/tree_close.gif",iconClose:"../libs/icons/tree_open.gif",icon:"skin/titlebar_arrow.gif"},
			List<SysFunction> menus = sessionContainer.getFunIds();
			int i = 1 ;
			for(SysFunction f :menus){
				SysFunction po = (SysFunction) session.load(SysFunction.class, f.getId());
				String parentid = po.getSysFunction().getId(); 
				if("FFFFFF".equals(parentid)/*&& po.getIsdir().equalsIgnoreCase("1")*/){
					if("1".equals(po.getIsdir())) {	// 目录
						//第一级菜单（目录）
						str_SB.append("{ id:"+i+", parentId:0, name:\""+f.getName()+"\",icon:\"skin/icons/"+f.getIcon()+"\",iconSkin:\"diy01\"},");
						//然后把子目录遍历出来
						int j = 1000;
						for(SysFunction item:menus){
							String id_son = (String) item.getId();// 功能id
							SysFunction po_son = (SysFunction) session.load(SysFunction.class, id_son);
							if(po.getId().equals(po_son.getSysFunction().getId())){
								if (po_son!= null){
									str_SB.append("{ id:"+(j++)+", parentId:"+(i)+", name:\""+po_son.getName()+"\",url:\""+path+po_son.getUrl()+"\", target:\"frmright\",iconOpen:\""+path+"/libs/icons/tree_close.gif\",iconClose:\""+path+"/libs/icons/tree_open.gif\",icon:\"skin/titlebar_arrow.gif\"},");							
								}
							}
						}
					} else { // 菜单
						str_SB.append("{ id:"+i+", parentId:0, name:\""+f.getName()+"\",url:\""+path+f.getUrl()+"\", target:\"frmright\",icon:\"skin/icons/"+f.getIcon()+"\",iconSkin:\"diy01\"},");
					}
					//str_SB.append("{ id:3002, parentId:0, name:\"表格模板1\",icon:\"skin/icons/icon_2.png\",iconSkin:\"diy01\"}");
				}
				i++;
			}
			str_SB.append("{ id:3003, parentId:0, name:\"-\",icon:\"skin/icons/icon_00.png\",iconSkin:\"diy01\"}");
			
			  
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return str_SB.toString();
	}
	
	
}
