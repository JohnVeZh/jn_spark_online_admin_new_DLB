package com.business.Dlb.Homework;

import com.business.NetworkCourse.NetworkCourse;
import com.business.NetworkCourseVideo.NetworkCourseVideo;
import com.business.ProductOrder.ProductOrder;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

/**
 * Created by sprke on 2017/7/5.
 */
public class HomeworkMgr extends AbstractHibernateDAO {
    private static LogUtils logger = LogUtils.getLogger(Homework.class);

    /**
     * 分页列表查询网课列表
     * @param queryConds
     * @param currentPageInt
     * @param itemsInPage
     * @param action
     * @param jumpPage
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public ListContainer courseList(Collection queryConds, int currentPageInt,
                              int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String hq = "from NetworkCourse where ncLevel in ('cet4','cet6') " + QueryHelper.toAndQuery(queryConds);
            Query qCount = ses.createQuery("select count(*) from NetworkCourse where ncLevel in ('cet4','cet6')  "
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
     * 分页列表查询作业列表
     * @param queryConds
     * @param currentPageInt
     * @param itemsInPage
     * @param action
     * @param jumpPage
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public ListContainer homeworkList(Collection queryConds, int currentPageInt,
                                    int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String hq = "from Homework where 1=1 " + QueryHelper.toAndQuery(queryConds);
            Query qCount = ses.createQuery("select count(*) from Homework where 1=1  "
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
     * 返回视频id列表
     * @param courseId
     * @return
     */
    public List getVideoList(String courseId) throws SystemException {

        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            String sql = "select id from network_course_video where nv_id = '" + courseId + "'";
            Query sqlQuery = ses.createSQLQuery(sql);
            return sqlQuery.list();

        }catch(Exception e) {
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
     * 添加或者跟新作业列表
     * @param homework
     * @throws SystemException
     */
    public void add(Homework homework) throws SystemException {

        Transaction tx = null;
        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.saveOrUpdate(homework);
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
     * 更新
     * @param homework
     * @throws SystemException
     */
    public void update(Homework homework) throws SystemException {

        Transaction tx = null;
        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.saveOrUpdate(homework);
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
     * 根据id查询结果
     * @param id
     * @return
     */
    public Homework view(String id) throws SystemException {

        try {
            Session ses = HibernateSessionFactory.openSession();
            Homework homework = (Homework) ses.get(Homework.class, id);
            return homework;
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
     * 根据网课id和视频id获取作业列表
     * @param ncId
     * @param videoId
     * @return
     * @throws SystemException
     */
    public List<Homework> getHomeworkByncIdAndVideoId(String ncId,String videoId) throws SystemException {
        try {
            Session ses = HibernateSessionFactory.openSession();
            String hq = "from Homework where courseId = '" + ncId + "' and courseCatalogId = '" + videoId +"'";
            Query query = ses.createQuery(hq);
            List list = query.list();
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
     * 根据作业id删除作业
     * @param homework
     * @throws SystemException
     */
    public void deleteById(Homework homework) throws SystemException {
        Transaction tx = null;
        try{
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            ses.delete(homework);
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

}
