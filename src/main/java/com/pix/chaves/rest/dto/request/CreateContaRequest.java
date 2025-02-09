package com.pix.chaves.rest.dto.request;

import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.enums.TipoConta;
import com.pix.chaves.domain.enums.TipoContaCadastro;
import com.pix.chaves.utils.validation.valid.NumeroAgencia;
import com.pix.chaves.utils.validation.valid.NumeroConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateContaRequest {

    @NotNull(message = "O tipo de conta cadastro é obrigatório.")
    private TipoContaCadastro tipoContaCadastro;

    @NotBlank(message = "O número da agência é obrigatório.")
    @NumeroAgencia
    private String numeroAgencia;

    @NotBlank(message = "O número da conta é obrigatório.")
    @NumeroConta
    private String numeroConta;
}
