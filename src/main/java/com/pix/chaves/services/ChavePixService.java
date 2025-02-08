package com.pix.chaves.services;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ChavePixService {

    @Resource
    private UpdateChavePixAction updateChavePixAction;

    @Resource
    private ReadChavePixAction readChavePixAction;

    @Resource
    private CreateChavePixAction createChavePixAction;

    @Resource
    private RemoveChavePixAction removeChavePixAction;

    public UUID criarChavePix(CreateChavePixRequest request) {
        return createChavePixAction.createChavePix(request).getId();
    }

    public ChavePixResponse atualizarChavePix(UpdateChavePixRequest request) {
        return ChavePixMapper.toResponse(updateChavePixAction.atualizarChavePix(request));
    }

    public ChavePixResponse inativarChavePix(UUID id) {
        return ChavePixMapper.toResponse(removeChavePixAction.removeChavePix(id));
    }

    public ChavePixResponse consultarChavePorId(UUID id) {
        return ChavePixMapper.toResponse(readChavePixAction.findById(id));
    }

    public List<ChavePixResponse> consultarChavePorAgenciaConta(String conta, String agencia) {
        return readChavePixAction.findByAgenciaConta(conta, agencia);
    }

    public List<ChavePixResponse> consultarChavePorTipoChave(TipoChave tipoChave) {
        return readChavePixAction.findByTipoChave(tipoChave);
    }

    public List<ChavePixResponse> consultarChavePorNomeCorrentista(String nomeCorrentista, String sobrenomeCorrentista) {
        return readChavePixAction.findByNomeCorrentista(nomeCorrentista, sobrenomeCorrentista);
    }

    public List<ChavePixResponse> consultarChavePorDataInclusao(LocalDateTime dataInclusaoInicio, LocalDateTime dataInclusaoFinal) {
        return readChavePixAction.findByDataInclusao(dataInclusaoInicio, dataInclusaoFinal);
    }

    public List<ChavePixResponse> consultarChavePorDataExclusao(LocalDateTime dataExclusaoInicio, LocalDateTime dataExclusaoFinal) {
        return readChavePixAction.findByDataExclusao(dataExclusaoInicio, dataExclusaoFinal);
    }
}
