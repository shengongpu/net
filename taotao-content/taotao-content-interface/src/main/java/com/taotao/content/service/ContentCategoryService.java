package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.utils.TaotaoResult;

public interface ContentCategoryService {
	List<EasyUITreeNode> getContentCategoryList(long parentId);
	TaotaoResult insertContentCat(long parentId, String name);
}
