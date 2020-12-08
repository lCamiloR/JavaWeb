package crudweblivraria.negocio;

import java.util.*;

import crudweblivraria.interfaces.IDAO;
import crudweblivraria.interfaces.IViewModelStrategy;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;

public class ViewModelLivro implements IViewModelStrategy {

	@Override
	public Map<String, List<EntidadeDominio>> processar(EntidadeDominio e, Map<String, IDAO> daos) {
		
		HashMap<String, List<EntidadeDominio>> viewModel = new HashMap<>();
		List<EntidadeDominio> livros = new ArrayList<>();
		
		String nmLivro = Livro.class.getName();	
		
		Livro l = (Livro) e;
		
		//Checa se é uma edição ou uma inserção; se inserção, atribui uma lista nula para a viewModel.
		if(l.getId() <= 0) viewModel.put(nmLivro, null);
		else {
			
			//Recebe o livro que vai ser editado do dao e adiciona a ViewModel
			IDAO dao = daos.get(nmLivro);
			try{
				livros.add(dao.consultar(l.getId()));
			}catch (Exception ex) {
				System.out.println(ex);
				return null;
			}
			viewModel.put(nmLivro, livros);
		}
		
		return viewModel;
	}

}
