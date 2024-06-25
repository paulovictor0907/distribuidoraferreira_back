package com.distribuidoraferreira.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.distribuidoraferreira.backend.dtos.CategoriaRequest;
import com.distribuidoraferreira.backend.dtos.CategoriaResponse;
import com.distribuidoraferreira.backend.dtos.GenericResponse;
import com.distribuidoraferreira.backend.services.interfaces.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategoria(@RequestBody CategoriaRequest categoriaRequest) {
        categoriaService.addCategoria(categoriaRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<CategoriaResponse>>> getCategorias() {
        return ResponseEntity.ok().body(categoriaService.getCategorias());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.getCategoriaById(id));
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCategoriaByNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(categoriaService.getCategoriaByNome(nome));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id) {
        categoriaService.deleteCategoriaById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
