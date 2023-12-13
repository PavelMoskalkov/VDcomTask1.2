package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    protected static final String FILE_NAME = "out.txt";
    public static int MAX_COUNT;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (MAX_COUNT <= 0 || MAX_COUNT % 2 != 0) {
            System.out.println("Ошибка! Введите число, которое больше 0 и кратно 2.");
            System.out.print("Повторите ввод: ");
            MAX_COUNT = scanner.nextInt();
        }
        try {
            AtomicInteger counter = new AtomicInteger(0);
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            fileWriter.write("0");
            fileWriter.close();

            Thread thread1 = new Thread(new CounterThread(counter));
            Thread thread2 = new Thread(new CounterThread(counter));

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            System.out.println("Конечное значение: " + counter.get());

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("Значение, содержащееся в файле " + FILE_NAME + ": " + line);
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}