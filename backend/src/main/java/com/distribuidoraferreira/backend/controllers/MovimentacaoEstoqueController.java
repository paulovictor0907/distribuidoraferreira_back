package com.distribuidoraferreira.backend.controllers;

import com.distribuidoraferreira.backend.dtos.GenericResponse;
import com.distribuidoraferreira.backend.dtos.MovimentacaoEstoqueRequest;
import com.distribuidoraferreira.backend.dtos.MovimentacaoEstoqueResponse;
import com.distribuidoraferreira.backend.services.interfaces.MovimentacaoEstoqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estoque/movimentacao")
public class MovimentacaoEstoqueController {
    @Autowired
    MovimentacaoEstoqueService movimentacaoEstoqueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovimentacaoEstoque(@RequestBody MovimentacaoEstoqueRequest movimentacaoEstoqueRequest) {
        movimentacaoEstoqueService.addMovimentacaoEstoque(movimentacaoEstoqueRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<MovimentacaoEstoqueResponse>>> getMovimentacoesEstoque() {
        return ResponseEntity.ok().body(movimentacaoEstoqueService.getMovimentacoesEstoque());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getMovimentacaoEstoqueById(@PathVariable Long id) {
        return ResponseEntity.ok().body(movimentacaoEstoqueService.getMovimentacaoEstoqueById(id));
    }

    @GetMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<MovimentacaoEstoqueResponse>>> getMovimentacoesEstoqueByProdutoId(
            @PathVariable Long produtoId) {
        return ResponseEntity.ok().body(movimentacaoEstoqueService.getMovimentacoesEstoqueByProdutoId(produtoId));
    }
}
