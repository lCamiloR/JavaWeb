package crudweblivraria.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.interfaces.*;
import crudweblivraria.model.domain.*;

public class LivroFacade implements IFacadeCRUD {
	
	IDAO dao;
	
	public LivroFacade(IDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void listarEntidades(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<EntidadeDominio> livros = dao.consultar();
        request.setAttribute("listaLivros", livros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Livros/ListaLivros.jsp");
        dispatcher.forward(request, response);
    }
	
	@Override
	public void mostrarFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Livros/FormLivro.jsp");
        dispatcher.forward(request, response);
    }
	
	@Override 
	public void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Livro livroAtual = (Livro) dao.consultar(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Livros/FormLivro.jsp");
        request.setAttribute("livro", livroAtual);
        dispatcher.forward(request, response);
 
    }
	
	@Override
	public void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editora = request.getParameter("editora");
        int edicao = Integer.parseInt(request.getParameter("edicao"));
        Date data = new SimpleDateFormat("yyyy/mm/dd").parse(request.getParameter("dtLancamento"));
        double preco = Double.parseDouble(request.getParameter("preco"));
 
        Livro novoLivro = new Livro(isbn, titulo, autor, editora, edicao, data, preco);
        dao.inserir(novoLivro);
        response.sendRedirect("list");
    }
	
	@Override
	public void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editora = request.getParameter("editora");
        int edicao = Integer.parseInt(request.getParameter("edicao"));
        Date data = new SimpleDateFormat("yyyy/mm/dd").parse(request.getParameter("dtLancamento"));
        double preco = Double.parseDouble(request.getParameter("preco"));
 
        Livro livro = new Livro(id, isbn, titulo, autor, editora, edicao, data, preco);

        dao.atualizar(livro);
        response.sendRedirect("list");
    }
	
	@Override
	public void deletar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Livro livro = new Livro(id);
        dao.deletar(livro);
        response.sendRedirect("list");
 
    }
	
}
