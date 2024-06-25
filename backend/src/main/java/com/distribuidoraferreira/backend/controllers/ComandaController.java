package com.distribuidoraferreira.backend.controllers;

import com.distribuidoraferreira.backend.dtos.GenericResponse;
import com.distribuidoraferreira.backend.dtos.ComandaRequest;
import com.distribuidoraferreira.backend.dtos.ComandaResponse;
import com.distribuidoraferreira.backend.dtos.ItemComandaRequest;
import com.distribuidoraferreira.backend.dtos.PagamentoComandaRequest;
import com.distribuidoraferreira.backend.services.interfaces.ComandaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comanda")
public class ComandaController {
    @Autowired
    ComandaService comandaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse<String> addComanda(@RequestBody ComandaRequest comandaRequest) {
        return comandaService.addComanda(comandaRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<ComandaResponse>>> getComandas() {
        return ResponseEntity.ok().body(comandaService.getComandas());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getComandasById(@PathVariable Long id) {
        return ResponseEntity.ok().body(comandaService.getComandaById(id));
    }

    @GetMapping("/{status}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<ComandaResponse>>> getComandasByStatus(@PathVariable String status) {
        return ResponseEntity.ok().body(comandaService.getComandasByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<String>> updateComandaById(@PathVariable("id") Long id,
            @RequestBody ComandaRequest comandaRequest) {
        return ResponseEntity.ok().body(comandaService.updateComandaById(id, comandaRequest));
    }

    @PostMapping("/itens/adicionar/{id}")
    public ResponseEntity<GenericResponse<String>> adicionarItemComandaById(@PathVariable("id") Long id,
            @RequestBody List<ItemComandaRequest> itemComandaRequest) {
        return ResponseEntity.ok().body(comandaService.addItemComanda(id, itemComandaRequest));
    }

    @PostMapping("/concluir/vendas")
    public ResponseEntity<GenericResponse<String>> concluirVendasComanda(
            @RequestBody PagamentoComandaRequest pagamentoRequest) {
        return ResponseEntity.ok().body(comandaService.concluirVendasComanda(pagamentoRequest));
    }

    @PostMapping("/fechar/{id}")
    public ResponseEntity<GenericResponse<String>> fecharComandaById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(comandaService.fecharComandaById(id));
    }

}
