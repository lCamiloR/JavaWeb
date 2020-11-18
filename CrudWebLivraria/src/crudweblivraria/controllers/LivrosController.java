package crudweblivraria.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.dao.LivroDAO;
import crudweblivraria.interfaces.IDAO;
import crudweblivraria.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LivrosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IDAO livroDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        livroDAO = new LivroDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
            	mostrarFormCadastro(request, response);
                break;
            case "/insert":
            	cadastrar(request, response);
                break;
            case "/delete":
                deletar(request, response);
                break;
            case "/edit":
            	mostrarFormEditar(request, response);
                break;
            case "/update":
            	atualizar(request, response);
                break;
            default:
            	listarEntidades(request, response);
                break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listarEntidades(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<EntidadeDominio> livros = livroDAO.listarTodos();
        request.setAttribute("listaLivros", livros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Livros/ListaLivros.jsp");
        dispatcher.forward(request, response);
    }
 
    private void mostrarFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Livros/FormLivro.jsp");
        dispatcher.forward(request, response);
    }
 
    private void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Livro livroAtual = (Livro) livroDAO.getEntidade(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Livros/FormLivro.jsp");
        request.setAttribute("livro", livroAtual);
        dispatcher.forward(request, response);
 
    }
 
    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editora = request.getParameter("editora");
        int edicao = Integer.parseInt(request.getParameter("edicao"));
        Date data = new SimpleDateFormat("yyyy/mm/dd").parse(request.getParameter("dtLancamento"));
        double preco = Double.parseDouble(request.getParameter("preco"));
 
        Livro novoLivro = new Livro(isbn, titulo, autor, editora, edicao, data, preco);
        livroDAO.inserir(novoLivro);
        response.sendRedirect("list");
    }
 
    private void atualizar(HttpServletRequest request, HttpServletResponse response)
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

        livroDAO.atualizar(livro);
        response.sendRedirect("list");
    }
 
    private void deletar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Livro livro = new Livro(id);
        livroDAO.deletar(livro);
        response.sendRedirect("list");
 
    }
}
