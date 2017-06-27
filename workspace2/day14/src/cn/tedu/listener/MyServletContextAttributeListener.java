package cn.tedu.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * 监听器
 * @author Administrator
 *
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    /**
     * 无参构造器
     */
    public MyServletContextAttributeListener() {}

	/**
     * ServletContext域中添加一个属性是调用该方法
     */
    public void attributeAdded(ServletContextAttributeEvent scab) {
    	
    }

	/**
     * ServletContext域中替换一个属性是调用该方法
     */
    public void attributeReplaced(ServletContextAttributeEvent scab) {
        // TODO Auto-generated method stub
    }

	/**
     * ServletContext域中删除一个属性是调用该方法
     */
    public void attributeRemoved(ServletContextAttributeEvent scab) {
        // TODO Auto-generated method stub
    }
	
}
