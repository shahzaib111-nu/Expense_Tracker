package com.example.expensetracker.Service;

import com.example.expensetracker.DTO.Request.TransactionRequestDto;
import com.example.expensetracker.DTO.Response.TransactionResponseDto;
import com.example.expensetracker.Entity.Transaction;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.Category;
import com.example.expensetracker.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public List<TransactionResponseDto> getTransactions(User user, Category category) {

        List<Transaction> transactions;

        if (category ==null) {
            transactions = transactionRepository.findByUser(user);
        } else {
            transactions = transactionRepository.findByUserAndCategory(user, category);
        }

        return transactions.stream()
                .map(transaction -> new TransactionResponseDto(
                        transaction.getId(),
                        transaction.getTitle(),
                        transaction.getAmount(),
                        transaction.getType(),
                        transaction.getCategory(),
                        transaction.getDate()
                ))
                .toList();
    }

    public TransactionResponseDto addTransaction(User user, TransactionRequestDto transactionRequestDto) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTitle(transactionRequestDto.getTitle());
        transaction.setAmount(transactionRequestDto.getAmount());
        transaction.setType(transactionRequestDto.getType());
        transaction.setCategory(transactionRequestDto.getCategory());
        transaction.setDate(transactionRequestDto.getDate());

        Transaction savedTransaction = transactionRepository.save(transaction);

        return modelMapper
                .map(savedTransaction, TransactionResponseDto.class);
    }

    public void deleteTransaction(User user, Long id) {
        Transaction transaction = transactionRepository.findByIdAndUser(id, user).orElseThrow(() -> new RuntimeException("Transaction not found"));
        transactionRepository.deleteById(id);
    }

    public TransactionResponseDto updateTransaction(User user, Long id, TransactionRequestDto transactionRequestDto) {
        Transaction transaction = transactionRepository.findByIdAndUser(id, user).orElseThrow(() -> new RuntimeException("Transaction not found"));
        modelMapper.map(transactionRequestDto, transaction);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return modelMapper
                .map(savedTransaction, TransactionResponseDto.class);
    }
}
