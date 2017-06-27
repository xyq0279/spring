package cn.tedu.service.impl;

import java.util.List;

import cn.tedu.Utils.TranManage;
import cn.tedu.dao.OrderDao;
import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.Product;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderAddService;

public class OrderAddServiceImpl implements OrderAddService{
	private ProdDao prodDao = BasicFactory.getFactory().getInstance(ProdDao.class);
	private OrderDao orderDao = BasicFactory.getFactory().getInstance(OrderDao.class);
	public void addOrder(Order order, List<OrderItem> oiList) throws MsgException{
		
		try{
			//开启事务
			TranManage.startTran();
			//向orders表中添加一个订单
			orderDao.addOrder(order);
			for(OrderItem oi : oiList){
				//判断库存是否充足
				Product prod = prodDao.findProdById(oi.getProduct_id());
				if(prod.getPnum() < oi.getBuynum()){
					throw new MsgException("库存数量不足，库存数量仅为："+prod.getPnum()+"件，请重新修改商品数量");
				}
				//向OrderItem中添加一个订单条目
				orderDao.addOrderItem(oi);
			}
			//提交事务
			TranManage.commitTran();
		}catch(MsgException me){
			//回滚事务
			TranManage.rollbackTran();
			throw me;
		}finally{
			//关闭连接
			TranManage.release();
		}
		
	}
	
}
