package crudweblivraria.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.dao.LivroDAO;
import crudweblivraria.interfaces.IFacadeCRUD;

import java.text.ParseException;

@WebServlet("/Livros")
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
 
        try {
            switch (action) {
            case "/new":
            	facade.mostrarFormCadastro(request, response);
                break;
            case "/insert":
            	facade.cadastrar(request, response);
                break;
            case "/delete":
            	facade.deletar(request, response);
                break;
            case "/edit":
            	facade.mostrarFormEditar(request, response);
                break;
            case "/update":
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
