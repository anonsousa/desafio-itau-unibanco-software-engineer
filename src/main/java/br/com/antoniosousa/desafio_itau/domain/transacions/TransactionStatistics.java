package br.com.antoniosousa.desafio_itau.domain.transacions;

import java.math.BigDecimal;

public record TransactionStatistics(

        long count,
        BigDecimal sum,
        BigDecimal avg,
        BigDecimal min,
        BigDecimal max
) {
    public TransactionStatistics(long count, BigDecimal sum, BigDecimal avg, BigDecimal min, BigDecimal max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }
}
