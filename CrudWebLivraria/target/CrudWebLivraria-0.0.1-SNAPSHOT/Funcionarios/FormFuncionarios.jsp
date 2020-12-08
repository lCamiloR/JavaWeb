<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>CRUD Web Livraria</title>
</head>
<body>
    <h1>Gerenciamento de Funcionarios</h1>
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
        <c:if test="${funcionario != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${funcionario == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1">
            <caption>
                    <c:if test="${funcionario != null}">
                        Editar funcionario
                    </c:if>
                    <c:if test="${funcionario == null}">
                        Adicionar novo funcionario
                    </c:if>
            </caption>
                <c:if test="${funcionario != null}">
                    <input type="hidden" name="id" value="<c:out value='${funcionario.id}' />" />
                </c:if>           
            <tr>
                <th>Nome: </th>
                <td>
                    <input type="text" id="nome" name="nome" size="45"
                            value="<c:out value='${funcionario.nome}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>CPF: </th>
                <td>
                    <input type="text" id="cpf" name="cpf" size="45"
                            value="<c:out value='${funcionario.cpf}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Matricula: </th>
                <td>
                    <input type="text" id="matricula" name="matricula" size="5"
                            value="<c:out value='${funcionario.matricula}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Salario: </th>
                <td>
                    <input type="text"  id="salario" name="salario" size="5"
                            value="<c:out value='${funcionario.salario}' />"
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