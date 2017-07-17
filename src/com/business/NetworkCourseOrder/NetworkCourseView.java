package com.business.NetworkCourseOrder;

import com.business.NetworkCourse.NetworkCourse;
import com.business.NetworkCourse.NetworkCourseActionForm;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.LogUtils;
import com.easecom.system.exception.SystemException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.hibernate.Session;

/**
 * Created by john on 2017/3/30.
 */
public class NetworkCourseView extends AbstractHibernateDAO {

    private static LogUtils logger = LogUtils.getLogger(NetworkCourse.class);
    
    public NetworkCourseActionForm view(String ID) throws SystemException, Exception {
      
        try {
            Session ses = HibernateSessionFactory.openSession();
            NetworkCourse po = (NetworkCourse) ses.load(NetworkCourse.class, ID);
            NetworkCourseActionForm form = new NetworkCourseActionForm();
            ConvertUtils.register(new SqlDateConverter(null),java.sql.Timestamp.class);
            super.copyProperties(form, po);
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
