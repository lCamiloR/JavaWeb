package crudweblivraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import crudweblivraria.interfaces.IDAO;
import crudweblivraria.model.connection.ConnectionFactory;
import crudweblivraria.model.domain.*;

//Essa classe lida somente com a tabela cwl_vendas. A tabela cwl_vendas_livros é gerenciada pela classe VendasLivrosDAO.

public class VendaDAO implements IDAO {

	private Connection jdbcConnection;
	private VendasLivrosDAO vendasLivrosDAO;
	
	@Override
	public String inserir(EntidadeDominio ent) throws SQLException {
		
		this.jdbcConnection = ConnectionFactory.getMysqlConnection();
    	Venda venda = (Venda) ent;
        String sqlVendas = "INSERT INTO cwl_vendas (ven_valor_total, ven_descontos, "
        		+ "fk_funcionarios_vendas) VALUES (?, ?, ?);";
        PreparedStatement statementVendas = null;
        vendasLivrosDAO = new VendasLivrosDAO();
        
        for(Map.Entry<Livro, Integer> entrada: venda.getLivros().entrySet()) {
        	System.out.println(entrada.getKey().getTitulo() + "");
        }
        
        try {    
        	
        	statementVendas = jdbcConnection.prepareStatement(sqlVendas, Statement.RETURN_GENERATED_KEYS);
        	
	        statementVendas.setDouble(1, venda.getValorTotal());
	        statementVendas.setDouble(2, venda.getDescontos());
	        statementVendas.setInt(3, venda.getFuncionario().getId());
	         
	        statementVendas.execute();
	        
	        ResultSet keys = statementVendas.getGeneratedKeys();
	        
	        while(keys.next()) {
	        	int id = keys.getInt(1);
	        	venda.setId(id);
	        }
        } catch (SQLException ex) {
            System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(jdbcConnection, statementVendas);
        }
        
        try {
        	vendasLivrosDAO.inserir(venda);
        	return "";
        }catch (SQLException ex){
        	System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
        }
        
        return "Não foi possível salvar os dados no banco de dados.";
	}

	@Override
	public List<EntidadeDominio> consultar() throws SQLException {
		
		this.jdbcConnection = ConnectionFactory.getMysqlConnection();
        List<Venda> vendas = new ArrayList<>();
        List<EntidadeDominio> retorno = new ArrayList<>();
        vendasLivrosDAO = new VendasLivrosDAO();
         
        String sql = "SELECT * "
        		+ " FROM cwl_vendas "
        		+ " INNER JOIN cwl_funcionarios "
        		+ " ON cwl_vendas.fk_funcionarios_vendas = cwl_funcionarios.fnc_id";
         
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Venda venda = new Venda();
        
        try {
        	statement = jdbcConnection.prepareStatement(sql);
        	resultSet = statement.executeQuery();
        	
	        while (resultSet.next()) {
	        	
	        	venda = new Venda();
	        	
	        	int id = resultSet.getInt("ven_id");
	        	
        		venda.setId( id );
	        	venda.setDescontos( resultSet.getDouble("ven_descontos") );
	        	
	        	Funcionario funcionario = getFuncionario(resultSet);
	        	venda.setFuncionario(funcionario);
	        	
	        	venda.setValorTotal();
	        	vendas.add(venda);
        	}
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados, erro no VendasDAO.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement, resultSet);
        }
        
        try {
        	for(Venda v: vendas) {
        		Venda vendaLivros = (Venda) vendasLivrosDAO.consultar(v.getId());
        		
        		v.setLivros( vendaLivros.getLivros() ) ;
        		retorno.add(v);
        	}
        	return retorno;
        	
        }catch (SQLException ex){
        	System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
        }
        
