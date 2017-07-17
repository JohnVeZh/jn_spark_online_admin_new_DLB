package com.business.Dlb.Message;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

/**
 * Created by qiu on 2017/7/5.
 */
public class TbMessageMgr extends AbstractHibernateDAO {
    private static LogUtils logger = LogUtils.getLogger(TbMessageMgr.class);

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
            String hq = "from TbMessage where   delFlag=0 " + QueryHelper.toAndQuery(queryConds) + " order by createDate desc";
            Query qCount = ses.createQuery("select count(*) from TbMessage where delFlag=0  "
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

    

    public void add(TbMessage TbMessage) throws SystemException {

        Transaction tx = null;
        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.saveOrUpdate(TbMessage);
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
            throw new SystemException("新增出错!请检查输入数据。", e);
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {
                logger.debug(e);
            }
        }
    }

    /**
     * 根据id查询结果
     * @param id
     * @return
     */
    public TbMessage view(String id) throws SystemException {

        try {
            Session ses = HibernateSessionFactory.openSession();
            TbMessage TbMessage = (TbMessage) ses.get(TbMessage.class, id);
            return TbMessage;
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
            TbMessage m =this.view(id);
            m.setDelFlag(1);
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
    public void updatePushStatus(String id, int pushStatus) throws SystemException {
        Transaction tx = null;
        try{
            TbMessage m =this.view(id);
            m.setPushStatus(pushStatus);
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

    public List<TbMessage> listWillSend() {
        String hq = "from TbMessage where   delFlag=0 and pushStatus=0 and pushTime<=now() order by createDate desc";

        Session ses = HibernateSessionFactory.openSession();
        Query query = ses.createQuery(hq.toString());
        return query.list();// 装填指定页的列表数据到列表容器
    }
}
