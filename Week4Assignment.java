

import java.util.*;
import java.util.stream.*;

public class Week4Assignment {

    // Record for Order
    record Order(int id, String customerName, double amount, String type) {}

    public static void main(String[] args) throws Exception
    {

        System.out.println("===== ORDER PROCESSING SYSTEM =====");

        List<Order> orders = List.of(
                new Order(1, "Dipti", 6000, "ONLINE"),
                new Order(2, "Rahul", 3000, "OFFLINE"),
                new Order(3, "Sneha", 8000, "ONLINE"),
                new Order(4, "Amit", 2000, "OFFLINE")
        );

        // Total Revenue
        double totalRevenue = orders.stream()
                .mapToDouble(Order::amount)
                .sum();

        System.out.println("Total Revenue: " + totalRevenue);

        // Premium Orders (amount > 5000)
        System.out.println("Premium Orders:");
        orders.stream()
                .filter(o -> o.amount() > 5000)
                .forEach(System.out::println);

        // Apply Discount using Switch Expression
        System.out.println("Orders After Discount:");

        orders.forEach(order -> {
            double discount = switch (order.type()) {
                case "ONLINE" -> order.amount() * 0.10;
                case "OFFLINE" -> order.amount() * 0.05;
                default -> 0;
            };

            double finalAmount = order.amount() - discount;

            System.out.println(order.customerName() +
                    " Final Amount: " + finalAmount);
        });

        // Call other sections
        bulkEmailSender();
        studentResultAnalyzer();
        logFormatter();
    }
    public static void bulkEmailSender() throws Exception {

        System.out.println("\n===== BULK EMAIL SENDER =====");

        int totalUsers = 1000;  // reduced for demo

        // Normal Threads
        long start1 = System.currentTimeMillis();

        for (int i = 0; i < totalUsers; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }).start();
        }

        long end1 = System.currentTimeMillis();
        System.out.println("Normal Thread Time: " + (end1 - start1) + " ms");

        // Virtual Threads (Java 21+)
        long start2 = System.currentTimeMillis();

        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < totalUsers; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {}
                });
            }
        }

        long end2 = System.currentTimeMillis();
        System.out.println("Virtual Thread Time: " + (end2 - start2) + " ms");
    }
    
    public static void studentResultAnalyzer() {

        System.out.println("\n===== STUDENT RESULT ANALYZER =====");

        List<Integer> marks = new ArrayList<>(List.of(78, 95, 60, 88, 45));

        // Topper
        int topper = marks.stream()
                .max(Integer::compare)
                .get();

        System.out.println("Topper Marks: " + topper);

        // Lowest Score
        int lowest = marks.stream()
                .min(Integer::compare)
                .get();

        System.out.println("Lowest Marks: " + lowest);

        // Using SequencedCollection (Java 21)
        System.out.println("First: " + marks.getFirst());
        System.out.println("Last: " + marks.getLast());

        // Reverse List
        marks.reversed().forEach(mark ->
                System.out.print(mark + " "));
    }
    public static void logFormatter() {

        System.out.println("\n\n===== LOG FORMATTER =====");

        String user = "Dipti";
        String status = "SUCCESS";

        String log = """
                ========================
                Application Log
                User: %s
                Status: %s
                ========================
                """.formatted(user, status);

        System.out.println(log);
    }
}
