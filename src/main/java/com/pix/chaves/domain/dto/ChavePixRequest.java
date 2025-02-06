
package com.pix.chaves.domain.dto;


import com.pix.chaves.domain.enums.TipoChave;
import com.pix.chaves.domain.enums.TipoConta;
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
public class ChavePixRequest {

    @jakarta.validation.constraints.NotNull(message = "O tipo da chave é obrigatório.")
    private TipoChave tipoChave;

    @NotBlank(message = "O valor da chave é obrigatório.")
    @Size(max = 77, message = "O valor da chave deve ter no máximo 77 caracteres.")
    private String valorChave;

    @NotNull(message = "O tipo de conta é obrigatório.")
    private TipoConta tipoConta;

    @NotBlank(message = "O número da agência é obrigatório.")
    @Size(max = 4, message = "O número da agência deve ter no máximo 4 dígitos.")
    private String numeroAgencia;

    @NotBlank(message = "O número da conta é obrigatório.")
    @Size(max = 8, message = "O número da conta deve ter no máximo 8 dígitos.")
    private String numeroConta;

    @NotBlank(message = "O nome do correntista é obrigatório.")
    @Size(max = 30, message = "O nome do correntista deve ter no máximo 30 caracteres.")
    private String nomeCorrentista;

    @Size(max = 45, message = "O sobrenome do correntista deve ter no máximo 45 caracteres.")
    private String sobrenomeCorrentista;
}
