package br.com.antoniosousa.desafio_itau.controller;

import br.com.antoniosousa.desafio_itau.domain.transacions.Transaction;
import br.com.antoniosousa.desafio_itau.domain.transacions.TransactionDto;
import br.com.antoniosousa.desafio_itau.domain.transacions.TransactionService;
import br.com.antoniosousa.desafio_itau.domain.transacions.TransactionStatistics;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transacao")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody @Valid TransactionDto transaction) {
        transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("transacao")
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("estatistica")
    public ResponseEntity<TransactionStatistics> getTransactionStatistics(@RequestParam("seconds") int seconds) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getStatistics(seconds));
    }
}
