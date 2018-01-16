package cn.fanyetu.web.async;

import cn.fanyetu.web.aspect.DeferredResultHolder;
import cn.fanyetu.web.aspect.MockQueue;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * 两种异步的方式，一种是callable
 * 一种是使用DeferredResult
 *
 * 注意：异步方式的controller不能使用之前的拦截器进行拦截
 *
 *
 * @author zhanghaonan
 * @date 2018/1/16
 */
@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Autowired
    private MockQueue mockQueue;

    @RequestMapping("/order2")
    public DeferredResult<String> order2() throws Exception{

        // 生成订单号
        String orderNo = RandomStringUtils.randomNumeric(8);

        // 交由消息队列处理
        mockQueue.setPlaceOrder(orderNo);

        // 放到延迟返回池中
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNo, result);

        return result;
    }

    @RequestMapping("/order")
    public Callable<String> order() throws Exception{
        logger.info("主线程开始");

        // 使用callable的方式
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };

//        Thread.sleep(1000);
        logger.info("主线程返回");
//        return "success";
        return callable;
    }
}
