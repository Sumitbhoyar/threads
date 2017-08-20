package com.example.multithreading;

import java.util.Random;

public class JoinLegacy {
    private static void run() {
        try {
            Thread.sleep(new Random().nextInt(5000));
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread task1 = new Thread(JoinLegacy::run,"Task1");
        Thread task2 = new Thread(JoinLegacy::run,"Task2");
        task1.start();
        task2.start();
        task1.join();
        task2.join();
    }
}