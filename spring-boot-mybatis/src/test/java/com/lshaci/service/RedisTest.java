package com.lshaci.service;

import org.junit.Test;

import com.lshaci.SpringBaseTest;
import com.lshaci.utils.RedisUtils;


public class RedisTest extends SpringBaseTest {

    @Test
    public void test() throws Exception {
    	
    	City c = new City();
    	c.setId(1L);
    	c.setName("成都");
    	c.setState("222");
    	A a = new A();
    	a.setName("对象里面的对象");
    	c.setA(a);
//    	RedisUtils.set("a", c);
    	City city = RedisUtils.get("a", City.class);
    	System.out.println(city.getA().getName());
//    	RedisUtils.del("com.lshaci.domain.City");
//    	redisTemplate.set("first", c);
//    	Object object = redisTemplate.get("first");
        // 保存字符串
//        stringRedisTemplate.opsForValue().set("aaa", "111");
//        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
}
