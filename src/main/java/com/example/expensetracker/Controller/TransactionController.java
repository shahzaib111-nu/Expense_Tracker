package com.example.expensetracker.Controller;

import com.example.expensetracker.DTO.Request.TransactionRequestDto;
import com.example.expensetracker.DTO.Response.TransactionResponseDto;
import com.example.expensetracker.Entity.Transaction;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.Category;
import com.example.expensetracker.Service.CsvExportService;
import com.example.expensetracker.Service.TransactionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {


    private final TransactionService transactionService;
    private final CsvExportService csvExportService;
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
    @GetMapping("/export/csv")
    public void exportToCSV(HttpServletResponse response, Authentication authentication) throws IOException {
        // Set content type and attachment headers
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"transactions.csv\"");

        // Fetch current user's transactions from DB
        String userEmail = authentication.getName();
        List<Transaction> transactions = transactionService.getTransactionsByUserEmail(userEmail);

        // Write CSV data directly to HTTP response writer
        csvExportService.writeTransactionsToCsv(response.getWriter(), transactions);
    }

}
