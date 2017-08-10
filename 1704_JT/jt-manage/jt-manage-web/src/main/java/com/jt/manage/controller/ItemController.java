package com.jt.manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	//引入日志文件
	private static final Logger logger = Logger.getLogger(ItemController.class);
	
	
	@Autowired
	private ItemService itemService;
	@RequestMapping("/query")
	@ResponseBody //将返回值直接转化为json串  [{},{},{}]
//	public List<Item> findItemList(int page,int rows){
	public EasyUIResult findItemList(int page, int rows){
		return itemService.findItemList(page,rows);
	}
	/*
	 * easyui 的全部请求都是ajax提交
	 * 值的传递是以json形式进行的
	 * 分页的参数
	 * http://localhost/item/query?page=1&rows=20
	 */
	/*@RequestMapping("/测试")
	public void findItemList(HttpServletResponse response){
		itemService.findItemList();
		try {
			response.getWriter().write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 
	 * @param item
	 * @param desc
	 * @return
	 */
			
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem (Item item,String desc){
//		SysResult result = new SysResult();
		try {
			itemService.saveItem(item,desc);
//			result.setStatus(200);
//			result.setMsg("新增成功！");
			return SysResult.build(200, "新增成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("~~~~~新增失败！"+e.getMessage());
			return SysResult.build(201, "新增失败！请联系管理员");
		}
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			return SysResult.build(200, "商品修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("~~~~~商品修改失败！"+e.getMessage());
			return SysResult.build(201, "商品修改失败！请联系管理员");
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItem(Long[] ids){
		try {
			int status = 3;
			itemService.updateItemStatus(ids,status);
			return SysResult.build(200, "商品删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("~~~~~商品删除失败！"+e.getMessage());
			return SysResult.build(201, "商品删除失败！请联系管理员");
		}
	}
	
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instockItem(Long[] ids){
		try {
			int status = 2;
			itemService.updateItemStatus(ids,status);
			return SysResult.build(200, "商品下架成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("~~~~~商品下架失败！"+e.getMessage());
			return SysResult.build(201, "商品下架失败！请联系管理员");
		}
	}
	
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelfItem(Long[] ids){
		try {
			int status = 1;
			itemService.updateItemStatus(ids,status);
			return SysResult.build(200, "商品上架成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("~~~~~商品上架失败！"+e.getMessage());
			return SysResult.build(201, "商品上架失败！请联系管理员");
		}
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDesc(@PathVariable Long itemId){
		try {
			
			ItemDesc itemDesc = itemService.findItemDesc(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("~~~~~查询商品描述信息失败！"+e.getMessage());
			return SysResult.build(201, "查询商品描述信息失败！请联系管理员");
		}
	}
	
}
