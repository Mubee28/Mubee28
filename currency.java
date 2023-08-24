package com.mubee;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class currency {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);


            Map<String, Double> exchangeRates = new HashMap<>();
            exchangeRates.put("USD", 1.0);
            exchangeRates.put("EUR", 0.85);
            exchangeRates.put("GBP", 0.73);

            System.out.println("Welcome to Currency Converter!");
            System.out.println("Supported currencies: USD, EUR, GBP");

            System.out.print("Enter the amount: ");
            double amount;
            while (true) {
                try {
                    amount = Double.parseDouble(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a valid amount: ");
                }
            }

            String sourceCurrency;
            while (true) {
                System.out.print("Enter the source currency (USD/EUR/GBP): ");
                sourceCurrency = scanner.nextLine().toUpperCase();
                if (exchangeRates.containsKey(sourceCurrency)) {
                    break;
                }
                System.out.println("Invalid currency. Please enter a valid source currency.");
            }

            String targetCurrency;
            while (true) {
                System.out.print("Enter the target currency (USD/EUR/GBP): ");
                targetCurrency = scanner.nextLine().toUpperCase();
                if (exchangeRates.containsKey(targetCurrency)) {
                    break;
                }
                System.out.println("Invalid currency. Please enter a valid target currency.");
            }

            double sourceRate = exchangeRates.get(sourceCurrency);
            double targetRate = exchangeRates.get(targetCurrency);


            if (!sourceCurrency.equals("USD")) {
                amount *= 1 / sourceRate;
            }

            if (!targetCurrency.equals("USD")) {
                amount *= targetRate;
            }

            System.out.println("Result: " + amount + " " + targetCurrency);
        }
    }
