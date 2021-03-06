package crudweblivraria.interfaces;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudweblivraria.model.domain.EntidadeDominio;

public interface IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request);
	
	public void setView(Object resultado, 
			HttpServletRequest request, HttpServletResponse response)throws ServletException;
	

}
