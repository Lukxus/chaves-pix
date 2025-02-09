package com.pix.chaves.repository;

import com.pix.chaves.domain.model.ContaCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaCadastroRepository extends JpaRepository<ContaCadastro, UUID> {

    Optional<ContaCadastro> findByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);

    boolean existsByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);


}
