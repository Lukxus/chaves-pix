package com.pix.chaves.rest.dto.request;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.enums.TipoConta;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ChavePixFilter {

    @Nullable
    private TipoChave tipoChave;

    @Nullable
    private String valorChavePix;

    @Nullable
    private TipoConta tipoConta;

    @Nullable
    private String numeroAgencia;

    @Nullable
    private String numeroConta;

    @Nullable
    private String nomeCorrentista;

    @Nullable
    private String sobrenomeCorrentista;

    @Nullable
    private LocalDate dataInclusao;

    @Nullable
    private LocalDate dataInativacao;

}