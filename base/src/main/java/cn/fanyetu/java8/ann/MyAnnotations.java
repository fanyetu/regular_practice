package cn.fanyetu.java8.ann;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotations { // 要使用重复注解时必须提供的容器

    MyAnnotation[] value();
}
