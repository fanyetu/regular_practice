package cn.fanyetu.concurrent.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Executor测试类，将产生任务和执行任务进行解耦
 *
 * Created by Administrator on 2017/4/6.
 */
public class TaskExecutionWebServer {

    private static final int NTHREADS = 100;
    private static final Executor executor = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = () -> {
                //任务处理内容
                System.out.println("处理任务");
            };

            executor.execute(task);//执行任务
        }
    }
}
