package com.pix.chaves.services.contaCadastro;

import com.pix.chaves.domain.enums.TipoContaCadastro;
import org.springframework.stereotype.Service;

@Service
public class ContaCadastroService {

    public TipoContaCadastro obterTipoConta(String numeroAgencia, String numeroConta) {
        int numeroValidador = Integer.parseInt(numeroConta) + Integer.parseInt(numeroAgencia);
        if (numeroValidador % 2 == 0) {
            return TipoContaCadastro.JURIDICA;
        } else {
            return TipoContaCadastro.FISICA;
        }
    }

}
