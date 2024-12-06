package com.example.universidadeESN3.repository;

import com.example.universidadeESN3.entity.Aluno;
import com.example.universidadeESN3.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNomeIgnoreCaseContaining(String nome);

    @Query(value = "SELECT * FROM aluno a WHERE LOWER(a.nome) LIKE LOWER(CONCAT(:nome, '%'))", nativeQuery = true)
    List<Aluno> findByNomeStartingWithIgnoreCase(@Param("nome") String nome);

}
