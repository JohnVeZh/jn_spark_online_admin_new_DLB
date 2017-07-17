package com.business.v2.questionAction;

import com.business.v2.question.TbQuestionListening;
import com.business.v2.question.TbQuestionListeningQuestion;
import com.business.v2.question.TbQuestionListeningQuestionOption;
import com.business.v2.question.TbQuestionListeningSubtitles;
import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.special.TbSpecialTraining;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.*;
import com.easecom.system.exception.SystemException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by john on 2017/4/13.
 */
public class TbQuestionListeningMgr extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(TbQuestionListening.class);

    public List getFuntree(String parentId) throws SystemException, Exception {
        List list = null;
        List treeNodes = new ArrayList();
        try {
            Collection queryConds = new ArrayList();
            Session ses = HibernateSessionFactory.openSession();
            String queryString = "(SELECT t.* FROM tb_special_catalog t WHERE t.p_id LIKE '0102%') UNION ALL (SELECT t.* FROM tb_special_catalog t WHERE t.p_id LIKE '0202%')";
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
                if (PId.equals("0102") || PId.equals("0202")){
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
                if (tbSpecialCatalogList == null || tbSpecialCatalogList.size()<1){
                    treeNode.setTreeNodeURL("TbQuestionListening.do?act=list&id=" + function.getId());//+"&state="+function.getState()

                }else {
                    treeNode.setTreeNodeURL("TbQuestionListening.do?act=list&parentid=" + function.getId());//+"&state="+function.getState()
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
            String sql = "SELECT tsc.id,tsc.name,tql.audio_url,tsc.sort_order,CASE tsc.p_id when '010201' THEN '短片新闻' WHEN '010202' THEN '长对话' WHEN '010203' THEN '短文理解' WHEN '020201' THEN '长对话' WHEN '020202'\n" +
                    "  THEN '短文理解' WHEN '020203' THEN '讲座讲话' END p_name,tsc.create_time FROM (SELECT * from tb_special_catalog WHERE 1=1 AND is_del = '0' " +
                    QueryHelper.toAndQuery(queryConds) +
                    ") tsc LEFT JOIN (SELECT * FROM tb_special_training WHERE training_type = '1') tst ON tst.catalog_id = tsc.id\n" +
                    "LEFT JOIN (select * from tb_question_listening where is_del = '0') tql ON tql.id = tst.training_id ORDER BY tsc.p_id ASC,tsc.sort_order ASC";
            Query qCount = ses
                    .createSQLQuery("SELECT count(1) FROM (SELECT * from tb_special_catalog WHERE 1=1  AND is_del = '0' " +
                            QueryHelper.toAndQuery(queryConds) +
                            ") tsc LEFT JOIN (SELECT * FROM tb_special_training WHERE training_type = '1') tst ON tst.catalog_id = tsc.id\n" +
                            "LEFT JOIN (select * from tb_question_listening where is_del = '0') tql ON tql.id = tst.training_id ORDER BY tsc.p_id ASC,tsc.sort_order ASC");

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
            String hql = "from TbQuestionListeningQuestion where 1 = 1 "+ QueryHelper.toAndQuery(queryConds) + " order by sortOrder ASC";
            Query qCount = ses
                    .createQuery("select count(id) from TbQuestionListeningQuestion where 1 = 1 "+ QueryHelper.toAndQuery(queryConds) + " order by sortOrder ASC");

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
     * 查看详细信息
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public TbSpecialCatalogActionForm viewCatalog(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            TbSpecialCatalog tsc = (TbSpecialCatalog) ses.get(TbSpecialCatalog.class, ID);
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
     * 查看详细信息得到听力片段
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public TbQuestionListeningActionForm view(String ID) throws SystemException {
        try {
            Session ses = HibernateSessionFactory.openSession();
            String sql = "select * from tb_question_listening where id = (\n" +
                    "select training_id from tb_special_training where  catalog_id = '" + ID + "')";
            Query query = ses.createSQLQuery(sql).addEntity(TbQuestionListening.class);
            List list = query.list();
            TbQuestionListening listening = null;

            if (null != list && list.size() > 0){
                listening = (TbQuestionListening) list.get(0);
            }else{
                return null;
            }
            TbQuestionListeningActionForm form = new TbQuestionListeningActionForm();
            super.copyProperties(form, listening);
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
     * 更新听力片段
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public void update(TbQuestionListeningActionForm form) throws SystemException {
        Transaction tx = null;
        try {

            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            TbQuestionListening po = (TbQuestionListening) session.get(TbQuestionListening.class, form.getId());
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
     * 更新专项训练目录
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public void updateCatalog(TbSpecialCatalogActionForm form) throws SystemException, Exception {
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
     * 添加
     *
     * @throws SystemException
     * @throws Exception
     * @prama po
     */
    public void add(TbQuestionListening po) throws SystemException,Exception{

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
     * 添加
     *
     * @throws SystemException
     * @throws Exception
     * @prama po
     */
    public void addAll(TbSpecialCatalog po1, TbSpecialTraining po2, TbQuestionListening po3) throws SystemException,Exception{

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
     * 删除所有
     *
     * @throws SystemException
     * @throws Exception
     * @prama po
     */
    public void deleteAll(String catalogId,String trainId,String listenId,String questionId,String optionId, String subtitlesId) throws SystemException,Exception{

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            TbSpecialCatalog catalog = null;
            TbSpecialTraining train = null;
            TbQuestionListening listen = null;
            TbQuestionListeningQuestion question = null;
            catalog = (TbSpecialCatalog) session.get(TbSpecialCatalog.class, catalogId);
            train = (TbSpecialTraining) session.get(TbSpecialTraining.class,trainId);
            listen = (TbQuestionListening) session.get(TbQuestionListening.class,listenId);
            question = (TbQuestionListeningQuestion) session.get(TbQuestionListeningQuestion.class,questionId);
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
                TbQuestionListeningQuestionOption option = (TbQuestionListeningQuestionOption) session.get(TbQuestionListeningQuestionOption.class,optionId);
                session.delete(option);
            }
            if (null != subtitlesId && !"".equals(subtitlesId)){
                TbQuestionListeningSubtitles subtitles = (TbQuestionListeningSubtitles) session.get(TbQuestionListeningSubtitles.class,subtitlesId);
                session.delete(subtitles);
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
            String hql = "from TbQuestionListeningQuestionOption where 1 = 1 "+ QueryHelper.toAndQuery(queryConds) + "order by prefix asc" ;
            Query qCount = ses
                    .createQuery("select count(id) from TbQuestionListeningQuestionOption where 1 = 1 "+ QueryHelper.toAndQuery(queryConds) + "order by prefix asc") ;

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
     * 添加excel中的数据
     *
     * @throws SystemException
     * @throws Exception
     * @prama po
     */
    public void addAbove(TbSpecialCatalog po1, TbSpecialTraining po2, TbQuestionListening po3,
                         TbQuestionListeningQuestion po4, TbQuestionListeningQuestionOption po5) throws SystemException {
        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            if (null != po1){
                session.save(po1);
            }
            if (null != po2){
                session.save(po2);
            }
            if (null != po3){
                session.save(po3);
            }
            if (null != po4){
                session.save(po4);
            }
            if (null != po5){
                session.save(po5);
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
     * 试卷绑定题目所需列表
     * @param queryConds
     * @param currentPageInt
     * @param itemsInPage
     * @param action
     * @param jumpPage
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public ListContainer questionListenList(Collection queryConds, int currentPageInt,
                              int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String hq = "from TbQuestionListening where target = '2' and isDel = '0' " + QueryHelper.toAndQuery(queryConds) + "order by createTime desc";
            Query qCount = ses
                    .createQuery("SELECT count(id) from TbQuestionListening where target = '2' and isDel = '0' " + QueryHelper.toAndQuery(queryConds) + "order by createTime desc");

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
     * 跟新目录和听力
     * @param listeningActionForm
     * @param catalogActionForm
     */
    public void updateCatalogAndListen(TbQuestionListeningActionForm listeningActionForm, TbSpecialCatalogActionForm catalogActionForm) throws SystemException {

        Transaction tx = null;
        try {

            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            TbQuestionListening listen = (TbQuestionListening) session.get(TbQuestionListening.class, listeningActionForm.getId());
            TbSpecialCatalog catalog = (TbSpecialCatalog) session.get(TbSpecialCatalog.class,catalogActionForm.getId());
            if (listen !=null && catalog != null){
                session.update(listen);
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
