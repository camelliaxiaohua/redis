package camellia;

import camellia.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;

@SpringBootTest
class RedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    // Spring Data Redis 提供的模板类，用于操作 Redis
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //Jackson 提供的对象映射器，用于 JSON 与对象之间的转换
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testRedisTemplate() {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("name", "白山茶");
        System.out.println(operations.get("name"));
    }

    @Test
    public void testPojo(){
        User user = new User("白山茶",26);
        // 存入数据
        redisTemplate.opsForValue().set("user:camellia",user);
        // 获取数据
        Object o = redisTemplate.opsForValue().get("user:camellia");
        System.out.println(o);
    }


    @Test
    public void testStringRedisTemplate() throws JsonProcessingException {
        // 创建一个 User 对象
        User user = new User("渡渡鸟", 21);

        // 将 User 对象转换为 JSON 字符串
        String json = mapper.writeValueAsString(user);

        // 使用 StringRedisTemplate 将 JSON 字符串存储到 Redis 中，键为 "user:duduniao"
        stringRedisTemplate.opsForValue().set("user:duduniao", json);

        // 从 Redis 中获取存储的 JSON 字符串，键为 "user:duduniao"
        json = stringRedisTemplate.opsForValue().get("user:duduniao");

        // 将 JSON 字符串反序列化为 User 对象
        User clazzUser = mapper.readValue(json, User.class);

        // 打印反序列化后的 User 对象
        System.out.println(clazzUser);
    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:400", "name", "虎哥");
        stringRedisTemplate.opsForHash().put("user:400", "age", "21");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println("entries = " + entries);
    }
}
