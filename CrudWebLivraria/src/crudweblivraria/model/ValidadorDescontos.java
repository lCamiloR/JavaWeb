package crudweblivraria.model;

import crudweblivraria.interfaces.IValidador;

public class ValidadorDescontos implements IValidador {

	@Override
	public boolean validarCampo(String campo) {
		double desc = Double.parseDouble(campo);
		if(desc<=60)return true;
		return false;
	}

}
