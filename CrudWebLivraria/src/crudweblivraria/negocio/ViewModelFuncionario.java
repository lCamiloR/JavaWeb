package crudweblivraria.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import crudweblivraria.interfaces.IDAO;
import crudweblivraria.interfaces.IViewModelStrategy;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

public class ViewModelFuncionario implements IViewModelStrategy {

	@Override
	public Map<String, List<EntidadeDominio>> processar(EntidadeDominio e, Map<String, IDAO> daos) {
		HashMap<String, List<EntidadeDominio>> viewModel = new HashMap<>();
		List<EntidadeDominio> funcionarios = new ArrayList<>();
		
		String nmFuncionario = Funcionario.class.getName();	
		
		Funcionario f = (Funcionario) e;
		
		//Checa se é uma edição ou uma inserção; se inserção, atribui uma lista nula para a viewModel.
		if(f.getId() <=0) viewModel.put(nmFuncionario, null);
		else {
			
			//Recebe o funcionario que vai ser editado do dao e adiciona a ViewModel
			IDAO dao = daos.get(nmFuncionario);
			try{
				funcionarios.add(dao.consultar(f.getId()));
			}catch (Exception ex) {
				System.out.println(ex);
				return null;
			}
		}
		
		return viewModel;
	}

}
