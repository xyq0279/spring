package jt.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestRedisShard {
	
	public static void main(String[] args) {
		//设置连接服务器IP地址和访问端口
		Jedis jedis = new Jedis("192.168.161.155",7000);
		
		//单个值
//		jedis.set("test", "123");				//设置值
//		System.out.println(jedis.get("test"));		//获取值
		
		//多个值
		jedis.mset("test1","1","test2","2");
		List<String> oList = jedis.mget("test1","test2");
		for(String s : oList){
			System.out.println(s);
		}
//		
		jedis.close();	//关闭
	}
	@Test	//分片
	public void shard(){
		
		
		//构造各个节点链接信息，host和port
		List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();
		JedisShardInfo info1 = new JedisShardInfo("192.168.161.153",6379);
		//info1.setPassword("123456");
		infoList.add(info1);
		JedisShardInfo info2 = new JedisShardInfo("192.168.161.153",6380);
		infoList.add(info2);
		JedisShardInfo info3 = new JedisShardInfo("192.168.161.153",6381);
		infoList.add(info3);
		
		//分片jedis
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(500);	//最大链接数
		
		ShardedJedisPool pool = new ShardedJedisPool(config, infoList);
		//ShardedJedis jedis = new ShardedJedis(infoList);
		ShardedJedis jedis = pool.getResource();	//从pool中获取
		for(int i=0;i<100;i++){
			jedis.set("n"+i, "t"+i);
		}
		System.out.println(jedis.get("n9"));
		jedis.close();
	}
}
