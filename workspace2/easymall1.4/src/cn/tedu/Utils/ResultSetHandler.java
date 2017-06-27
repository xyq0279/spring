package cn.tedu.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
	/**
	 * 将结果集转成一个Object并返回
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	T handle(ResultSet rs) throws Exception;
}
