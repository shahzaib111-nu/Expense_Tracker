package com.example.expensetracker.Repository;

import com.example.expensetracker.DTO.Response.CategoryExpensedto;
import com.example.expensetracker.Entity.Transaction;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserAndCategory(User user, Category category);

    Optional<Transaction> findByIdAndUser(Long id, User user);


    List<Transaction> findByUser(User user);

    List<Transaction> findTop5ByUserOrderByDateDesc(User user);

    List<Transaction> findByUserEmail(String userEmail);
    @Query("""
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Transaction t
    WHERE t.user = :user
      AND t.date BETWEEN :startDate AND :endDate
    """)
    double sumExpensesBetweenDates(@Param("user") User user,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.example.expensetracker.DTO.Response.CategoryExpensedto(t.category, SUM(t.amount)) " +
            "FROM Transaction t " +
            "WHERE t.user = :user " +
            "GROUP BY t.category")
    List<CategoryExpensedto> findCategoryExpensesByUser(@Param("user") User user);
}
