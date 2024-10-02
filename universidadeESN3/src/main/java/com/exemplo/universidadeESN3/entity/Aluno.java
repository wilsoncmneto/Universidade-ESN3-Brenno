package com.exemplo.universidadeESN3.entity;

import lombok.Data;

@Data
public class Aluno {

    private long id;
    private long matricula;
    private String nome;
    private Genero genero;
}
