package crudweblivraria.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {

	private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASS;
    
    
    private static Connection getConnection() {
    	try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar a classe de Conexão.\nErro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o banco de dados.\nErro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static Connection getMysqlConnection(){
        DRIVER = "com.mysql.cj.jdbc.Driver";
        URL = "jdbc:mysql://localhost:3306/crud_web_livraria?serverTimezone=UTC";
        USER = "root";
        PASS = "";
        
        return getConnection();
    }
    
    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão.\nErro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão.\nErro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        closeConnection(conn);
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão.\nErro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        closeConnection(conn, stmt);
    }
    
	
}
