package com.credenciamento.service;

import com.credenciamento.model.Credencial;
import com.credenciamento.repository.CredencialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredencialService {

    private final CredencialRepository credencialRepository;

    public CredencialService(CredencialRepository credencialRepository) {
        this.credencialRepository = credencialRepository;
    }

    public List<Credencial> listarTodos() {
        return credencialRepository.findAll();
    }

    public Optional<Credencial> buscarPorId(Long id) {
        return credencialRepository.findById(id);
    }

    public List<Credencial> listarPorUsuario(Long usuarioId) {
        return credencialRepository.findByUsuarioId(usuarioId);
    }

    public Credencial salvar(Credencial credencial) {
        return credencialRepository.save(credencial);
    }

    public void deletar(Long id) {
        credencialRepository.deleteById(id);
    }
}
