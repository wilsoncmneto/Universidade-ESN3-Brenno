package com.exemplo.universidadeESN3.service;

import com.exemplo.universidadeESN3.entity.Aluno;
import com.exemplo.universidadeESN3.entity.Professor;

import java.util.List;

public interface IProfessorService {

    Professor buscarPorId(long id);

    List<Professor> buscarTodos();

    Aluno salvar(Professor professor);

    void atualizar(Professor professor);

    void excluir(long id);

}
