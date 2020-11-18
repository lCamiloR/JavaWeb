<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>CRUD Web Livraria</title>
</head>
<body>
    <h1>Gerenciamento de Livros</h1>
    <h2>
        <a href="/CrudWebLivraria//new">Adicionar novo livro</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/CrudWebLivraria//list">Listar todos os livros</a>
         
    </h2>
    <div align="center">
        <c:if test="${book != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${book == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1">
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
                <th>Título: </th>
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
                <th>Edição: </th>
                <td>
                    <input type="number" id="edicao" name="edicao" size="5"
                            value="<c:out value='${livro.edicao}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Data de Lançamento: </th>
                <td>
                    <input type="date" id="dtLancamento" name="dtLancamento" size="5"
                            value="<c:out value='${livro.dtLancamento}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Preço: </th>
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