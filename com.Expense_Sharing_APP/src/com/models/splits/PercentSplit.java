package com.models.splits;

import com.models.Users;

public class PercentSplit extends Split{
    double percent;
    public PercentSplit(Users user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
