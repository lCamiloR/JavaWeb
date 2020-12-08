package crudweblivraria.controllers.viewhelpers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IViewHelper;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;

public class VHEditarLivro implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id"));
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editora = request.getParameter("editora");
        int edicao = Integer.parseInt(request.getParameter("edicao"));
        Date data = null;
        try {
        	SimpleDateFormat strData =  new SimpleDateFormat("yyyy-MM-dd");
        	data = strData.parse(request.getParameter("dtLancamento"));
        }catch(Exception ex) {
        	System.out.println(ex);
        	return null;
        }
    	double preco = Double.parseDouble(request.getParameter("preco"));
 
        Livro livro = new Livro(id, isbn, titulo, autor, editora, edicao, data, preco);

        return livro;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
		String msg = (String) resultado;
		
		if(msg.equals("")){
			try {
				response.sendRedirect("/CrudWebLivraria/Livros?operacao=list");
			}catch(Exception ex) {
				System.out.println(ex);
			}
		}else{
			request.setAttribute("msgErro", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Livros/FormLivro.jsp");
	        try{
	        	dispatcher.forward(request, response);
	        }catch(Exception ex) {
	        	System.out.println(ex);
	        }
		}

	}

}
