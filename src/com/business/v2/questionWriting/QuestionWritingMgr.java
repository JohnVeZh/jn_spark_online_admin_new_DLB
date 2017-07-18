package com.business.v2.questionWriting;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.business.v2.question.TbQuestionTranslation;
import com.business.v2.question.TbQuestionWriting;
import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.word.TbWord;
import com.business.v2.word.TbWordQuestionOption;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.Tool;
import com.easecom.common.util.TreeNode;
import com.easecom.system.exception.SystemException;

public class QuestionWritingMgr extends AbstractHibernateDAO{
	private static LogUtils logger = LogUtils.getLogger(TbQuestionTranslation.class);
	
	/**
	 * 生成功能树
	 * 
	 * @param
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getFuntree(String sql) throws SystemException, Exception {
		List list = null;
		List treeNodes = new ArrayList();
		try {
			Collection queryConds = new ArrayList();
			Session ses = HibernateSessionFactory.openSession();
			String queryString = "(select * from tb_special_catalog where id='0105') union ALL (select * from tb_special_catalog where id='0205')";
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
				treeNode = new TreeNode(function.getId(), function.getPId().equals("01")?"四级":"六级", "");
				treeNode.setTreeNodeURL("QuestionWriting.do?act=list&Pid="+function.getId());//+"&state="+function.getState()
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
	
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage,String pid) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq = null;
			String sql_pid="";
			if(pid==null || pid.equals("")){
				sql_pid="and p_id in (0105,0205)";
			}else{
				sql_pid="and id like '"+pid+"%' and p_id ='"+pid+"'";
			}
			hq="select * from tb_special_catalog where 1=1 "+sql_pid+" and is_del='0'  ORDER BY sort_order desc";
			Query qCount = ses
					.createSQLQuery("select count(*) from tb_special_catalog where 1=1 "+sql_pid+" and is_del='0' ");

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
			Query query = ses.createSQLQuery(hq.toString()).addEntity(TbSpecialCatalog.class);
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
	
	@SuppressWarnings("unchecked")
	public ListContainer listByObj(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage,String id) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			
			// 查询对象
			String sql="SELECT a.* FROM tb_word_question_option a LEFT JOIN tb_word_question b ON a.question_id = b.id WHERE b.word_id = '"+id+"'  and is_del='0'";
			Query qCount = ses
					.createSQLQuery("SELECT count(a.id) FROM tb_word_question_option a LEFT JOIN tb_word_question b ON a.question_id = b.id WHERE b.word_id = '"+id+"'  and is_del='0'");
			
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
			Query query = ses.createSQLQuery(sql.toString()).addEntity(TbWordQuestionOption.class);
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

	public void UploadAdd(String Pid, TbQuestionWriting vo) throws SystemException, SQLException {
		vo.setId(UUID.randomUUID().toString().replace("-", ""));
		String MaxSort = Tool.getValue("select max(sort_order) from tb_special_catalog where p_id ='"+Pid+"'");
		int sortOrder=Integer.parseInt(MaxSort);
		int max_sort_order=sortOrder+1;
		//最大ID
		String MaxID = Tool.getValue("select max(id) from tb_special_catalog where p_id ='"+Pid+"'");
		int maxID=Integer.parseInt(MaxID);
		int maxId=maxID+1;
		String max_id="0"+maxId;
		//级别 01:四级 02:六级
		String p_id = Tool.getValue("select p_id from tb_special_catalog where id ='"+Pid+"'");
		//添加 tb_special_catalog 表信息
		String specialSQL="INSERT INTO tb_special_catalog(id,p_id,name,icon_url,sort_order,remarks,is_del,create_time)VALUES"
				 + "('"+max_id+"','"+Pid+"','"+vo.getName()+"',null,"+max_sort_order+",null,'0','"+DateUtils.getCurrDateTimeStr()+"')";
		//添加 tb_special_training 中间表信息
		String trainingSQL="INSERT INTO tb_special_training(id,section,catalog_id,training_type,training_id,sort_order)VALUES"
				 + "('"+UUID.randomUUID().toString().replace("-", "")+"','"+p_id+"','"+max_id+"','4','"+vo.getId()+"','1')";
		//添加 tb_question_translation 翻译题表信息
		String title=null;
		String analysis=null;
		String reference=null;
		String content=null;
		String target=null;
		
		if(vo.getTitle()!=null && !vo.getTitle().equals("")){
			title="\""+vo.getTitle()+"\"";
		}
		if(vo.getAnalysis()!=null && !vo.getAnalysis().equals("")){
			analysis="\""+vo.getAnalysis()+"\"";
		}
		if(vo.getReference()!=null && !vo.getReference().equals("")){
			reference="\""+vo.getReference()+"\"";
			
		}
		if(vo.getContent()!=null && !vo.getContent().equals("")){
			content="\""+vo.getContent()+"\"";
		}
		if (vo.getTarget()!=null && !vo.getTarget().equals("")){
			target="\""+vo.getTarget()+"\"";
		}
		String translationSQL="INSERT INTO tb_question_writing(id,title,content,reference,analysis,analysis_url,target,is_del,create_time)VALUES"
						+ "('"+vo.getId()+"',"+title+","+content+","+reference+","+analysis+","+getFormatField(vo.getAnalysisUrl())+","+target+",0,'"+DateUtils.getCurrDateTimeStr()+"')";
		uploadAdd(specialSQL,trainingSQL,translationSQL);
	}
	public void uploadAdd(String specialSQL, String trainingSQL, String translationSQL) throws SQLException, SystemException {
		logger.info("uploadAdd() " + specialSQL);
		logger.info("uploadAdd() " + trainingSQL);
		logger.info("uploadAdd() " + translationSQL);
		Session session = HibernateSessionFactory.openSession();
		Transaction tx = null;
		Connection conn = null;
		try {
			conn = session.connection();
			Statement stat = conn.createStatement();
	        tx = session.beginTransaction();
	        stat.executeUpdate(specialSQL);
	        stat.executeUpdate(trainingSQL);
	        stat.executeUpdate(translationSQL);
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
	 * sql拼接去''
	 * @param obj
	 * @return
	 */
	public static String getFormatField(Object obj) {
		String resultStr = "NULL";
		if (obj instanceof Integer) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr =  obj.toString() ;
			}
		} else if(obj instanceof String) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Timestamp) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Double) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = obj.toString();
			}
		}
		return resultStr;
	}

	/**
	 * 绑定作业时需要的翻译列表
	 * @param queryConds
	 * @param currentPageInt
	 * @param itemsInPage
	 * @param action
	 * @param jumpPage
	 * @return
	 */
    public ListContainer questionWriteList(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
            String sq = "select * from tb_question_writing where target = '2' and is_del = '0' " + QueryHelper.toAndQuery(queryConds) + "order by create_time desc";
            Query qCount = ses
                    .createSQLQuery("select count(id) from tb_question_writing where target = '2' and is_del = '0' " + QueryHelper.toAndQuery(queryConds) + "order by create_time desc");

            // 新建并设置列表容器
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString() : "0");
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
}
