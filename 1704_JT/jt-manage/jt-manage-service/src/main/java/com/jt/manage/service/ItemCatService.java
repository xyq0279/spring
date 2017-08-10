package com.jt.manage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatService extends BaseService<ItemCat>{
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private RedisService redisService;//redis分片
//	@Autowired
//	private JedisCluster jedisCluster;//redis集群
//	private JedisCluster redisService;
	//引入java对象和json串转换对象ObjectMapper；全局唯一
	private static final ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * 后台商品分类列表easyUI树状结构
	 * @param parentId
	 * @return
	 * @throws IOException
	 */
	public List<ItemCat> findItemCatList(Long parentId) throws IOException{
		/*
		 * 商品分类要使用缓存步骤：
		 * 1）先判断缓存中是否有数据，如果有数据就读取，直接返回
		 * 2）如果缓存中没有数据，要继续执行业务，不能抛出异常
		 * 3）执行完业务，要多一步动作，要把结果放入缓存中string，先把java对象转换成json串，kv写入缓存中。
		 */
		
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//设置唯一
		String ITEM_CAT_KEY = "ITEM_CAT"+parentId;
		//从redis内存中获得
		String jsonItemCat = redisService.get(ITEM_CAT_KEY);
		if(StringUtils.isEmpty(jsonItemCat)){//如果redis缓存中 不存在，则从数据库中查找
			/*
			 * 如果传入的是对象
			 */
			
			ItemCat itemCat = new ItemCat();
			itemCat.setParentId(parentId);
			List<ItemCat> itemCatList = itemCatMapper.select(itemCat);
			String json = MAPPER.writeValueAsString(itemCatList);
			redisService.set(ITEM_CAT_KEY, json);
			
			return itemCatList;
		}else{//如果redis中存在，则从缓存中读取
			JsonNode jsonNode = MAPPER.readTree(jsonItemCat);//把json串转换JsonNode
			//利用jackson提供方法，将json串转成java对象，List<Object>
			List<ItemCat> itemCatList = MAPPER.readValue(jsonNode.traverse(),
                    MAPPER.getTypeFactory().constructCollectionType(List.class, ItemCat.class));
			return itemCatList;
			
		}
	}
	/**
	 * 前台商品目录jsonP传输
	 * @return
	 */
	public ItemCatResult findItemCatAll() {
		ItemCatResult result = new ItemCatResult();
		List<ItemCat> itemCatList = itemCatMapper.select(null);
		Map<Long,List<ItemCat>> map = new HashMap<Long,List<ItemCat>>();
		for (ItemCat itemCat : itemCatList) {
			//判断是否存在相应的商品分类
			if(!map.containsKey(itemCat.getParentId())){
				map.put(itemCat.getParentId(), new ArrayList<ItemCat>());
			}
			map.get(itemCat.getParentId()).add(itemCat);
		}
		//创建一级目录
		List<ItemCatData> list1 = new ArrayList<ItemCatData>();
		String url = "";
		String name = "";
		
		for (ItemCat itemCat : map.get(0L)) {
			ItemCatData itemCatData = new ItemCatData();
			url = "/product/"+itemCat.getId()+".html";
			itemCatData.setUrl(url);
			name = "<a href = '"+url+"'>"+itemCat.getName()+"</a>";
			itemCatData.setName(name);
			
			//创建二级目录
			List<ItemCatData> list2 = new ArrayList<ItemCatData>();
			for (ItemCat itemCat2 : map.get(itemCat.getId())) {
				ItemCatData itemCatData2 = new ItemCatData();
				url = "/product/"+itemCat2.getId()+".html";
				itemCatData2.setUrl(url);
				name = itemCat2.getName();
				itemCatData2.setName(name);
				
				//创建三级目录
				List<String> list3 = new ArrayList<String>();
				for (ItemCat itemCat3 : map.get(itemCat2.getId())) {
					 list3.add("/product/"+itemCat3.getId()+".html|"+itemCat3.getName());
				}
				itemCatData2.setItems(list3);
				list2.add(itemCatData2);
			}
			itemCatData.setItems(list2);
			list1.add(itemCatData);
			if(list1.size()>14){
				break;
			}
		}
		result.setItemCats(list1);
		
		return result;
	}
	
}
