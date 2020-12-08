package crudweblivraria.negocio;

import crudweblivraria.interfaces.IValidador;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

public class ValidadorCpf implements IValidador {

	@Override
	public String validarCampo(EntidadeDominio ent) {
		
		Funcionario f = (Funcionario) ent;
		String campo = f.getCpf();
		
		if (campo == null) {
	        return "CPF n�o pode ser nulo.\n";
	    }
		if(campo.length()!=11) {
			return "CPF tem de ter 11 caracteres.\n";
		}
		
		try {
			@SuppressWarnings("unused")
			int i = Integer.parseInt(campo);
		}catch(NumberFormatException nfe) {
			return "CPF tem de ser n�merico.\n";
		}
		return algoritmoCpf(campo);
	}
	
	public String algoritmoCpf(String cpf) {
		
		int soma = 0;
		
		for(int i = 0; i<cpf.length(); i++) {
			soma += Character.getNumericValue(cpf.charAt(i));
		}	
		
		String strSoma = soma + "";
		
		if(strSoma.charAt(0) == strSoma.charAt(1)) return "";
		
		return "N�o � um CPF v�lido.\n";
	}

	
}
