package crudweblivraria.controllers.viewhelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

public class VHDeletarFuncionario implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Funcionario funcionario = new Funcionario(id);
		return funcionario;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			response.sendRedirect("/CrudWebLivraria/Funcionarios?operacao=list");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
