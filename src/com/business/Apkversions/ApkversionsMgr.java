package com.business.Apkversions;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

public class ApkversionsMgr extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(Apkversions.class);

    /**
     * 添加
     */
    public void add(ApkversionsActionForm apkversionsActionForm) throws SystemException, Exception {
        Transaction tx = null;

        try {
            Apkversions apkversions = new Apkversions();
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            super.copyProperties(apkversions, apkversionsActionForm);
            ses.save(apkversions);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            try {
                if (tx != null)
                    tx.rollback();
            } catch (Exception ex) {
                logger.error(ex);
            }
            throw new SystemException("新增出错!请检查输入数据。", e);
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
    }
    /**
     *更新
     */
    public void update(ApkversionsActionForm apkversionsActionForm) throws SystemException, Exception {
        Transaction tx = null;
        try {

            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();
            Apkversions apkversions = (Apkversions) session.load(Apkversions.class, apkversionsActionForm.getId());
            apkversions.setApkname(apkversionsActionForm.getApkname());
            apkversions.setApkversion(apkversionsActionForm.getApkversion());
            apkversions.setVersionName(apkversionsActionForm.getVersionName());
            apkversions.setFilesize(apkversionsActionForm.getFilesize());
            apkversions.setUpdatetime(apkversionsActionForm.getUpdatetime());
            apkversions.setApkurl(apkversionsActionForm.getApkurl());
            apkversions.setDownloadUrl(apkversionsActionForm.getDownloadUrl());
            apkversions.setRemark(apkversionsActionForm.getRemark());
            session.update(apkversions);
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
     * 删除
     */
    @SuppressWarnings("unchecked")
    public void delete(String ID) throws SystemException, Exception {
        Transaction tx = null;
        try {
            Session ses = HibernateSessionFactory.openSession();
            tx = ses.beginTransaction();
            Apkversions apkversions = null;
            apkversions = (Apkversions) ses.load(Apkversions.class, ID);
            ses.delete(apkversions);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            try {
                if (tx != null)
                    tx.rollback();
            } catch (Exception ex) {
                logger.error(ex);
            }
            throw new SystemException(e.getMessage(), e);
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
    /**
     * 查看详细信息
     *
     * @prama ID
     * @throws SystemException
     * @throws Exception
     */
    public ApkversionsActionForm view(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            Apkversions apk = (Apkversions) ses.load(Apkversions.class, ID);
            ApkversionsActionForm form = new ApkversionsActionForm();
            super.copyProperties(form, apk);
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
    @SuppressWarnings("unchecked")
    public ListContainer list(Collection queryConds, int currentPageInt,
                              int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String hq = "from Apkversions as Apkversions where 1=1 "
                    + QueryHelper.toAndQuery(queryConds)
                    + " order by Apkversions.updatetime  desc ";
            Query qCount = ses
                    .createQuery("select count(id) from Apkversions as Apkversions where 1=1 "
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
}
