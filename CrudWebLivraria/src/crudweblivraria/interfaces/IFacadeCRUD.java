package crudweblivraria.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IFacadeCRUD {

	public void listarEntidades(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException;
	
	public void mostrarFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
	
	public void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException;
	
	public void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException;
	
	public void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException;
	
	public void deletar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException;
	
}
