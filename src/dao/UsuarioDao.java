/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import conexao.ConexaoBD;
import model.Usuario;
/**
 *
 * @author lucelia
 */
public class UsuarioDao {
    
    public Usuario autenticar(String login, String senha){
    
    Usuario usuario = null;
    
    String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
      
    try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setPerfil(rs.getString("perfil"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
    
    public Integer buscarUsuarioPorLogin(String login) {
        String sql = "SELECT id_usuario FROM Usuario WHERE login = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // não encontrou
    }
    
    public int criarUsuarioPai(String login, String senha) {
        String sql = "INSERT INTO Usuario (login, senha, perfil) VALUES (?, ?, 'Pai')";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // retorna o id do novo usuário criado
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
   
    
}
