package cn.fanyetu.redis.lock;

import cn.fanyetu.redis.lock.simple.SimpleLock;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghaonan
 * @date 2018/11/21
 */
public class Lock extends SimpleLock {

    public static final int DEFAULT_LOCK_TIMOUT = 1000;

    /**
     * 构造方法
     *
     * @param redisTemplate
     */
    public Lock(StringRedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    /**
     * 带超时机制的锁
     * @param lockName
     * @param acquireTimeout
     * @param lockTimeout
     * @return
     */
    public boolean acquireLock(String lockName, int acquireTimeout, int lockTimeout){
        String identifier = UUID.randomUUID().toString();
        lockName = genLockName(lockName);
        lockTimeout = Math.abs(lockTimeout);

        long end = System.currentTimeMillis() + acquireTimeout;
        while (System.currentTimeMillis() < end){
            if (setnx(lockName, identifier, lockTimeout)){
                this.identifier = identifier;
                return true;
            }else if (redisTemplate.getExpire(lockName, TimeUnit.MILLISECONDS) < 1){
                redisTemplate.expire(lockName, lockTimeout, TimeUnit.MILLISECONDS);
            }
        }
        return false;
    }
}
