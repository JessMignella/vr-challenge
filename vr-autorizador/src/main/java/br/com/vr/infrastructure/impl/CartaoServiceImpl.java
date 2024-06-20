package br.com.vr.infrastructure.impl;

import br.com.vr.domain.model.Cartao;
import br.com.vr.domain.service.CartaoService;
import br.com.vr.domain.validator.TRansacaoValidator;
import br.com.vr.infrastructure.repository.CartaoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartaoServiceImpl  implements CartaoService {

    private final CartaoRepository cartaoRepository;
    private final List<TRansacaoValidator> transacaoValidators;


    @Transactional
    public Cartao criarCartao(Cartao cartao) {
        return Optional.of(cartao)
                .filter(c -> !cartaoRepository.existsById(c.getNumeroCartao()))
                .map(c -> {
                    c.setSaldo(500.0);
                    return cartaoRepository.save(c);
                })
                .orElse(null);
    }

    public Cartao obterCartao(String numeroCartao) {
        return cartaoRepository.findById(numeroCartao).orElse(null);
    }

    @Transactional
    public String autorizarTransacao(String numeroCartao, String senha, double valor) {
        return cartaoRepository.findById(numeroCartao)
                .map(cartao -> transacaoValidators.stream()
                        .map(validator -> validator.validar(cartao, senha, valor))
                        .filter(result -> result != null)
                        .findFirst()
                        .orElseGet(() -> {
                            cartao.setSaldo(cartao.getSaldo() - valor);
                            cartaoRepository.save(cartao);
                            return "OK";
                        }))
                .orElse("CARTAO_INEXISTENTE");
    }
}