package crudweblivraria.controllers.commands;

import crudweblivraria.interfaces.ICommand;
import crudweblivraria.model.domain.EntidadeDominio;

public class IndexCommand extends AbstractCommand implements ICommand {

	@Override
	public Object execute(EntidadeDominio entidade) {
		return "";
	}

}
