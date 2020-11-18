package crudweblivraria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import crudweblivraria.interfaces.IDAO;
import crudweblivraria.model.EntidadeDominio;
import crudweblivraria.model.Livro;
 
/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class LivroDAO extends DAO implements IDAO {
     
    public LivroDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    public boolean inserir(EntidadeDominio ent) throws SQLException {
    	Livro livro = (Livro) ent;
        String sql = "INSERT INTO cwl_livros (liv_titulo, liv_autor, liv_editora, liv_edicao, "
        		+ "liv_dt_lancamento, liv_preco, liv_isbn) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, livro.getTitulo());
        statement.setString(2, livro.getAutor());
        statement.setString(3, livro.getEditora());
        statement.setInt(4, livro.getEdicao());
        statement.setDate(5, new java.sql.Date (livro.getDtLancamento().getTime()));
        statement.setDouble(6, livro.getPreco());
        statement.setString(7, livro.getIsbn());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<EntidadeDominio> listarTodos() throws SQLException{ 
        List<EntidadeDominio> livros = new ArrayList<>();
         
        String sql = "SELECT * FROM cwl_livros";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("liv_id");
            String isbn = resultSet.getString("liv_isbn");
            String titulo = resultSet.getString("liv_titulo");
            String autor = resultSet.getString("liv_autor");
            String editora = resultSet.getString("liv_editora");
            int edicao = resultSet.getInt("liv_edicao");
            Date dtLancamento = resultSet.getDate("liv_dt_lancamento");
            double preco = resultSet.getDouble("liv_preco");
            
             
            EntidadeDominio livro = new Livro(id, isbn, titulo, autor, editora, edicao, dtLancamento, preco);
            livros.add(livro);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return (ArrayList<EntidadeDominio>)livros;
    }
     
    public boolean deletar(EntidadeDominio ent) throws SQLException {
    	Livro livro = (Livro) ent;
        String sql = "DELETE FROM cwl_livros where liv_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, livro.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean atualizar(EntidadeDominio ent) throws SQLException {
    	Livro livro = (Livro) ent;
        String sql = "UPDATE cwl_livros SET liv_titulo = ?, liv_autor = ?, liv_editora = ?,"
        		+ "liv_edicao = ?, liv_dt_lancamento = ?, liv_preco = ?, liv_isbn = ?";
        sql += " WHERE liv_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, livro.getTitulo());
        statement.setString(2, livro.getAutor());
        statement.setString(3, livro.getEditora());
        statement.setInt(4, livro.getEdicao());
        statement.setDate(5, new java.sql.Date (livro.getDtLancamento().getTime()));
        statement.setDouble(6, livro.getPreco());
        statement.setString(7, livro.getIsbn());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public EntidadeDominio getEntidade(int id) throws SQLException {
        Livro livro = null;
        String sql = "SELECT * FROM cwl_livros WHERE liv_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String isbn = resultSet.getString("liv_isbn");
            String titulo = resultSet.getString("liv_titulo");
            String autor = resultSet.getString("liv_autor");
            String editora = resultSet.getString("liv_editora");
            int edicao = resultSet.getInt("liv_edicao");
            Date dtLancamento = resultSet.getDate("liv_dt_lancamento");
            double preco = resultSet.getDouble("liv_preco");
             
            livro = new Livro(id, isbn, titulo, autor, editora, edicao, dtLancamento, preco);
        }
         
        resultSet.close();
        statement.close();
         
        return livro;
    }
}
