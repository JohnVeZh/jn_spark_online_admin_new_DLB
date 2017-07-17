package com.business.Users; 



import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.CodeGenerator;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.WebUtil;
import com.easecom.system.exception.SystemException;
import com.business.Users.Users;
import com.business.Users.UsersActionForm;


public class UsersMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(Users.class);
  

	/**
	 * 添加
	 * 
	 * @prama vo 要添加的对象，类型 根据具体的来定义
	 * @throws Exception
	 *           要抛出的异常 可定义多个
	 */
	public void add(UsersActionForm vo) throws SystemException, Exception {
		Transaction tx = null;
		 
		try {
			Users po = new Users();
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			super.copyProperties(po, vo);
			ses.save(po);	
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(ex);
			}
			throw new SystemException("新增出错!请检查输入数据。", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

    /**
	*更新
	*/
	public void update(UsersActionForm vo) throws SystemException, Exception {
		Transaction tx = null;
		try {
		
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			Users po = (Users) session.load(Users.class, vo.getId());
			super.copyProperties(po, vo);
			session.update(po);
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
	 * @prama IDs
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void delete(String[] IDs) throws SystemException, Exception {
		Transaction tx = null;
		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			Users po = null;
			for (int i = 0; i < IDs.length; i++) {
				po = (Users) ses.load(Users.class, IDs[i]);
				//List list = ses.createFilter(po.getUsers(), "").list();
				//if (list.size() < 1 && po.getSysRoleFunrights().size() < 1)// 没有引用
					ses.delete(po);
				//else
				//	throw new Exception("功能:" + po.getName() + "已经使用或存在下级功能或操作，不能删除!");
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(ex);
			}
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	/**
	 * 查看详细信息
	 * 
	 * @prama ID
	 * @throws SystemException
	 * @throws Exception
	 */
	public UsersActionForm view(String ID) throws SystemException, Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			Users po = (Users) ses.load(Users.class, ID);
			UsersActionForm form = new UsersActionForm();
			super.copyProperties(form, po);
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new SystemException("获取详情信息出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	/**
	 * 列表
	 * 
	 * @prama queryConds 查询条件
	 * @prama currentPageInt 当前页码
	 * @prama itemsInPage 每页的记录数
	 * @prama action 页面行为
	 * @prama jumpPage 跳页的目标页码
	 * @return 返回符合条件的列表对象
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq = "from Users as Users where 1=1 "
					+ QueryHelper.toAndQuery(queryConds)
					+ " order by Users.createTime desc   ";
			Query qCount = ses
					.createQuery("select count(id) from Users as Users where 1=1 "
							+ QueryHelper.toAndQuery(queryConds));

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
			throw new SystemException("查询数据列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}

/**
	 * 接口：列表
	 * @param queryConds
	 * @param mp
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 * @created 2015-1-19 下午5:43:06
	 */
	public List<Users> mlist(Collection queryConds,com.easecom.common.mobile.MobilePage mp) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			
			// 保存对象
			String hq = "from Users as Users where 1=1 "
					+ QueryHelper.toAndQuery(queryConds) + " order by Users.id desc";
			Query qCount = ses
					.createQuery("select count(id) from Users as Users where 1=1 "
							+ QueryHelper.toAndQuery(queryConds));
		
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString()
					: "0");
			mp.setTotalCount(totalItems); // 设置记录总数
			Query query = ses.createQuery(hq.toString());
			query.setFirstResult(mp.getStartRecord());
			query.setMaxResults(mp.getRowCountPerPage());
			return query.list();
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("接口列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
	}
	
	
	/**
	 * @Description: 上传
	 * @return Map<String,String>  
	 * @throws
	 * @author ty_sxy
	 * @date 2015年11月5日   filePath:子文件夹名字
	 */
	public Map<String, String> upload(HttpServletRequest request,String filePath)
			throws SystemException {
		Map<String, String> map = new HashMap<String, String>();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			String path = WebUtil.getWebRoot(request)
					+ "/"+filePath+"/";
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				Iterator<FileItem> iter = fileItems.iterator();
				List<String> url = new ArrayList<String>();

				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						// 如果是正常表单域
						String name = item.getFieldName();
						String value = null;
						value = new String(item.getString() != null ? item
								.getString().getBytes("ISO-8859-1")
								: new byte[0], "UTF-8");
						map.put(name, value);
					} else {
						String fileName = item.getName();
						if (StringUtils.isNotEmpty(fileName)) {
							File pathF = new File(path);
							// 如果不存在则创建
							if (!pathF.exists())
								pathF.mkdirs();
							if (fileName.lastIndexOf("\\") > 0
									&& (fileName.lastIndexOf("\\") + 1) < fileName
											.length()) {
								fileName = fileName.substring(fileName
										.lastIndexOf("\\") + 1);
							}
							String name="";
							if(!"".equals(FileUploadUtil.getFileType(fileName))&&null!=FileUploadUtil.getFileType(fileName)){
								name = CodeGenerator.getUUID() + "."
								+ FileUploadUtil.getFileType(fileName);
							}else{
								name= CodeGenerator.getUUID() + ".png";
							}
							String savepath = path + name;
							File saveFilepath = new File(savepath);
							item.write(saveFilepath);
							url.add( "/" +filePath+"/"
									+ name);
							String urlStr = FileUploadUtil.getStrFromList(url);
							map.put("url",urlStr);//多图一起返回
							map.put(item.getFieldName(), "/" +filePath+"/"
									+ name);
						}
					}
				}
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
				throw new SystemException(e.getMessage(), e);
			} catch (FileUploadException e) {
				logger.error(e.getMessage(), e);
				throw new SystemException(e.getMessage(), e);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new SystemException(e.getMessage(), e);
			}
		}

		return map;
	}
}
