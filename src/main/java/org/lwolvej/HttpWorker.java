package org.lwolvej;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 一次http服务
 *
 * @author lwolvej
 */
class HttpWorker extends Thread {

    private final Socket socket;

    HttpWorker(final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取连接中的输入，类似接收到请求
//            InputStream inputStream = socket.getInputStream();
//            int input;
//            StringBuilder requestMsg = new StringBuilder();
//            while ((input = inputStream.read()) != -1) {
//                requestMsg.append((char) input);
//                if (requestMsg.lastIndexOf("\r\n\r\n") != -1) {
//                    break;
//                }
//            }
            //输出实体
//            System.out.println(requestMsg);

            System.out.println("有用户访问" + Thread.currentThread());
            //输出，类似作出响应
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);

            //构建响应
            String message = "{\"code\":\"200\", \"data\":\"hello world\"}";

            //http中HTTP/1.1 200 OK不能丢。
            //header和body之间有一个非常重要的空行
            String builder = "HTTP/1.1 200 OK" +
                    "Content-Type: application/json\n" +
                    "Content-Length: " +
                    message.getBytes().length +
                    "\n\n" +
                    message;

            printWriter.println(builder);
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //响应结束后立刻关闭连接
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
