package com.services;
import com.models.Users;
import com.models.Users;
import com.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    ExpenseRepository expenseRepository;

    public UserService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public void addUser(Users user){
        expenseRepository.addUser(user);
    }
    public Users getUser(String userName){
        return expenseRepository.getUser(userName);
    }
}
