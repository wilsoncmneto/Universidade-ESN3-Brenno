package com.exemplo.universidadeESN3.service;

import com.exemplo.universidadeESN3.entity.Aluno;

import java.util.List;

public interface IAlunoService {

   Aluno buscarPorId(long id);
   List<Aluno> buscarTodos();
   Aluno salvar(Aluno aluno);

   void atualizar(Aluno aluno);
   void excluir(long id);


}
