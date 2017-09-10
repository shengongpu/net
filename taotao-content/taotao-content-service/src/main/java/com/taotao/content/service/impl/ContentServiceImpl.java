package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
@Service
public class ContentServiceImpl implements ContentService {
	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Override
	public TaotaoResult insertContent(TbContent content) {
		//补全属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入数据
		contentMapper.insert(content);
		//清空redis对应的缓存，待下次查询时同步到redis中
		jedisClient.hdel(CONTENT_KEY, content.getCategoryId().toString());
		return TaotaoResult.ok();
	}
	@Override
	public List<TbContent> getContentList() {
		TbContentExample example = new TbContentExample();
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<TbContent> getContentList(long categoryId) {
		try {
			String json = jedisClient.hget(CONTENT_KEY, categoryId+"");
			if(StringUtils.isNoneBlank(json)){
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		//向redis保存查询结果
		try {
			jedisClient.hset(CONTENT_KEY, categoryId+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
