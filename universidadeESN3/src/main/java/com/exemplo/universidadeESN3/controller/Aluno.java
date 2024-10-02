package com.exemplo.universidadeESN3.controller;

import com.exemplo.universidadeESN3.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class Aluno {

    @Autowired
    private AlunoService aluno;

    @GetMapping
    public ResponseEntity<List<com.exemplo.universidadeESN3.entity.Aluno>> buscarTodos(){
        return ResponseEntity.ok(aluno.buscarTodos());
    }
}
