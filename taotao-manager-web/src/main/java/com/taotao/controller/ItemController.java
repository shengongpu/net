package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.TaotaoResult;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult itemList = itemService.getItemList(page, rows);
		return itemList;
	}
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item,String desc){
		TaotaoResult result = itemService.addItem(item, desc);
		return result;
	}
}
