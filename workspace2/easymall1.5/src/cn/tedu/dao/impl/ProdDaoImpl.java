package cn.tedu.dao.impl;

import java.sql.SQLException;
import java.util.List;

import cn.tedu.Utils.BeanHandler;
import cn.tedu.Utils.BeanListHandler;
import cn.tedu.Utils.DaoUtils;
import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Product;

public class ProdDaoImpl implements ProdDao {
	
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		try {
			return DaoUtils.query("select * from products where deleted = 'N'", new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public boolean updatePnum(int pnum, String pid) {
		// TODO Auto-generated method stub
		try {
			return DaoUtils.update("update products set pnum=? where deleted = 'N' and id=?", pnum,pid)>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public boolean delProdById(String pid) {
		try {
			return DaoUtils.update("update products set deleted = 'Y' where id =?", pid)>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Product> findProduct(String _name, String _category,
			double _minprice, double _maxprice) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from products where deleted = 'N' and name like ? and category like ? and price >= ? and price <= ?";
			return DaoUtils.query(sql, new BeanListHandler<Product>(Product.class), "%"+_name+"%","%"+_category+"%",_minprice,_maxprice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Product findProdById(String pid) {
		try {
			return DaoUtils.query("select * from products where deleted = 'N' and id =?", new BeanHandler<Product>(Product.class), pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void updatePnumById(int BuyNum,String pid) {
		String sql = "update products set pnum = pnum+? where id = ?";
		try {
			DaoUtils.update(sql,BuyNum,pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
