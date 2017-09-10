package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.utils.TaotaoResult;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/content/query/list")
	@ResponseBody
	public List<TbContent> getContentList(){
		List<TbContent> contentList = contentService.getContentList();
		return contentList;
	}
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content){
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
}
