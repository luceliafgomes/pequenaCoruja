/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author lucelia
 */
public class Aluno {
    private String nome;
    private String dataNascimento;
    private String HistoricoEscolar;
    
    public Aluno(String nome, String dataNascimento, String historicoEscolar){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.HistoricoEscolar = historicoEscolar;
    }
    
    @Override
    public String toString(){
        return nome + " - Nasc: " + dataNascimento + " -  Histórico: " + HistoricoEscolar;
    }
    
    
}
