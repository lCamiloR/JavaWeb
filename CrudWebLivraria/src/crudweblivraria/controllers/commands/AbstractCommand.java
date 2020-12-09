package crudweblivraria.controllers.commands;

import crudweblivraria.interfaces.ICommand;
import crudweblivraria.interfaces.IFacadeCRUD;
import crudweblivraria.controllers.Facade;

public abstract class AbstractCommand implements ICommand {

	public IFacadeCRUD facade = new Facade();

}
