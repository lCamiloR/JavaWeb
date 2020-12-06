<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
    <link href="../css/mystyle.css" rel="stylesheet" type="text/css"/>
    <title>CRUD Web Livraria</title>
</head>
<body>
    <h1>Gerenciamento de Livros</h1>
    <div>
        <nav class="navbar navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Crud Web Livraria</a>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Livros/new">Adicionar novo livro</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Livros">Listar todos os livros</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Funcionarios/new">Adicionar novo funcionario</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Funcionarios">Listar todos os funcionarios</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Vendas/new">Adicionar novo venda</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Vendas">Listar todos os venda</a>
            </span>
        </nav>
    </div>
    
    <div class="container centro">
        <c:if test="${livro != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${livro == null}">
            <form action="insert" method="post">
        </c:if>
        <table class="table table-borderless">
            <caption>
                    <c:if test="${livro != null}">
                        Editar livro
                    </c:if>
                    <c:if test="${livro == null}">
                        Adicionar novo livro
                    </c:if>
            </caption>
                <c:if test="${livro != null}">
                    <input type="hidden" name="id" value="<c:out value='${livro.id}' />" />
                </c:if>           
            <tr>
                <th>ISBN: </th>
                <td>
                    <input type="text" id="isbn" name="isbn" size="45"
                            value="<c:out value='${livro.isbn}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>T�tulo: </th>
                <td>
                    <input type="text" id="titulo" name="titulo" size="45"
                            value="<c:out value='${livro.titulo}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Autor: </th>
                <td>
                    <input type="text" id="autor" name="autor" size="5"
                            value="<c:out value='${livro.autor}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Editora: </th>
                <td>
                    <input type="text"  id="editora" name="editora" size="5"
                            value="<c:out value='${livro.editora}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Edi��o: </th>
                <td>
                    <input type="number" id="edicao" name="edicao" size="5"
                            value="<c:out value='${livro.edicao}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Data de Lan�amento: </th>
                <td>
                    <input type="date" id="dtLancamento" name="dtLancamento" size="5"
                            value="<c:out value='${livro.dtLancamento}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Pre�o: </th>
                <td>
                    <input type="number" id="preco" name="preco" size="5"
                            value="<c:out value='${livro.preco}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" id="salvar" value="Salvar" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>