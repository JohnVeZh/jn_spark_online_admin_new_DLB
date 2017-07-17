package com.business.v2.questionAction;

import com.business.v2.question.TbQuestionReadingQuestionOption;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.LogUtils;
import com.easecom.system.exception.SystemException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by john on 2017/4/24.
 */
public class TbQuestionReadingQuestionOptionMgr extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(TbQuestionReadingQuestionOption.class);

    /**
     * 添加
     *
     */
    public void add(TbQuestionReadingQuestionOption po) throws SystemException,Exception{

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
    public void delete(TbQuestionReadingQuestionOption po) throws SystemException,Exception{

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
    public TbQuestionReadingQuestionOptionActionForm view(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            TbQuestionReadingQuestionOption tsc = (TbQuestionReadingQuestionOption) ses.get(TbQuestionReadingQuestionOption.class, ID);
            TbQuestionReadingQuestionOptionActionForm form = new TbQuestionReadingQuestionOptionActionForm();
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
}
