package br.com.antoniosousa.desafio_itau.domain.transacions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByDataHoraBetween(OffsetDateTime start, OffsetDateTime end);

}
