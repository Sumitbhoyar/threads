package com.example.multithreading;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class JoinNew {
    private static class Task implements Runnable{
        private CountDownLatch countDownLatch;
        private String name;
        public Task(CountDownLatch countDownLatch, String name) {
            this.countDownLatch = countDownLatch;
            this.name = name;
        }

        public void run() {
            try {
                Thread.sleep(new Random().nextInt(5000));
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting Main thread");
        CountDownLatch countDownLatch = new CountDownLatch(4);
        new Thread(new Task(countDownLatch, "Task1")).start();
        new Thread(new Task(countDownLatch, "Task2")).start();
        new Thread(new Task(countDownLatch, "Task3")).start();
        new Thread(new Task(countDownLatch, "Task4")).start();
        System.out.println("Waiting in Main thread");
        countDownLatch.await();
        System.out.println("Exiting Main thread");
    }
}