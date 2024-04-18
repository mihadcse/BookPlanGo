package com.example.testing_for_project;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;

public class ExpenseTrackerController {
    @FXML
    private ListView<Expense> expenseListView;

    private ArrayList<Expense> expenses;

    @FXML
    public void initialize() {
        expenses = ExpenseStorage.loadExpenses();
        expenseListView.getItems().addAll(expenses);
    }

    @FXML
    private void handleAddExpense() {
        // Create input dialogs for date, description, and amount
        TextInputDialog dateDialog = new TextInputDialog();
        dateDialog.setTitle("Add Expense");
        dateDialog.setHeaderText("Enter date (YYYY-MM-DD):");
        dateDialog.setContentText("Date:");

        TextInputDialog descriptionDialog = new TextInputDialog();
        descriptionDialog.setTitle("Add Expense");
        descriptionDialog.setHeaderText("Enter description:");
        descriptionDialog.setContentText("Description:");

        TextInputDialog amountDialog = new TextInputDialog();
        amountDialog.setTitle("Add Expense");
        amountDialog.setHeaderText("Enter amount:");
        amountDialog.setContentText("Amount:");

        // Show input dialogs and get user input
        dateDialog.showAndWait();
        descriptionDialog.showAndWait();
        amountDialog.showAndWait();

        String date = dateDialog.getResult();
        String description = descriptionDialog.getResult();
        String amountStr = amountDialog.getResult();
        double amount = Double.parseDouble(amountStr);

        // Create new expense and add to list
        Expense newExpense = new Expense(date, description, amount);
        expenses.add(newExpense);
        expenseListView.getItems().add(newExpense);

        // Save expenses
        ExpenseStorage.saveExpenses(expenses);
    }

    @FXML
    private void handleViewExpenses() {
        // Expenses are already loaded in ListView
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
