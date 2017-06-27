package cn.tedu.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TranManage {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>(){
		protected Connection initialValue(){	
			try {
				return DaoUtils.getconn();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	};

	
	private TranManage(){}
	//提供一个数据库连接
	public static Connection getconn(){
		return tl.get();
	}
	//开启事务的方法
	public static void startTran(){
		try {
			tl.get().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//提交事务的方法
	public static void commitTran(){
		try {
			tl.get().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//回滚事务的方法
	public static void rollbackTran(){
		try {
			tl.get().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//关闭连接的方法
	public static void release(){
		try {
			tl.get().close();
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
