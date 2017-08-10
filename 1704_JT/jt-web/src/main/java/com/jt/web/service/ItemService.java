package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

@Service
public class ItemService {
	
	@Autowired
	private HttpClientService httpClientService;
	@PropertyConfig
	public String MANAGE_URL; 
	
	public final static ObjectMapper MAPPER = new ObjectMapper();
	
	public Item findItemById(Long itemId) {
		String url = MANAGE_URL+"web/item/"+itemId;
		try {
			String jsonData = httpClientService.doGet(url);
			Item item =MAPPER.readValue(jsonData, Item.class);
			return item;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public ItemDesc findItemDescById(Long itemId) {
		String url = MANAGE_URL+"web/item/itemDesc/"+itemId;
		try {
			String jsonData = httpClientService.doGet(url);
			ItemDesc itemDesc =MAPPER.readValue(jsonData, ItemDesc.class);
			return itemDesc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
