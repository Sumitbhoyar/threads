package com.example.multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WaitNotifyNew {
    public static class Resource{
        private Float id = new Random().nextFloat();
        public Float getId(){
            return id;
        }
    }
    public static class Producer implements Runnable{
        private BlockingQueue<Resource> resources;
        public Producer(BlockingQueue<Resource> resources) {
            this.resources = resources;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(new Random().nextInt(100));
                    resources.put(new Resource());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable{
        private BlockingQueue<Resource> resources;
        public Consumer(BlockingQueue<Resource> resources) {
            this.resources = resources;
        }

        @Override
        public void run() {
            while(true)  {
                Resource resource = null;
                try {
                    resource = resources.take();
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(resource.getId());
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Resource> resources = new ArrayBlockingQueue<>(2);
        new Thread(new Producer(resources)).start();
        new Thread(new Consumer(resources)).start();
    }
}