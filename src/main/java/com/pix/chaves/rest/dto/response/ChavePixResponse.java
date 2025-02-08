package com.pix.chaves.rest.dto.response;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChavePixResponse {

    private UUID id;
    private TipoChave tipoChave;
    private String valorChave;
    private TipoConta tipoConta;
    private String numeroAgencia;
    private String numeroConta;
    private String nomeCorrentista;
    private String sobrenomeCorrentista;
    private LocalDateTime dataHoraInclusao;
    private LocalDateTime dataHoraExclusao;

}