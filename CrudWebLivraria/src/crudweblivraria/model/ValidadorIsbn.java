package crudweblivraria.model;

import crudweblivraria.interfaces.IValidador;

public class ValidadorIsbn implements IValidador {

	public boolean validarCampo(String campo) {
		if((campo.length() != 10)&&(campo.length() != 13)) return false;
		
		int soma = 0;
		
		for(int i = 0; i<campo.length(); i++) {
			int n = Character.getNumericValue(campo.charAt(i));
			soma += n*(campo.length() - i);
		}
		
		if(soma%11 != 0) return false;
		
		return true;
	}

}
