package br.com.vr.domain.service;

import br.com.vr.domain.model.Cartao;
public interface CartaoService {

    Cartao criarCartao(Cartao cartao);
    Cartao obterCartao(String numeroCartao);

     String autorizarTransacao(String numeroCartao, String senha, double valor);

}
