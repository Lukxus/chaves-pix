package com.pix.chaves.services.chavesPix;

import com.pix.chaves.mapper.ChavePixMapper;
import com.pix.chaves.rest.dto.request.ChavePixFilter;
import com.pix.chaves.rest.dto.request.CreateChavePixRequest;
import com.pix.chaves.rest.dto.request.UpdateChavePixRequest;
import com.pix.chaves.rest.dto.response.ChavePixResponse;
import com.pix.chaves.rest.dto.response.ChavePixUpdateResponse;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public ChavePixUpdateResponse atualizarChavePix(UpdateChavePixRequest request) {
        return ChavePixMapper.toUpdateResponse(updateChavePixAction.atualizarChavePix(request));
    }

    public ChavePixResponse inativarChavePix(UUID id) {
        return ChavePixMapper.toResponse(removeChavePixAction.removeChavePix(id));
    }

    public ChavePixResponse consultarChavePorId(UUID id) {
        return ChavePixMapper.toResponse(readChavePixAction.findById(id));
    }

    public Page<ChavePixResponse> consultarChavePaginada(ChavePixFilter chavePixFilter, Pageable pageable) {
        return readChavePixAction.getChavesPaginada(chavePixFilter, pageable);
    }
}
