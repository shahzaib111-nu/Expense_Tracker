package com.example.expensetracker.Controller;

import com.example.expensetracker.DTO.Request.TransactionRequestDto;
import com.example.expensetracker.DTO.Response.TransactionResponseDto;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.Category;
import com.example.expensetracker.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {


    private final TransactionService transactionService;
    // filter on category
    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(
            @AuthenticationPrincipal User user,@RequestParam(required = false) Category category) {

        return ResponseEntity.ok(transactionService.getTransactions(user,category));
    }
    // add Transaction
    @PostMapping
    public ResponseEntity<TransactionResponseDto> addTransaction(
            @AuthenticationPrincipal User user,
            @RequestBody TransactionRequestDto transactionRequestDto) {

        return ResponseEntity.ok(transactionService.addTransaction(user, transactionRequestDto));
    }
    // delete Transaction
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(
            @AuthenticationPrincipal User user,
            @PathVariable Long id) {

        transactionService.deleteTransaction(user, id);
        return ResponseEntity.noContent().build();
    }
    // update Transaction
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> updateTransaction(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody TransactionRequestDto transactionRequestDto) {

        return ResponseEntity.ok(transactionService.updateTransaction(user, id, transactionRequestDto));
    }

}
