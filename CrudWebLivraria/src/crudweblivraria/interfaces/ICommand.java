package crudweblivraria.interfaces;

import crudweblivraria.model.domain.EntidadeDominio;

public interface ICommand {
	public Object execute(EntidadeDominio entidade);
	
}
