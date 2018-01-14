package cn.fanyetu.validator;

import cn.fanyetu.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zhanghaonan
 * @date 2018/1/14
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    // 可以直接注入spring的bean
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("" + value);
        System.out.println(value);
        return false;
    }
}
