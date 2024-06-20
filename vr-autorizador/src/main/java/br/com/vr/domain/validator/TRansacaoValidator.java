package br.com.vr.domain.validator;

import br.com.vr.domain.model.Cartao;

public interface TRansacaoValidator {
    String validar(Cartao cartao, String senha, double valor);
}
