package com.diego.planningpoker.api.controllers;

import com.diego.planningpoker.api.requests.PlanningRequest;
import com.diego.planningpoker.api.services.PlanningService;
import com.diego.planningpoker.domain.Planning;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/planning")
public class PlanningController {

    @Autowired
    PlanningService planningService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") long page,
                                     @RequestParam(name = "size", required = false, defaultValue = "20") long size,
                                     @RequestParam(name = "sort", required = false, defaultValue = "id") String sort) {
        return new ResponseEntity<>(planningService.listarTodos(page, size, sort), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(planningService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping (value = "")
    public ResponseEntity<?> create (@Valid @RequestBody PlanningRequest planningRequest){
        return new ResponseEntity<>(planningService.cadastrar(planningRequest), HttpStatus.CREATED);
    }

    @PatchMapping (value = "/{id}")
    public ResponseEntity<?> updateValues (@PathVariable("id") Long id, @Valid @RequestBody PlanningRequest planningRequest){
        return new ResponseEntity<>(planningService.alterarValores(id, planningRequest), HttpStatus.ACCEPTED);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<?> update (@PathVariable("id") Long id, @Valid @RequestBody Planning planning){
        return new ResponseEntity<>(planningService.alterar(id, planning), HttpStatus.ACCEPTED);
    }


}
