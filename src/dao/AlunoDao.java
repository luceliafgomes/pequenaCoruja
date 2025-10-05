/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    
     public static boolean atualizarAluno(Aluno aluno) {
        String sql = "UPDATE Aluno SET nome = ?, data_nascimento = ?, historico_escolar = ?, id_responsavel = ?, id_turma = ? WHERE id_aluno = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aluno.getNome());
            ps.setDate(2, Date.valueOf(aluno.getDataNascimento()));
            ps.setString(3, aluno.getHistoricoEscolar());
            ps.setInt(4, aluno.getIdResponsavel());
            ps.setInt(5, aluno.getIdTurma());       // aqui vai a turma
            ps.setInt(6, aluno.getIdAluno());   

            int linhasAfetadas = ps.executeUpdate();

            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
            return false;
        }
    }
     
         public static boolean excluirAluno(int idAluno) {
        String sql = "DELETE FROM Aluno WHERE id_aluno = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAluno);

            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
            return false;
        }
    }
         
    public List<String[]> listarAlunosComTurma() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT a.nome AS aluno, t.nome AS turma "
                   + "FROM Aluno a LEFT JOIN Turma t ON a.id_turma = t.id_turma";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nomeAluno = rs.getString("aluno");
                String nomeTurma = rs.getString("turma");
                lista.add(new String[]{nomeAluno, nomeTurma});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Aluno buscarPorNome(String nome) {
        Aluno aluno = null;
        String sql = "SELECT * FROM Aluno WHERE nome = ?";
        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                aluno = new Aluno();
                aluno.setIdAluno(rs.getInt("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                aluno.setHistoricoEscolar(rs.getString("historico_escolar"));
                aluno.setIdResponsavel(rs.getInt("id_responsavel"));
                aluno.setIdTurma(rs.getInt("id_turma"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }
    
    
}
