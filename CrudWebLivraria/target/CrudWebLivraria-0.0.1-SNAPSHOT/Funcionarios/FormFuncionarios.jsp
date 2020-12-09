<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
    <link href="../css/mystyle.css" rel="stylesheet" type="text/css"/>
<head>
    <title>CRUD Web Livraria</title>
</head>
<body>
    <h1>Gerenciamento de Funcionarios</h1>
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
        <c:if test="${funcionario != null}">
            <form action="/CrudWebLivraria/crud/Funcionarios?operacao=update" method="post">
        </c:if>
        <c:if test="${funcionario == null}">
            <form action="/CrudWebLivraria/crud/Funcionarios?operacao=insert" method="post">
        </c:if>
        
        <h3>
        	<c:out value='${msgErro}' />
        </h3>
        
        <table class="table table-borderless">
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
                    <input type="number" min="0" id="cpf" name="cpf" size="45"
                            value="<c:out value='${funcionario.cpf}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Matricula: </th>
                <td>
                    <input type="number" min="0" id="matricula" name="matricula" size="5"
                            value="<c:out value='${funcionario.matricula}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Salario: </th>
                <td>
                    <input type="number" min="0" step=0.01  id="salario" name="salario" size="5"
                            value="<c:out value='${funcionario.salario}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                <c:if test="${funcionario == null}">
                    <input type="submit" id="operacao" name="operacao" value="Salvar" />
                </c:if>
                <c:if test="${funcionario != null}">
                    <input type="submit" id="operacao" name="operacao" value="Editar" />
                </c:if>
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>