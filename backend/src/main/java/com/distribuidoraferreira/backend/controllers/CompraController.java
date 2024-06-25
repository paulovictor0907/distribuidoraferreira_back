package com.distribuidoraferreira.backend.controllers;

import com.distribuidoraferreira.backend.dtos.GenericResponse;
import com.distribuidoraferreira.backend.services.interfaces.CompraService;
import com.distribuidoraferreira.backend.dtos.CompraRequest;
import com.distribuidoraferreira.backend.dtos.CompraResponse;

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
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    CompraService compraService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompra(@RequestBody CompraRequest compraRequest) {
        compraService.addCompra(compraRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<CompraResponse>>> getCompras() {
        return ResponseEntity.ok().body(compraService.getCompras());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCompraById(@PathVariable Long id) {
        return ResponseEntity.ok().body(compraService.getCompraById(id));
    }

    // TODO: Implementar query personalizada para getCompraByProdutoId
    /*
     * @GetMapping("/{produtoId}")
     * 
     * @ResponseStatus(HttpStatus.OK)
     * public ResponseEntity<GenericResponse<List<CompraResponse>>>
     * getCompraByProdutoId(
     * 
     * @PathVariable Long produtoId) {
     * return
     * ResponseEntity.ok().body(compraService.getCompraByProdutoId(produtoId));
     * }
     */
}
