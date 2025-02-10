package com.pix.chaves.repository;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.model.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChavePixRepository extends CrudRepository<ChavePix, UUID>, JpaSpecificationExecutor<ChavePix> {

    boolean existsByValorChave(String valorChave);

    long countByNumeroAgenciaAndNumeroContaAndDataHoraInativacaoIsNull(String numeroAgencia, String numeroConta);

    Optional<ChavePix> findByIdAndDataHoraInativacaoIsNull(UUID id);


}
