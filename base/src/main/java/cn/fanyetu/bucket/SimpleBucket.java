package cn.fanyetu.bucket;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.local.LocalBucket;

import java.time.Duration;

/**
 * @author zhanghaonan
 * @date 2019/10/16
 */
public class SimpleBucket {

    public static void main(String[] args) {
        Bandwidth limit = Bandwidth.simple(10, Duration.ofSeconds(1));
        LocalBucket bucket = Bucket4j.builder().addLimit(limit).build();
        if (bucket.tryConsume(1)) {
            System.out.println("do something");
        } else {
            System.out.println("do nothing");
        }
    }
}
