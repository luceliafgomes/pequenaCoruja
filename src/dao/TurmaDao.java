/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.ConexaoBD;
import java.sql.*;
import java.util.*;
import model.Turma;
/**
 *
 * @author lucelia
 */
public class TurmaDao {
     private String gerarCodigo() {
        String sql = "SELECT COUNT(*) AS total FROM Turma";
        int total = 0;
        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) total = rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.format("TURMA%03d", total + 1);
    }

   public int salvarERetornarId(Turma turma) {
        String sql = "INSERT INTO Turma (codigo, nome) VALUES (?, ?)";
        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, gerarCodigo());
            stmt.setString(2, turma.getNome());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // retorna o id_turma gerado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // erro
    }

    // 🔹 Atualizar turma
    public boolean atualizar(Turma turma) {
        String sql = "UPDATE Turma SET nome = ? WHERE id_turma = ?";
        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getIdTurma());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Deletar turma
    public boolean deletar(int idTurma) {
        String sql = "DELETE FROM Turma WHERE id_turma = ?";
        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idTurma);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Listar todas as turmas
    public List<Turma> listarTodas() {
        List<Turma> lista = new ArrayList<>();
        String sql = "SELECT * FROM Turma ORDER BY id_turma";

        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turma t = new Turma();
                t.setIdTurma(rs.getInt("id_turma"));
                t.setCodigo(rs.getString("codigo"));
                t.setNome(rs.getString("nome"));
                lista.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(lista);
        return lista;
    }

    // 🔹 Buscar turma por ID
    public Turma buscarPorId(int idTurma) {
        Turma t = null;
        String sql = "SELECT * FROM Turma WHERE id_turma = ?";
        try (Connection con = ConexaoBD.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idTurma);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    t = new Turma();
                    t.setIdTurma(rs.getInt("id_turma"));
                    t.setCodigo(rs.getString("codigo"));
                    t.setNome(rs.getString("nome"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
    
    public boolean excluirTurmaComAlunos(int idTurma) {
        Connection con = null;
    PreparedStatement psAtualizar = null;
    PreparedStatement psExcluir = null;

    try {
        con = ConexaoBD.getConexao();
        con.setAutoCommit(false); // transação

        // 1️⃣ Atualiza os alunos para a turma padrão
        String sqlAtualizar = "UPDATE Aluno SET id_turma = 1 WHERE id_turma = ?";
        psAtualizar = con.prepareStatement(sqlAtualizar);
        psAtualizar.setInt(1, idTurma);
        psAtualizar.executeUpdate();

        // 2️⃣ Exclui a turma
        String sqlExcluir = "DELETE FROM Turma WHERE id_turma = ?";
        psExcluir = con.prepareStatement(sqlExcluir);
        psExcluir.setInt(1, idTurma);
        int linhasAfetadas = psExcluir.executeUpdate();

        con.commit(); // confirma transação
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        try {
            if (con != null) con.rollback(); // desfaz alterações em caso de erro
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    } finally {
        try {
            if (psAtualizar != null) psAtualizar.close();
            if (psExcluir != null) psExcluir.close();
            if (con != null) con.setAutoCommit(true);
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
    
}
