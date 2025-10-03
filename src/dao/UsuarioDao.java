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
   
    
}
