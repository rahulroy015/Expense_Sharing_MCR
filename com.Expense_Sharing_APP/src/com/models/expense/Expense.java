package com.models.expense;

import com.models.Users;
import com.models.splits.Split;

import java.util.List;
import java.util.UUID;



abstract public class Expense {
    private final String id;
    private double amount;
    private Users expensePaidBy;
    private List<Split> splits;
    private ExpenseData expenseData;


    public Expense(double amount, Users expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.expensePaidBy = expensePaidBy;
        this.splits = splits;
        this.expenseData = expenseData;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Users getExpensePaidBy() {
        return expensePaidBy;
    }

    public void setExpensePaidBy(Users expensePaidBy) {
        this.expensePaidBy = expensePaidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public ExpenseData getExpenseData() {
        return expenseData;
    }

    public void setExpenseData(ExpenseData expenseData) {
        this.expenseData = expenseData;
    }

    public abstract boolean validate();
}
