package crudweblivraria.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IDAO;
import crudweblivraria.interfaces.IFacadeCRUD;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

public class FuncionariosFacade implements IFacadeCRUD {
	
	private IDAO dao;
	
	public FuncionariosFacade(IDAO dao) {
		this.dao = dao;
	}

	@Override
	public void listarEntidades(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<EntidadeDominio> funcionarios = dao.consultar();
        request.setAttribute("listaFuncionarios", funcionarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Funcionarios/ListaFuncionarios.jsp");
        dispatcher.forward(request, response);
	}

	@Override
	public void mostrarFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Funcionarios/FormFuncionarios.jsp");
        dispatcher.forward(request, response);
	}

	@Override
	public void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        Funcionario funcionarioAtual = (Funcionario) dao.consultar(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Funcionarios/FormFuncionarios.jsp");
        request.setAttribute("funcionario", funcionarioAtual);
        dispatcher.forward(request, response);
	}

	@Override
	public void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		double salario = Double.parseDouble(request.getParameter("salario"));
		
		Funcionario funcionario = new Funcionario(nome, cpf, matricula, salario);
		dao.inserir(funcionario);
		response.sendRedirect("list");
		
	}

	@Override
	public void atualizar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		double salario = Double.parseDouble(request.getParameter("salario"));
		
		Funcionario funcionario = new Funcionario(nome, cpf, matricula, salario);
		dao.atualizar(funcionario);
		response.sendRedirect("list");
		
	}

	@Override
	public void deletar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
        int id = Integer.parseInt(request.getParameter("id"));
        
        Funcionario funcionario = new Funcionario(id);
        dao.deletar(funcionario);
        response.sendRedirect("list");
	}

}
