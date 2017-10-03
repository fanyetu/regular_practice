package cn.fanyetu.java8.ann;

import org.junit.Test;

import java.lang.reflect.Method;

public class TestAnnotation{

    private @MyAnnotation Object object;

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;

        Method show = clazz.getMethod("show", String.class);

        MyAnnotation[] anns = show.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation ann : anns) {
            System.out.println(ann.value());
        }
    }

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(String str){

        @MyAnnotation String s = "";

    }
}
