package com.example.expensetracker.Controller;

import com.example.expensetracker.DTO.Response.DashboardDTO;
import com.example.expensetracker.DTO.Response.TransactionResponseDto;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;
    @GetMapping
    public ResponseEntity<DashboardDTO>DashboardInfo(@AuthenticationPrincipal User user){
         return ResponseEntity.ok(dashboardService.getinfo(user));

    }
    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionResponseDto>> recentTransaction(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(dashboardService.getRecentTransaction(user));
    }
}
