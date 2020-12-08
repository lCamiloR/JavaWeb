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
    <h1>Gerenciamento de Funcionarios</h1>
    <h2>
        <a href="/CrudWebLivraria/Livros?operacao=new">Adicionar novo livro</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Livros">Listar todos os livros</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Funcionarios?operacao=new">Adicionar novo funcionario</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Funcionarios">Listar todos os funcionarios</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Vendas?operacao=new">Adicionar novo venda</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/Vendas">Listar todos os venda</a>
        &nbsp;&nbsp;
         
    </h2>
    <div align="center">
        <table border="1">
            <caption>Lista de Funcionarios</caption>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Matrícula</th>
                <th>Salário</th>
            </tr>
            <c:forEach var="f" items="${listaFuncionarios}">
                <tr>
                    <td><c:out value="${f.id}" /></td>
                    <td><c:out value="${f.nome}" /></td>
                    <td><c:out value="${f.cpf}" /></td>
                    <td><c:out value="${f.matricula}" /></td>
                    <td><c:out value="${f.salario}" /></td>
                    <td>
                        <a href="/CrudWebLivraria/Funcionarios?operacao=edit&id=<c:out value='${f.id}' />">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/CrudWebLivraria/Funcionarios?operacao=delete&id=<c:out value='${f.id}' />">Deletar</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>