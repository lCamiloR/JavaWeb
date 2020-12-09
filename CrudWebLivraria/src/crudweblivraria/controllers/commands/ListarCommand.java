package crudweblivraria.controllers.commands;

import crudweblivraria.model.domain.EntidadeDominio;

public class ListarCommand extends AbstractCommand {

	@Override
	public Object execute(EntidadeDominio entidade) {
		return facade.listarEntidades(entidade);
	}

}
