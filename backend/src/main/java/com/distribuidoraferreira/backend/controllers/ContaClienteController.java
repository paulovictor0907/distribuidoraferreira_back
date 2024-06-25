package com.distribuidoraferreira.backend.controllers;

import java.util.List;

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

import com.distribuidoraferreira.backend.dtos.ContaClienteAlterRequest;
import com.distribuidoraferreira.backend.dtos.ContaClienteRequest;
import com.distribuidoraferreira.backend.dtos.ContaClienteResponse;
import com.distribuidoraferreira.backend.dtos.GenericResponse;
import com.distribuidoraferreira.backend.dtos.PagamentoRequest;
import com.distribuidoraferreira.backend.services.interfaces.ContaClienteService;

@RestController
@RequestMapping("/contas-cliente")
public class ContaClienteController {

    @Autowired
    private ContaClienteService contaClienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenericResponse<ContaClienteResponse>> addContaCliente(
            @RequestBody ContaClienteRequest contaClienteRequest) {
        return ResponseEntity.ok().body(contaClienteService.addContaCliente(contaClienteRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<ContaClienteResponse>> getContaClienteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(contaClienteService.getContaClienteById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<ContaClienteResponse>>> getAllContaClientes() {
        return ResponseEntity.ok().body(contaClienteService.getAllContaClientes());
    }

    @GetMapping("/inadimplentes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<ContaClienteResponse>>> getAllContaClientesInadimplentes() {
        return ResponseEntity.ok().body(contaClienteService.getAllContaClientesInadimplentes());
    }

    @PostMapping("/pagar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<String>> pagarContaCliente(@RequestBody PagamentoRequest pagamentoRequest) {
        return ResponseEntity.ok().body(contaClienteService.pagarContaCliente(pagamentoRequest));
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<String>> atualizarInfosContaCliente(
            @RequestBody ContaClienteAlterRequest contaClienteAlterRequest) {
        return ResponseEntity.ok().body(contaClienteService.atualizarInfosContaCliente(contaClienteAlterRequest));
    }

}