        return null;
	}

	@Override
	public boolean deletar(EntidadeDominio ent) throws SQLException {
		
		Venda venda = (Venda) ent;
        String sql = "DELETE FROM cwl_vendas where ven_id = ?";
        boolean delecaoSucesso = false;
        vendasLivrosDAO = new VendasLivrosDAO();
        
        this.jdbcConnection = ConnectionFactory.getMysqlConnection();
         
        PreparedStatement statementVendas = null;

        try{
        	statementVendas = jdbcConnection.prepareStatement(sql);
            statementVendas.setInt(1, venda.getId());
            
        	if(statementVendas.executeUpdate() == 1){
        		delecaoSucesso = true;
            }
        	
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statementVendas);
        }
        
        if(delecaoSucesso) {
        	try {
            	vendasLivrosDAO.deletar(venda);
            	return true;
            }catch (SQLException ex){
            	System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
            }
        }
        
        return false;
	}

	@Override
	public String atualizar(EntidadeDominio ent) throws SQLException {
		this.jdbcConnection = ConnectionFactory.getMysqlConnection();
        
    	Venda venda = (Venda) ent;
        String sql = "UPDATE cwl_vendas SET ven_valor_total = ?, ven_descontos = ?, "
        		+ "fk_funcionarios_vendas = ?,";
        sql += " WHERE ven_id = ?";
        vendasLivrosDAO = new VendasLivrosDAO();
        
        PreparedStatement statement = null;
        
        boolean rowUpdated = false;
        
        try {
        	statement = jdbcConnection.prepareStatement(sql);
        	
	        statement.setDouble(1, venda.getValorTotal());
	        statement.setDouble(2, venda.getDescontos());
	        statement.setInt(3, venda.getFuncionario().getId());
	        statement.setDouble(4, venda.getId());
	         
	        rowUpdated = statement.executeUpdate() > 0;
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement);
        }
        
        if(rowUpdated) {
	        try {
	        	vendasLivrosDAO.atualizar(venda);
	        	return "";
	        }catch (SQLException ex){
	        	System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
	        }
        }
        
        return "Não foi possível salvar os dados no banco de dados.";
	}

	@Override
	public EntidadeDominio consultar(int id) throws SQLException {
		this.jdbcConnection = ConnectionFactory.getMysqlConnection();
		Venda venda = new Venda();
		vendasLivrosDAO = new VendasLivrosDAO();
         
        String sql = "SELECT *"
        		+ "FROM cwl_vendas "
        		+ "INNER JOIN cwl_funcionarios "
        		+ "ON cwl_vendas.fk_funcionarios_vendas = cwl_funcionarios.fnc_id "
        		+ "WHERE ven_id = ?";
         
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
        	statement = jdbcConnection.prepareStatement(sql);
        	
        	statement.setInt(1, id);
        	resultSet = statement.executeQuery();
        	
        	venda.setId( resultSet.getInt("ven_id") );
        	venda.setDescontos( resultSet.getDouble("ven_descontos") );
        	
        	venda.setFuncionario(getFuncionario(resultSet));
        }catch(SQLException ex) {
            System.out.println("Não foi possível consultar os dados no banco de dados.\nErro: " + ex.getMessage());
        } finally {
        	ConnectionFactory.closeConnection(jdbcConnection, statement, resultSet);
        }
        
        try {
        	Venda vendaLivros = (Venda) vendasLivrosDAO.consultar(venda.getId());
    		venda.setLivros( vendaLivros.getLivros() ) ;
    		return venda;
        }catch (SQLException ex){
        	System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + ex.getMessage());
        }
        
        return null;
	}

	private Funcionario getFuncionario(ResultSet resultSet) throws SQLException {
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setId( resultSet.getInt("fnc_id") );
    	funcionario.setNome( resultSet.getString("fnc_nome") );
    	funcionario.setCpf( resultSet.getString("fnc_cpf") );
    	funcionario.setMatricula( resultSet.getInt("fnc_matricula") );
    	funcionario.setSalario( resultSet.getDouble("fnc_salario") );
    	
    	return funcionario;
	}
}
