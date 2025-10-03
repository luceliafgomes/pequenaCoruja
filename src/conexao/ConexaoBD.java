/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucelia
 */
public class ConexaoBD {
     
    private static final String URL = "jdbc:mysql://localhost:3306/pequenaCoruja"; // nome do banco
    private static final String USUARIO = "root";  // seu usuário MySQL
    private static final String SENHA = "123456789"; // sua senha

    public static Connection getConexao() {
    try {
            // Carregar o driver (opcional no JDBC 4+, mas bom garantir)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("✅ Conexão realizada com sucesso!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver JDBC não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar no banco de dados!");
            e.printStackTrace();
        }
        return null;
    }
}
