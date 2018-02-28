package cn.fanyetu.jvm.outofmemory;

/**
 *
 * VM Args: -Xss128k
 *
 * 虚拟机栈和本地方法栈溢出
 *
 * @author zhanghaonan
 * @date 2018/2/28
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

    /*
    stack length:985
    Exception in thread "main" java.lang.StackOverflowError
        at cn.fanyetu.jvm.outofmemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:17)
        at cn.fanyetu.jvm.outofmemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:18)
        at cn.fanyetu.jvm.outofmemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:18)
        at cn.fanyetu.jvm.outofmemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:18)
        at cn.fanyetu.jvm.outofmemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:18)
     */
}
