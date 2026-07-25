package com.example.expensetracker.Service;

import com.example.expensetracker.DTO.Response.CategoryExpensedto;
import com.example.expensetracker.DTO.Response.DashboardDTO;
import com.example.expensetracker.DTO.Response.TransactionResponseDto;
import com.example.expensetracker.Entity.Transaction;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.Type;
import com.example.expensetracker.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public DashboardDTO getinfo(User user) {
        List<Transaction>userTransactions=transactionRepository.findByUser(user);
        double budget=4000.0;
        if(user.getBudget()!=null){
            budget=user.getBudget();
        }

        int count=userTransactions.size();
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        Double dbExpense=transactionRepository.sumExpensesBetweenDates(user,startOfMonth,today);
        double expense=(dbExpense!=null) ? dbExpense:0.0;
        int day= LocalDate.now().getDayOfMonth();
        List<CategoryExpensedto> categoryExpensedtos=transactionRepository.findCategoryExpensesByUser(user);
        return DashboardDTO.builder()
                .count(count)
                .expense(expense)
                .budget(budget)
                .remain(budget-expense)
                .dailyAvg(expense/day)
                .expensebyCategory(categoryExpensedtos)
                .build();
    }

    public List<TransactionResponseDto> getRecentTransaction(User user) {

        List<Transaction>transactions=transactionRepository.findTop5ByUserOrderByDateDesc(user);

        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionResponseDto.class))
                .collect(Collectors.toList());

    }
}
