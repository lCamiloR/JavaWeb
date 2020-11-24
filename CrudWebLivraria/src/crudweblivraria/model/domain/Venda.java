package crudweblivraria.model.domain;

import java.util.Iterator;
import java.util.Map;

import crudweblivraria.interfaces.IValidador;
import crudweblivraria.model.ValidadorDescontos;

public class Venda extends EntidadeDominio {

	protected int id;
	protected Funcionario funcionario;
	protected Map<Livro, Integer> livros;
	protected double valorTotal;
	protected double descontos;
	protected IValidador validadorDescontos;
	
	public Venda() {
		validadorDescontos = new ValidadorDescontos();
	}
	
	public Venda(int id) {
		this();
		this.id = id;
	}
	
	public Venda(int id, Funcionario funcionario, Map<Livro, Integer> livros, double valorTotal, double descontos) {
		this(funcionario, livros, valorTotal, descontos);
		this.id = id;
	}
	
	public Venda(Funcionario funcionario, Map<Livro, Integer> livros, double valorTotal, double descontos) {
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

	public Map<Livro, Integer> getLivros() {
		return livros;
	}

	public void setLivros(Map<Livro, Integer> livros) {
		this.livros = livros;
		this.setValorTotal();
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal() {
		valorTotal = 0;
		Iterator it = livros.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Livro, Integer> entrada = (Map.Entry<Livro, Integer>) it.next();
			valorTotal = entrada.getKey().getPreco() * entrada.getValue();
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
	
	public void addLivro(Livro livro, int quantia) {
		livros.put(livro, quantia);
	}
	
	public void removeLivro(Livro livro) {
		livros.remove(livro);
	}
	
	public void removeLivro(Livro livro, int quantia) {
		livros.remove(livro, quantia);
	}
	
}
