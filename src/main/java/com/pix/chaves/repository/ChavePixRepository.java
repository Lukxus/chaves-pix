package com.pix.chaves.repository;

import com.pix.chaves.domain.model.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {

    boolean existsByValorChave(String valorChave);

    Optional<ChavePix> findByValorChave(String valorChave);

    Optional<ChavePix> findByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);
}
