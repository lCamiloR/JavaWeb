package crudweblivraria.dao;

import java.sql.*;
import java.util.*;


import crudweblivraria.interfaces.IDAO;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;
import crudweblivraria.model.connection.ConnectionFactory;

public class FuncionarioDAO implements IDAO {

	private Connection jdbcConnection;
    
    @Override
    public boolean inserir(EntidadeDominio ent) throws SQLException {
    	
    	this.jdbcConnection = ConnectionFactory.getConnection();
    	Funcionario funcionario = (Funcionario) ent;
        String sql = "INSERT INTO cwl_funcionarios (fnc_nome, fnc_cpf, fnc_matricula, "
        		+ "fnc_salario) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        
        try {    
        	statement = jdbcConnection.prepareStatement(sql);
        	
	        statement.setString(1, funcionario.getNome());
	        statement.setString(2, funcionario.getCpf());
	        statement.setInt(3, funcionario.getMatricula());
	        statement.setDouble(4, funcionario.getSalario());
	         
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
	public List<EntidadeDominio> consultar() throws SQLException {
    	this.jdbcConnection = ConnectionFactory.getConnection();
        List<EntidadeDominio> funcionarios = new ArrayList<>();
         
        String sql = "SELECT * FROM cwl_funcionarios";
         
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Funcionario funcionario = new Funcionario();
        try {
        	statement = jdbcConnection.prepareStatement(sql);
        	resultSet = statement.executeQuery();
        	
	        while (resultSet.next()) {
	        	funcionario.setId( resultSet.getInt("fnc_id") );
	        	funcionario.setNome( resultSet.getString("fnc_nome") );
	        	funcionario.setCpf( resultSet.getString("fnc_cpf") );
	        	funcionario.setMatricula( resultSet.getInt("fnc_matricula") );
	        	funcionario.setSalario( resultSet.getDouble("fnc_salario") );
	            
	        	funcionarios.add(funcionario);
	        }
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement, resultSet);
        }
        
        return (ArrayList<EntidadeDominio>) funcionarios;
	}

	@Override
	public boolean deletar(EntidadeDominio ent) throws SQLException {
		Funcionario funcionario = (Funcionario) ent;
        String sql = "DELETE FROM cwl_funcionarios where fnc_id = ?";
         
        this.jdbcConnection = ConnectionFactory.getConnection();
         
        PreparedStatement statement = null;

        try{
        	statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, funcionario.getId());
            
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
        
    	Funcionario funcionario = (Funcionario) ent;
        String sql = "UPDATE cwl_funcionarios SET fnc_nome = ?, fnc_cpf = ?, fnc_matricula = ?, "
        		+ "fnc_salario = ? ";
        sql += " WHERE fnc_id = ?";
        
        PreparedStatement statement = null;
        
        try {
        	statement = jdbcConnection.prepareStatement(sql);
        	
	        statement.setString(1, funcionario.getNome());
	        statement.setString(2, funcionario.getCpf());
	        statement.setInt(3, funcionario.getMatricula());
	        statement.setDouble(4, funcionario.getSalario());
	         
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
		Funcionario funcionario = new Funcionario();
        String sql = "SELECT * FROM cwl_funcionarios WHERE fnc_id = ?";
         
        this.jdbcConnection = ConnectionFactory.getConnection();
         
        PreparedStatement statement = null;
         
        ResultSet resultSet = null;
        
        try {
        	
        	statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);
             
            resultSet = statement.executeQuery();
        	
	        if (resultSet.next()) {
	        	funcionario.setNome( resultSet.getString("fnc_nome") );
	        	funcionario.setCpf( resultSet.getString("fnc_cpf") );
	        	funcionario.setMatricula( resultSet.getInt("fnc_matricula") );
	        	funcionario.setSalario( resultSet.getDouble("fnc_salario") );
	             
	            return funcionario;
	        }
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement, resultSet);
        }
        return null; 
	}

}
