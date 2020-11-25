package crudweblivraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import crudweblivraria.model.connection.ConnectionFactory;
import crudweblivraria.model.domain.*;

public class VendasLivrosDAO{

	private Connection jdbcConnection;
	private LivroDAO livroDAO;
	
	public boolean inserir(EntidadeDominio ent) throws SQLException {
		
		this.jdbcConnection = ConnectionFactory.getConnection();
    	Venda venda = (Venda) ent;
        String sql = "INSERT INTO cwl_vendas_livros (vpl_quantia_livros, fk_vendas, "
        		+ "fk_livros) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        
        try {    
        	statement = jdbcConnection.prepareStatement(sql);
        	
        	Iterator it = venda.getLivros().entrySet().iterator();
        	while(it.hasNext()) {
        		Map.Entry<Livro, Integer> entrada = (Map.Entry<Livro, Integer>) it.next();
        		
        		statement.setInt(1, entrada.getValue());
        		statement.setInt(2, venda.getId());
        		statement.setInt(3, entrada.getKey().getId());
        		
        		statement.addBatch();
        	}
        	
	        statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(jdbcConnection, statement);
        }
        
        return false;
	}

	public boolean deletar(EntidadeDominio ent) throws SQLException {
		Venda venda = (Venda) ent;
        String sql = "DELETE FROM cwl_vendas_livros where fk_vendas = ? ";
         
        this.jdbcConnection = ConnectionFactory.getConnection();
         
        PreparedStatement statement = null;

        try{
        	statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, venda.getId());
            
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

	public boolean atualizar(EntidadeDominio ent) throws SQLException {
		
		this.jdbcConnection = ConnectionFactory.getConnection();
        
    	Venda venda = (Venda) ent;
        String sql = "UPDATE cwl_vendas_livros SET vpl_quantia_livros = ? ";
        sql += " WHERE fk_vendas = ?, fk_livros = ?";
        
        PreparedStatement statement = null;
        
        try {
        	
        	statement = jdbcConnection.prepareStatement(sql);
        	
        	Iterator it = venda.getLivros().entrySet().iterator();
        	while(it.hasNext()) {
        		Map.Entry<Livro, Integer> entrada = (Map.Entry<Livro, Integer>) it.next();
        		
        		statement.setInt(1, entrada.getValue());
        		statement.setInt(2, venda.getId());
        		statement.setInt(3, entrada.getKey().getId());
        		
        		statement.addBatch();
        	}
	         
	        boolean rowUpdated = statement.executeUpdate() > 0;
	        return rowUpdated;
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement);
        }
        
        return false;
	}

	public EntidadeDominio consultar(int id) throws SQLException {
		
		Venda venda = new Venda();
		Map<Integer, Integer> mapaIds = new HashMap<Integer, Integer>();
		Map<Livro, Integer> mapaVendas = new HashMap<Livro, Integer>();
		livroDAO = new LivroDAO();
        String sql = "SELECT * FROM cwl_vendas_livros WHERE fk_vendas = ?";
         
        this.jdbcConnection = ConnectionFactory.getConnection();
         
        PreparedStatement statement = null;
         
        ResultSet resultSet = null;
        
        try {
        	
        	statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);
             
            resultSet = statement.executeQuery();
        	
	        if (resultSet.next()) {
	        	int quantia = resultSet.getInt("vpl_quantia_livros");
	        	int livroId = resultSet.getInt("fk_livros");
	        	
	        	mapaIds.put(livroId, quantia);
	        }
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement, resultSet);
        }
        
        try {
	        Iterator it = mapaIds.entrySet().iterator();
	    	while(it.hasNext()) {
	    		Map.Entry<Integer, Integer> livroId = (Map.Entry<Integer, Integer>) it.next();
	    		Livro livro = (Livro) livroDAO.consultar(livroId.getKey());
	    		mapaVendas.put(livro, livroId.getValue());
	    	}
	    	
	    	venda.setLivros(mapaVendas);
	    	return venda;
	    	
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        }

        return null; 
	}

}
