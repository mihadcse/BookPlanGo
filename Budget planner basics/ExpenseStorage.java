package com.example.testing_for_project;

import java.io.*;
import java.util.ArrayList;

public class ExpenseStorage {
    private static final String FILENAME = "expenses.txt";

    public static void saveExpenses(ArrayList<Expense> expenses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Expense e : expenses) {
                writer.write(e.getDate() + "," + e.getDescription() + "," + e.getAmount());
                writer.newLine();
            }
        } catch (IOException e) {
            // Exception handler removed
        }
    }

    public static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        File file = new File(FILENAME);

        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String date = parts[0];
                    String description = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    expenses.add(new Expense(date, description, amount));
                }
            }
        } catch (IOException e) {
            // Exception handler removed
        }
        return expenses;
    }
}
