package crudweblivraria.negocio;

import crudweblivraria.interfaces.IValidador;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;

public class ValidadorIsbn implements IValidador {

	public String validarCampo(EntidadeDominio ent) {
		
		Livro l = (Livro) ent;
		String campo = l.getIsbn();
		
		if((campo.length() != 10)&&(campo.length() != 13)) return "ISBN deve ter 10 ou 13 caracteres de comprimento.\n";
		
		int soma = 0;
		
		for(int i = 0; i<campo.length(); i++) {
			int n = Character.getNumericValue(campo.charAt(i));
			soma += n*(campo.length() - i);
		}
		
		if(soma%11 != 0) return "Não é um ISBN válido.\n";
		
		return "";
	}

}
