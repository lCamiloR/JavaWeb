package crudweblivraria.model;

import java.util.List;

import crudweblivraria.interfaces.IValidador;

public class Venda extends EntidadeDominio {

	protected int id;
	protected Funcionario funcionario;
	protected List<Livro> livros;
	protected double valorTotal;
	protected double descontos;
	protected IValidador validadorDescontos;
	
	public Venda() {
		validadorDescontos = new ValidadorDescontos();
	}
	
	public Venda(int id, Funcionario funcionario, List<Livro> livros, double valorTotal, double descontos) {
		this(funcionario, livros, valorTotal, descontos);
		this.id = id;
	}
	
	public Venda(Funcionario funcionario, List<Livro> livros, double valorTotal, double descontos) {
		this();
		this.funcionario = funcionario;
		this.livros = livros;
		this.setValorTotal();
		this.descontos = descontos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
		this.setValorTotal();
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal() {
		valorTotal = 0;
		for(Livro l: livros) {
			valorTotal += l.getPreco();
		}
	}

	public double getDescontos() {
		return descontos;
	}

	public void setDescontos(double descontos) {
		this.descontos = descontos;
	}
	
	public double calcDescontos(double descontos) {
		if(this.getValorTotal()>=200) return descontos+=20;
		return descontos;
	}
	
	public boolean validarDescontos() {
		return validadorDescontos.validarCampo(this.getDescontos() + "");
	}
	
}
