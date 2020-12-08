package crudweblivraria.controllers.viewhelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Venda;

public class VHDeletarVenda implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		 
		Venda venda = new Venda(id);
        return venda;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			response.sendRedirect("/CrudWebLivraria/Vendas?operacao=list");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
