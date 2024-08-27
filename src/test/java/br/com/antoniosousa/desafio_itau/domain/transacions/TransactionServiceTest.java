package br.com.antoniosousa.desafio_itau.domain.transacions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    @DisplayName("Should create transaction")
    void shouldCreateTransaction() {

        var value = BigDecimal.valueOf(123.25);

        TransactionDto transactionDto = new TransactionDto(value,
                OffsetDateTime.now().minusSeconds(10));

        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);


        Transaction transactionSaved = transactionService.save(transactionDto);

        Assertions.assertEquals(value.doubleValue(), transactionSaved.getValor().doubleValue());
        Assertions.assertNotNull(transactionSaved);

        Mockito.verify(transactionRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(transactionRepository).save(Mockito.isA(Transaction.class));
    }

    @Test
    @DisplayName("Should delete all transactions on database")
    void shouldDeleteAllTransactions() {


        transactionService.deleteAllTransactions();

        Mockito.verify(transactionRepository, Mockito.times(1)).deleteAll();

    }
  
}