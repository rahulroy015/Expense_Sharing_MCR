package com;


import com.models.ExpenseType;
import com.models.Type;
import com.models.Users;
import com.models.expense.ExpenseData;
import com.models.splits.EqualSplit;
import com.models.splits.ExactSplit;
import com.models.splits.PercentSplit;
import com.models.splits.Split;
import com.repository.ExpenseRepository;
import com.services.SplitWiseService;
import com.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Users user1 = new Users(1, "u1","u1@gmail.com","9890098900");
        Users user2 = new Users(2, "u2","u2@gmail.com","9999999999");
        Users user3 = new Users(3, "u3","u3@gmail.com","9898989899");
        Users user4 = new Users(4, "u4","u4@gmail.com","8976478292");

        // Adding Expenses
        ExpenseRepository expenseRepository = new ExpenseRepository();
        UserService userService = new UserService(expenseRepository);
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        SplitWiseService service = new SplitWiseService(expenseRepository);

        while (true) {
            Scanner scan = new Scanner(System.in);
            String[] commands = scan.nextLine().split(" ");
            Type type = Type.of(commands[0]);
            switch (type){
                case EXPENSE:
                    String userName = commands[1];
                    double amountSpend = Double.parseDouble(commands[2]);
                    int totalMembers = Integer.parseInt(commands[3]);
                    List<Split> splits = new ArrayList<>();
                    int expenseIndex = 3 + totalMembers + 1;
                    ExpenseType expense = ExpenseType.of(commands[expenseIndex]);
                    switch (expense){
                        case EQUAL:
                            for (int i = 0; i < totalMembers; i++) {
                                splits.add(new EqualSplit(userService.getUser(commands[4+i])));
                            }
                            service.addExpense(
                                    ExpenseType.EQUAL, amountSpend, userName, splits, new ExpenseData("GoaFlight")
                            );
                            break;
                        case EXACT:
                            for (int i = 0; i < totalMembers; i++) {
                                splits.add(
                                        new ExactSplit(
                                                userService.getUser(commands[4+i]),
                                                Double.parseDouble(commands[expenseIndex+i+1]))
                                );
                            }
                            service.addExpense(
                                    ExpenseType.EXACT, amountSpend, userName, splits, new ExpenseData("CabTickets")
                            );

                            break;
                        case PERCENT:
                            for (int i = 0; i < totalMembers; i++) {
                                splits.add(
                                        new PercentSplit(
                                                userService.getUser(commands[4+i]),
                                                Double.parseDouble(commands[expenseIndex+i+1]))
                                );
                            }
                            service.addExpense(
                                    ExpenseType.PERCENT, amountSpend, userName, splits, new ExpenseData("Dinner")
                            );
                            break;
                    }
                    break;
                case SHOW:
                    if(commands.length == 1)
                        service.showBalances();
                    else
                        service.showBalance(commands[1]);
                    break;
                case QUIT:
                    System.out.println("Quiting...");
                    return;
                default:
                    System.out.println("No Expected Argument Found");
                    break;
            }

        }

    }
}
