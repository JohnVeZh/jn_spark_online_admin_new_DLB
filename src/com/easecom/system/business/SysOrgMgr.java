/**
 * @(#)$CurrentFile
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

package com.easecom.system.business;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.struts.upload.FormFile;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.framework.struts.UploadCmd;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.Tool;
import com.easecom.common.util.TreeNode;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysOrg;
import com.easecom.system.web.SysOrgForm;

@SuppressWarnings("unchecked")
public class SysOrgMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(SysOrgMgr.class);
	public static String orgId = "";
	public static String orgName = "";

	public SysOrgMgr() {
		super();
	}

	/**
	 * 判断id为parentId的组织下是否已存在组织名orgName,组织代码orgCode
	 * 
	 * @param parentid
	 * @param orgName
	 * @param orgCode
	 * @return
	 */
	public boolean isOrgExist(String parentid, String orgName, String orgCode) {
		Session session = HibernateSessionFactory.openSession();
		String hql = "from SysOrg as org where org.sysOrg.id = '" + parentid
				+ "'  and (org.name = '" + orgName + "' or org.code = '" + orgCode
				+ "') ";
		try {
			Query query = session.createQuery(hql);
			List list = query.list();
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return false;
	}

	public boolean isOrgExistupdate(String parentid, String orgName,
			String orgCode) {
		Session session = HibernateSessionFactory.openSession();
		String hql = "select count(org.id) from SysOrg as org where org.sysOrg.id = '"
				+ parentid
				+ "'  and (org.name = '"
				+ orgName
				+ "' or org.code = '"
				+ orgCode + "') and org.isvalid='1' ";
		try {
			Query query = session.createQuery(hql);
			List list = query.list();
			int s = Integer.parseInt((list.get(0).toString()));
			if (list != null && s == 1)
				return false;
			else if (list != null && s > 1)
				return true;
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return false;
	}

	/**
	 * 添加
	 * 
	 * @param vo
	 *          要添加的对象，类型要根据具体来定义
	 * @throws Exception
	 *           要抛出的异常，可定义多个
	 */
	public void add(SysOrgForm vo) throws SystemException, Exception {
		Transaction tx = null;
		try {
			String path = getOrgPathName(vo.getParentid()) + vo.getName();
			try {
				Session session = HibernateSessionFactory.openSession();
				tx = session.beginTransaction();
				SysOrg po = new SysOrg();
				super.copyProperties(po, vo);
				po.setFullname(path);
				SysOrg parent = (SysOrg) session.load(SysOrg.class, vo.getParentid());
				po.setSysOrg(parent);

				// 侯福庆04.21日进行修改，如果父部门的类型为特殊部门，则添加的部门也为特殊部门；
				// 如果父部门的类型为一般部门，则添加的部门类型任意；
				if ("0".equals(parent.getType())) {
					po.setType(vo.getType());
				} else if ("1".equals(parent.getType())) {
					po.setType("1");
				}

				po.setIsvalid("1");
				session.save(po);
				tx.commit();
			} catch (Exception e) {
				logger.error(e);
				try {
					if (tx != null)
						tx.rollback();
				} catch (Exception ex) {
					logger.error(e);
				}
				throw new SystemException(e.getMessage(), e);
			} finally {
				try {
					HibernateSessionFactory.closeSession();
				} catch (Exception ex) {
					logger.error(ex);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException(e.getMessage(), e);
		}
	}

	/**
	 * 判断id为parentId的组织下是否已存在组织名orgName,组织代码orgCode
	 * 
	 * @param vo
	 *          要添加的对象，类型要根据具体来定义
	 * @throws Exception
	 *           要抛出的异常，可定义多个
	 */
	public void update(SysOrgForm vo) throws SystemException, Exception {
		Transaction tx = null;
		try {
			if (isOrgExistupdate(vo.getParentid(), vo.getName(), vo.getCode()))
				throw new Exception("名称为" + vo.getName() + "或编码为" + vo.getCode()
						+ "的组织已经存在!");
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();

			// 加载SysOrg对象
			SysOrg po = (SysOrg) session.load(SysOrg.class, vo.getId());
			po.setRemark(vo.getRemark());
			po.setFullname(vo.getFullname());
			po.setName(vo.getName());
			po.setSort(vo.getSort());
			po.setType(vo.getType());

			if (vo.getType().equals("1")) {
				String hq = "update SysOrg as org set org.type=1 where org.sysOrg.id = '"
						+ vo.getId() + "'";
				session.createQuery(hq).executeUpdate();
			}
			session.save(po);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(e);
			}
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 删除
	 * 
	 * @param IDs
	 * @throws SystemException
	 * @throws Exception
	 */
	public void delete(String[] ids) throws SystemException, Exception {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			SysOrg po = null;

			// 删除对象
			for (int i = 0; i < ids.length; i++) {
				po = (SysOrg) session.load(SysOrg.class, ids[i]);
				String hq = "update SysOrg as org set org.isvalid=0 where org.code like '"
						+ po.getCode() + "%'";
				session.createQuery(hq).executeUpdate();
			}
			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(e);
			}
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}
	
	/**
	 * 删除
	 * 
	 * @param IDs
	 * @throws SystemException
	 * @throws Exception
	 */
	public void deleteByIds(String ids) throws SystemException, Exception {
		Transaction tx = null;
		try {
//			Session session = HibernateSessionFactory.openSession();
//			tx = session.beginTransaction();
//			SysOrg po = null;

			// 删除对象
//			for (int i = 0; i < ids.length; i++) {
//				po = (SysOrg) session.load(SysOrg.class, ids[i]);
				
				String sql = "update sys_org as org set org.isvalid=0 where org.id in ("+ ids+")";
				Tool.execute(sql);
//			}
//			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(e);
			}
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 查看详细信息
	 * 
	 * @param IDs
	 * @throws SystemException
	 * @throws Exception
	 */
	public SysOrgForm view(String id) throws SystemException, Exception {
		try {

			Session session = HibernateSessionFactory.openSession();

			// 根据id加载SysOrg对象
			SysOrg po = (SysOrg) session.load(SysOrg.class, id);

			// 创建OrgActionForm
			SysOrgForm form = new SysOrgForm();
			super.copyProperties(form, po);

			form.setOldname(po.getName());

			if (!id.equalsIgnoreCase("FFFFFF")) {
				form.setParentid(po.getSysOrg().getId());
				form.setParentcode(po.getSysOrg().getCode());
				form.setParentname(po.getSysOrg().getName());
				form.setParenttype(po.getSysOrg().getType());
				form.setType(po.getType());
			} else {
				form.setParentid("");
				form.setParentcode("");
				form.setParenttype("");
			}
			return form;
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("获取组织信息出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 列表
	 * 
	 * @param queryConds
	 *          查询条件
	 * @param currentPageInt
	 *          当前页码
	 * @param itemsInPage
	 *          每页的记录数
	 * @param action
	 *          页面行为
	 * @param jumpPage
	 *          跳页的目标页码
	 * @return 返回符合条件的列表对象
	 * @throws SystemException
	 * @throws Exception
	 */
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {

			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq = "from SysOrg as org where 1=1 "
					+ QueryHelper.toAndQuery(queryConds)
					+ " and org.isvalid='1' order by org.code  ";
			String hql = "select count(org) from SysOrg as org where 1=1 and org.isvalid='1' "
					+ QueryHelper.toAndQuery(queryConds);
			Query qCount = ses.createQuery(hql);

			// 新建并设置列表容器
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString()
					: "0");
			lc.setTotalItem(totalItems); // 设置记录总数
			Query query = ses.createQuery(hq.toString());
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());
			lc.getList().addAll(query.list());// 装填指定页的列表数据到列表容器
			return lc;
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("组织列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
	}

	/**
	 * 生成组织树
	 * 
	 * @param parentId
	 * @param orgId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public List getOrgtree(String parentId, String orgId) throws SystemException,
			Exception {
		List list = null;
		List treeNodes = new ArrayList();
		try {
			Session ses = HibernateSessionFactory.openSession();
			SysOrg org1 = (SysOrg) ses.load(SysOrg.class, orgId);
			String queryString = "from SysOrg as org where org.id not in ('"
					+ parentId + "','" + orgId + "'" + ") and org.code like '"
					+ org1.getCode() + "%' and org.isvalid=1 order by   org.code  asc";
			Query query = ses.createQuery(queryString);
			if (query == null) {
				list = new ArrayList();
			} else {
				list = query.list();
			}
			Iterator tempArray = list.iterator();
			SysOrg org = null;
			TreeNode treeNode = null;
			if (!org1.getId().equalsIgnoreCase("FFFFFF")) {
				treeNode = new TreeNode(org1.getId(), org1.getName(), "");
				treeNode
				.setTreeNodeURL("sysOrg.do?act=list&parentid="
						+ org1.getId());
				treeNodes.add(treeNode);
				if (!org1.getSysOrg().getId().equalsIgnoreCase("FFFFFF")) {
					parentId = org1.getSysOrg().getId();
				}
			}
			while (tempArray.hasNext()) {
				org = (SysOrg) tempArray.next();
				treeNode = new TreeNode(org.getId(), org.getName(), org.getSysOrg()
						.getId().equals(parentId) ? "" : org.getSysOrg().getId());
				
				treeNode
				.setTreeNodeURL("sysOrg.do?act=list&parentid="
						+ org.getId());
				treeNodes.add(treeNode);
			}
			return treeNodes;
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 根据ID得到其编码
	 * 
	 * @param id
	 * @return
	 */
	public String getCodeById(String id) {
		return Tool.getValue("select code from sys_org where id='" + id + "'");
	}

	/**
	 * 根据ID得到其部门类型
	 * 
	 * @param id
	 * @return
	 */
	public String getTypeById(String id) {
		return Tool.getValue("select type from sys_org where id='" + id + "'");
	}

	/**
	 * 根据父ID生成编码,每一级四位编码
	 * 
	 * @param parentid
	 * @return
	 */
	public String getNewCodeByparentid(String parentid) {
		String code = getCodeById(parentid);
		String temp = Tool
				.getValue("select max(code) from sys_org where parent_id='" + parentid
						+ "'");
		if (temp.equalsIgnoreCase("") || temp == null)
			temp = "0000";
		temp = temp.substring(temp.length() - 4);
		int i = Integer.parseInt(temp) + 1;
		temp = String.valueOf(i);
		if (temp.length() == 1)
			code += "000" + temp;
		else {
			if (temp.length() == 2)
				code += "00" + temp;
			else {
				if (temp.length() == 3)
					code += "0" + temp;
				else {
					code = temp;
				}
			}
		}
		return code;
	}

	/**
	 * 根据父ID生成排序值
	 * 
	 * @param parentid
	 * @return
	 */
	public int getNewSortByParentid(String parentid) {
		return Tool.getIntValue("select max(sort) from sys_org where parent_id='"
				+ parentid + "'") + 1;
	}

	/**
	 * 得到组织的全名
	 * 
	 * @param parentid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public String getOrgPathName(String parentid) throws SystemException,
			Exception {
		String name = "";
		try {
			Session session = HibernateSessionFactory.openSession();
			String idx = parentid;
			while (!idx.equalsIgnoreCase("FFFFFF")) {
				SysOrg po = (SysOrg) session.load(SysOrg.class, idx);
				name = po.getName() + "-" + name;
				idx = po.getSysOrg().getId();
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("得到组织全名出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
		return name;
	}

	/**
	 * 单个文件上传
	 * 
	 * @param ff
	 *          文件的名字
	 * @param folderPath
	 *          上传路径
	 * @return 包含上传路径和上传文件名字的对象UploadCmd
	 * @throws Exception
	 */
	public UploadCmd logoupload(FormFile ff, String folderPath) throws Exception {
		UploadCmd uploadCmd = new UploadCmd();
		try {
			File file = new File(folderPath);

			// 判断目录是否存在
			if (!file.exists()) { // 不存在创建
				file.mkdirs();
			}
			InputStream io = null;
			OutputStream os = null;
			String filename = ff.getFileName();
			if (!filename.equalsIgnoreCase("")) {
				io = ff.getInputStream();
				String filenameExt = filename.substring(filename.lastIndexOf("."))
						.toLowerCase();

				// 给图片重命名
				String newFilename = "_" + System.currentTimeMillis() + filenameExt;
				uploadCmd.setTitle(newFilename);
				uploadCmd.setFullPath(folderPath + newFilename);
				os = new FileOutputStream(folderPath + newFilename);
				int readByte = 0;
				byte buf[] = new byte[8129];
				while ((readByte = io.read(buf, 0, 8129)) != -1) {
					os.write(buf, 0, readByte);
				}
			}
			os.close();
			io.close();
			ff.destroy();
		} catch (Exception e) {
			logger.error(e.toString());

		}
		return uploadCmd;
	}

	/**
	 * 保存logo
	 * 
	 * @param form
	 * @throws SystemException
	 * @throws Exception
	 */
	public void logosave(SysOrgForm form) throws SystemException, Exception {
		Transaction tx = null;
		try {
			try {
				Session session = HibernateSessionFactory.openSession();
				tx = session.beginTransaction();

				SysOrg po = (SysOrg) session.load(SysOrg.class, form.getId());
				po.setLogoPath(form.getFileName());
				session.save(po);
				tx.commit();
			} catch (Exception e) {
				logger.error(e);
				try {
					if (tx != null)
						tx.rollback();
				} catch (Exception ex) {
					logger.error(e);
				}
				throw new SystemException(e.getMessage(), e);
			} finally {
				try {
					HibernateSessionFactory.closeSession();
				} catch (Exception ex) {
					logger.error(ex);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException(e.getMessage(), e);
		}
	}

	/**
	 * 显示logo列表
	 * 
	 * @param orgid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public ListContainer logolist(String orgid) throws SystemException, Exception {
		try {

			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			String hq = "";
			if ("FFFFFF".equals(orgid)) {
				hq = "from SysOrg as org where  org.id='" + orgid
						+ "' or org.sysOrg.id='" + orgid + "' order by org.id desc";
			} else {
				hq = "from SysOrg as org where 1=1 and org.id='" + orgid + "'";
			}

			// 新建并设置列表容器
			ListContainer lc = new ListContainer();
			Query query = ses.createQuery(hq.toString());
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());
			lc.getList().addAll(query.list());// 装填指定页的列表数据到列表容器
			return lc;
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("logo列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
	}

	/**
	 * 加载指定的SysOrg对象
	 * 
	 * @param orgid
	 * @return
	 */
	public SysOrg load(String orgid) {
		Session session = HibernateSessionFactory.openSession();
		try {
			return (SysOrg) session.get(SysOrg.class, orgid);
		} catch (HibernateException he) {
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 判断上传图片的大小是否符合要求
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public boolean panDuanWH(FormFile file) throws FileNotFoundException,
			IOException {

		boolean flag = true;
		InputStream io = file.getInputStream();
		BufferedImage sourceImg = ImageIO.read(io);
		int width = sourceImg.getWidth();
		int height = sourceImg.getHeight();
		if (width < 900 || width > 1100) {
			return false;
		}
		if (height < 110 || height > 130) {
			return false;
		}

		io.close();
		return flag;
	}

}