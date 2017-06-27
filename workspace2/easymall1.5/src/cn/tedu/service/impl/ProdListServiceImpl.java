package cn.tedu.service.impl;

import java.util.List;

import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdListService;

public class ProdListServiceImpl implements ProdListService {
	
	private ProdDao proddao = BasicFactory.getFactory().getInstance(ProdDao.class);
	/**
	 * 查询所有商品信息
	 * @return 所有商品的集合
	 */
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return proddao.findAll();
	}
	/**
	 * 根据商品的ID修改商品的库存数量
	 * @param pnum 商品的库存数量
	 * @param pid 商品的ID
	 * @return 
	 */
	public boolean updatePnum(int pnum, String pid) {
		return proddao.updatePnum(pnum,pid);
		
	}
	/**
	 * 根据商品的ID删除商品
	 * @param pid 商品的ID
	 * @return
	 */
	public boolean delProdByID(String pid) {
		return proddao.delProdById(pid);
	}
	/**
	 * 根据相应的查询条件查询商品
	 * @param _name 商品的名称
	 * @param _category 商品的类型
	 * @param _minprice 商品价格的最小值
	 * @param _maxprice 商品价格的最大值
	 * @return
	 */
	public List<Product> findProduct(String _name, String _category,
			double _minprice, double _maxprice) {
		// TODO Auto-generated method stub
		return proddao.findProduct(_name,_category,_minprice,_maxprice);
	}
	/**
	 * 根据商品的ID查询商品信息
	 * @param pid 商品ID
	 * @return
	 */
	public Product findProdById(String pid) {
		return proddao.findProdById(pid);
	}
	
}
