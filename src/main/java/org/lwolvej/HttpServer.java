package org.lwolvej;

import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * http服务端
 *
 * @author lwolvej
 */
public class HttpServer {

    public static void main(final String... args) throws Exception {
        //将一个boss线程交给boss线程池
        ThreadPoolContext.bossExecutor.submit(
                new HttpBoss(new ServerSocket(
                        8080, 1, InetAddress.getByName("127.0.0.1")
                ))
        );
    }
}
