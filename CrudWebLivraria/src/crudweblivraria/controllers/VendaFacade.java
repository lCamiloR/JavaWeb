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
import crudweblivraria.model.domain.*;

public class VendaFacade implements IFacadeCRUD {

	private IDAO dao;
	
	public VendaFacade(IDAO dao) {
		this.dao = dao;
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("Vendas/FormVendas.jsp");
        dispatcher.forward(request, response);
	}

	@Override
	public void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        Venda vendaAtual = (Venda) dao.consultar(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Vendas/FormVendas.jsp");
        request.setAttribute("venda", vendaAtual);
        dispatcher.forward(request, response);
	}

	@Override
	public void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {
		int idFuncionario = Integer.parseInt(request.getParameter("funcionarioID") );
        double descontos = Double.parseDouble(request.getParameter("descontos") );
        
        Venda novaVenda = new Venda();
        dao.inserir(novaVenda);
        response.sendRedirect("list");
		
	}

	@Override
	public void atualizar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {
		int idFuncionario = Integer.parseInt(request.getParameter("funcionarioID") );
        double descontos = Double.parseDouble(request.getParameter("descontos") );
        
        Venda venda = new Venda();
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
