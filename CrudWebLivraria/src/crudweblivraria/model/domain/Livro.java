package crudweblivraria.model.domain;

import java.util.Date;

import crudweblivraria.interfaces.IValidador;
import crudweblivraria.model.ValidadorIsbn;

public class Livro extends EntidadeDominio{

	private int id;
	private String isbn;
	private String titulo;
	private String autor;
    private String editora;
    private int edicao;
    private Date dtLancamento;
    private double preco;
    
    private IValidador validadorIsbn;
    
    public Livro() {
    	validadorIsbn = new ValidadorIsbn();
    }
 
    public Livro(int id) {
    	this();
        this.id = id;
    }
 
    public Livro(int id, String isbn, String titulo, String autor, String editora, int edicao, Date dtLancamento, double preco) {
    	this(isbn, titulo, autor, editora, edicao, dtLancamento, preco);
    	this.id = id;
    }
    
    public Livro(String isbn, String titulo, String autor, String editora, int edicao, Date dtLancamento, double preco) {
    	this();
    	this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.edicao = edicao;
        this.dtLancamento = dtLancamento;
        this.preco = preco;
        this.isbn = isbn;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public Date getDtLancamento() {
		return dtLancamento;
	}

	public void setDtLancamento(Date dtLancamento) {
		this.dtLancamento = dtLancamento;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
 
	public boolean validarIsbn() {
		return validadorIsbn.validarCampo(isbn);
	}

}
