package crudweblivraria.interfaces;

import java.util.Map;
import java.util.List;

import crudweblivraria.model.domain.EntidadeDominio;

public interface IViewModelStrategy {
	
	public Map<String, List<EntidadeDominio>> processar(EntidadeDominio e, Map<String, IDAO> daos);

}
