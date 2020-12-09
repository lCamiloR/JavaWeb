package crudweblivraria.negocio;

import crudweblivraria.interfaces.IValidador;
import crudweblivraria.dao.FuncionarioDAO;
import crudweblivraria.interfaces.IDAO;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class ValidadorExistenciaCpf implements IValidador {

	@Override
	public String validarCampo(EntidadeDominio ent) {

		Funcionario funcionario = (Funcionario) ent;
		IDAO dao = new FuncionarioDAO();
		List<EntidadeDominio> funcionarios;
		
		try {
			funcionarios = dao.consultar();
		}catch(SQLException ex) {
			return "Erro no acesso ao banco de dados.";
		}
		
		
		for(EntidadeDominio e: funcionarios) {
			Funcionario f = (Funcionario) e;
			
			if(f.getCpf().equals(funcionario.getCpf())) return "Já existe um funcionário com esse CPF no banco de dados.";
			
		}
		
		return "";
	}

}
