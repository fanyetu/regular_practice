package cn.fanyetu.jvm.outofmemory;

/**
 * VM Args: -Xss2m
 *
 * 虚拟机栈和本地方法栈OutOfMemory
 * 风险操作，请谨慎执行
 *
 * @author zhanghaonan
 * @date 2018/2/28
 */
public class JavaVMStackOOM {

    private void notStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    notStop();
                }
            });

            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
