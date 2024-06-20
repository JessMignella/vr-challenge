package br.com.vr.domain.validator;


import br.com.vr.domain.model.Cartao;
import org.springframework.stereotype.Component;

@Component
public class SaldoValidator implements TRansacaoValidator {

    @Override
    public String validar(Cartao cartao, String senha, double valor) {
        return cartao.getSaldo() < valor ? "SALDO_INSUFICIENTE" : null;
    }
}
