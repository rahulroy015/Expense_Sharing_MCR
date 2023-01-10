package com.services;

import com.models.ExpenseType;
import com.models.Users;
import com.models.expense.*;
import com.models.splits.PercentSplit;
import com.models.splits.Split;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(ExpenseType expenseType, double amount,
                                        Users expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        switch (expenseType) {
            case EXACT:
                return new ExactExpense(amount, expensePaidBy, splits, expenseData);
            case PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount*percentSplit.getPercent())/100.0);
                }
                return new PercentageExpense(amount, expensePaidBy, splits, expenseData);
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }
                return new EqualExpense(amount, expensePaidBy, splits, expenseData);
            default:
                return null;
        }
    }
}
