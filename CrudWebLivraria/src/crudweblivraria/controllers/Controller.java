package crudweblivraria.controllers;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.*;
import crudweblivraria.controllers.commands.*;
import crudweblivraria.controllers.viewhelpers.*;
import crudweblivraria.model.domain.EntidadeDominio;

@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String op = null;
	private static Map<String, ICommand> cmds;
	private static Map<String, IViewHelper> vhs;
       
    public Controller() {
        super();
        
        cmds = new HashMap<String, ICommand>();
        cmds.put("insert", new SalvarCommand());
        cmds.put("delete", new ExcluirCommand());	
        cmds.put("list", new ListarCommand());
        cmds.put("update", new AlterarCommand());
        cmds.put("new", new FormCommand());
        cmds.put("edit", new FormCommand());
        cmds.put(null, cmds.get("list"));
        
        vhs = new HashMap<String, IViewHelper>();
        vhs.put("/CrudWebLivraria/Livros?operacao=insert", new VHSalvarLivro());
        vhs.put("/CrudWebLivraria/Funcionarios?operacao=insert", new VHSalvarFuncionario());
        vhs.put("/CrudWebLivraria/Vendas?operacao=insert", new VHSalvarVenda());
        vhs.put("/CrudWebLivraria/Livros?operacao=list", new VHListarLivro());
        vhs.put("/CrudWebLivraria/Funcionarios?operacao=list", new VHListarFuncionario());
        vhs.put("/CrudWebLivraria/Vendas?operacao=list", new VHListarVenda());
        vhs.put("/CrudWebLivraria/Livros?operacao=delete", new VHDeletarLivro());
        vhs.put("/CrudWebLivraria/Funcionarios?operacao=delete", new VHDeletarFuncionario());
        vhs.put("/CrudWebLivraria/Vendas?operacao=delete", new VHDeletarVenda());
        vhs.put("/CrudWebLivraria/Livros?operacao=update", new VHEditarLivro());
        vhs.put("/CrudWebLivraria/Funcionarios?operacao=update", new VHEditarFuncionario());
        vhs.put("/CrudWebLivraria/Vendas?operacao=update", new VHEditarVenda());
        vhs.put("/CrudWebLivraria/Livros?operacao=new", new VHFormLivro());
        vhs.put("/CrudWebLivraria/Funcionarios?operacao=new", new VHFormFuncionario());
        vhs.put("/CrudWebLivraria/Vendas?operacao=new", new VHFormVenda());
        vhs.put("/CrudWebLivraria/Livros?operacao=edit", new VHFormLivro());
        vhs.put("/CrudWebLivraria/Funcionarios?operacao=edit", new VHFormFuncionario());
        vhs.put("/CrudWebLivraria/Vendas?operacao=edit", new VHFormVenda());
        vhs.put("/CrudWebLivraria/Livros/", new VHListarLivro());
        vhs.put("/CrudWebLivraria/Funcionarios/", new VHListarFuncionario());
        vhs.put("/CrudWebLivraria/Vendas/", new VHListarVenda());
        vhs.put("/CrudWebLivraria/Livros", new VHListarLivro());
        vhs.put("/CrudWebLivraria/Funcionarios", new VHListarFuncionario());
        vhs.put("/CrudWebLivraria/Vendas", new VHListarVenda());
        vhs.put("/CrudWebLivraria/", new VHIndex());
        vhs.put(null, new VHIndex());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		op = request.getParameter("operacao");		
		
		StringBuilder vhKeyBuilder = new StringBuilder();
		vhKeyBuilder.append(request.getRequestURI());
		if(op != null) {
			vhKeyBuilder.append("?operacao=");
			vhKeyBuilder.append(op);
		}
		
		
		String vhKey = vhKeyBuilder.toString();
		
		IViewHelper vh = vhs.get(vhKey);
		
		EntidadeDominio entidade = vh.getEntidade(request);
		
		ICommand cmd = cmds.get(op);		
				
		Object msg = cmd.execute(entidade);
		
		vh.setView(msg, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
