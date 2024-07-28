package jedis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * @Datetime: 2024/7/27下午1:35
 * @author: Camellia.xioahua
 */
public class JedisTest {

    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // 1. 建立连接
        jedis = new Jedis("localhost",6379);

        // 2. 设置密码
        jedis.aclCat();

        // 3. 选择库
        jedis.select(0);
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

