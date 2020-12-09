package crudweblivraria.logs;

import java.text.SimpleDateFormat;
import java.util.Date;

import crudweblivraria.interfaces.ILogger;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;

public class LivroLog implements ILogger {

	@Override
	public void printLog(EntidadeDominio e) {
		Livro l = (Livro) e;
		
		StringBuilder str = new StringBuilder();
		
		Date agora = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		str.append("---------------------------------------------------\nLivro cadastrado com sucesso!\nHora do cadastro: "); 
		str.append(formatter.format(agora));
		str.append("\n\tTítulo: ");
		str.append(l.getTitulo());
		str.append("\n\tISBN: ");
		str.append(l.getIsbn());
		str.append("\n");
		
		str.append("\n\n---------------------------------------------------\n");
	}

}
