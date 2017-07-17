package com.business.v2.questionAction;

import com.business.v2.question.TbQuestionReadingQuestion;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.LogUtils;
import com.easecom.system.exception.SystemException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by john on 2017/4/24.
 */
public class TbQuestionReadingQuestionMgr extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(TbQuestionReadingQuestion.class);

    /**
     * 添加
     *
     */
    public void add(TbQuestionReadingQuestion po) throws SystemException,Exception{

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
    public void delete(TbQuestionReadingQuestion po) throws SystemException,Exception{

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
    public TbQuestionReadingQuestionActionForm view(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            TbQuestionReadingQuestion tsc = (TbQuestionReadingQuestion) ses.get(TbQuestionReadingQuestion.class, ID);
            TbQuestionReadingQuestionActionForm form = new TbQuestionReadingQuestionActionForm();
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
    public void update(TbQuestionReadingQuestionActionForm form) throws SystemException,Exception {
        Transaction tx = null;
        try {
            Session ses = HibernateSessionFactory.openSession();
            TbQuestionReadingQuestion po = new TbQuestionReadingQuestion();
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
            Query QueryQuestion = session.createQuery("DELETE FROM TbQuestionReadingQuestion WHERE id = "+"'"+id+"'");
            Query QueryOptions = session.createQuery("DELETE FROM TbQuestionReadingQuestionOption where questionId = "+"'"+id+"'");

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
