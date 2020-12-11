package com.example.demo.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author JonesYang
 * @Data 2020-12-11
 * @Function 阻塞队列实现生产者和消费者模式
 */
public class ConsumerAndProducer {

    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> mQueue = new ArrayBlockingQueue<>(queueSize);

    public static void main(String[] args) {
        ConsumerAndProducer consumerAndProducer = new ConsumerAndProducer();
        Consumer consumer = consumerAndProducer.new Consumer();
        Producer producer = consumerAndProducer.new Producer();
        consumer.start();
        producer.start();
    }


    class Consumer extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends  Thread{
        @Override
        public void run() {
            while (true){
                try {
                    mQueue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
