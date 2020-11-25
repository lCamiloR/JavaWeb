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
    <h1>Gerenciamento de Livros</h1>
    <h2>
        <a href="/CrudWebLivraria/Livros/new">Adicionar novo livro</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Livros/list">Listar todos os livros</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Funcionarios/new">Adicionar novo funcionario</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Funcionarios/list">Listar todos os funcionarios</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Vendas/new">Adicionar novo venda</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Vendas/list">Listar todos os venda</a>
        &nbsp;&nbsp;
         
    </h2>
    <div align="center">
        <table border="1">
            <caption>Lista de Livros</caption>
            <tr>
                <th>ID</th>
                <th>Isbn</th>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Editora</th>
                <th>Edicao</th>
                <th>Data de Lan�amento</th>
                <th>Pre�o</th>
                <th>A��es</th>
            </tr>
            <c:forEach var="livro" items="${listaLivros}">
                <tr>
                    <td><c:out value="${livro.id}" /></td>
                    <td><c:out value="${livro.isbn}" /></td>
                    <td><c:out value="${livro.titulo}" /></td>
                    <td><c:out value="${livro.autor}" /></td>
                    <td><c:out value="${livro.editora}" /></td>
                    <td><c:out value="${livro.edicao}" /></td>
                    <td><c:out value="${livro.dtLancamento}" /></td>
                    <td><c:out value="${livro.preco}" /></td>
                    <td>
                        <a href="/CrudWebLivraria/edit?id=<c:out value='${livro.id}' />">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/CrudWebLivraria/delete?id=<c:out value='${livro.id}' />">Deletar</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>