package br.com.vr.application.controller;

import br.com.vr.domain.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> autorizarTransacao( @SchemaProperties( {
            @SchemaProperty(name = "numeroCartao", schema = @Schema(type = "string", example = "1234567890123456")),
            @SchemaProperty(name = "senhaCartao", schema = @Schema(type = "string", example = "1234")),
            @SchemaProperty(name = "valor", schema = @Schema(type = "number", format = "double", example = "100.0"))
    })

            @RequestBody Map<String, Object> payload) {

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
