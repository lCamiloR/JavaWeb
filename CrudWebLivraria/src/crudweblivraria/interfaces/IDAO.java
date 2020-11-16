package crudweblivraria.interfaces;

import java.sql.SQLException;
import java.util.List;

import crudweblivraria.model.EntidadeDominio;

public interface IDAO {
	
	public boolean insert(EntidadeDominio ent) throws SQLException;

	public List<EntidadeDominio> listarTodos() throws SQLException;
	
	public boolean deletar(EntidadeDominio ent) throws SQLException;
	
	public boolean atualizar(EntidadeDominio ent) throws SQLException;
	
	public EntidadeDominio getEntidade(int id) throws SQLException;

}
