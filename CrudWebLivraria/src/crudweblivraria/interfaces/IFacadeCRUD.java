package crudweblivraria.interfaces;

import crudweblivraria.model.domain.EntidadeDominio;

import java.util.Map;
import java.util.List;

public interface IFacadeCRUD {

	public List<EntidadeDominio> listarEntidades(EntidadeDominio e);
	
	public String cadastrar(EntidadeDominio e);
	
	public String atualizar(EntidadeDominio e);
	
	public boolean deletar(EntidadeDominio e);
	
	public Map<String, List<EntidadeDominio>> mostrarForm(EntidadeDominio e);
	
}
