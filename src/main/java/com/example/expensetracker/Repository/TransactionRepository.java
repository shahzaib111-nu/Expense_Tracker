package com.example.expensetracker.Repository;

import com.example.expensetracker.Entity.Transaction;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserAndCategory(User user, Category category);

    Optional<Transaction> findByIdAndUser(Long id, User user);


    List<Transaction> findByUser(User user);

    List<Transaction> findTop5ByUserOrderByDateDesc(User user);
}
