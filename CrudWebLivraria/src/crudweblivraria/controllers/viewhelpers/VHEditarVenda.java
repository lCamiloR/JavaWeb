package crudweblivraria.controllers.viewhelpers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.dao.*;
import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.interfaces.IDAO;
import crudweblivraria.model.domain.*;

public class VHEditarVenda implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		IDAO dao = new FuncionarioDAO();
		int idFuncionario = Integer.parseInt(request.getParameter("funcionarioID") );
        double descontos = Double.parseDouble(request.getParameter("descontos") );
        Funcionario funcionario;
        
        try{
        	 funcionario = (Funcionario) dao.consultar(idFuncionario);
        }catch(Exception ex) {
        	System.out.println(ex);
        	return null;
        }
        
        String[] strLivros = request.getParameterValues("livro");
        String[] strQuantias = request.getParameterValues("quantia");
        
        Map<Livro, Integer> mapaLivros = new HashMap<>();
        
        try {
        	dao = new LivroDAO();
	        for(int i = 0; i<strQuantias.length; i++) {
	        	
	        	int id = -1;
	        	int quantia =-1;
	        	
	        	id = Integer.parseInt(strQuantias[i]);
	        	quantia = Integer.parseInt(strLivros[i]);
	        	
	        	
	        	mapaLivros.put((Livro) dao.consultar(id), quantia);
	        }
        }catch(Exception e) {
        	return null;
        }
        
        Venda venda = new Venda(funcionario, mapaLivros, descontos);
        return venda;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
		String msg = (String) resultado;
		
		if(msg.equals("")) {
			try {
				response.sendRedirect("/CrudWebLivraria/Vendas?operacao=list");
			}catch(Exception ex) {
				System.out.println(ex);
			}
		}else{
			request.setAttribute("msgErro", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vendas/FormVendas.jsp");
	        try{
	        	dispatcher.forward(request, response);
	        }catch(Exception ex) {
	        	System.out.println(ex);
	        }
		}
		
	}

}
