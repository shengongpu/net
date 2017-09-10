package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.utils.TaotaoResult;

public interface ItemService {
	EasyUIDataGridResult getItemList(int page,int rows);
	TaotaoResult addItem(TbItem item,String desc);
}
