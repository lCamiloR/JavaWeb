package crudweblivraria.interfaces;

import java.sql.SQLException;
import java.util.List;

import crudweblivraria.model.domain.EntidadeDominio;

public interface IDAO {
	
	public String inserir(EntidadeDominio ent) throws SQLException;

	public List<EntidadeDominio> consultar() throws SQLException;
	
	public boolean deletar(EntidadeDominio ent) throws SQLException;
	
	public String atualizar(EntidadeDominio ent) throws SQLException;
	
	public EntidadeDominio consultar(int id) throws SQLException;

}
