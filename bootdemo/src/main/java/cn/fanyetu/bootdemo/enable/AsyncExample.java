package cn.fanyetu.bootdemo.enable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghaonan
 * @date 2018/8/16
 */
@Component
public class AsyncExample {

    @Async
    public void run() {
        try {
            for (int i = 1; i < 10; i++) {
                System.out.println("====================" + i);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void main(){
        AsyncExample bean = applicationContext.getBean(AsyncExample.class);
        bean.run();

        System.out.println("======================= after running ================");
    }
}
