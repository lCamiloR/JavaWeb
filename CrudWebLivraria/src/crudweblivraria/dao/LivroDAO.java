package crudweblivraria.dao;

import java.sql.*;
import java.util.*;


import crudweblivraria.interfaces.IDAO;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;
import crudweblivraria.model.connection.ConnectionFactory;
	
public class LivroDAO implements IDAO {
     
    private Connection jdbcConnection;
    
    @Override
    public boolean inserir(EntidadeDominio ent) throws SQLException {
    	
    	this.jdbcConnection = ConnectionFactory.getConnection();
    	Livro livro = (Livro) ent;
        String sql = "INSERT INTO cwl_livros (liv_titulo, liv_autor, liv_editora, liv_edicao, "
        		+ "liv_dt_lancamento, liv_preco, liv_isbn) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        
        try {    
        	statement = jdbcConnection.prepareStatement(sql);
        	
	        statement.setString(1, livro.getTitulo());
	        statement.setString(2, livro.getAutor());
	        statement.setString(3, livro.getEditora());
	        statement.setInt(4, livro.getEdicao());
	        statement.setDate(5, new java.sql.Date (livro.getDtLancamento().getTime()));
	        statement.setDouble(6, livro.getPreco());
	        statement.setString(7, livro.getIsbn());
	         
	        statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(jdbcConnection, statement);
        }
        return false;
    }
    
    @Override
    public List<EntidadeDominio> consultar() throws SQLException{
    	
    	this.jdbcConnection = ConnectionFactory.getConnection();
        List<EntidadeDominio> livros = new ArrayList<>();
         
        String sql = "SELECT * FROM cwl_livros";
         
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Livro livro = new Livro();
        try {
        	statement = jdbcConnection.prepareStatement(sql);
        	resultSet = statement.executeQuery();
        	
	        while (resultSet.next()) {
	        	livro.setId( resultSet.getInt("liv_id") );
	            livro.setIsbn( resultSet.getString("liv_isbn") );
	            livro.setTitulo( resultSet.getString("liv_titulo") );
	            livro.setAutor( resultSet.getString("liv_autor") );
	            livro.setEditora( resultSet.getString("liv_editora") );
	            livro.setEdicao( resultSet.getInt("liv_edicao") );
	            livro.setDtLancamento( resultSet.getDate("liv_dt_lancamento"));
	            livro.setPreco( resultSet.getDouble("liv_preco") );
	            
	            livros.add(livro);
	        }
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement, resultSet);
        }
        
        return (ArrayList<EntidadeDominio>)livros;
    }
    
    @Override
    public boolean deletar(EntidadeDominio ent) throws SQLException {
    	Livro livro = (Livro) ent;
        String sql = "DELETE FROM cwl_livros where liv_id = ?";
         
        this.jdbcConnection = ConnectionFactory.getConnection();
         
        PreparedStatement statement = null;

        try{
        	statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, livro.getId());
            
        	if(statement.executeUpdate() == 1){
                return true;
            }
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement);
        }
        
        return false;
        
    }
    
    @Override
    public boolean atualizar(EntidadeDominio ent) throws SQLException {
    	
    	this.jdbcConnection = ConnectionFactory.getConnection();
        
    	Livro livro = (Livro) ent;
        String sql = "UPDATE cwl_livros SET liv_titulo = ?, liv_autor = ?, liv_editora = ?,"
        		+ "liv_edicao = ?, liv_dt_lancamento = ?, liv_preco = ?, liv_isbn = ?";
        sql += " WHERE liv_id = ?";
        
        PreparedStatement statement = null;
        
        try {
	        statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, livro.getTitulo());
	        statement.setString(2, livro.getAutor());
	        statement.setString(3, livro.getEditora());
	        statement.setInt(4, livro.getEdicao());
	        statement.setDate(5, new java.sql.Date (livro.getDtLancamento().getTime()));
	        statement.setDouble(6, livro.getPreco());
	        statement.setString(7, livro.getIsbn());
	        statement.setInt(8, livro.getId());
	         
	        boolean rowUpdated = statement.executeUpdate() > 0;
	        return rowUpdated;
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement);
        }
        
        return false;
    }    
    
    @Override
    public EntidadeDominio consultar(int id) throws SQLException {
        Livro livro = new Livro();
        String sql = "SELECT * FROM cwl_livros WHERE liv_id = ?";
         
        this.jdbcConnection = ConnectionFactory.getConnection();
         
        PreparedStatement statement = null;
         
        ResultSet resultSet = null;
        
        try {
        	
        	statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);
             
            resultSet = statement.executeQuery();
        	
	        if (resultSet.next()) {
	        	livro.setId( resultSet.getInt("liv_id") );
	            livro.setIsbn( resultSet.getString("liv_isbn") );
	            livro.setTitulo( resultSet.getString("liv_titulo") );
	            livro.setAutor( resultSet.getString("liv_autor") );
	            livro.setEditora( resultSet.getString("liv_editora") );
	            livro.setEdicao( resultSet.getInt("liv_edicao") );
	            livro.setDtLancamento( resultSet.getDate("liv_dt_lancamento"));
	            livro.setPreco( resultSet.getDouble("liv_preco") );
	             
	            return livro;
	        }
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement, resultSet);
        }
        return null; 

    }
}
