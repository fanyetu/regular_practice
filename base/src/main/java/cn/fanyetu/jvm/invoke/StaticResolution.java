package cn.fanyetu.jvm.invoke;

/**
 * 方法静态解析的例子
 *
 * @author zhanghaonan
 * @date 2018/3/21
 */
public class StaticResolution {

    public static void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }

    /*
    javap输出

     public static void main(java.lang.String[]);
        flags: ACC_PUBLIC, ACC_STATIC
        Code:
          stack=0, locals=1, args_size=1
             0: invokestatic  #5                  // Method sayHello:()V
             3: return
          LineNumberTable:
            line 16: 0
            line 17: 3
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                   0       4     0  args   [Ljava/lang/String;
     */
}
