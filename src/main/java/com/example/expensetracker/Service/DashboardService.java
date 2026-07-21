package com.example.expensetracker.Service;

import com.example.expensetracker.DTO.Response.DashboardDTO;
import com.example.expensetracker.DTO.Response.TransactionResponseDto;
import com.example.expensetracker.Entity.Transaction;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.Type;
import com.example.expensetracker.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public DashboardDTO getinfo(User user) {
        List<Transaction>userTransactions=transactionRepository.findByUser(user);
        double income=0;
        double expense=0;
        double balance=0;
        int count=0;
        for(Transaction transaction:userTransactions){
            count++;
            if(transaction.getType()== Type.INCOME){
                income+=transaction.getAmount();
            }
            else if (transaction.getType()==Type.EXPENSE){
                expense+=transaction.getAmount();
            }
        }

        balance=income-expense;

        return DashboardDTO.builder()
                .count(count)
                .income(income)
                .expense(expense)
                .balance(balance)
                .build();
    }

    public List<TransactionResponseDto> getRecentTransaction(User user) {

        List<Transaction>transactions=transactionRepository.findTop5ByUserOrderByDateDesc(user);

        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionResponseDto.class))
                .collect(Collectors.toList());

    }
}
