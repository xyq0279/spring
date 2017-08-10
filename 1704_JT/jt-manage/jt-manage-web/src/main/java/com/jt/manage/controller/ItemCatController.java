package com.jt.manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@Autowired
	private ItemService itemService;
	
	//当不传任何值，默认为一级目录
	@RequestMapping("/list")
	@ResponseBody
	public List<ItemCat> findItemCatList(@RequestParam(value="id",defaultValue="0")Long parentId) throws IOException{
		return itemCatService.findItemCatList(parentId);
	}
	
/*	// 根据商品分类id查询产品分类名称
	@RequestMapping("/queryItemName")
	public void queryItemName(Long itemId, HttpServletResponse response) {
		String name = itemService.findItemCatName(itemId);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	// 根据商品分类id查询产品分类名称
	@RequestMapping(value = "/queryItemName", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String queryItemName(Long itemId, HttpServletResponse response) {
		String name = itemService.findItemCatName(itemId);
		return  name;

	}
}
