package cn.fanyetu.service.impl;

import cn.fanyetu.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author zhanghaonan
 * @date 2018/1/14
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello " + name;
    }
}
