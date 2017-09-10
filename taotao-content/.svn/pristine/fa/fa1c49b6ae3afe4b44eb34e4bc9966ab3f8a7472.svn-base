package com.taotao.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class Jedis_Test {
	@Test
	public void jedisSingle(){
		//创建jedis对象连接redis
		Jedis jedis = new Jedis("192.168.25.128", 6379);
		jedis.set("hello", "redis单机版测试");//向redis存值
		String value = jedis.get("hello");//向redis取值
		System.out.println(value);
	}
	@Test
	public void jedisPool(){
		JedisPool jedisPool = new JedisPool("192.168.25.128", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("pool", "redis单机连接池测试");
		String value = jedis.get("pool");
		System.out.println(value);
		jedis.close();
		jedisPool.close();
	}
	@Test
	public void jedisCluster(){
		// 第一步：使用JedisCluster对象。需要一个Set<HostAndPort>参数。Redis节点的列表。
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.128", 7001));
		nodes.add(new HostAndPort("192.168.25.128", 7002));
		nodes.add(new HostAndPort("192.168.25.128", 7003));
		nodes.add(new HostAndPort("192.168.25.128", 7004));
		nodes.add(new HostAndPort("192.168.25.128", 7005));
		nodes.add(new HostAndPort("192.168.25.128", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("redisCluster", "redis集群测试");
		String value = jedisCluster.get("redisCluster");
		System.out.println(value);
		jedisCluster.close();//关闭资源
	}
	@Test
	public void jedis_Spring(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.set("jedis_spring", "spring整合redis集群测试");
		String value = jedisClient.get("jedis_spring");
		System.out.println(value);
	}
	
}
