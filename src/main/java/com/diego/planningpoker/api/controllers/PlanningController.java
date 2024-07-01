package com.diego.planningpoker.api.controllers;

import com.diego.planningpoker.presentation.assembler.PlanningAssembler;
import com.diego.planningpoker.presentation.dto.http.request.PlanningRequest;
import com.diego.planningpoker.api.services.PlanningService;
import com.diego.planningpoker.domain.Planning;
import com.diego.planningpoker.presentation.dto.http.response.PlanningResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/planning")
public class PlanningController {

    @Autowired
    PlanningService planningService;

    @Autowired
    ObjectMapper mapper;

    @GetMapping(value = "/all")
    public ResponseEntity<Page<PlanningResponse>> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") long page,
                                                          @RequestParam(name = "size", required = false, defaultValue = "20") long size,
                                                          @RequestParam(name = "sort", required = false, defaultValue = "id") String sort) {

        return new ResponseEntity<>(planningService.convertToPageResponse(planningService.listarTodos(page, size, sort)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlanningResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(planningService.convertToResponse(planningService.buscarPorId(id)));
    }

    @PostMapping (value = "")
    public ResponseEntity<PlanningResponse> create (@Valid @RequestBody PlanningRequest planningRequest){
        return new ResponseEntity<>(planningService.convertToResponse(planningService.cadastrar(planningRequest)), HttpStatus.CREATED);
    }

    @PatchMapping (value = "/{id}")
    public ResponseEntity<PlanningResponse> updateValues (@PathVariable("id") Long id, @Valid @RequestBody PlanningRequest planningRequest){
        return new ResponseEntity<>(planningService.convertToResponse(planningService.alterarValores(id, planningRequest)), HttpStatus.ACCEPTED);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<PlanningResponse> update (@PathVariable("id") Long id, @Valid @RequestBody Planning planning){
        return new ResponseEntity<>(planningService.convertToResponse(planningService.alterar(id, planning)), HttpStatus.ACCEPTED);
    }

//    public PlanningResponse convertToResponse(Planning entity) {
//        return PlanningAssembler.convertToResponse(entity);
//    }
//
//    public Page<PlanningResponse> convertToPageResponse(Page<Planning> entitys) {
//        return PlanningAssembler.toPageObjectDto(entitys);
//    }


}
