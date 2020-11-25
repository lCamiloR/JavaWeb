package crudweblivraria.interfaces;

import java.sql.SQLException;
import java.util.List;

import crudweblivraria.model.domain.EntidadeDominio;

public interface IDAO {
	
	public boolean inserir(EntidadeDominio ent) throws SQLException;

	public List<EntidadeDominio> consultar() throws SQLException;
	
	public boolean deletar(EntidadeDominio ent) throws SQLException;
	
	public boolean atualizar(EntidadeDominio ent) throws SQLException;
	
	public EntidadeDominio consultar(int id) throws SQLException;

}
