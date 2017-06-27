package cn.tedu.dao;

import java.util.List;

import cn.tedu.domain.Product;

public interface ProdDao extends Dao{
	/**
	 * 查询所有商品信息
	 * @return 所有商品的集合
	 */
	List<Product> findAll();
	/**
	 * 根据商品的ID修改商品的库存数量
	 * @param pnum 商品的库存数量
	 * @param pid 商品的ID
	 * @return 
	 */
	boolean updatePnum(int pnum, String pid);
	/**
	 * 根据商品的ID删除商品
	 * @param pid 商品的ID
	 * @return
	 */
	boolean delProdById(String pid);
	/**
	 * 根据相应的查询条件查询商品
	 * @param _name 商品的名称
	 * @param _category 商品的类型
	 * @param _minprice 商品价格的最小值
	 * @param _maxprice 商品价格的最大值
	 * @return
	 */
	List<Product> findProduct(String _name, String _category, double _minprice, double _maxprice);
	/**
	 * 根据商品的ID查询商品信息
	 * @param pid 商品ID
	 * @return
	 */
	Product findProdById(String pid);
	/**
	 * 
	 */
	void updatePnumById(int Buynum,String pid);

}
