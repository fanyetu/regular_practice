package cn.fanyetu.web.aspect;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author zhanghaonan
 * @date 2018/1/16
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread(()->{
            // 监听消息队列的消息，将处理结果写入到deferredResult中，通过controller返回
            while (true){
                String orderNo = mockQueue.getCompleteOrder();
                if (StringUtils.isNotBlank(orderNo)){
                    logger.info("返回订单处理结果: " + orderNo);

                    DeferredResult<String> deferredResult = deferredResultHolder.getMap().get(orderNo);
                    deferredResult.setResult("plack order success");

                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
