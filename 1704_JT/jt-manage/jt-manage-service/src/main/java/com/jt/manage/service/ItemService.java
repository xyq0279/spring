package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapepr;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;


@Service
public class ItemService extends BaseService<Item>{
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapepr itemDescMapper;
	@Autowired
	private RedisService redisService;
	@PropertyConfig
	public String ITEM_KEY;
	/**
	 * 分页查询
	 * {"title":2000,"rows":[{},{},{}]}
	 * title:总条数
	 * rows:查询的数据
	 * @param page
	 * @param rows
	 * @return
	 */
//	public List<Item> findItemList(int page, int rows){
	public EasyUIResult findItemList(int page, int rows){
		
		//使用分页插件进行分页		page:表示查询页数；rows:查询的数据量
		PageHelper.startPage(page, rows);
		List<Item> itemList = itemMapper.findItemList();
		//自己计算全部的计算信息数
		PageInfo<Item> info = new PageInfo<>(itemList);
		return new EasyUIResult(info.getTotal(), info.getList());
		
		/**
		 * 手动分页配置
		int title = itemMapper.selectItemCount();
		
		int startNum = (page-1)*rows;
		List<Item> itemList = itemMapper.findPageInfoList(startNum, rows);
//		return itemMapper.findItemList();
		//将数据转化为json串
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			String json = mapper.writeValueAsString(itemList);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		EasyUIResult result= new EasyUIResult();
		result.setTotal(title);
		result.setRows(itemList);
		return result;
		 */
	}

	public String findItemCatName(Long itemCatId) {
		// TODO Auto-generated method stub
		return itemMapper.findItemCatName(itemCatId);
	}
	/**
	 * 新增商品信息
	 * @param item
	 * @param desc 
	 */
	public void saveItem(Item item, String desc) {
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//动态插入
		itemMapper.insertSelective(item);
		/*
		 * 问题：商品描述信息中，需要进行入库操作，但是入库的主键id应该是商品id
		 * 但是，现在商品处于要插入的状态，MySQL还没有为其分配id值，所以现在的操作拿不到id
		 * 解决方法：
		 * mybatis+MySQL+通用mapper的具体实现，换做Oracle就不能正常运行
		 * itemMapper.insertSelective(item);
		 * 又自己查询了item的数据
		 */
		//新增商品描述信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
	}
	/**
	 * 修改商品信息
	 * @param item
	 * @param desc 
	 */
	public void updateItem(Item item, String desc) {
		//修改商品信息
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		//修改 商品描述信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
		redisService.del(ITEM_KEY+item.getId());
	}

	public void updateItemStatus(Long[] ids,int status) {
		
		for (Long id : ids) {
			Item item = new Item();
			item.setId(id);
			item.setStatus(status);
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}
		
	}
	/**
	 * 手写通用mapper中的接口方法
	 * @return
	 */
	public int findCount() {
		// TODO Auto-generated method stub
		return itemMapper.findCount();
	}

	public ItemDesc findItemDesc(Long itemId) {
		// TODO Auto-generated method stub
		return itemDescMapper.selectByPrimaryKey(itemId);
	}
}
