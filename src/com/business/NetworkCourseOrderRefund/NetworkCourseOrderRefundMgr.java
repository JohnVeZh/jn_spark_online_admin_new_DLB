package com.business.NetworkCourseOrderRefund;

import com.business.ProductOrder.ProductOrder;
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
 * Created by john on 2017/4/10.
 */
public class NetworkCourseOrderRefundMgr  extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(NetworkCourseOrderRefund.class);
    /**
     * 列表
     *
     * @return 返回符合条件的列表对象
     * @throws SystemException
     * @throws Exception
     * @prama queryConds 查询条件
     * @prama currentPageInt 当前页码
     * @prama itemsInPage 每页的记录数
     * @prama action 页面行为
     * @prama jumpPage 跳页的目标页码
     */
    @SuppressWarnings("unchecked")
    public ListContainer list(Collection queryConds, Collection queryconds1, Collection queryconds2, int currentPageInt,
                              int itemsInPage, String action, int jumpPage) throws SystemException,
            Exception {
        try {
            // 在数据准备完成后，获取hiernate会话
            Session ses = HibernateSessionFactory.openSession();

            // 查询对象
            String sql = "SELECT * FROM product_order_refund WHERE product_order_details_id IN (" +
                    "SELECT id FROM product_order_details WHERE product_id IN (" +
                    "SELECT id FROM network_course WHERE 1=1 "+QueryHelper.toAndQuery(queryconds1)+") ) AND order_code IN (" +
                    "SELECT order_code FROM product_order WHERE 1=1 "+QueryHelper.toAndQuery(queryconds2)+")" +
                    " AND product_type IN (1,2,3) " +QueryHelper.toAndQuery(queryConds)+
                    " ORDER BY createtime  DESC";
            Query sqlCount = ses.createSQLQuery("SELECT count(id) FROM product_order_refund WHERE product_order_details_id IN (" +
                    "SELECT id FROM product_order_details WHERE product_id IN (" +
                    "SELECT id FROM network_course WHERE 1=1 "+QueryHelper.toAndQuery(queryconds1)+")) AND order_code IN (" +
                    "SELECT order_code FROM product_order WHERE 1=1 "+QueryHelper.toAndQuery(queryconds2)+")" +
                    " AND product_type IN (1,2,3) " +QueryHelper.toAndQuery(queryConds)+
                    " ORDER BY createtime  DESC");
            // 新建并设置列表容器
            ListContainer lc = new ListContainer();
            lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
            lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
            lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
            lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
            List L = sqlCount.list();
            int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString()
                    : "0");
            lc.setTotalItem(totalItems); // 设置记录总数
            Query query = ses.createSQLQuery(sql).addEntity(NetworkCourseOrderRefund.class);
//            Query query = ses.createQuery(hq.toString());
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
    public NetworkCourseOrderRefundActionForm view(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            NetworkCourseOrderRefund ncor = (NetworkCourseOrderRefund) ses.get(NetworkCourseOrderRefund.class, ID);
            NetworkCourseOrderRefundActionForm form = new NetworkCourseOrderRefundActionForm();
            super.copyProperties(form, ncor);
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
     * 查看详细信息
     *
     * @throws SystemException
     * @throws Exception
     * @prama ID
     */
    public NetworkCourseOrderRefund viewPOJO(String ID) throws SystemException, Exception {
        try {
            Session ses = HibernateSessionFactory.openSession();
            NetworkCourseOrderRefund ncor = (NetworkCourseOrderRefund) ses.get(NetworkCourseOrderRefund.class, ID);
            NetworkCourseOrderRefundActionForm form = new NetworkCourseOrderRefundActionForm();
            super.copyProperties(form, ncor);
            return ncor;
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
     */
    public void updatePOJO(NetworkCourseOrderRefund ncor) throws SystemException, Exception {
        Transaction tx = null;
        try {

            Session session = HibernateSessionFactory.openSession();
            tx = session.beginTransaction();

            session.update(ncor);
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
     * 添加获取网课订单导出数据
     *
     * @param queryConds
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public List getNetWorkOrderRefundList(Collection queryConds,Collection queryConds1,Collection queryConds2) throws SystemException, Exception {
        try {
            String sql ="SELECT nc.nc_name,ncor.order_code,logistics.logisticsCode,pod.pcount,po.consignee,po.postage,u.account,po.remark," +
                    "po.telephone,CASE ncor.check_status when 'check_ing' THEN" +
                    "  '退款审核中' when  'check_passed' THEN '退款中' WHEN 'pay_finished' THEN '已退款' END AS check_status,CASE nc.is_gift_book WHEN '0'" +
                    "  THEN '无' WHEN '1' THEN '有' END  AS is_gift_book ,po.price,po.pay_price,ncor.fee,ncor.createtime,p.pname,c.city,a.area,po.address, " +
                    "case po.type when '1' then '网课1.0' when '2' then '网课1.0' when '3' then '网课2.0' when '4' then '网课2.0' else '' END as type," +
                    " CASE po.payType WHEN '0' THEN '支付宝'WHEN '1' THEN '微信'WHEN '3' THEN '兑换码'END AS payType" +
                    " FROM (SELECT * FROM product_order_refund WHERE product_order_details_id IN (" +
                    "SELECT id FROM product_order_details WHERE product_id IN (" +
                    "SELECT id FROM network_course WHERE 1=1 "+QueryHelper.toAndQuery(queryConds1)+"))" +
                    " AND order_code IN (SELECT order_code FROM product_order WHERE 1=1 "+QueryHelper.toAndQuery(queryConds2)+")" +
                    " AND product_type IN (1,2,3) "+QueryHelper.toAndQuery(queryConds)+" ORDER BY createtime  DESC) ncor " +
                    "LEFT JOIN product_order_details pod ON ncor.product_order_details_id = pod.id " +
                    "LEFT JOIN product_order po ON ncor.order_code = po.order_code " +
                    "LEFT JOIN network_course nc ON nc.id = pod.product_id " +
                    "LEFT JOIN users u ON po.user_id = u.id " +
                    "LEFT JOIN province p ON po.province_id = p.provinceID " +
                    "LEFT JOIN city c ON po.city_id = c.cityID " +
                    "LEFT JOIN area a ON po.area_id = a.areaID " +
                    "LEFT JOIN product_order_logistics logistics ON po.id = logistics.Order_History ORDER BY createtime DESC";
            List list = super.SQLQuery(sql);
            return list;
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
