package com.pix.chaves.mapper;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChavePixMapper {

    public static ChavePix toEntity(CreateChavePixRequest request) {
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
        return ChavePixResponse.builder()
                .id(chavePix.getId())
                .tipoChave(chavePix.getTipoChave())
                .valorChave(chavePix.getValorChave())
                .tipoConta(chavePix.getTipoConta())
                .numeroAgencia(chavePix.getNumeroAgencia())
                .numeroConta(chavePix.getNumeroConta())
                .nomeCorrentista(chavePix.getNomeCorrentista())
                .sobrenomeCorrentista(chavePix.getSobrenomeCorrentista())
                .dataHoraInclusao(chavePix.getDataHoraInclusao())
                .dataHoraExclusao(chavePix.getDataHoraInativacao())
                .build();
    }
}
