package br.com.antoniosousa.desafio_itau.domain.transacions;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionDto(
        @NotNull
        @PositiveOrZero
        BigDecimal valor,
        @Past
        @NotNull
        OffsetDateTime dataHora
) {
}
