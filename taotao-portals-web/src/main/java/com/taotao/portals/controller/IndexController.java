package com.taotao.portals.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.Ad1Node;
import com.taotao.utils.JsonUtils;

@Controller
public class IndexController {
	@Value("${AD1_CONTENT}")
	private Long AD1_CONTENT;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model){
		//取轮播图的内容信息
		List<TbContent> contentList = contentService.getContentList(AD1_CONTENT);
		//转换成Ad1NodeList
		List<Ad1Node> nodeList = new ArrayList<>();
		for (TbContent tbContent : contentList) {
			Ad1Node ad1Node = new Ad1Node();
			ad1Node.setHeight(AD1_HEIGHT);
			ad1Node.setHeightB(AD1_HEIGHT_B);
			ad1Node.setWidth(AD1_WIDTH);
			ad1Node.setWidthB(AD1_WIDTH_B);
			ad1Node.setAlt(tbContent.getTitle());
			ad1Node.setHref(tbContent.getUrl());
			ad1Node.setSrc(tbContent.getPic());
			ad1Node.setSrcB(tbContent.getPic2());
			nodeList.add(ad1Node);
		}
		model.addAttribute("ad1", JsonUtils.objectToJson(nodeList));
		System.out.println(JsonUtils.objectToJson(nodeList));
		return "index";
	}
}
