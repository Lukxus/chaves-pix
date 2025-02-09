package com.pix.chaves.services.contaCadastro;

import com.pix.chaves.rest.dto.request.CreateContaRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ContaCadastroService {

    @Resource
    private CreateContaCadastroAction createContaCadastroAction;

    public UUID createContaCadastro(CreateContaRequest createContaRequest) {
        return createContaCadastroAction.createContaCadastro(createContaRequest).getId();
    }
}
