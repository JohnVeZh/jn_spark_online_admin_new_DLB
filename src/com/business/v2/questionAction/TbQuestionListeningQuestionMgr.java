package com.business.v2.questionAction;

import com.business.v2.question.TbQuestionListeningQuestion;
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
public class TbQuestionListeningQuestionMgr extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(TbQuestionListeningQuestion.class);

    /**
     * 添加
     *
     */
    public void add(TbQuestionListeningQuestion po) throws SystemException,Exception{

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
    public void delete(TbQuestionListeningQuestion po) throws SystemException,Exception{

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

    /**
     * 查看详细信息
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public TbQuestionListeningQuestionActionForm view(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            TbQuestionListeningQuestion tsc = (TbQuestionListeningQuestion) ses.get(TbQuestionListeningQuestion.class, ID);
            TbQuestionListeningQuestionActionForm form = new TbQuestionListeningQuestionActionForm();
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
     * 更新信息
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public void update(TbQuestionListeningQuestionActionForm form) throws SystemException,Exception {
        Transaction tx = null;
        try {
            Session ses = HibernateSessionFactory.openSession();
            TbQuestionListeningQuestion po = new TbQuestionListeningQuestion();
            super.copyProperties(po, form);
            tx = ses.beginTransaction();
            ses.update(po);
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
            throw new SystemException("修改出错!请检查输入数据。", e);
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    /**
     * 删除听力题目包括选项
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public void deleteAll(String id) throws SystemException {

        Transaction tx = null;
        try{
            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            Query QueryQuestion = session.createQuery("DELETE FROM TbQuestionListeningQuestion WHERE id = "+"'"+id+"'");
            Query QueryOptions = session.createQuery("DELETE FROM TbQuestionListeningQuestionOption where questionId = "+"'"+id+"'");

            QueryQuestion.executeUpdate();
            QueryOptions.executeUpdate();

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
            throw new SystemException("删除出错!请检查输入数据。", e);
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
}
