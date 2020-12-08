package crudweblivraria.controllers.commands;

import crudweblivraria.interfaces.ICommand;
import crudweblivraria.model.domain.EntidadeDominio;

public class FormCommand extends AbstractCommand implements ICommand {

	@Override
	public Object execute(EntidadeDominio entidade) {
		return facade.mostrarForm(entidade);
	}

}
