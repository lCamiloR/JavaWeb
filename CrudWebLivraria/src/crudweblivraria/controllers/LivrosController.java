package crudweblivraria.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.dao.LivroDAO;
import crudweblivraria.interfaces.*;

import java.text.ParseException;

@WebServlet(urlPatterns = {"/Livros", "/Livros/new", "/Livros/insert", "/Livros/delete", "/Livros/update", "/Livros/list",
		"/Livros/edit"})
public class LivrosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IFacadeCRUD facade;
 
    public void init() {
        facade = new LivroFacade(new LivroDAO());
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
        String action = request.getServletPath();
        //response.getWriter().println(action);
        try {
            switch (action) {
            case "/Livros/new":
            	facade.mostrarFormCadastro(request, response);
                break;
            case "/Livros/insert":
            	facade.cadastrar(request, response);
                break;
            case "/Livros/delete":
            	facade.deletar(request, response);
                break;
            case "/Livros/edit":
            	facade.mostrarFormEditar(request, response);
                break;
            case "/Livros/update":
            	facade.atualizar(request, response);
                break;
            default:
            	facade.listarEntidades(request, response);
                break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }
 
}
