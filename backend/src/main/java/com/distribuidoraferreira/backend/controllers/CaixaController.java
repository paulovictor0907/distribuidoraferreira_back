package com.distribuidoraferreira.backend.controllers;

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

import com.distribuidoraferreira.backend.dtos.CaixaRequest;
import com.distribuidoraferreira.backend.services.interfaces.CaixaService;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    CaixaService caixaService;

    @PostMapping("/abrir")
    public ResponseEntity<?> abrirCaixa(@RequestBody CaixaRequest caixaRequest) {
        return ResponseEntity.ok().body(caixaService.abrirCaixa(caixaRequest));
    }

    @PostMapping("/fechar/{id}")
    public ResponseEntity<?> fecharCaixa(@PathVariable Long id) {
        return ResponseEntity.ok().body(caixaService.fecharCaixa(id));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCaixaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(caixaService.getCaixaById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCaixas() {
        return ResponseEntity.ok().body(caixaService.getCaixas());
    }

    @GetMapping("/aberto")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCaixa() {
        return ResponseEntity.ok().body(caixaService.getCaixa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCaixa(@PathVariable("id") Long id,
            @RequestBody CaixaRequest caixaRequest) {
        caixaService.updateCaixa(id, caixaRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("verificar")
    public ResponseEntity<?> verifyCaixaOpen() {
        return ResponseEntity.ok().body(caixaService.isCaixaAberto());
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<?> getDetalhesCaixa(@PathVariable Long id) {
        return ResponseEntity.ok().body(caixaService.getDetalhesCaixa(id));
    }

    @GetMapping("pegarID")
    public ResponseEntity<?> getIDCaixaOpen() {
        return ResponseEntity.ok().body(caixaService.getCaixaByID());
    }

}
