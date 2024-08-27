package br.com.antoniosousa.desafio_itau.domain.transacions;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_TRANSACTIONS")
public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        private BigDecimal valor;
        private OffsetDateTime dataHora;


        public BigDecimal getValor() {
                return valor;
        }

        public void setValor(BigDecimal valor) {
                this.valor = valor;
        }

        public OffsetDateTime getDataHora() {
                return dataHora;
        }

        public void setDataHora(OffsetDateTime dataHora) {
                this.dataHora = dataHora;
        }
}
