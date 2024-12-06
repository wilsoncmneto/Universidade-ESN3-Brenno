package com.example.universidadeESN3.controller;

import com.example.universidadeESN3.entity.Aluno;
import com.example.universidadeESN3.entity.Professor;
import com.example.universidadeESN3.service.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
@Slf4j
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> buscarTodos() {
        return ResponseEntity.ok(alunoService.buscarTodos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id){

        Aluno response = alunoService.buscarPorId(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome){
        log.info("buscarPorNome() - nome: {}", nome);
        List<Aluno> response = alunoService.buscarPorNome(nome);
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body("Nenhum Aluno encontrado com o nome especificado");
    }

    @PostMapping
    public ResponseEntity<Aluno> salvarAluno(@RequestBody Aluno aluno){
        log.info("salvarAluno() - aluno:{}", aluno );
        return ResponseEntity.ok(alunoService.salvar(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno) {
        // Verificar se o Aluno existe no banco de dados pelo ID fornecido na URL
        Aluno existente = alunoService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }

        // Atualizar os campos do Aluno existente com os valores do objeto recebido
        existente.setMatricula(aluno.getMatricula());
        existente.setNome(aluno.getNome());
        existente.setGenero(aluno.getGenero());
        existente.setActive(aluno.getActive());

        // Salvar o Aluno atualizado no banco de dados
        Aluno atualizado = alunoService.atualizar(existente);

        // Retornar o Aluno atualizado
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Aluno response = alunoService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        alunoService.excluir(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<?> desativar(@PathVariable Long id) {
        Aluno response = alunoService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        alunoService.desativar(response);
        return ResponseEntity.ok(null);
    }


}
