package crudweblivraria.controllers;

import java.util.*;

import crudweblivraria.interfaces.*;
import crudweblivraria.model.domain.*;
import crudweblivraria.dao.*;
import crudweblivraria.negocio.*;

public class Facade implements IFacadeCRUD {

	private Map<String, IDAO> daos;

	private Map<String, List<IValidador>> rns;
	
	private Map<String, IViewModelStrategy> vms;
	
	public Facade() {
		definirDAO();
		definirStrategies();
		definirViewModelStrategies();
	}
	
	private void definirDAO() {
		daos = new HashMap<String, IDAO>();
		daos.put(Livro.class.getName(), new LivroDAO());
		daos.put(Venda.class.getName(), new VendaDAO());
		daos.put(Funcionario.class.getName(), new FuncionarioDAO());
	}
	
	private void definirStrategies() {
		
		rns = new HashMap<String, List<IValidador>>();
		
		List<IValidador> rnsFuncionario = new ArrayList<>();
		rnsFuncionario.add(new ValidadorCpf());
		
		List<IValidador> rnsLivro = new ArrayList<>();
		rnsLivro.add(new ValidadorIsbn());
		
		List<IValidador> rnsVenda = new ArrayList<>();
		rnsVenda.add(new ValidadorDescontos());
		
		rns.put(Funcionario.class.getName(), rnsFuncionario);
		rns.put(Livro.class.getName(), rnsLivro);
		rns.put(Venda.class.getName(), rnsVenda);
	}
	
	private void definirViewModelStrategies() {
		vms = new HashMap<>();
		
		vms.put(Funcionario.class.getName(), new ViewModelFuncionario());
		vms.put(Livro.class.getName(), new ViewModelLivro());
		vms.put(Venda.class.getName(), new ViewModelVenda());
	}
	
	private String executarRegras(EntidadeDominio e) {
		String nmClasse = e.getClass().getName();
		String naoFalhou = "";
		
		List<IValidador> regras = rns.get(nmClasse);
		
		if(regras != null) {
			for (IValidador i : regras) {
				naoFalhou += i.validarCampo(e);
			}
		}
		
		return naoFalhou;
	}
	
	@Override
	public List<EntidadeDominio> listarEntidades(EntidadeDominio e) {
		String nmClasse = e.getClass().getName();
		IDAO dao = daos.get(nmClasse);
		
		try {
			List<EntidadeDominio> entidades = dao.consultar();
			return entidades;
		}catch(Exception ex){
			System.out.println(ex);
		}
		return null;
	}

	@Override
	public String cadastrar(EntidadeDominio e) {
		String nmClasse = e.getClass().getName();
		IDAO dao = daos.get(nmClasse);
		
		String msg = executarRegras(e);
		
		if(msg.equals("")){
			try {				
				return dao.inserir(e);
			}catch(Exception ex){
				System.out.println(ex);
			}
			
			return "Erro.";
		}else return msg;

	}

	@Override
	public String atualizar(EntidadeDominio e) {
		String nmClasse = e.getClass().getName();
		IDAO dao = daos.get(nmClasse);
		String msg = executarRegras(e);
		
		if(msg.equals("")) {
			try {
				return dao.atualizar(e);
			}catch(Exception ex){
				System.out.println(ex);
			}
			
			return "Erro.";
		}else return msg;
		
		
	}

	@Override
	public boolean deletar(EntidadeDominio e) {
		String nmClasse = e.getClass().getName();
		IDAO dao = daos.get(nmClasse);
		
		try {
			return dao.deletar(e);
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return false;
	}

	@Override
	public Map<String, List<EntidadeDominio>> mostrarForm(EntidadeDominio e) {
		String nmClasse = e.getClass().getName();
		IViewModelStrategy vm = vms.get(nmClasse);
		Map<String, List<EntidadeDominio>> viewModel;
		
		viewModel = vm.processar(e, daos);
		
		return viewModel;
	}

	

}
