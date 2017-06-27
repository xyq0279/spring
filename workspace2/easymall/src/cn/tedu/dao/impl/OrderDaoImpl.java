package cn.tedu.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.Utils.BeanHandler;
import cn.tedu.Utils.BeanListHandler;
import cn.tedu.Utils.DaoUtils;
import cn.tedu.dao.OrderDao;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.SaleInfo;

public class OrderDaoImpl implements OrderDao {
	/**
	 * 向order添加一条订单记录
	 * @param order 封装了订单信息的order类对象
	 */
	public void addOrder(Order order) {
		String sql = "insert into orders(id,money,receiverinfo,paystate,ordertime,user_id) values(?,?,?,?,?,?)";
		try {
			DaoUtils.update(sql, order.getId(),order.getMoney(),order.getReceiverinfo(),order.getPaystate(),order.getOrdertime(),order.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 向orderitem添加一条订单条目记录
	 * @param oi 封装了订单条目信息的orderitem类对象
	 */
	public void addOrderItem(OrderItem oi) {
		String sql = "insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
		try {
			DaoUtils.update(sql, oi.getOrder_id(),oi.getProduct_id(),oi.getBuynum());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Order> findOrderByUid(int uid) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where user_id = ?";
		try {
			return DaoUtils.query(sql, new BeanListHandler<Order>(Order.class), uid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<OrderItem> findOrderItemByUid(int uid) {
		String sql = "select * from orderitem oi,orders od where oi.order_id = od.id and od.user_id =?";
		try {
			return DaoUtils.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<OrderItem>();
		}
	}
	
	public Order findOrderByOid(String oid) {
		String sql = "select * from orders where id =?";
		try {
			return DaoUtils.query(sql, new BeanHandler<Order>(Order.class), oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public boolean delOrderByOid(String oid) {
		String sql = "delete from orders where id = ?";
		try {
			return DaoUtils.update(sql, oid)>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean delOrderitemByOid(String oid) {
		// TODO Auto-generated method stub
		String sql = "delete from orderitem where order_id = ?";
		try {
			return DaoUtils.update(sql, oid)>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public void updatePaystate(String r6_Order, int i) {
		// TODO Auto-generated method stub
		String sql = "update orders set paystate=? where id=?";
		try {
			DaoUtils.update(sql, i,r6_Order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Order findOrderByOidForUpdate(String r6_Order) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where id =? for update";
		try {
			return DaoUtils.query(sql, new BeanHandler<Order>(Order.class), r6_Order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public List<SaleInfo> findSaleInfo() {
		// TODO Auto-generated method stub
		String sql = "select pd.id prod_id,pd.name prod_name,sum(oi.buynum) sale_num from orders od,products pd,orderitem oi where" +
				" od.id = oi.order_id and oi.product_id = pd.id and od.paystate=1 group by pd.id order by sale_num desc limit 0,100";
		try {
			return DaoUtils.query(sql, new BeanListHandler<SaleInfo>(SaleInfo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<SaleInfo>();
		}
	}
	



}
