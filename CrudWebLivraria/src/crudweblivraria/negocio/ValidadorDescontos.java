package crudweblivraria.negocio;

import crudweblivraria.interfaces.IValidador;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Venda;

public class ValidadorDescontos implements IValidador {

	@Override
	public String validarCampo(EntidadeDominio ent) {
		
		Venda v = (Venda) ent;
		
		double desc = v.getDescontos();
		if(desc<=60)return "";
		return "Descontos devem ser menores ou iguais a 60%.\n";
	}

}
