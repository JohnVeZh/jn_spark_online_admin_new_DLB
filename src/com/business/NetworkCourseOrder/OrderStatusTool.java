package com.business.NetworkCourseOrder;

import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.Tool;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.*;
import java.util.*;

/**
 * Created by john on 2017/3/30.
 */
public class OrderStatusTool {
    public static Logger logger = Logger.getLogger(Tool.class);

    public static String getList(String sql, String display, String value) {
        try {
            String s3 = "";
            Session session = HibernateSessionFactory.openSession();
            Connection conn = session.connection();
            Statement statement = conn.createStatement();
            ResultSet resultset;
            for (resultset = statement.executeQuery(sql); resultset.next();) {
                String val = resultset.getString(value);
                if("not_paid".equals(val) || "not_deliver".equals(val) || "not_received".equals(val) ||
                        "completed".equals(val) || "been_canceled".equals(val)){

                    String displayvalue = resultset.getString(display);
                    if (displayvalue == null)
                        displayvalue = "";
                    if("未支付".equals(displayvalue)){
                        displayvalue="待付款";
                    }
                   /* if("审核通过".equals(displayvalue)){
                        displayvalue="退款中";
                    }
                    if("已退款、退款完成".equals(displayvalue)){
                        displayvalue="已退款";
                    }*/
                    s3 = s3 + "<option value=\"" + val
                            + "\">" + displayvalue + "</option>";
                }
            }

            statement.close();
            resultset.close();
            return s3;
        } catch (Exception exception) {
            return "<option>Error</option>";
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public static String getList(String sql, String display, String value,
                                 String realValue) {
        try {
            String s4 = "";
            String s5 = "";
            Session session = HibernateSessionFactory.openSession();
            Connection conn = session.connection();
            Statement statement = conn.createStatement();
            ResultSet resultset;
            for (resultset = statement.executeQuery(sql); resultset.next();) {
                String s6 = resultset.getString(value);
                if("not_paid".equals(s6) || "not_deliver".equals(s6) || "not_received".equals(s6) ||
                        "completed".equals(s6) || "been_canceled".equals(s6)) {
                    String displayvalue = resultset.getString(display);
                    if (displayvalue == null)
                        displayvalue = "";
                    if("未支付".equals(displayvalue)){
                        displayvalue="待付款";
                    }
                    /*if("审核通过".equals(displayvalue)){
                        displayvalue="退款中";
                    }
                    if("已退款、退款完成".equals(displayvalue)){
                        displayvalue="已退款";
                    }*/
                    if (s6.equals(realValue))
                        s4 = s4 + "<option value=\"" + s6 + "\" selected>"
                                + displayvalue + "</option>";
                    else
                        s4 = s4 + "<option value=\"" + s6 + "\">" + displayvalue
                                + "</option>";
                }
            }
            statement.close();
            resultset.close();
            return s4;
        } catch (Exception exception) {
            logger.error(exception);
            return "<option>Error</option>";
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public static List query(String sql,Class obj){
        Session session = null;
        session = HibernateSessionFactory.openSession();
        List list = new ArrayList();
        try {
            Query query = session.createSQLQuery(sql).addEntity(obj);
            list = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            HibernateSessionFactory.closeSession();
        }
        return list;
    }




}
