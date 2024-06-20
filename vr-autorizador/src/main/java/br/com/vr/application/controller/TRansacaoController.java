package br.com.vr.application.controller;

import br.com.vr.domain.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TRansacaoController {

    private final CartaoService cartaoService;

    @Operation(summary = "Autoriza uma transação com cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação autorizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro ao processar a transação")
    })
    @PostMapping
    public ResponseEntity<String> autorizarTransacao(@RequestBody Map<String, Object> payload) {
        String numeroCartao = (String) payload.get("numeroCartao");
        String senha = (String) payload.get("senhaCartao");
        double valor = (Double) payload.get("valor");

        String resultado = cartaoService.autorizarTransacao(numeroCartao, senha, valor);

        if ("OK".equals(resultado)) {
            return ResponseEntity.status(201).body("OK");
        }
        return ResponseEntity.unprocessableEntity().body(resultado);
    }
}
