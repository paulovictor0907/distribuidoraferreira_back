package com.distribuidoraferreira.backend.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.distribuidoraferreira.backend.dtos.EstoqueMinimoRequest;
import com.distribuidoraferreira.backend.dtos.GenericResponse;
import com.distribuidoraferreira.backend.dtos.ImageResponse;
import com.distribuidoraferreira.backend.dtos.ProdutoRequest;
import com.distribuidoraferreira.backend.dtos.ProdutoResponse;
import com.distribuidoraferreira.backend.services.interfaces.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenericResponse<ProdutoResponse>> addProduto(@RequestBody ProdutoRequest produtoRequest) {
        return ResponseEntity.ok().body(produtoService.addProduto(produtoRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<List<ProdutoResponse>>> getProdutos() {
        return ResponseEntity.ok().body(produtoService.getProdutos());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getProdutoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.getProdutoById(id));
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getProdutoByNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(produtoService.getProdutoByNome(nome));
    }

    @GetMapping("/cod/{codBarras}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse<?>> getProdutoByCodBarras(@PathVariable String codBarras) {
        return ResponseEntity.ok().body(produtoService.getProdutoByCodBarras(codBarras));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduto(@PathVariable("id") Long id,
            @RequestBody ProdutoRequest produtoRequest) {
        produtoService.updateProduto(id, produtoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/estoquemin")
    public ResponseEntity<GenericResponse<String>> updateEstoqueMinimoProduto(
            @RequestBody EstoqueMinimoRequest estoqueMinimoRequest) {
        return ResponseEntity.ok().body(produtoService.updateEstoqueMinimoProduto(estoqueMinimoRequest.getIdProduto(),
                estoqueMinimoRequest.getEstoqueMinimo()));
    }

    @PostMapping("/upload")
    public ResponseEntity<GenericResponse<ImageResponse>> uploadImage(@RequestParam("img") MultipartFile file)
            throws IOException {
        return ResponseEntity.ok().body(produtoService.uploadImage(file));
    }

    @DeleteMapping("/deleteImage/{ImageID}")
    public ResponseEntity<GenericResponse<ProdutoResponse>> deleteImage(@PathVariable("ImageID") String ImageID) {
        return ResponseEntity.ok().body(produtoService.deleteImage(ImageID));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<ProdutoResponse>> deleteProduto(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(produtoService.deleteProduto(id));
    }

}
