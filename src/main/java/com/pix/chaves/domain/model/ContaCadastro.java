package com.pix.chaves.domain.model;


import com.pix.chaves.domain.enums.TipoContaCadastro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "conta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaCadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta", nullable = false, length = 10)
    private TipoContaCadastro tipoContaCadastro;

    @Column(name = "numero_agencia", nullable = false, length = 4)
    private String numeroAgencia;

    @Column(name = "numero_conta", nullable = false, length = 8)
    private String numeroConta;
}
