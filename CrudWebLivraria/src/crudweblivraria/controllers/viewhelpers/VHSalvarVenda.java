package crudweblivraria.controllers.viewhelpers;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IDAO;
import crudweblivraria.dao.FuncionarioDAO;
import crudweblivraria.dao.LivroDAO;
import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;
import crudweblivraria.model.domain.Livro;
import crudweblivraria.model.domain.Venda;

public class VHSalvarVenda implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		IDAO dao = new FuncionarioDAO();
		
		int idFuncionario = Integer.parseInt(request.getParameter("funcionariosSelect") );
        double descontos = Double.parseDouble(request.getParameter("descontos") );
        Funcionario funcionario = null;
        
        try{
        	funcionario = (Funcionario) dao.consultar(idFuncionario);
        }catch(Exception ex){
        	System.out.println(ex);
        }
        
        String[] strLivros = request.getParameterValues("livro");
        String[] strQuantias = request.getParameterValues("quantia");
        
        Livro livro = new Livro();
        int quantia;
        int id;
        
        Map<Livro, Integer> mapaLivros = new HashMap<>();
    	dao = new LivroDAO();
    	
        try {
	        for(int i = 0; i<strQuantias.length; i++) {
	        	
	        	livro = new Livro();
	        	
	        	id = -1;
	        	quantia =-1;
	        	
	        	id = Integer.parseInt(strLivros[i]);
	        	quantia = Integer.parseInt(strQuantias[i]);
	        	
	        	livro = (Livro) dao.consultar(id);
	        	
	        	mapaLivros.put(livro, quantia);
	        }
        }catch(Exception e) {
        	System.out.println("Não foi possível recuperar a entidade da página" + e + "\n\n");
        }
        
        Venda novaVenda = new Venda(funcionario, mapaLivros, descontos);      
        return novaVenda;
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
