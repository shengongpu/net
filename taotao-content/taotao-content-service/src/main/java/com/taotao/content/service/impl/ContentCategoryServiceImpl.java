package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.utils.TaotaoResult;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> listNode = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode treeNode = new EasyUITreeNode();
			treeNode.setId(tbContentCategory.getId());
			treeNode.setText(tbContentCategory.getName());
			treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			listNode.add(treeNode);
		}
		return listNode;
	}
	@Override
	public TaotaoResult insertContentCat(long parentId, String name) {
		TbContentCategory  contentCategory  = new TbContentCategory();
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//新增节点为叶子节点
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		//可选值:1(正常),2(删除)'
		contentCategory.setStatus(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//向tb_content_category表中插入数据
		contentCategoryMapper.insert(contentCategory);
		// 判断父节点的isparent是否为true，不是true需要改为true。
		TbContentCategory _contentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!_contentCategory.getIsParent()){
			_contentCategory.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(_contentCategory);
		}
		
		return TaotaoResult.ok(contentCategory);
	}

}
