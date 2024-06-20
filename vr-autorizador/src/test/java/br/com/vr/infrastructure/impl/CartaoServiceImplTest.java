package br.com.vr.infrastructure.impl;

import br.com.vr.domain.model.Cartao;
import br.com.vr.infrastructure.validator.CartaoValidator;
import br.com.vr.infrastructure.validator.SaldoValidator;
import br.com.vr.infrastructure.validator.SenhaValidator;
import br.com.vr.infrastructure.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class CartaoServiceImplTest {

    @InjectMocks
    private CartaoServiceImpl cartaoService;

    @Mock
    private CartaoRepository cartaoRepository;

    @Spy
    private CartaoValidator cartaoValidator;

    @Spy
    private SenhaValidator senhaValidator;

    @Spy
    private SaldoValidator saldoValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartaoService = new CartaoServiceImpl(cartaoRepository, List.of(cartaoValidator, senhaValidator, saldoValidator));
    }

    @Test
    void testCriarCartao() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("6549873025634501");
        cartao.setSenha("1234");

        when(cartaoRepository.existsById("6549873025634501")).thenReturn(false);
        when(cartaoRepository.save(cartao)).thenReturn(cartao);

        Cartao novoCartao = cartaoService.criarCartao(cartao);

        assertNotNull(novoCartao);
        assertEquals(500.0, novoCartao.getSaldo());
        verify(cartaoRepository, times(1)).save(cartao);
    }

    @Test
    void testObterSaldo() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("6549873025634501");
        cartao.setSenha("1234");
        cartao.setSaldo(500.0);

        when(cartaoRepository.findById("6549873025634501")).thenReturn(Optional.of(cartao));

        Cartao encontrado = cartaoService.obterCartao("6549873025634501");

        assertNotNull(encontrado);
        assertEquals(500.0, encontrado.getSaldo());
    }

    @Test
    void testAutorizarTransacaoComSucesso() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("6549873025634501");
        cartao.setSenha("1234");
        cartao.setSaldo(500.0);

        when(cartaoRepository.findById("6549873025634501")).thenReturn(Optional.of(cartao));
        when(cartaoValidator.validar(cartao, "1234", 100.0)).thenReturn(null);
        when(senhaValidator.validar(cartao, "1234", 100.0)).thenReturn(null);
        when(saldoValidator.validar(cartao, "1234", 100.0)).thenReturn(null);

        String resultado = cartaoService.autorizarTransacao("6549873025634501", "1234", 100.0);

        assertEquals("OK", resultado);
        assertEquals(400.0, cartao.getSaldo());
        verify(cartaoRepository, times(1)).save(cartao);
    }

    @Test
    void testAutorizarTransacaoSaldoInsuficiente() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("6549873025634501");
        cartao.setSenha("1234");
        cartao.setSaldo(50.0);

        when(cartaoRepository.findById("6549873025634501")).thenReturn(Optional.of(cartao));
        when(cartaoValidator.validar(cartao, "1234", 100.0)).thenReturn(null);
        when(senhaValidator.validar(cartao, "1234", 100.0)).thenReturn(null);
        when(saldoValidator.validar(cartao, "1234", 100.0)).thenReturn("SALDO_INSUFICIENTE");

        String resultado = cartaoService.autorizarTransacao("6549873025634501", "1234", 100.0);

        assertEquals("SALDO_INSUFICIENTE", resultado);
    }

    @Test
    void testAutorizarTransacaoSenhaInvalida() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("6549873025634501");
        cartao.setSenha("1234");
        cartao.setSaldo(500.0);

        when(cartaoRepository.findById("6549873025634501")).thenReturn(Optional.of(cartao));
        when(cartaoValidator.validar(cartao, "1234", 100.0)).thenReturn(null);
        when(senhaValidator.validar(cartao, "1234", 100.0)).thenReturn("SENHA_INVALIDA");

        String resultado = cartaoService.autorizarTransacao("6549873025634501", "1234", 100.0);

        assertEquals("SENHA_INVALIDA", resultado);
    }

    @Test
    void testAutorizarTransacaoCartaoInexistente() {
        when(cartaoRepository.findById("6549873025634501")).thenReturn(Optional.empty());

        String resultado = cartaoService.autorizarTransacao("6549873025634501", "1234", 100.0);

        assertEquals("CARTAO_INEXISTENTE", resultado);
    }


}