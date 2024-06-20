package br.com.vr.application.controller;

import br.com.vr.domain.model.Cartao;
import br.com.vr.domain.service.CartaoService;
import br.com.vr.infrastructure.impl.CartaoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    @Operation(summary = "Criar um novo cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartão criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Cartão já existe no sistema")
    })
    @PostMapping
    public ResponseEntity<Cartao> criarCartao(@RequestBody Cartao cartao) {
        Cartao novoCartao = cartaoService.criarCartao(cartao);
        if (novoCartao == null) {
            return ResponseEntity.unprocessableEntity().body(cartao);
        }
        return ResponseEntity.status(201).body(novoCartao);
    }

    @Operation(summary = "Obter saldo de um cartão pelo número do cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo obtido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    @GetMapping("/{numeroCartao}/saldo")
    public ResponseEntity<Double> obterSaldo(@PathVariable String numeroCartao) {
        Cartao cartao = cartaoService.obterCartao(numeroCartao);
        if (cartao == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(cartao.getSaldo());
    }
}
