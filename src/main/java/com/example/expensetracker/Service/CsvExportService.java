package com.example.expensetracker.Service;



import com.example.expensetracker.Entity.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class CsvExportService {

    public void writeTransactionsToCsv(Writer writer, List<Transaction> transactions) throws IOException {
        // Define CSV Headers matching your Expense Tracker fields
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("ID", "Title", "Amount", "Category", "Date")
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
            for (Transaction t : transactions) {
                csvPrinter.printRecord(
                        t.getId(),
                        t.getTitle(),
                        t.getAmount(),
                        t.getCategory(),
                        t.getDate()// or t.getDate() depending on your entity field
                );
            }
        }
    }
}