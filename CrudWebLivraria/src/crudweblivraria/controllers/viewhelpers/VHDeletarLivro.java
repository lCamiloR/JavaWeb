package crudweblivraria.controllers.viewhelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;

public class VHDeletarLivro implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		 
        Livro livro = new Livro(id);
        return livro;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			response.sendRedirect("/CrudWebLivraria/crud/Livros?operacao=list");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
