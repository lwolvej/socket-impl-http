package org.lwolvej;

import java.util.concurrent.*;

/**
 * 维护两个线程池的上下文（类似netty中boss和work EventLoopGroup）
 *
 * @author lwolvej
 */
class ThreadPoolContext {

    //boss线程只需要一个，负责接受连接
    static final ExecutorService bossExecutor = Executors.newSingleThreadExecutor();

    //worker线程设置多个，同时设置完成后的存活时间为0S（立刻结束！）
    static final ExecutorService workerExecutor = new ThreadPoolExecutor(6, 6, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
}
