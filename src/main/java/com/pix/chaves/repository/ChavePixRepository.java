package com.pix.chaves.repository;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.model.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {

    boolean existsByValorChave(String valorChave);

    long countByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);

    Optional<ChavePix> findByValorChave(String valorChave);

    List<ChavePix> findByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);

    List<ChavePix> findByTipoChave(TipoChave tipoChave);

    List<ChavePix> findByNomeCorrentistaContainingIgnoreCaseAndSobrenomeCorrentistaContainingIgnoreCase(
            String nomeCorrentista, String sobrenomeCorrentista);


    List<ChavePix> findByDataHoraInclusaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<ChavePix> findByDataHoraInativacaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);


}
