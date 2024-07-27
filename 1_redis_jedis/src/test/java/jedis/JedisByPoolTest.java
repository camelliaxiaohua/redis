package jedis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import utils.JedisConnectionFactory;

/**
 * @Datetime: 2024/7/27下午1:57
 * @author: Camellia.xioahua
 */
public class JedisByPoolTest {

    private Jedis jedis;

    @BeforeEach
    public void setUp() {
        jedis = JedisConnectionFactory.getJedis();
    }

    @Test
    void jedisTest(){
        //存入数据
        String result = jedis.set("name", "camellia");
        System.out.println(result);

        //获取数据
        String name = jedis.get("name");
        System.out.println("name："+name);
    }

    @AfterEach
    void tearDown() {
        // 判断，避免空指针。
        if (jedis != null) {
            jedis.close();
        }
    }

}
