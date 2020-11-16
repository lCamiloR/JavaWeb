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
        <a href="/new">Adicionar novo livro</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">Listar todos os livros</a>
         
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
                <th>Data de Lançamento</th>
                <th>Preço</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="livro" items="${listBook}">
                <tr>
                    <td><c:out value="${livro.id}" /></td>
                    <td><c:out value="${livro.isbn}" /></td>
                    <td><c:out value="${livro.autor}" /></td>
                    <td><c:out value="${livro.editora}" /></td>
                    <td><c:out value="${livro.edicao}" /></td>
                    <td><c:out value="${livro.dtLancamento}" /></td>
                    <td><c:out value="${livro.preco}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${book.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${book.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>