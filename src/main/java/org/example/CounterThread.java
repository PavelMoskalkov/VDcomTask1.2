package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

class CounterThread implements Runnable {
    private final AtomicInteger counter;

    CounterThread(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (counter) {
                int currentValue = counter.get();
                if (currentValue >= Main.MAX_COUNT) {
                    break;
                }

                int newValue = currentValue + 1;
                counter.set(newValue);

                System.out.println(
                        "Текущее значение: " + currentValue +
                                ", Новое значение: " + newValue +
                                ", Id потока: " + Thread.currentThread().getId()
                );

                try (FileWriter fileWriter = new FileWriter(Main.FILE_NAME)) {
                    fileWriter.write(String.valueOf(newValue));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Thread.yield();
        }
    }
}
