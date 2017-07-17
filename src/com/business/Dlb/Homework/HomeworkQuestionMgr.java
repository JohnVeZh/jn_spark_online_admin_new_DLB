package com.business.Dlb.Homework;

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
 * Created by sprke on 2017/7/8.
 */
public class HomeworkQuestionMgr  extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(HomeworkQuestion.class);

    /**
     * 返回听力题目列表
     * @param queryConds
     * @param currentPageInt
     * @param itemsInPage
     * @param action
     * @param jumpPage
     * @return
     * @throws SystemException
     */
    public ListContainer listentList(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage,String questionType) throws SystemException {

        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            String sq = "";
            String countSq = "";

            //判断类型
            if (!"".equals(questionType)){
                //听力查询
                if ("1".equals(questionType)){
                    sq = "SELECT deq.*,tql.audio_url from dlb_execrise_question deq LEFT JOIN tb_question_listening tql ON deq.question_id = tql.id " +
                            "WHERE tql.target = '2' and tql.is_del = '0' " + QueryHelper.toAndQuery(queryConds) + " ORDER BY deq.sort ASC";
                    countSq = "SELECT COUNT(deq.id) from dlb_execrise_question deq LEFT JOIN tb_question_listening tql ON deq.question_id = tql.id " +
                            "WHERE tql.target = '2' and tql.is_del = '0' " + QueryHelper.toAndQuery(queryConds) + " ORDER BY deq.sort ASC";
                }
                //阅读查询
                if ("2".equals(questionType)){
                    sq = "SELECT deq.* from dlb_execrise_question deq LEFT JOIN tb_question_reading tqr ON deq.question_id = tqr.id " +
                            "WHERE tqr.target = '2' and tqr.is_del = '0' " + QueryHelper.toAndQuery(queryConds) + " ORDER BY deq.sort ASC";
                    countSq = "SELECT COUNT(deq.id) from dlb_execrise_question deq LEFT JOIN tb_question_reading tqr ON deq.question_id = tqr.id " +
                            "WHERE tqr.target = '2' and tqr.is_del = '0' " + QueryHelper.toAndQuery(queryConds) + " ORDER BY deq.sort ASC";
                }
                //翻译查询
                if ("3".equals(questionType)){
                    sq = "SELECT deq.* from dlb_execrise_question deq LEFT JOIN tb_question_translation tqt ON deq.question_id = tqt.id " +
                            "WHERE tqt.target = '2' and tqt.is_del = '0' " + QueryHelper.toAndQuery(queryConds) + " ORDER BY deq.sort ASC";
                    countSq = "SELECT COUNT(deq.id) from dlb_execrise_question deq LEFT JOIN tb_question_translation tqt ON deq.question_id = tqt.id " +
                            "WHERE tqt.target = '2' and tqt.is_del = '0' " + QueryHelper.toAndQuery(queryConds) + " ORDER BY deq.sort ASC";
                }
                //写作查询
                if ("4".equals(questionType)){
                    sq = "SELECT deq.* from dlb_execrise_question deq LEFT JOIN tb_question_writing tqw ON deq.question_id = tqw.id " +
                            "WHERE tqw.target = '2' and tqw.is_del = '0' " + QueryHelper.toAndQuery(queryConds) + " ORDER BY deq.sort ASC";
                    countSq = "SELECT COUNT(deq.id) from dlb_execrise_question deq LEFT JOIN tb_question_writing tqw ON deq.question_id = tqw.id " +
                            "WHERE tqw.target = '2' and tqw.is_del = '0' " + QueryHelper.toAndQuery(queryConds) + " ORDER BY deq.sort ASC";
                }
            }

            // 查询对象
            Query qCount = ses.createSQLQuery(countSq);

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
            Query query = ses.createSQLQuery(sq.toString());
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
     * 添加作业和题目的关系
     * @param questionIds
     * @param questionNames
     * @param sorts
     * @param execriseId
     */
    public void add(String[] questionIds, String[] questionNames, String[] sorts, String execriseId) throws SystemException {

        Transaction tx = null;
        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            for(int i = 0;i<questionIds.length;i++){
                HomeworkQuestion homeworkQuestion = new HomeworkQuestion();
                homeworkQuestion.setHomeworkId(execriseId);
                homeworkQuestion.setQuestionId(questionIds[i]);
                homeworkQuestion.setQuestionName(questionNames[i]);
                homeworkQuestion.setSort(Integer.parseInt(sorts[i]));
                ses.saveOrUpdate(homeworkQuestion);
            }
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
     * 通过homeworkId获取作业列表
     * @param id
     * @return
     */
    public List<HomeworkQuestion> getHQByHomeId(String id) throws SystemException {
        try {
            Session ses = HibernateSessionFactory.openSession();

            String hql = "from HomeworkQuestion where homeworkId = '" + id + "'";
            List list = ses.createQuery(hql).list();
            return list;
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
     * 根据id查询题目
     * @param id
     * @return
     */
    public HomeworkQuestion view(String id) throws SystemException {
        try {
            Session ses = HibernateSessionFactory.openSession();
            HomeworkQuestion homeworkQuestion = (HomeworkQuestion) ses.get(HomeworkQuestion.class, id);
            return homeworkQuestion;
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
     * 更新
     * @param  homeworkQuestion
     * @throws SystemException
     */
    public void update(HomeworkQuestion homeworkQuestion) throws SystemException {

        Transaction tx = null;
        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.saveOrUpdate(homeworkQuestion);
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
            throw new SystemException("更新出错!请检查输入数据。", e);
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {
                logger.debug(e);
            }
        }
    }

    /**
     * 作业和题目解绑
     * @param homeworkQuestion
     */
    public void deleteById(HomeworkQuestion homeworkQuestion) throws SystemException {
        Transaction tx = null;
        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.delete(homeworkQuestion);
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

    public void add(HomeworkQuestion homeworkQuestion) throws SystemException {
        Transaction tx = null;
        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.saveOrUpdate(homeworkQuestion);
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
}
