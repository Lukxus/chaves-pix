package com.pix.chaves.mapper;

import com.pix.chaves.domain.model.ChavePix;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.rest.dto.response.ChavePixUpdateResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                .valorChave(safeString(chavePix.getValorChave()))
                .tipoConta(chavePix.getTipoConta())
                .numeroAgencia(safeString(chavePix.getNumeroAgencia()))
                .numeroConta(safeString(chavePix.getNumeroConta()))
                .nomeCorrentista(safeString(chavePix.getNomeCorrentista()))
                .sobrenomeCorrentista(safeString(chavePix.getSobrenomeCorrentista()))
                .dataHoraInclusao(safeDate(chavePix.getDataHoraInclusao()))
                .dataHoraInativacao(safeDate(chavePix.getDataHoraInativacao()))
                .build();
    }

    public static ChavePixUpdateResponse toUpdateResponse(ChavePix chavePix) {
        return ChavePixUpdateResponse.builder()
                .id(chavePix.getId())
                .tipoChave(chavePix.getTipoChave())
                .valorChave(safeString(chavePix.getValorChave()))
                .tipoConta(chavePix.getTipoConta())
                .numeroAgencia(safeString(chavePix.getNumeroAgencia()))
                .numeroConta(safeString(chavePix.getNumeroConta()))
                .nomeCorrentista(safeString(chavePix.getNomeCorrentista()))
                .sobrenomeCorrentista(safeString(chavePix.getSobrenomeCorrentista()))
                .dataHoraInclusao(safeDate(chavePix.getDataHoraInclusao()))
                .build();
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static String safeDate(LocalDateTime date) {
        return date == null ? "" : date.format(FORMATTER);
    }

    private static String safeString(String value) {
        return value == null ? "" : value;
    }
}
