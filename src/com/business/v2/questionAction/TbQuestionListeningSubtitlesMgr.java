package com.business.v2.questionAction;

import com.business.v2.question.TbQuestionListeningSubtitles;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.LogUtils;
import com.easecom.system.exception.SystemException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by john on 2017/4/20.
 */
public class TbQuestionListeningSubtitlesMgr extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(TbQuestionListeningSubtitles.class);

    /**
     * 添加
     *
     */
    public void add(TbQuestionListeningSubtitles po) throws SystemException,Exception{

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
    public void delete(TbQuestionListeningSubtitles po) throws SystemException,Exception{

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
    public TbQuestionListeningSubtitlesActionForm view(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            TbQuestionListeningSubtitles tsc = (TbQuestionListeningSubtitles) ses.get(TbQuestionListeningSubtitles.class, ID);
            TbQuestionListeningSubtitlesActionForm form = new TbQuestionListeningSubtitlesActionForm();
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
