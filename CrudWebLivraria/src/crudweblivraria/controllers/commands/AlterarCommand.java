package crudweblivraria.controllers.commands;

import crudweblivraria.model.domain.EntidadeDominio;

public class AlterarCommand extends AbstractCommand {

	@Override
	public Object execute(EntidadeDominio entidade) {
		return facade.atualizar(entidade);
	}

}
