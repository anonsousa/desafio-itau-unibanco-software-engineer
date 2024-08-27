package br.com.antoniosousa.desafio_itau.domain.transacions;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    @Transactional
    public Transaction save(TransactionDto transaction) {
        Transaction transactionEntity = new Transaction();
        BeanUtils.copyProperties(transaction, transactionEntity);
        return transactionRepository.save(transactionEntity);
    }

    @Transactional
    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }

    public TransactionStatistics getStatistics(int seconds){
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00"));
        OffsetDateTime start = now.minusSeconds(seconds);


        List<Transaction> recentTransactions = transactionRepository.findByDataHoraBetween(start, now);

        return calculateStatistics(recentTransactions);
    }


    private TransactionStatistics calculateStatistics(List<Transaction> transactions){
        //SUM
        BigDecimal sum = transactions.stream()
                .map(Transaction::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //AVG
        BigDecimal avg = transactions.isEmpty()? BigDecimal.ZERO : sum.divide(BigDecimal.valueOf(transactions.size()), RoundingMode.HALF_UP);

        // MIN
        BigDecimal min = transactions.stream()
                .map(Transaction::getValor)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        //MIN
        BigDecimal max = transactions.stream()
                .map(Transaction::getValor)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return new TransactionStatistics(transactions.size(), sum, avg, min, max);
    }





}
