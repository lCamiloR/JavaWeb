package crudweblivraria.controllers.viewhelpers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.*;

public class VHFormVenda implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		int id;
		
		try {
			 id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException ex) {
			id = 0;
		}
		
		return new Venda(id);
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
		@SuppressWarnings("unchecked")
		Map<String, List<EntidadeDominio>> viewModel = (Map<String, List<EntidadeDominio>>) resultado;
		String nmVenda = Venda.class.getName();
		String nmFuncionario = Funcionario.class.getName();	
		String nmLivro = Livro.class.getName();	
		Venda v = null;
		List<EntidadeDominio> livros = null;
		List<EntidadeDominio> funcionarios = null;
		
		//Get os objetos da viewModel
		try {
		v = (Venda) viewModel.get(nmVenda).get(0);
		}catch(NullPointerException ex) {
			v = null;
		}
		
		try {
			funcionarios = viewModel.get(nmFuncionario);
		}catch(NullPointerException ex) {
			funcionarios = null;
		}
		
		try {
			livros = viewModel.get(nmLivro);
		}catch(NullPointerException ex) {
			livros = null;
		}
		
		//Atribui como paramentros da jsp, a venda sendo passada so em caso de edicao
		request.setAttribute("funcionarios", funcionarios);
		request.setAttribute("livros", livros);
        if (v != null) request.setAttribute("venda", v);
        
        //Redireciona
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vendas/FormVendas.jsp");
        try{
        	dispatcher.forward(request, response);
        }catch(Exception ex) {
        	System.out.println(ex);
        }
	}

}
