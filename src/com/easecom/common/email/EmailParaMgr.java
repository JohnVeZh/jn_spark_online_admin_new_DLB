/**
 * EmailParaMgr.java   Feb 7, 2012 9:03:59 AM
 * Copyright:  Copyright (c) 2011
 * Company:山东益信通科贸有限公司
 */
package com.easecom.common.email;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.LogUtils;

/**
 *
 * @Description 邮件参数维护
 *
 * @author YuPeng
 * @date Feb 7, 2012 9:03:59 AM
 */
public class EmailParaMgr extends AbstractHibernateDAO{
	//跟踪日志
	private static LogUtils logger = LogUtils.getLogger(EmailParaMgr.class);
	
	/**
	 * 
	 * @Description:  邮件参数预修改
	 * @return  
	 * @author: YuPeng
	 * @date Feb 7, 2012 9:25:48 AM
	 */
	public EmailParaForm initAdd() {
//		Session session = HibernateSessionFactory.openSession();
//		Transaction tx = null;
		EmailParaForm spForm = new EmailParaForm();
		EmailPara sp = new EmailPara();
		try {
//			tx = session.beginTransaction();
//			String hql = " from EmailPara ";
//			List list = session.createQuery(hql).list();
//			if(list != null && list.size() > 0){
//				sp = (EmailPara)list.get(0);
//			}
//			spForm.setId(sp.getOid());
//			spForm.setTfrom(sp.getTfrom());
//			spForm.setStmpHost(sp.getSmtpHost());
//			spForm.setPopHost(sp.getPop3Host());
//			spForm.setNeedAuth(sp.getNeedAuth());
//			spForm.setIsDebug(sp.getIsDebug());
//			spForm.setProtocol(sp.getTprotocol());
//			spForm.setPprotocol(sp.getPprotocol());
//			spForm.setUserName(sp.getUsername());
//			spForm.setPassword(sp.getPassword());
//			spForm.setAgentIp(sp.getAgentip());
//			spForm.setAgentPort(sp.getAgentport());
//			spForm.setTport(sp.getTport());
//			spForm.setPport(sp.getPport());
			
			
			
			/**
			 * 这里我给做了些更改，因为这个系统中没有配置email的数据表，我直接的手动添加的数据
			 */
			spForm.setId("FFFFFF");
			spForm.setTfrom("www9qmycom@163.com"); // 发出者邮箱
			spForm.setStmpHost("smtp.163.com"); // 邮箱类型
			spForm.setNeedAuth("true");//是否需要认证
			spForm.setIsDebug("false"); //调试
			spForm.setProtocol("smtp"); //协议
			spForm.setUserName("www9qmycom@163.com");//用户名
			spForm.setPassword("zhangxing888");//密码
			spForm.setTport("25");//端口。默认发出
			System.out.println("SMS测试hht163");
//			tx.commit();
			return spForm;			
		} catch (HibernateException he) {
//			tx.rollback();
			throw he;
		} finally {
//			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * 
	 * @Description:  邮件参数预修改
	 * @param spForm 邮件参数表单 
	 * @return  
	 * @author: YuPeng
	 * @date Feb 7, 2012 9:25:48 AM
	 */
	public void add(EmailParaForm spForm) {
		Session session = HibernateSessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			EmailPara sp = new EmailPara();
			if(spForm.getId() != null && !"".equals(spForm.getId())){
				sp = (EmailPara)session.load(EmailPara.class, spForm.getId());
			}
			sp.setTfrom(spForm.getTfrom());
			sp.setSmtpHost(spForm.getStmpHost());
			sp.setPop3Host(spForm.getPopHost());
			sp.setNeedAuth(spForm.getNeedAuth());
			sp.setIsDebug(spForm.getIsDebug());
			sp.setTprotocol(spForm.getProtocol());
			sp.setPprotocol(spForm.getPprotocol());
			sp.setUsername(spForm.getUserName());
			sp.setPassword(spForm.getPassword());
			sp.setAgentip(spForm.getAgentIp());
			sp.setAgentport(spForm.getAgentPort());
			sp.setTport(spForm.getTport());
			sp.setPport(spForm.getPport());
			if(sp.getOid() != null && !"".equals(sp.getOid())){
				session.update(sp);
			}else{
				session.save(sp);
			}
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * 
	 * @Description: 获得邮件参数 
	 * @return  
	 * @author: YuPeng
	 * @date 2012-5-24 下午2:30:26
	 */
	public EmailPara getEmailPara(){
		Session session = HibernateSessionFactory.openSession();
		try {
			EmailPara sp = new EmailPara();
			String hql = " from EmailPara ";
			List list = session.createQuery(hql).list();
			if(list != null && list.size() > 0){
				sp = (EmailPara)list.get(0);
			}
			return sp;
		} catch (HibernateException he) {
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
}
