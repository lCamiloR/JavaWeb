<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRUD Web Livraria</title>
</head>
<body>
    <h1>Gerenciamento de Vendas</h1>
    <h2>
        <a href="/CrudWebLivraria/Livros/new">Adicionar novo livro</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Livros">Listar todos os livros</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Funcionarios/new">Adicionar novo funcionario</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Funcionarios">Listar todos os funcionarios</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Vendas/new">Adicionar novo venda</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Vendas">Listar todos os venda</a>
        &nbsp;&nbsp;
         
    </h2>
    <div align="center">
        <table border="1">
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
                    <c:forEach var="livro" items="${venda.livros.keySet()}">
                    	<td><c:out value="${livro.titulo}" /></td>
                    </c:forEach>
                    <td><c:out value="${venda.valorTotal} * ${venda.descontos}" /></td>
                    

                    <td>
                        <a href="/CrudWebLivraria/Vendas/edit?id=<c:out value='${livro.id}' />">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/CrudWebLivraria/Vendas/delete?id=<c:out value='${livro.id}' />">Deletar</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>