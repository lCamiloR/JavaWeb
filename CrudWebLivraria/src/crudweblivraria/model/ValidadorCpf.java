package crudweblivraria.model;

import crudweblivraria.interfaces.IValidador;

public class ValidadorCpf implements IValidador {

	@Override
	public boolean validarCampo(String campo) {
		if (campo == null) {
	        return false;
	    }
		if(campo.length()!=11) {
			return false;
		}
		
		try {
			int n = Integer.parseInt(campo);
		}catch(NumberFormatException nfe) {
			return false;
		}
		return algoritmoCpf(campo);
	}
	
	public boolean algoritmoCpf(String cpf) {
		
		int soma = 0;
		
		for(int i = 0; i<cpf.length(); i++) {
			soma += Character.getNumericValue(cpf.charAt(i));
		}	
		
		String strSoma = soma + "";
		
		if(strSoma.charAt(0) == strSoma.charAt(1)) return true;
		
		return false;
	}

	
}
