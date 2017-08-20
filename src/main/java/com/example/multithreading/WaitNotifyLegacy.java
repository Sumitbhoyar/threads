package com.example.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaitNotifyLegacy {
    public static class Resource{
        private Float id = new Random().nextFloat();
        public Float getId(){
            return id;
        }
    }
    public static class Producer implements Runnable{
        private List<Resource> resources;
        public Producer(List<Resource> resources) {
            this.resources = resources;
        }

        @Override
        public void run() {
            while(true) synchronized (resources) {
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resources.add(new Resource());
                resources.notify();
                try {
                    resources.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable{
        private List<Resource> resources;
        public Consumer(List<Resource> resources) {
            this.resources = resources;
        }

        @Override
        public void run() {
            while(true) synchronized (resources) {
                Resource resource = resources.get(0);
                resources.remove(0);
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(resource.getId());
                resources.notify();
                try {
                    resources.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Resource> resources = new ArrayList<>();
        new Thread(new Producer(resources)).start();
        new Thread(new Consumer(resources)).start();
    }
}