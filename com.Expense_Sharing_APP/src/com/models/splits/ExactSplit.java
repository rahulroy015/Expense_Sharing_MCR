package com.models.splits;

import com.models.Users;

public class ExactSplit extends Split{
    public ExactSplit(Users user, double amount) {
        super(user);
        this.amount = amount;
    }
}
