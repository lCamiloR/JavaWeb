package crudweblivraria.controllers.viewhelpers;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;

public class VHFormLivro implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		int id;
		
		try {
			 id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException ex) {
			id = 0;
		}
		
		return new Livro(id);
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
		@SuppressWarnings("unchecked")
		Map<String, List<EntidadeDominio>> viewModel = (Map<String, List<EntidadeDominio>>) resultado;
		String nmLivro = Livro.class.getName();
		
		//Obtém da viewmodel o livro
		Livro l = null;
		try{
			 l = (Livro) viewModel.get(nmLivro).get(0);
		}catch(NullPointerException ex) {
			l = null;
		}
		
		//Se for uma edicao a viewmodel retorna um EntidadeDominio nula, entao checa-se para nao passar algo nulo para a view
		if (l != null) request.setAttribute("livro", l);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Livros/FormLivro.jsp");
		
        try{
        	dispatcher.forward(request, response);
        }catch(Exception ex) {
        	System.out.println(ex);
        }
		
	}

}
