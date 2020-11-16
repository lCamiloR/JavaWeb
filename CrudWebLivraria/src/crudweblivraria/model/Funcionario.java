package crudweblivraria.model;

import crudweblivraria.interfaces.IValidador;

public class Funcionario extends EntidadeDominio{

	private int id;
	private String nome;
	private String cpf;
	private int matricula;
	private double salario;
	
	private IValidador validadorCpf;
	
	public Funcionario() {
		validadorCpf = new ValidadorCpf();
	}
	
	public Funcionario(int id, String nome, String cpf, int matricula, double salario) {
		this(nome, cpf, matricula, salario);
		this.id = id;
	}
	
	public Funcionario(String nome, String cpf, int matricula, double salario) {
		this();
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.salario = salario;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public boolean validarCpf() {
		return validadorCpf.validarCampo(cpf);
	}

}
