/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author lucelia
 */
public class Aluno {
    private int idAluno;
    private String nome;
    private LocalDate dataNascimento;
    private String historicoEscolar;
    private int idResponsavel;
    private int idTurma;

    
     public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getHistoricoEscolar() { return historicoEscolar; }
    public void setHistoricoEscolar(String historicoEscolar) { this.historicoEscolar = historicoEscolar; }

    public int getIdResponsavel() { return idResponsavel; }
    public void setIdResponsavel(int idResponsavel) { this.idResponsavel = idResponsavel; }

    public int getIdTurma() { return idTurma; }
    public void setIdTurma(int idTurma) { this.idTurma = idTurma; }
    
}
