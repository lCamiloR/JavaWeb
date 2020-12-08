package crudweblivraria.controllers.viewhelpers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

public class VHEditarFuncionario implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		double salario = Double.parseDouble(request.getParameter("salario"));
		
		Funcionario funcionario = new Funcionario(nome, cpf, matricula, salario);
		return funcionario;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		String msg = (String) resultado; 
		if(msg.equals("")) {
			try {
				response.sendRedirect("/CrudWebLivraria/Funcionarios?operacao=list");
			}catch(Exception ex) {
				System.out.println(ex);
			}
		}else {
			request.setAttribute("msgErro", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Funcionarios/FormFuncionarios.jsp");
	        try{
	        	dispatcher.forward(request, response);
	        }catch(Exception ex) {
	        	System.out.println(ex);
	        }
		}

	}

}
