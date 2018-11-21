package cn.fanyetu.redis.lock.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 * 使用Redis实现的简单的分布式锁
 *
 * @author zhanghaonan
 * @date 2018/11/21
 */
public class SimpleLock {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected StringRedisTemplate redisTemplate;

    public static final int DEFAULT_ACQUIRE_TIMEOUT = 100;

    public static final String LOCK_PREFIX = "lock:";

    protected String identifier;

    /**
     * 构造方法
     * @param redisTemplate
     */
    public SimpleLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 释放锁
     * @param lockName
     * @return
     */
    public boolean releaseLock(String lockName){
        // 设置开启事务
        redisTemplate.setEnableTransactionSupport(true);
        lockName = genLockName(lockName);

        if (this.identifier == null){
            return false;
        }

        while (true){
            redisTemplate.watch(lockName);
            if (this.identifier.equals(redisTemplate.opsForValue().get(lockName))){
                redisTemplate.multi();
                redisTemplate.delete(lockName);
                redisTemplate.exec();
                return true;
            }
            redisTemplate.unwatch();
            break;
        }

        return false;
    }

    /**
     * 获取锁
     * @param lockname
     * @return
     */
    public boolean acquireLock(String lockname){
        return acquireLock(lockname, DEFAULT_ACQUIRE_TIMEOUT);
    }

    /**
     * 获取锁
     * @param lockName
     * @param acquireTimeout
     * @return
     */
    public boolean acquireLock(String lockName, int acquireTimeout){
        String identifier = UUID.randomUUID().toString();

        long end = System.currentTimeMillis() + acquireTimeout;
        lockName = genLockName(lockName);

        while (System.currentTimeMillis() < end){
            if (setnx(lockName, identifier)){
                this.identifier = identifier;
                return true;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
                return false;
            }
        }
        return false;
    }

    protected String genLockName(String lockName){
        return LOCK_PREFIX + lockName;
    }

    protected boolean setnx(String key, String value){
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * setnx并设置超时时间
     * @param key
     * @param value
     * @param expire 毫秒
     * @return
     */
    protected boolean setnx(String key, String value, long expire){
        return redisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.MILLISECONDS);
    }

}
