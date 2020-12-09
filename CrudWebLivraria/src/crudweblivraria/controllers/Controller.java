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

@WebServlet({"/crud/", "/crud/Livros", "/crud/Funcionarios", "/crud/Vendas",
	"/crud/Livros/", "/crud/Funcionarios/", "/crud/Vendas/"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String op = null;
	private static Map<String, ICommand> cmds;
	private static Map<String, IViewHelper> vhs;
    private static String urlPrefix = "/CrudWebLivraria/crud/";
	
	
    public Controller() {
        super();
        
        cmds = new HashMap<String, ICommand>();
        cmds.put("insert", new SalvarCommand());
        cmds.put("delete", new ExcluirCommand());	
        cmds.put("list", new ListarCommand());
        cmds.put("update", new AlterarCommand());
        cmds.put("new", new FormCommand());
        cmds.put("edit", new FormCommand());
        cmds.put(null, new ListarCommand());
        
        vhs = new HashMap<String, IViewHelper>();
        vhs.put(urlPrefix + "Livros?operacao=insert", new VHSalvarLivro());
        vhs.put(urlPrefix + "Funcionarios?operacao=insert", new VHSalvarFuncionario());
        vhs.put(urlPrefix + "Vendas?operacao=insert", new VHSalvarVenda());
        vhs.put(urlPrefix + "Livros?operacao=list", new VHListarLivro());
        vhs.put(urlPrefix + "Funcionarios?operacao=list", new VHListarFuncionario());
        vhs.put(urlPrefix + "Vendas?operacao=list", new VHListarVenda());
        vhs.put(urlPrefix + "Livros?operacao=delete", new VHDeletarLivro());
        vhs.put(urlPrefix + "Funcionarios?operacao=delete", new VHDeletarFuncionario());
        vhs.put(urlPrefix + "Vendas?operacao=delete", new VHDeletarVenda());
        vhs.put(urlPrefix + "Livros?operacao=update", new VHEditarLivro());
        vhs.put(urlPrefix + "Funcionarios?operacao=update", new VHEditarFuncionario());
        vhs.put(urlPrefix + "Vendas?operacao=update", new VHEditarVenda());
        vhs.put(urlPrefix + "Livros?operacao=new", new VHFormLivro());
        vhs.put(urlPrefix + "Funcionarios?operacao=new", new VHFormFuncionario());
        vhs.put(urlPrefix + "Vendas?operacao=new", new VHFormVenda());
        vhs.put(urlPrefix + "Livros?operacao=edit", new VHFormLivro());
        vhs.put(urlPrefix + "Funcionarios?operacao=edit", new VHFormFuncionario());
        vhs.put(urlPrefix + "Vendas?operacao=edit", new VHFormVenda());
        vhs.put(urlPrefix + "Livros/", new VHListarLivro());
        vhs.put(urlPrefix + "Funcionarios/", new VHListarFuncionario());
        vhs.put(urlPrefix + "Vendas/", new VHListarVenda());
        vhs.put(urlPrefix + "Livros", new VHListarLivro());
        vhs.put(urlPrefix + "Funcionarios", new VHListarFuncionario());
        vhs.put(urlPrefix + "Vendas", new VHListarVenda());
        vhs.put(urlPrefix, new VHIndex());
        vhs.put(null, new VHIndex());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		op = request.getParameter("operacao");		
		
		StringBuilder vhKeyBuilder = new StringBuilder();
		vhKeyBuilder.append(request.getRequestURI());
		if(op != null) {
			if(!op.equals("")) {
				vhKeyBuilder.append("?operacao=");
				vhKeyBuilder.append(op);
			}
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
