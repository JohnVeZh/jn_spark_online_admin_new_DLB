package com.business.v2.questionAction;

import com.business.v2.special.TbSpecialCatalog;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.LogUtils;
import com.easecom.system.exception.SystemException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by john on 2017/4/20.
 */
public class TbSpecialCatatlogMgr extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(TbSpecialCatalog.class);

    /**
     * 查看详细信息
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public TbSpecialCatalogActionForm view(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            TbSpecialCatalog tsc = (TbSpecialCatalog) ses.get(TbSpecialCatalog.class, ID);
            if(tsc == null){
                return null;
            }
            TbSpecialCatalogActionForm form = new TbSpecialCatalogActionForm();
            super.copyProperties(form, tsc);
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
     * 更新专项训练目录
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public void update(TbSpecialCatalogActionForm form) throws SystemException, Exception {
        Transaction tx = null;
        try {

            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            TbSpecialCatalog po = (TbSpecialCatalog) session.get(TbSpecialCatalog.class, form.getId());
            super.copyProperties(po, form);
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
     * 获取排序最大值
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public String getLast(String name,String PId) throws SystemException, Exception {

        try {
            Session ses = HibernateSessionFactory.openSession();
            Query query = ses.createSQLQuery("select max("+name+") from tb_special_catalog t where p_id = '"+PId+"'");

            Object obj = query.uniqueResult();
            String lobj = (String) obj;
            return lobj;
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
     * 添加
     *
     */
    public void add(TbSpecialCatalog po) throws SystemException,Exception{

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
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
     * 删除
     *
     */
    public void delete(TbSpecialCatalog po) throws SystemException,Exception{

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(po);
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
}
