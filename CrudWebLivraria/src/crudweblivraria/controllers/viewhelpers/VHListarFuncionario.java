package crudweblivraria.controllers.viewhelpers;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

public class VHListarFuncionario implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		return new Funcionario();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
		ArrayList<Funcionario> funcionarios = null;
		
		try{
			funcionarios = (ArrayList<Funcionario>) resultado;
		}catch(Exception ex) {
			System.out.println(ex);
			return;
		}
        request.setAttribute("listaFuncionarios", funcionarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Funcionarios/ListaFuncionarios.jsp");
        try{
        	dispatcher.forward(request, response);
        }catch(Exception ex) {
        	System.out.println(ex);
        }
		
	}
	
}
