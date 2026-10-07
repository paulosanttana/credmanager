package com.credenciamento.repository;

import com.credenciamento.model.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    List<Credencial> findByUsuarioId(Long usuarioId);
}
