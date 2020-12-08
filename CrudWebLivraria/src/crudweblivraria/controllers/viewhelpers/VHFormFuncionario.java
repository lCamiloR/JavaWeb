package crudweblivraria.controllers.viewhelpers;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

public class VHFormFuncionario implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		int id;
		
		try {
			 id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException ex) {
			id = 0;
		}
		
		return new Funcionario(id);
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		@SuppressWarnings("unchecked")
		Map<String, List<EntidadeDominio>> viewModel = (Map<String, List<EntidadeDominio>>) resultado;
		String nmFuncionario = Funcionario.class.getName();
		Funcionario f = null;
		//Obtém da viewmodel o livro
		try{
			 f = (Funcionario) viewModel.get(nmFuncionario).get(0);
		}catch(NullPointerException ex) {
			f = null;
		}
		
		//Se for uma edicao a viewmodel retorna um EntidadeDominio nula, entao checa-se para nao passar algo nulo para a view
		if (f != null) request.setAttribute("funcionario", f);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Funcionarios/FormFuncionarios.jsp");
		
        try{
        	dispatcher.forward(request, response);
        }catch(Exception ex) {
        	System.out.println(ex);
        }

	}

}
