package cn.fanyetu.java8.ann;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Repeatable(MyAnnotations.class) // 使用Repeatable元注解声明这个注解可以进行重复注解，并且指定重复注解容器的类
@Target({METHOD,TYPE_USE}) // java8提供了新的TYPE_USER和TYPE_PARAMETER两个类型注解的作用域
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "fanyetu";
}
