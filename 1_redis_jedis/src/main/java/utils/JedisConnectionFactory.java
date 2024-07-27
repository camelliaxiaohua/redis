package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Datetime: 2024/7/27下午1:51
 * @author: Camellia.xioahua
 */
public class JedisConnectionFactory {

    private static JedisPool jedisPool;

    static {
        //配置连接池
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(10);
        //最大空闲连接
        config.setMaxIdle(10);
        //最小空闲连接
        config.setMaxIdle(1);
        //等待时长
        config.setMaxWaitMillis(1000);


        //创建连接对象
        jedisPool = new JedisPool(config, "127.0.0.1", 6379,1000,null);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
