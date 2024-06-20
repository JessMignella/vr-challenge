package br.com.vr.domain.validator;

import br.com.vr.domain.model.Cartao;
import br.com.vr.infrastructure.validator.SaldoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SaldoValidatorTest {


    @InjectMocks
    private SaldoValidator saldoValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidarSaldoInsuficiente() {
        Cartao cartao = new Cartao();
        cartao.setSaldo(50.0);
        String resultado = saldoValidator.validar(cartao, "1234", 100.0);
        assertEquals("SALDO_INSUFICIENTE", resultado);
    }

    @Test
    void testValidarSaldoSuficiente() {
        Cartao cartao = new Cartao();
        cartao.setSaldo(150.0);
        String resultado = saldoValidator.validar(cartao, "1234", 100.0);
        assertNull(resultado);
    }
}

