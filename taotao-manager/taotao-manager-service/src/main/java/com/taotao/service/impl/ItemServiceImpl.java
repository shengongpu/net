package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import com.taotao.utils.TaotaoResult;
/**
 * 商品管理
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> itemList = itemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
		//创建返回结果对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(itemList);
		return result;
	}
	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		//不全item信息
		Long id = IDUtils.genItemId();
		item.setId(id);
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//向商品表插入数据
		itemMapper.insert(item);
		//向商品标书表插入数据
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemDesc(desc);
		itemDescMapper.insert(itemDesc);
		//返回结果
		TaotaoResult ok = TaotaoResult.ok();
		return ok;
	}
	
	
}