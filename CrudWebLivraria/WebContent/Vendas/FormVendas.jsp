<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="ISO-8859-1">
    <title>CRUD Web Livraria</title>
</head>
<body>
    <h1>Gerenciamento de Venda</h1>
    <h2>
        <a href="/CrudWebLivraria/Vendas/new">Adicionar nova venda</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/CrudWebLivraria/Vendas/list">Listar todas as Vendas</a>
         
    </h2>
    <div align="center">
        <c:if test="${venda != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${venda == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1">
            <caption>
                    <c:if test="${venda != null}">
                        Editar livro
                    </c:if>
                    <c:if test="${venda == null}">
                        Adicionar novo livro
                    </c:if>
            </caption>
                <c:if test="${venda != null}">
                    <input type="hidden" name="id" value="<c:out value='${livro.id}' />" />
                </c:if>           
            <tr>
                <th>Funcionario: </th>
                <td>
                    <input type="text" id="isbn" name="funcionario" size="45"
                            value="<c:out value='${venda.funcionario.id}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Valor Total: </th>
                <td>
                    <input type="text" id="titulo" name="titulo" size="45"
                            value="<c:out value='${venda.valorTotal}' />"
                    />
                </td>
            </tr>
            <tr>
            	<td>
		            <table id="livrosTabela">
		            	
		            </table>
		            Novo livro:
		            
		            <select name="livros" id="livrosSelect">
		            	<c:forEach var="livro" items="${livros}">
						 	<option value="${livro.titulo}">${livro.titulo}</option>
						 </c:forEach>
					</select>
		            <button id = "btAdicionarLivro">Adicionar Livro</button>
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

<script>

</script>
</html>