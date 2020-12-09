<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
<link href="../css/mystyle.css" rel="stylesheet" type="text/css"/>
<meta charset="ISO-8859-1">
<title>CRUD Web Livraria</title>
</head>
<body>
    <h1>Gerenciamento de Vendas</h1>
    <div>
        <nav class="navbar navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Crud Web Livraria</a>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/crud/Livros?operacao=new">Adicionar novo livro</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/crud/Livros">Listar todos os livros</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/crud/Funcionarios?operacao=new">Adicionar novo funcionario</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/crud/Funcionarios">Listar todos os funcionarios</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/crud/Vendas?operacao=new">Adicionar nova venda</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/crud/Vendas">Listar todos os venda</a>
            </span>
        </nav>
    </div>
    <div class="container centro">
        <table class="table table-hover table-bordered"">
            <caption>Lista de venda</caption>
            <tr>
                <th>ID</th>
                <th>Funcionario</th>
                <th>Valor Total</th>
                <th>Descontos</th>
                <th>Livros</th>
                <th>Valor Final</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="venda" items="${listaVendas}">
                <tr>
                    <td><c:out value="${venda.id}" /></td>
                    <td><c:out value="${venda.funcionario.nome}" /></td>
                    <td><c:out value="${venda.valorTotal}" /></td>
                    <td><c:out value="${venda.descontos}" /></td>
                    <td><c:forEach var="livro" items="${venda.livros.keySet()}">
                    	<c:out value="${livro.titulo}" /><br>
                    </c:forEach></td>
                    <td><c:out value="${venda.valorTotal * ((venda.descontos)/100) }" /></td>
                    

                    <td>
                        <a href="/CrudWebLivraria/crud/Vendas?operacao=edit&id=<c:out value='${venda.id}' />">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/CrudWebLivraria/crud/Vendas?operacao=delete&id=<c:out value='${venda.id}' />">Deletar</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>