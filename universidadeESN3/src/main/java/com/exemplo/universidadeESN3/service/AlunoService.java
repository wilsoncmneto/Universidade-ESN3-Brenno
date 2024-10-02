package com.exemplo.universidadeESN3.service;

import com.exemplo.universidadeESN3.entity.Aluno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
public class AlunoService implements IAlunoService{


    @Override
    public Aluno buscarPorId(long id) {
        return null;
    }

    @Override
    public List<Aluno> buscarTodos() {
        return null;
    }

    @Override
    public Aluno salvar(Aluno aluno) {
        return null;
    }

    @Override
    public void atualizar(Aluno aluno) {

    }

    @Override
    public void excluir(long id) {

    }
}
