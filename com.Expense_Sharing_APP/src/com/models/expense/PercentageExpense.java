package com.models.expense;

import com.models.Users;
import com.models.splits.PercentSplit;
import com.models.splits.Split;

import java.util.List;

public class PercentageExpense extends  Expense{
    public PercentageExpense(double amount, Users expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        super(amount, expensePaidBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        double totalSplitPercent = 0;
        for(Split split: getSplits()){
            if(!(split instanceof PercentSplit)) return false;
            PercentSplit percentSplit = (PercentSplit) split;
            totalSplitPercent+=percentSplit.getPercent();
        }
        return 100 == totalSplitPercent;
    }

}
