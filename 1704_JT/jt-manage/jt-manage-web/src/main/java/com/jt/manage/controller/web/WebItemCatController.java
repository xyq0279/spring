package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.service.ItemCatService;

@Controller
@RequestMapping("/web/itemcat")
public class WebItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@ResponseBody
	@RequestMapping("/all")
	public Object findItemCatAll(String callback){
		MappingJacksonValue mjv  = new MappingJacksonValue(itemCatService.findItemCatAll());
		mjv.setJsonpFunction(callback);
		return mjv;
	}
}
