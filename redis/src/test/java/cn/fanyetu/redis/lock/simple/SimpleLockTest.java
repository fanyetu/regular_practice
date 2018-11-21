package cn.fanyetu.redis.lock.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanghaonan
 * @date 2018/11/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleLockTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testLock(){
        SimpleLock lock = new SimpleLock(redisTemplate);
        boolean test = lock.acquireLock("test");
        if (test){

            boolean test1 = lock.releaseLock("test");
            System.out.println(test1);
        }

    }

}