/**
 * mysqlDialect.java   Dec 12, 2011 10:13:42 AM
 * Copyright:  Copyright (c) 2011
 * Company:山东益信通科贸有限公司
 */

package com.easecom.common.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

/**
 *
 * @Description 功能描述
 *
 * @author Administrator
 * @date Dec 12, 2011 10:13:42 AM
 */

public class mysqlDialect extends MySQLDialect{

	public mysqlDialect()
	{
	     super();
	     registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());
	     registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
	}

}
