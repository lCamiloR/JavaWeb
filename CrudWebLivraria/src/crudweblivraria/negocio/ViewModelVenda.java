package crudweblivraria.negocio;

import java.util.*;

import crudweblivraria.interfaces.*;
import crudweblivraria.model.domain.*;

public class ViewModelVenda implements IViewModelStrategy{

	@Override
	public Map<String, List<EntidadeDominio>> processar(EntidadeDominio e, Map<String, IDAO> daos) {
		
		HashMap<String, List<EntidadeDominio>> viewModel = new HashMap<>();
		List<EntidadeDominio> vendas = new ArrayList<>();
		List<EntidadeDominio> livros = new ArrayList<>();
		List<EntidadeDominio> funcionarios = new ArrayList<>();
		IDAO dao;
		
		String nmVenda = Venda.class.getName();
		String nmFuncionario = Funcionario.class.getName();	
		String nmLivro = Livro.class.getName();	
			
		Venda v = (Venda) e;
		
		//Se for uma venda nova, atribui ela nula no viewModel
		if(v.getId() <= 0) viewModel.put(nmVenda, null);
		else {
			
			//Não é nula, então pega a venda correta do dao
			dao = daos.get(nmVenda);
			try{
				vendas.add( dao.consultar(v.getId()));
			}catch (Exception ex) {
				System.out.println(ex);
				return null;
			}
			viewModel.put(nmVenda, vendas);
		}
		
		//Pega a lista de funcionarios para fazer o select na view
		dao = daos.get(nmFuncionario);
		try{
			funcionarios = dao.consultar();
		}catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
		viewModel.put(nmFuncionario, funcionarios);
		
		//Pega a lista de livros para fazer o select na view
		dao = daos.get(nmLivro);
		try{
			livros = dao.consultar();
		}catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
		viewModel.put(nmLivro, livros);
					
		
		
		return viewModel;
	}

}
