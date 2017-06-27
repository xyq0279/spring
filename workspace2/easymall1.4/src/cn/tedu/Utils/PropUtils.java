package cn.tedu.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropUtils {
	private static Properties prop = new Properties();
	
	static{
		try {
			//通过类加载器读取配置文件的路径
			String pathname = PropUtils.class.getClassLoader().getResource("/conf.properties").getPath();
			prop.load(new FileInputStream(new File(pathname)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 根据key获取配置文件中的value
	 * @param key
	 * @return key
	 */
	public static String getPro(String key) {
		return prop.getProperty(key);
	}

}
