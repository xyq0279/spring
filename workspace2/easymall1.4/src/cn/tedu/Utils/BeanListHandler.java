package cn.tedu.Utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements ResultSetHandler<List<T>>{
	private Class<T> clz;
	public BeanListHandler(Class<T> clz){
		this.clz = clz;
	}

	
	/**
	 * 将结果集中的多行记录转成
	 */
	public List<T> handle(ResultSet rs) throws Exception {
		T t = null;
		List<T> list = new ArrayList<T>();
		while(rs.next()){
			
			t = (T) clz.newInstance();
			
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);
			
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			
			for(PropertyDescriptor pd: pds){
					//获取该组中属性的名字
					String name = pd.getName();
					
					Method md = pd.getWriteMethod();
					
				try {
					md.invoke(t, rs.getObject(name));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					continue;//遍历到的属性在rs中没有对应的列，直接跳过！！
					
				}
				
			}
			list.add(t);
		}
		// TODO Auto-generated method stub
		return list.size() == 0 ? null : list;
	}

}
