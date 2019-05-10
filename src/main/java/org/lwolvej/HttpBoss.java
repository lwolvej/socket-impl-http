package org.lwolvej;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 每次来一个http请求启动一个新的线程(worker)来处理
 *
 * @author lwolvej
 */
class HttpBoss extends Thread {

    private final ServerSocket serverSocket;

    HttpBoss(final ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        //无限循环，维持等待request的状态
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                //收到连接，开启一个socket
                Socket socket = serverSocket.accept();
                //生成一个worker处理当前的socket
                HttpWorker worker = new HttpWorker(socket);
                //将该worker放入线程池中
                ThreadPoolContext.workerExecutor.submit(worker);
            } catch (IOException e) {
                e.printStackTrace();
                //出现异常立刻让该线程中断
                Thread.currentThread().interrupt();
            }
        }
    }
}
