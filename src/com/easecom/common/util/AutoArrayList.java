/*
 * ArrayList的扩展类：get(i)时自动返回一个对象，保证不越界
 */
package com.easecom.common.util;

import java.util.ArrayList;

@SuppressWarnings( { "unchecked", "serial" })
public class AutoArrayList extends ArrayList {

	private Class itemClass;

	public AutoArrayList(Class itemClass) {
		this.itemClass = itemClass;
	}

	public Object get(int index) {
		try {
			while (index >= size()) {
				add(itemClass.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.get(index);
	}
}