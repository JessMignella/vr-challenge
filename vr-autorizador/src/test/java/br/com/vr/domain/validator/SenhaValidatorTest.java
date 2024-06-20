package br.com.vr.domain.validator;

import br.com.vr.domain.model.Cartao;
import br.com.vr.infrastructure.validator.SenhaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;


class SenhaValidatorTest {

    @InjectMocks
    private SenhaValidator senhaValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidarSenhaInvalida() {
        Cartao cartao = new Cartao();
        cartao.setSenha("1234");
        String resultado = senhaValidator.validar(cartao, "4321", 100.0);
        assertEquals("SENHA_INVALIDA", resultado);
    }

    @Test
    void testValidarSenhaValida() {
        Cartao cartao = new Cartao();
        cartao.setSenha("1234");
        String resultado = senhaValidator.validar(cartao, "1234", 100.0);
        assertNull(resultado);
    }
}
