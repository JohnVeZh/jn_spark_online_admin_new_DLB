package com.business.Dlb.Popup;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.*;
import com.easecom.system.exception.SystemException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by qiu on 2017/7/5.
 */
public class TbPopupMgr extends AbstractHibernateDAO {
    private static LogUtils logger = LogUtils.getLogger(TbPopupMgr.class);

    AbstractHibernateDAO dao = new AbstractHibernateDAO();

    /**
     * 分页列表查询
     * @param queryConds
     * @param currentPageInt
     * @param itemsInPage
     * @param action
     * @param jumpPage
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public ListContainer list(String beanName, Collection queryConds, int currentPageInt,
                              int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String hq = "from TbPopup where 1=1  " + QueryHelper.toAndQuery(queryConds) + " order by createDate desc";
            Query qCount = ses.createQuery("select count(*) from TbPopup where 1=1  "
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
    public long count(int module, String startTime, String endTime, String selfId) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
//            Session ses = HibernateSessionFactory.openSession();
//            Query qCount = ses.createQuery("select count(*) from TbPopup where 1=1 and status=1 and module="+module
//                    +"  and not (startTime> str_to_date('"+endTime+"','%Y-%m-%d %H:%i:%s')  or endTime<str_to_date('"+startTime+"','%Y-%m-%d %H:%i:%s'))   "
//                    + QueryHelper.toAndQuery(queryConds)
//            );
//
//            List L = qCount.list();
//            int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString()
//                    : "0");

            String sql = "select count(*) from tb_popup where 1=1 and status=1 and module="+module
                    +"  and not (start_time> str_to_date('"+endTime+"','%Y-%m-%d %H:%i:%s')  or end_time<str_to_date('"+startTime+"','%Y-%m-%d %H:%i:%s')) " +
                    " and now()<= end_time  ";
            if (StringUtils.isNotEmpty(selfId)){
                sql +=( " and id!='"+selfId+"' ");
            }
            long totalItems = dao.queryObjectBySql(sql);
            return totalItems;
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

    

    public void add(TbPopup popup) throws SystemException {
        dao.save(popup);
    }

    /**
     * 根据id查询结果
     * @param id
     * @return
     */
    public TbPopup view(String id) throws SystemException {

        try {
            Session ses = HibernateSessionFactory.openSession();
            TbPopup TbPopup = (TbPopup) ses.get(TbPopup.class, id);
            return TbPopup;
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

    public void deleteById(String id) throws SystemException {
        Transaction tx = null;
        try{
            TbPopup m =this.view(id);
            m.setDelFlag(1);
            m.setStatus(3);
            m.setStartTime(null);
            m.setEndTime(null);
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.update(m);
            tx.commit();
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
            try{
                if(tx !=null){
                    tx.rollback();
                }
            }catch(Exception ex){
                logger.error(ex);
            }
            throw new SystemException("删除出错!请检查输入数据。", e);
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {
                logger.debug(e);
            }
        }
    }


    public void down(String id)throws SystemException {
        Transaction tx = null;
        try{
            TbPopup m =this.view(id);
            m.setStatus(2);
            m.setEndTime(new Date());
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.update(m);
            tx.commit();
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
            try{
                if(tx !=null){
                    tx.rollback();
                }
            }catch(Exception ex){
                logger.error(ex);
            }
            throw new SystemException("下线出错!请检查输入数据。", e);
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {
                logger.debug(e);
            }
        }
    }
}
