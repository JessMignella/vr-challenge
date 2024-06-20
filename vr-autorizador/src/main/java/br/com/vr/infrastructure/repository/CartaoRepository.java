package br.com.vr.infrastructure.repository;

import br.com.vr.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartaoRepository extends JpaRepository<Cartao, String> {
}