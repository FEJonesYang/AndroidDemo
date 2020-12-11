package com.example.demo.mulThreadCP;

import java.util.PriorityQueue;

/**
 * @author JonesYang
 * @Data 2020-12-11
 * @Function 使用 Object.wait() 和 Object.notify() 实现生产者和消费者模式
 */
public class ConsumerAndProducer {
    private int queueSize = 10;
    private PriorityQueue<Integer> mQueue = new PriorityQueue<>(queueSize);

    public static void main(String[] args) {
        ConsumerAndProducer consumerAndProducer = new ConsumerAndProducer();
        Consumer consumer = consumerAndProducer.new Consumer();
        Producer producer = consumerAndProducer.new Producer();
        producer.start();
        consumer.start();
    }


    class Consumer extends Thread {
        @Override
        public void run() {
            //死循环
            while (true) {
                //锁住队列，多线程不能同时操作该队列
                synchronized (mQueue) {
                    //如果数据为空
                    while (mQueue.size() == 0) {
                        try {
                            System.out.println("队列空，等待数据");
                            //则进入等待状态
                            mQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            //唤醒其他线程
                            mQueue.notify();
                        }
                    }
                    //如果数据不为空
                    System.out.println(mQueue.peek());
                    mQueue.poll();
                    mQueue.notify();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (mQueue) {
                    //如果数据已经达到队列的最大值
                    while (mQueue.size() == queueSize) {
                        try {
                            System.out.println("数据满，等待空余空间...");
                            //则进入等待状态
                            mQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            //唤醒其他的线程
                            mQueue.notify();
                        }
                    }
                    mQueue.add(1);
                    mQueue.notify();
                }
            }
        }
    }

}




