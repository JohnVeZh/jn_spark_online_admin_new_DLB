package com.business.v2.questionAction;

import com.business.v2.question.TbQuestionReading;
import com.business.v2.question.TbQuestionReadingQuestion;
import com.business.v2.question.TbQuestionReadingQuestionOption;
import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.special.TbSpecialTraining;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.*;
import com.easecom.system.exception.SystemException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by john on 2017/4/24.
 */
public class TbQuestionReadingMgr extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(TbQuestionReading.class);

    public List getFuntree(String parentId) throws SystemException, Exception {
        List list = null;
        List treeNodes = new ArrayList();
        try {
            Collection queryConds = new ArrayList();
            Session ses = HibernateSessionFactory.openSession();
            String queryString = "(SELECT t.* FROM tb_special_catalog t WHERE t.p_id LIKE '0103%') UNION ALL (SELECT t.* FROM tb_special_catalog t WHERE t.p_id LIKE '0203%')";
            Query query = ses.createSQLQuery(queryString).addEntity(TbSpecialCatalog.class);
            if (query == null) {
                list = new ArrayList();
            } else {
                list = query.list();
            }
            Iterator tempArray = list.iterator();

            TbSpecialCatalog function = null;
            TreeNode treeNode = null;
            while (tempArray.hasNext()) {
                function =  (TbSpecialCatalog)tempArray.next();
                String PId = function.getPId();
                if (PId.equals("0103") || PId.equals("0203")){
                    PId = "";
                }
                String ancestorId = function.getId().substring(0, 2);
                String ancestorName = "";
                if (ancestorId.equals("01")){
                    ancestorName = "四级";
                }
                if (ancestorId.equals("02")){
                    ancestorName = "六级";
                }
                treeNode = new TreeNode(function.getId(),ancestorName + function.getName(), PId);
                String isFather = "select * from tb_special_catalog where p_id = '" + function.getId() + "'";
                Query sqlQuery = ses.createSQLQuery(isFather).addEntity(TbSpecialCatalog.class);
                List tbSpecialCatalogList = sqlQuery.list();
                if (tbSpecialCatalogList.size() < 1 || tbSpecialCatalogList == null){
                    treeNode.setTreeNodeURL("TbQuestionReading.do?act=list&id=" + function.getId());//+"&state="+function.getState()
                }else{
                    treeNode.setTreeNodeURL("TbQuestionReading.do?act=list&parentid=" + function.getId());//+"&state="+function.getState()
                }
                treeNodes.add(treeNode);
            }
            return treeNodes;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

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
    public ListContainer list(Collection queryConds, int currentPageInt,
                              int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String sql = "SELECT t1.`id`,t1.`name`,t1.`sort_order`,t2.`name`as pname,t1.`create_time` FROM tb_special_catalog t1 LEFT\n" +
                    " JOIN tb_special_catalog t2 ON t2.id = t1.`p_id`\n" +
                    " WHERE t1.`is_del`=0 "+QueryHelper.toAndQuery(queryConds)+" ORDER BY t1.`p_id` ASC,t1.`sort_order` ASC";
            Query qCount = ses
                    .createSQLQuery("SELECT COUNT(1) FROM tb_special_catalog t1 LEFT\n" +
                            " JOIN tb_special_catalog t2 ON t2.id = t1.`p_id`\n" +
                            " WHERE t1.`is_del`=0 "+QueryHelper.toAndQuery(queryConds)+" ORDER BY t1.`p_id` ASC,t1.`sort_order` ASC" );

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
            Query query = ses.createSQLQuery(sql.toString());
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
     * 查看详细信息得到阅读片段
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public TbQuestionReadingActionForm view(String ID) throws SystemException {
        try {
            Session ses = HibernateSessionFactory.openSession();

            TbQuestionReading reading = null;

            if (null != ID && !"".equals(ID)){
                reading = (TbQuestionReading) ses.get(TbQuestionReading.class,ID);
            }
            TbQuestionReadingActionForm form = new TbQuestionReadingActionForm();
            super.copyProperties(form, reading);
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
     * 更新阅读
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public void update(TbQuestionReadingActionForm form) throws SystemException {
        Transaction tx = null;
        try {

            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            TbQuestionReading po = (TbQuestionReading) session.get(TbQuestionReading.class, form.getId());
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
     * 删除所有
     *
     * @throws SystemException
     * @throws Exception
     * @prama po
     */
    public void deleteAll(String catalogId,String trainId,String listenId,String questionId,String optionId) throws SystemException,Exception{

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            TbSpecialCatalog catalog = null;
            TbSpecialTraining train = null;
            TbQuestionReading listen = null;
            TbQuestionReadingQuestion question = null;
            catalog = (TbSpecialCatalog) session.get(TbSpecialCatalog.class, catalogId);
            train = (TbSpecialTraining) session.get(TbSpecialTraining.class,trainId);
            listen = (TbQuestionReading) session.get(TbQuestionReading.class,listenId);
            question = (TbQuestionReadingQuestion) session.get(TbQuestionReadingQuestion.class,questionId);
            if (null != catalog){
                catalog.setIsDel("1");
                session.update(catalog);

            }
            if( null != train){
                session.delete(train);
            }
            if (null != listen){
                listen.setIsDel("1");
                session.update(listen);
            }
            if (null != question){
                session.delete(question);
            }
            if (null != optionId && !"".equals(optionId)){
                TbQuestionReadingQuestionOption option = (TbQuestionReadingQuestionOption) session.get(TbQuestionReadingQuestionOption.class,optionId);
                session.delete(option);
            }

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
     * 添加
     *
     * @throws SystemException
     * @throws Exception
     * @prama po
     */
    public void addAll(TbSpecialCatalog po1, TbSpecialTraining po2, TbQuestionReading po3) throws SystemException,Exception{

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(po1);
            session.save(po2);
            session.save(po3);
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
     * 配题列表
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
    public ListContainer matchList(Collection queryConds, int currentPageInt,
                                   int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String hql = "from TbQuestionReadingQuestion where 1 = 1 "+ QueryHelper.toAndQuery(queryConds) + " order by sortOrder ASC";
            Query qCount = ses
                    .createQuery("select count(id) from TbQuestionReadingQuestion where 1 = 1 "+ QueryHelper.toAndQuery(queryConds) + " order by sortOrder ASC");

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
            Query query = ses.createQuery(hql.toString());
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
     * 选项列表
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
    public ListContainer optionList(Collection queryConds, int currentPageInt,
                                    int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String hql = "from TbQuestionReadingQuestionOption where 1 = 1 "+ QueryHelper.toAndQuery(queryConds) + "order by prefix asc" ;
            Query qCount = ses
                    .createQuery("select count(id) from TbQuestionReadingQuestionOption where 1 = 1 "+ QueryHelper.toAndQuery(queryConds) + "order by prefix asc") ;

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
            Query query = ses.createQuery(hql.toString());
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
     * 绑定作业时需要的阅读列表
     * @param queryConds
     * @param currentPageInt
     * @param itemsInPage
     * @param action
     * @param jumpPage
     * @return
     */
    public ListContainer questionReadList(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String hq = "from TbQuestionReading where target = '2' and isDel = '0' " + QueryHelper.toAndQuery(queryConds) + "order by createTime desc";
            Query qCount = ses
                    .createQuery("SELECT count(id) from TbQuestionReading where target = '2' and isDel = '0' " + QueryHelper.toAndQuery(queryConds) + "order by createTime desc");

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


    public void updateCatalogAndRead(TbQuestionReadingActionForm readingActionForm, TbSpecialCatalogActionForm catalogActionForm) throws SystemException {
        Transaction tx = null;
        try {

            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            TbQuestionReading read = (TbQuestionReading) session.get(TbQuestionReading.class, readingActionForm.getId());
            TbSpecialCatalog catalog = (TbSpecialCatalog) session.get(TbSpecialCatalog.class,catalogActionForm.getId());
            if (read !=null && catalog != null){
                session.update(read);
                session.update(catalog);
            }
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
}
