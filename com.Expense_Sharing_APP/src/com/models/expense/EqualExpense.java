package com.models.expense;

import java.util.List;

import com.models.Users;
import com.models.splits.EqualSplit;
import com.models.splits.Split;

public class EqualExpense extends  Expense{
    public EqualExpense(double amount, Users expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        super(amount, expensePaidBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        for(Split splits: getSplits()){
            if(!(splits instanceof EqualSplit)) return false;
        }
        return true;
    }
}
