package com.guojing.common;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 三个线程交替打印，线程1打印1,2,3；接着线程2打印4,5,6；接着线程3打印7,8,9；依此类推直到打印到75；
 *
 * @author: guojing
 * @date: 2021-06-26
 * @time: 上午10:55
 */
public class SolutionTest3 {

    // 线程1打印的初始许可证
    private static Semaphore s1 = new Semaphore(1);

    // 线程2打印的初始许可证
    private static Semaphore s2 = new Semaphore(0);

    // 线程2打印的初始许可证
    private static Semaphore s3 = new Semaphore(0);

    public static volatile AtomicInteger atomicCount = new AtomicInteger(1);

    public static void main(String[] args) throws Exception {
        new PrintThread(s1, s2, "线程1", 75).start();
        Thread.sleep(1000);
        new PrintThread(s2, s3, "线程2", 75).start();
        Thread.sleep(1000);
        new PrintThread(s3, s1, "线程3", 75).start();
        Thread.sleep(1000);
    }

    static class PrintThread extends Thread {

        // 总数
        private int num;

        // 线程名称
        private String threadName;

        // 开始信号量
        private Semaphore startSemaphore;

        // 结束信号量
        private Semaphore endSemaphore;


        public PrintThread(Semaphore startSemaphore, Semaphore endSemaphore, String name, int num) {
            this.startSemaphore = startSemaphore;
            this.endSemaphore = endSemaphore;
            this.threadName = name;
            this.num = num;
        }

        public void run() {
            try {
                for (int i = atomicCount.get(); i <= num; i = atomicCount.addAndGet(3)) {
                    startSemaphore.acquire();
                    System.out.println(threadName + "---->" + i + "," + (i + 1) + "," + (i + 2));
                    endSemaphore.release();
                }

            } catch (Exception e) {

            }

        }
    }
}
