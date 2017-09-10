package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.utils.TaotaoResult;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id",defaultValue="0") Long parentId){
		List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult insertContentCat(long parentId, String name){
		TaotaoResult result = contentCategoryService.insertContentCat(parentId, name);
		return result;
	}
}
