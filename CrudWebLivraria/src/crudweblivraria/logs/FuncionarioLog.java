package crudweblivraria.logs;

import java.text.SimpleDateFormat;
import java.util.Date;

import crudweblivraria.interfaces.ILogger;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

public class FuncionarioLog implements ILogger {

	@Override
	public void printLog(EntidadeDominio e) {
		
		Funcionario f = (Funcionario) e;
		
		StringBuilder str = new StringBuilder();
		
		Date agora = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		str.append("---------------------------------------------------\nFuncionario cadastrado com sucesso!\nHora do cadastro: "); 
		str.append(formatter.format(agora));
		str.append("\n\tNome: ");
		str.append(f.getNome());
		str.append("\n\tCPF: ");
		str.append(f.getCpf());
		str.append("\n");
		
		str.append("\n\n---------------------------------------------------\n");

		System.out.println(str.toString());
	}

}
