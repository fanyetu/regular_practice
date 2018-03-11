package cn.fanyetu.jvm.gc;

/**
 * 一次对象自我拯救的演示
 *
 * 演示内容：
 * 1.对象可以在被GC时自我拯救
 * 2.这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 *
 * @author zhanghaonan
 * @date 2018/3/11
 */
public class FinzlizeEspaceGC {

    public static FinzlizeEspaceGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("i am still alive. :)");
    }

    /**
     * Object的finalize方法
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinzlizeEspaceGC.SAVE_HOOK = this;
    }

    /**
     *   finalize method executed!
     *   i am still alive. :)
     *   i am dead. :(
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinzlizeEspaceGC();

        // 对象第一次拯救自己
        SAVE_HOOK = null;
        System.gc();

        // 因为finalize方法优先级较低，所以暂停等待其执行
        Thread.sleep(1000);

        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("i am dead. :(");
        }


        // 这段代码和之前的一样，但是拯救失败了
        SAVE_HOOK = null;
        System.gc();

        // 因为finalize方法优先级较低，所以暂停等待其执行
        Thread.sleep(1000);

        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("i am dead. :(");
        }
    }
}
