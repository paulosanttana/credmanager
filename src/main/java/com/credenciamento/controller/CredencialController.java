package com.credenciamento.controller;

import com.credenciamento.model.Credencial;
import com.credenciamento.model.Usuario;
import com.credenciamento.service.CredencialService;
import com.credenciamento.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credenciais")
public class CredencialController {

    private final CredencialService credencialService;
    private final UsuarioService usuarioService;

    public CredencialController(CredencialService credencialService, UsuarioService usuarioService) {
        this.credencialService = credencialService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Credencial> listarTodos() {
        return credencialService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Credencial> listarPorUsuario(@PathVariable Long usuarioId) {
        return credencialService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credencial> buscarPorId(@PathVariable Long id) {
        return credencialService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Credencial credencial) {
        Long usuarioId = credencial.getUsuario() != null ? credencial.getUsuario().getId() : null;

        if (usuarioId == null || usuarioService.buscarPorId(usuarioId).isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário informado não existe");
        }

        Usuario usuario = usuarioService.buscarPorId(usuarioId).get();
        credencial.setUsuario(usuario);

        Credencial salva = credencialService.salvar(credencial);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credencial> atualizar(@PathVariable Long id, @Valid @RequestBody Credencial credencialAtualizada) {
        return credencialService.buscarPorId(id)
                .map(credencial -> {
                    credencial.setTipo(credencialAtualizada.getTipo());
                    credencial.setNumero(credencialAtualizada.getNumero());
                    credencial.setStatus(credencialAtualizada.getStatus());
                    return ResponseEntity.ok(credencialService.salvar(credencial));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (credencialService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        credencialService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
