package com.diego.planningpoker.api.controllers;

import com.diego.planningpoker.presentation.dto.http.request.VotoRequest;
import com.diego.planningpoker.api.services.VotoService;
import com.diego.planningpoker.domain.Voto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/votacao")
public class VotacaoController {

    @Autowired
    VotoService votoService;

    @GetMapping(value = "/historia/{id}/fechados")
    public ResponseEntity<List<String>> findVotosByHistoriaFechados(@PathVariable("id") Long id) {
        return new ResponseEntity<>(votoService.votaram(id), HttpStatus.OK);
    }

    @GetMapping(value = "/historia/{id}/final")
    public ResponseEntity<List<Voto>> findVotosByHistoriaAbertos(@PathVariable("id") Long id) {
        return new ResponseEntity<>(votoService.buscarPorIdHistoria(id), HttpStatus.OK);
    }

    @PostMapping (value = "/votar")
    public ResponseEntity<?> create (@Valid @RequestBody VotoRequest votoRequest){
        votoService.cadastrar(votoRequest);
        return new ResponseEntity<>("Voto da História "+ votoRequest.getIdHistoria() + " realizado com Sucesso!", HttpStatus.CREATED);
    }

    @DeleteMapping (value = "/historia/{id}/reset")
    public ResponseEntity<?> update (@PathVariable("id") Long id){
        votoService.deletePorHistoria(id);
        return new ResponseEntity<>("Votos da história "+ id + " removidos com sucesso!", HttpStatus.ACCEPTED);
    }


}
