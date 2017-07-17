

package com.business.v2.questionAction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.business.v2.special.TbSpecialCatalog;
import com.easecom.common.util.Tool;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.easecom.common.framework.hibernate.HibernateSessionFactory;

public class QuestionGetSelelct {
    public static Logger logger = Logger.getLogger(QuestionGetSelelct.class);

    /**
     * 根据传入的查询sql语句返回可以向<select></select>的<option>项中添加项的字符串
     *
     * @param sql
     *            查询sql语句
     * @param display
     * @param value
     * @return
     */
    public static String getList(String sql, String display, String value) {
        try {
            String s3 = "";
            Session session = HibernateSessionFactory.openSession();
            Connection conn = session.connection();
            Statement statement = conn.createStatement();
            ResultSet resultset;
            for (resultset = statement.executeQuery(sql); resultset.next();) {
                String displayvalue = resultset.getString(display);
                if (displayvalue == null)
                    displayvalue = "";
                String id = resultset.getString(value);
                String ancestorId = id.substring(0,2);
                String ancestorName = "";
                if (ancestorId.equals("01")){
                    ancestorName = "四级";
                }
                if (ancestorId.equals("02")){
                    ancestorName = "六级";
                }
                s3 = s3 + "<option value=\"" + id
                        + "\">" + ancestorName+displayvalue + "</option>";
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

    /**
     * 根据传入的查询sql语句返回可以向<select></select>的<option>项中添加项的字符串(html片段),并根据传入的值selected
     *
     * @param sql
     * @param display:显示的值
     * @param value：实际值
     * @param realValue：缺省值
     * @return 例如：str="select id ,name from table" 调用getList(str,name,id,"001")
     *         返回：<option value="001" selected>name1</option> <option
     *         value="002" >name2</option> <option value="003" >name3</option>
     *         ...
     */
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
                String displayvalue = resultset.getString(display);
                if (displayvalue == null)
                    displayvalue = "";

                String ancestorId = s6.substring(0, 2);;
                String ancestorName = "";
                if (ancestorId.equals("01")){
                    ancestorName = "四级";
                }
                if (ancestorId.equals("02")){
                    ancestorName = "六级";
                }

                if (s6.equals(realValue))
                    s4 = s4 + "<option value=\"" + s6 + "\" selected>"
                            + ancestorName+displayvalue + "</option>";
                else
                    s4 = s4 + "<option value=\"" + s6 + "\">" + ancestorName+displayvalue
                            + "</option>";
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

}