package com.example.universidadeESN3.repository;

import com.example.universidadeESN3.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByNomeIgnoreCaseContaining(String nome);

    @Query(value = "SELECT * FROM professor a WHERE LOWER(a.nome) LIKE LOWER(CONCAT(:nome, '%'))", nativeQuery = true)
    List<Professor> findByNomeStartingWithIgnoreCase(@Param("nome") String nome);

}
