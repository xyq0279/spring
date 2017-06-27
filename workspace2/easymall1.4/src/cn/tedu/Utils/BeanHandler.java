package cn.tedu.Utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;

public class BeanHandler<T> implements ResultSetHandler<T> {
	private Class<T> clz;

	public BeanHandler(Class<T> clz) {
		// TODO 获取T的Class对象
		this.clz = clz;
	}

	
	/**
	 * 将结果集中的第一行记录转化成一个Bean对象并返回，如果没有记录
	 */
	public T handle(ResultSet rs) throws Exception {
		T t = null;
		if (rs.next()) {
			t = (T) clz.newInstance();
			// TODO 解析Class对象，获知Class对象所属的类中有哪些属性及公共方法
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);

			// TODO 该方法返回每组属性及方法组成的PropertyDescriptor
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor pd : pds) {
				// TODO 获取该组中的属性
				try {
					String name = pd.getName();
					// rs.getObject(name);

					Method md = pd.getWriteMethod();
					md.invoke(t, rs.getObject(name));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					continue;
				}
			}
		}
		return t;
	}
}
