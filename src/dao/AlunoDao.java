/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.ConexaoBD;
import java.sql.*;
import javax.swing.JOptionPane;
import model.Aluno;

/**
 *
 * @author lucelia
 */
public class AlunoDao {
    public void salvarAluno(Aluno aluno){
        String sql = "INSERT INTO aluno (nome, data_nascimento, historico_escolar, id_responsavel, id_turma) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, Date.valueOf(aluno.getDataNascimento())); // LocalDate -> SQL Date
            stmt.setString(3, aluno.getHistoricoEscolar());
            stmt.setInt(4, aluno.getIdResponsavel());
            stmt.setInt(5, aluno.getIdTurma());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar aluno: " + e.getMessage());
        }
    }
}
