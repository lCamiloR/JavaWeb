package crudweblivraria.controllers.commands;

import crudweblivraria.model.domain.EntidadeDominio;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Object execute(EntidadeDominio entidade) {
		return facade.deletar(entidade);
	}

}
