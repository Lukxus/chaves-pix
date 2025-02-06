package com.pix.chaves.mapper;

import com.pix.chaves.domain.dto.ChavePixRequest;
import com.pix.chaves.domain.dto.ChavePixResponse;
import com.pix.chaves.domain.model.ChavePix;

import java.time.LocalDateTime;

public class ChavePixMapper {

    public static ChavePix toEntity(ChavePixRequest request) {
        return ChavePix.builder()
                .tipoChave(request.getTipoChave())
                .valorChave(request.getValorChave())
                .tipoConta(request.getTipoConta())
                .numeroAgencia(request.getNumeroAgencia())
                .numeroConta(request.getNumeroConta())
                .nomeCorrentista(request.getNomeCorrentista())
                .sobrenomeCorrentista(request.getSobrenomeCorrentista())
                .dataHoraInclusao(LocalDateTime.now())
                .build();
    }

    public static ChavePixResponse toResponse(ChavePix chavePix) {
        return new ChavePixResponse(chavePix);
    }
}
