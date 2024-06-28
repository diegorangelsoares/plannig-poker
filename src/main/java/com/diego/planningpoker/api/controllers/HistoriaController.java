package com.diego.planningpoker.api.controllers;

import com.diego.planningpoker.api.requests.HistoriaRequest;
import com.diego.planningpoker.api.services.HistoriaService;
import com.diego.planningpoker.domain.Historia;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/historia")
public class HistoriaController {

    @Autowired
    HistoriaService historiaService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") long page,
                                     @RequestParam(name = "size", required = false, defaultValue = "20") long size,
                                     @RequestParam(name = "sort", required = false, defaultValue = "id") String sort) {
        return new ResponseEntity<>(historiaService.listarTodos(page, size, sort), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(historiaService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping (value = "")
    public ResponseEntity<?> create (@Valid @RequestBody HistoriaRequest historiaRequest){
        return new ResponseEntity<>(historiaService.cadastrar(historiaRequest), HttpStatus.CREATED);
    }

    @PatchMapping (value = "/{id}")
    public ResponseEntity<?> updateValues (@PathVariable("id") Long id, @Valid @RequestBody HistoriaRequest historiaRequest){
        return new ResponseEntity<>(historiaService.alterarValores(id, historiaRequest), HttpStatus.ACCEPTED);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<?> update (@PathVariable("id") Long id, @Valid @RequestBody Historia historia){
        return new ResponseEntity<>(historiaService.alterar(id, historia), HttpStatus.ACCEPTED);
    }


}
