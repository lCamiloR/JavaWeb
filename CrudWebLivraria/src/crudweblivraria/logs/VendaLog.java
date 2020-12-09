package crudweblivraria.logs;

import crudweblivraria.interfaces.ILogger;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Venda;
import crudweblivraria.model.domain.Livro;
import java.util.Date;  
import java.text.SimpleDateFormat;  

public class VendaLog implements ILogger {

	@Override
	public void printLog(EntidadeDominio e) {

		Venda venda = (Venda) e;

		StringBuilder str = new StringBuilder();
		
		Date agora = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		str.append("---------------------------------------------------\n\nVenda cadastrada com sucesso!\nHora do cadastro: "); 
		str.append(formatter.format(agora));
		for(Livro l: venda.getLivros().keySet()){
			str.append("\n\tTítulo: ");
			str.append(l.getTitulo());
			str.append("\tISBN: ");
			str.append(l.getIsbn());
		}
		
		str.append("\n\n---------------------------------------------------\n");
		
		
		
	}

}
