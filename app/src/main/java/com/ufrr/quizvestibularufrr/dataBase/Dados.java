package com.ufrr.quizvestibularufrr.dataBase;

import java.io.Serializable;

public class Dados implements Serializable {

    public static String TABELA        = "RANKQUIZ";
    public static String ID            = "_id";
    public static String NOME          = "NOME";
    public static String QUIZNAME      = "QUIZNAME";
    public static String PONTOS        = "PONTOS";
    public static String DATA          = "DATA";

    private String nome;
    private String quizName;
    private String pontos;
    private String data;
    private long id;


    public Dados() {
        id = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
