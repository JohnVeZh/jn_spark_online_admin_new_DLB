package com.easecom.system.business;

import java.sql.SQLException;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.util.IdGenerator;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.Tool;

/**
 * 错误日志 Mgr
 * @author 周志君
 * 2013-6-21 上午09:40:57
 */
public class LogErrorMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(LogErrorMgr.class);
	
	/**
	 * 添加错误日志
	 * @param content 错误内容
	 * @param variable 错误发生时相关的变量
	 * @param sysuserId 错误发生时操作的用户编号
	 * @param sysuserName 错误发生时操作的用户名称
	 * @param createtime 错错误发生时的时间
	 * @param type 错误发生的类型  1 普通用户类型 2 系统操作用户 3 系统运行时错误
	 * @param ip 错误发生时请求的Ip
	 * @author 周志君
	 * 2013-6-21 上午09:43:48
	 */
	public static void addLog(String content,String variable,String sysuserId,String sysuserName,
								Long createtime,String type,String ip){
		try {
			Tool.execute("insert into log_error(id, content, variable, sysuser_id, sysuser_name, createtime, type, ip) values("
							+"'"+IdGenerator.GenerateUUID()+"','"+(content==null?"":content)+"','"+(variable==null?"":variable)
							+"','"+(sysuserId==null?"":sysuserId)+"','"+(sysuserName==null?"":sysuserName)+"','"
							+(createtime==null?"":createtime)+"','"+(type==null?"":type)+"','"+(ip==null?"":ip)+"')");
		} catch (SQLException e) {
			logger.error("添加到数据库异常信息时发生错误", e);
			e.printStackTrace();
		}
	}
}
