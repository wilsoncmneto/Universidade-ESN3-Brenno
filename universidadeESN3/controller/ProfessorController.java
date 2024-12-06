package com.example.universidadeESN3.controller;

import com.example.universidadeESN3.entity.Aluno;
import com.example.universidadeESN3.entity.Professor;
import com.example.universidadeESN3.service.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/professor")
@Slf4j
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> buscarTodos() {

        return ResponseEntity.ok(professorService.buscarTodos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {

        Professor response = professorService.buscarPorId(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
        log.info("buscarPorNome() - nome: {}", nome);
        List<Professor> response = professorService.buscarPorNome(nome);
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body("Nenhum professor encontrado com o nome especificado");
    }

    @PostMapping
    public ResponseEntity<Professor> salvarProfessor(@RequestBody Professor professor) {
        log.info("salvarProfessor() - professor:{}", professor);
        return ResponseEntity.ok(professorService.salvar(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable Long id, @RequestBody Professor professor) {
        // Verificar se o professor existe no banco de dados pelo ID fornecido na URL
        Professor existente = professorService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }

        // Atualizar os campos do professor existente com os valores do objeto recebido
        existente.setNome(professor.getNome());
        existente.setGenero(professor.getGenero());
        existente.setDisciplina(professor.getDisciplina());
        existente.setActive(professor.getActive());

        // Salvar o professor atualizado no banco de dados
        Professor atualizado = professorService.atualizar(existente);

        // Retornar o professor atualizado
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Professor response = professorService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        professorService.excluir(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<?> desativar(@PathVariable Long id) {
        Professor response = professorService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        professorService.desativar(response);
        return ResponseEntity.ok(null);

    }
}