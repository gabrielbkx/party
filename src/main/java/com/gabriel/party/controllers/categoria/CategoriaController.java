package com.gabriel.party.controllers.categoria;


import com.gabriel.party.dtos.categoria.CategoriaReponseDTO;
import com.gabriel.party.dtos.categoria.CategoriaRequestDTO;
import com.gabriel.party.services.categoria.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaReponseDTO> criarCategoria(@Valid @RequestBody CategoriaRequestDTO dto){

        var categoriaCriada = categoriaService.salvarCategoria(dto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaCriada.id())
                .toUri();

        return ResponseEntity.created(uri).body(categoriaCriada);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaReponseDTO>> listarTodasCategorias(Pageable pageable){

        return ResponseEntity.ok(categoriaService.listarCategorias(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID id){
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaReponseDTO> atualizarCategoria(@Valid @RequestBody CategoriaRequestDTO dto,
                                                                  @PathVariable UUID id){
        var categoriaAtualizada = categoriaService.atualizarCategoria(dto, id);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaReponseDTO> buscarCategoriaPorId(@PathVariable UUID id){
        var categoria = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }
}
