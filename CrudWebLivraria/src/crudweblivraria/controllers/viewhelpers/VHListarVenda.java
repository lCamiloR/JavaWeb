package crudweblivraria.controllers.viewhelpers;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Venda;

public class VHListarVenda implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		return new Venda();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ArrayList<Venda> vendas = (ArrayList<Venda>) resultado;
		request.setAttribute("listaVendas", vendas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vendas/ListaVendas.jsp");
        try{
        	dispatcher.forward(request, response);
        }catch(Exception ex) {
        	System.out.println(ex);
        }
		
	}

}
