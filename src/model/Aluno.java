/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public String getResponsavelNome() {
        if (idResponsavel == 0) {
            return "Não definido";
        }

        String nomeResponsavel = "Não definido";
        String sql = "SELECT login FROM Usuario WHERE id_usuario = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idResponsavel);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nomeResponsavel = rs.getString("login");
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar responsável: " + e.getMessage());
        }

        return nomeResponsavel;
    }
    
}
