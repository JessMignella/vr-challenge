package br.com.vr.domain.validator;

import br.com.vr.domain.model.Cartao;
import br.com.vr.infrastructure.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartaoValidator implements TRansacaoValidator {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Override
    public String validar(Cartao cartao, String senha, double valor) {
        return cartao == null ? "CARTAO_INEXISTENTE" : null;
    }
}