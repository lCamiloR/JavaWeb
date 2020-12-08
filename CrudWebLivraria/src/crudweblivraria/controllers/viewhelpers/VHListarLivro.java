package crudweblivraria.controllers.viewhelpers;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;

public class VHListarLivro implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		return new Livro();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
		ArrayList<Livro> livros = null;
		
		try{
			livros = (ArrayList<Livro>) resultado;
		}catch(Exception ex) {
			System.out.println(ex);
			return;
		}
        request.setAttribute("listaLivros", livros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Livros/ListaLivros.jsp");
        try{
        	dispatcher.forward(request, response);
        }catch(Exception ex) {
        	System.out.println(ex);
        }

	}

}
