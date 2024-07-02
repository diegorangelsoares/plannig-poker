package com.diego.planningpoker.api.controllers;

import com.diego.planningpoker.domain.Planning;
import com.diego.planningpoker.presentation.assembler.HistoriaAssembler;
import com.diego.planningpoker.presentation.assembler.PlanningAssembler;
import com.diego.planningpoker.presentation.dto.http.request.HistoriaRequest;
import com.diego.planningpoker.api.services.HistoriaService;
import com.diego.planningpoker.domain.Historia;
import com.diego.planningpoker.presentation.dto.http.response.HistoriaResponse;
import com.diego.planningpoker.presentation.dto.http.response.PlanningResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/historia")
public class HistoriaController {

    @Autowired
    HistoriaService historiaService;

    @GetMapping(value = "/all")
    public ResponseEntity<Page<HistoriaResponse>> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") long page,
                                                          @RequestParam(name = "size", required = false, defaultValue = "20") long size,
                                                          @RequestParam(name = "sort", required = false, defaultValue = "id") String sort) {
        return new ResponseEntity<>(historiaService.convertToPageResponse(historiaService.listarTodos(page, size, sort)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HistoriaResponse> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(convertToResponse(historiaService.buscarPorId(id)), HttpStatus.OK);
    }

    @PostMapping (value = "")
    public ResponseEntity<HistoriaResponse> create (@Valid @RequestBody HistoriaRequest historiaRequest){
        return new ResponseEntity<>(convertToResponse(historiaService.cadastrar(historiaRequest)), HttpStatus.CREATED);
    }

    @PatchMapping (value = "/{id}")
    public ResponseEntity<HistoriaResponse> updateValues (@PathVariable("id") Long id, @Valid @RequestBody HistoriaRequest historiaRequest){
        return new ResponseEntity<>(convertToResponse(historiaService.alterarValores(id, historiaRequest)), HttpStatus.ACCEPTED);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<HistoriaResponse> update (@PathVariable("id") Long id, @Valid @RequestBody Historia historia){
        return new ResponseEntity<>(convertToResponse(historiaService.alterar(id, historia)), HttpStatus.ACCEPTED);
    }

    public HistoriaResponse convertToResponse(Historia entity) {
        return HistoriaAssembler.convertToResponse(entity);
    }

//    public Page<HistoriaResponse> convertToPageResponse(Page<Historia> entitys) {
//        return HistoriaAssembler.toPageObjectDto(entitys);
//    }


}
