package com.business.NetworkCourseVideo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.business.NetworkVideo.NetworkVideo;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

public class NetworkCourseVideoMgr extends AbstractHibernateDAO{

	private static LogUtils logger = LogUtils.getLogger(NetworkCourseVideo.class);
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq = "from NetworkCourseVideo as NetworkCourseVideo where NetworkCourseVideo.isDelete=0  "
					+ QueryHelper.toAndQuery(queryConds)
					+ " order by NetworkCourseVideo.sort   ";
			Query qCount = ses
					.createQuery("select count(id) from NetworkCourseVideo as NetworkCourseVideo where NetworkCourseVideo.isDelete=0  "
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
	public Timestamp timestamp(Timestamp times,String time) throws Exception{
		 if(time.split(":").length==1){
    		time="00:"+time+":00";
    	 }else if(time.split(":").length==2){
    		time="00:"+time;
    	 }
		 //时分秒
		 SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		 //转化为年月日时分秒格式
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String timeString = sdf.format(times);
		 Date dateHMS = df.parse(time);
		 Date dateStr = sdf.parse(timeString);
		 dateStr.setSeconds(dateHMS.getSeconds()+dateStr.getSeconds());
		 dateStr.setMinutes(dateHMS.getMinutes()+dateStr.getMinutes());
		 dateStr.setHours(dateHMS.getHours()+dateStr.getHours());
		String string = sdf.format(dateStr);
		Timestamp tms=Timestamp.valueOf(string);
		return tms;
		
	}

	public NetworkCourseVideo view(String id) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			NetworkCourseVideo video = (NetworkCourseVideo) ses.get(NetworkCourseVideo.class, id);
			return video;
		}  catch (Exception e) {
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
