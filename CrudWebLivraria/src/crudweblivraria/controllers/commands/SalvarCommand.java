package crudweblivraria.controllers.commands;

import crudweblivraria.model.domain.EntidadeDominio;

public class SalvarCommand extends AbstractCommand{

	@Override
	public Object execute(EntidadeDominio entidade) {
		return facade.cadastrar(entidade); 
	}
	
}
