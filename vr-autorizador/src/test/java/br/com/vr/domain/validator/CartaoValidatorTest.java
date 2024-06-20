package br.com.vr.domain.validator;

import br.com.vr.domain.model.Cartao;
import br.com.vr.infrastructure.repository.CartaoRepository;
import br.com.vr.infrastructure.validator.CartaoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CartaoValidatorTest {


    @InjectMocks
    private CartaoValidator cartaoValidator;

    @Mock
    private CartaoRepository cartaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidarCartaoInexistente() {
        when(cartaoRepository.findById("6549873025634501")).thenReturn(java.util.Optional.empty());
        String resultado = cartaoValidator.validar(null, "1234", 100.0);
        assertEquals("CARTAO_INEXISTENTE", resultado);
    }

    @Test
    void testValidarCartaoExistente() {
        Cartao cartao = new Cartao();
        when(cartaoRepository.findById("6549873025634501")).thenReturn(java.util.Optional.of(cartao));
        String resultado = cartaoValidator.validar(cartao, "1234", 100.0);
        assertNull(resultado);
    }
}