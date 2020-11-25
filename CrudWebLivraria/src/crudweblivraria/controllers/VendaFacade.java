package crudweblivraria.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.IDAO;
import crudweblivraria.interfaces.IFacadeCRUD;
import crudweblivraria.model.domain.*;
import crudweblivraria.dao.*;

public class VendaFacade implements IFacadeCRUD {

	private IDAO dao;
	
	public VendaFacade() {
		this.dao = new VendaDAO();
	}
	
	@Override
	public void listarEntidades(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<EntidadeDominio> vendas = dao.consultar();
        request.setAttribute("listaVendas", vendas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Vendas/ListaVendas.jsp");
        dispatcher.forward(request, response);
	}

	@Override
	public void mostrarFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<EntidadeDominio> funcionarios = new ArrayList<>();
		List<EntidadeDominio> livros = new ArrayList<>();
		
		try {
		dao = new FuncionarioDAO();
		funcionarios = dao.consultar();
        dao = new LivroDAO();
        livros = dao.consultar();
        dao = new VendaDAO();
		}catch(SQLException e) {
			System.out.println("Não foi possível salvar os dados no banco de dados.\nErro: " + e.getMessage());
		}
		
		request.setAttribute("funcionarios", funcionarios);
		request.setAttribute("livros", livros);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Livros/FormVendas.jsp");
        dispatcher.forward(request, response);
	}

	@Override
	public void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        Venda vendaAtual = (Venda) dao.consultar(id);
        
        List<EntidadeDominio> funcionarios = new ArrayList<>();
		List<EntidadeDominio> livros = new ArrayList<>();
		
		dao = new FuncionarioDAO();
		funcionarios = dao.consultar();
        dao = new LivroDAO();
        livros = dao.consultar();
        dao = new VendaDAO();
		
		request.setAttribute("funcionarios", funcionarios);
		request.setAttribute("livros", livros);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Livros/FormVendas.jsp");
        request.setAttribute("venda", vendaAtual);
        dispatcher.forward(request, response);
	}

	@Override
	public void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {
		int idFuncionario = Integer.parseInt(request.getParameter("funcionarioID") );
        double descontos = Double.parseDouble(request.getParameter("descontos") );
        
        this.dao = new FuncionarioDAO();
        Funcionario funcionario = (Funcionario) dao.consultar(idFuncionario);
        
        String[] strLivros = request.getParameterValues("livro");
        String[] strQuantias = request.getParameterValues("quantia");
        
        Map<Livro, Integer> mapaLivros = new HashMap<>();
        
        try {
	        for(int i = 0; i<strQuantias.length; i++) {
	        	
	        	int id = -1;
	        	int quantia =-1;
	        	
	        	id = Integer.parseInt(strQuantias[i]);
	        	quantia = Integer.parseInt(strLivros[i]);
	        	
	        	this.dao = new LivroDAO();
	        	mapaLivros.put((Livro) dao.consultar(id), quantia);
	        }
        }catch(Exception e) {
        	return;
        }
        
        
        Venda novaVenda = new Venda(funcionario, mapaLivros, descontos);
        this.dao = new VendaDAO();
        dao.inserir(novaVenda);
        response.sendRedirect("list");
		
	}

	@Override
	public void atualizar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {
		int idFuncionario = Integer.parseInt(request.getParameter("funcionarioID") );
        double descontos = Double.parseDouble(request.getParameter("descontos") );
        
        this.dao = new FuncionarioDAO();
        Funcionario funcionario = (Funcionario) dao.consultar(idFuncionario);
        
        String[] strLivros = request.getParameterValues("livro");
        String[] strQuantias = request.getParameterValues("quantia");
        
        Map<Livro, Integer> mapaLivros = new HashMap<>();
        
        try {
	        for(int i = 0; i<strQuantias.length; i++) {
	        	
	        	int id = -1;
	        	int quantia =-1;
	        	
	        	id = Integer.parseInt(strQuantias[i]);
	        	quantia = Integer.parseInt(strLivros[i]);
	        	
	        	this.dao = new LivroDAO();
	        	mapaLivros.put((Livro) dao.consultar(id), quantia);
	        }
        }catch(Exception e) {
        	return;
        }
        
        
        Venda venda = new Venda(funcionario, mapaLivros, descontos);
        this.dao = new VendaDAO();
        dao.atualizar(venda);
        response.sendRedirect("list");
		
	}

	@Override
	public void deletar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		 
		Venda venda = new Venda();
        dao.deletar(venda);
        response.sendRedirect("list");
		
	}

}
