package cn.sn;

import cn.sn.domain.User;
import cn.sn.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootRedisApplicationTests.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Resource
    private RedisUtils redisUtils;

    @Test
    public void redisTest(){
        //redis存储数据
        String key = "name";
        redisTemplate.opsForValue().set(key,"sn");
        //redis获取数据
        String value = (String)redisTemplate.opsForValue().get(key);
        logger.info("获取缓存中的key为：{}，value为:{}",key,value);

        User user = new User();
        user.setUsername("sndada");
        user.setId(1L);
        user.setSex(1);
        user.setPassword("123456");
        user.setAge(18);
        String userKey = "sn";
        redisTemplate.opsForValue().set(userKey,user);
        Object userValue = redisTemplate.opsForValue().get(userKey);
        logger.info("获取缓存中的key为：{}，value为:{}",userKey,userValue);


    }

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtils.set("redis_key", "redis_vale");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        Object value = redisUtils.get("redis_key");
        System.out.println(value);
    }


}
