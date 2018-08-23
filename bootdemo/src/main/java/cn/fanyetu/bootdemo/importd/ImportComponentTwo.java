package cn.fanyetu.bootdemo.importd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhanghaonan
 * @date 2018/8/16
 */
@Component
@Import(ImportBeanRegister.class)
public class ImportComponentTwo {

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void main(){
        System.out.println(applicationContext.getBean(Dinosaur.class));
        System.out.println(applicationContext.getBean(Phoenix.class));
    }

}
