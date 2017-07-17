package com.business.NetworkCourseTeacher;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.business.NetworkVideoTeaher.NetworkVideoTeaher;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

public class NetworkCourseTeacherMgr extends AbstractHibernateDAO {

	private static LogUtils logger = LogUtils.getLogger(NetworkVideoTeaher.class);
	
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
	
	public ListContainer list(Collection queryConds,int currentPageInt,int itemsInPage
			,String action,int jumpPage) throws SystemException,Exception{
		
		try{
			Session ses = HibernateSessionFactory.openSession(); 
			//查询对象
			String hq = "from NetworkCourseTeacher as NetworkCourseTeacher where 1=1"
					+QueryHelper.toAndQuery(queryConds)
					+"order by NetworkCourseTeacher.id";
			Query qCount = ses.createQuery("select count(id) from NetworkCourseTeacher as "
					+ "NetworkCourseTeacher where 1=1"+QueryHelper.toAndQuery(queryConds));
			// 新建并设置列表容器
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略
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
	 * 添加
	 * 
	 * @prama vo 要添加的对象，类型 根据具体的来定义
	 * @throws Exception
	 *           要抛出的异常 可定义多个
	 */
	public void add(NetworkCourseTeacherActionForm vo) throws SystemException,Exception{
		
		Transaction tx = null;
		try {
			NetworkCourseTeacher po = new NetworkCourseTeacher();
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			super.copyProperties(po, vo);
			session.save(po);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try{
				if(tx !=null){
					tx.rollback();
				}
			}catch(Exception ex){
				logger.error(ex);
			}
			throw new SystemException("新增出错!请检查输入数据。", e);
		}finally{
			try{
				HibernateSessionFactory.closeSession();
			}catch(Exception ex){
				logger.error(ex);
			}
		}
	}

	/**
	 * 根据id查询数据
	 * @parma id
	 * @throws SystemException
	 * @throws Exception
	 * @return form
	 */
	
	public NetworkCourseTeacherActionForm view(String id) throws SystemException,Exception {
		
		try {
			Session session = HibernateSessionFactory.openSession();
			NetworkCourseTeacher po = (NetworkCourseTeacher) session.load(NetworkCourseTeacher.class, id);
			NetworkCourseTeacherActionForm form = new NetworkCourseTeacherActionForm();
			super.copyProperties(form, po);
			return form;
		}catch (Exception e) {
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
	 * 更新操作
	 * @param vo
	 * @throws SystemException
	 */
	public void update(NetworkCourseTeacherActionForm vo) throws SystemException {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			NetworkCourseTeacher po = (NetworkCourseTeacher) session.load(NetworkCourseTeacher.class, vo.getId());
			po.setIntroduce(vo.getIntroduce());
			po.setLogo(vo.getLogo());
			po.setMoblie(vo.getMoblie());
			po.setName(vo.getName());
			po.setSex(vo.getSex());
			session.update(po);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				if(tx!=null){
					tx.rollback();
				}
			} catch (Exception ex) {
				logger.error(ex);
				throw new SystemException(e.getMessage(), e);
			}
		}finally{
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	public NetworkCourseTeacher deleteById(String id) throws SystemException {
		
		Transaction tx = null;
		try{
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			NetworkCourseTeacher po = (NetworkCourseTeacher) session.load(NetworkCourseTeacher.class, id);
			po.setIsDelete(1);
			session.update(po);
			tx.commit();
			return po;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			try{
				if(null != tx){
					tx.rollback();
				}
			}catch(Exception ex){
				logger.error(ex);
				throw new SystemException(e.getMessage(),e);
			}
		}finally{
			try{
				HibernateSessionFactory.closeSession();
			}catch(Exception ex){
				logger.error(ex);
			}
		}
		
		return null;
	}
}
