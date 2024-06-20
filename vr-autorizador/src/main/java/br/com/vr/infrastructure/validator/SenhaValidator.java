package br.com.vr.infrastructure.validator;

import br.com.vr.domain.model.Cartao;
import org.springframework.stereotype.Component;

@Component
public class SenhaValidator implements TRansacaoValidator {

    @Override
    public String validar(Cartao cartao, String senha, double valor) {
        return !cartao.getSenha().equals(senha) ? "SENHA_INVALIDA" : null;
    }
}