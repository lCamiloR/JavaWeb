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
                    <input type="hidden" name="id" value="<c:out value='${venda.id}' />" />
                </c:if>           
            <tr>
                <th>Funcionario: </th>
                <td>
                    <select name="funcionariosSelect" id="funcionariosSelect">
		            	<c:if test="${funcionarios != null}">
			            	 <c:forEach var="funcionario" items="${funcionarios}">
							 	 <option value="<c:out value='${funcionario.id}'/>"> <c:out value='${funcionario.nome}'/> </option>
							</c:forEach>
						</c:if>
					</select>
                </td>
            </tr>
            <tr>
                <th>Valor Total: </th>
                <td>
                    <input type="text" id="valorTotal" name="valorTotal" size="45"
                            value=0
                    />
                </td>
            </tr>
            <tr>
                <th>Descontos: </th>
                <td>
                    <input type="text" id="descontos" name="descontos" size="45"
                            value="<c:out value='${venda.descontos}' />"
                        />
                </td>
            </tr>
            <tr>
            <th>Livros: </th>
            	<td>
		            <table id="livrosTabela">
		            	
		            </table>
		            Novo livro:
		            
		            <select name="livrosSelect" id="livrosSelect">
		            	<c:if test="${livros != null}">
			            	 <c:forEach var="livro" items="${livros}">
							 	 <option value="<c:out value='${livro.id}'/>"><c:out value='${livro.titulo}'/></option>
							</c:forEach>
						</c:if>
						<!--<option value=1>Volvo</option>
						<option value=2>Banana</option>-->
					</select>
					 <input type="number" id = "quantia"/>
		            <button id = "btAdicionarLivro">Adicionar Livro</button>
	            </td>
	        </tr>
            <tr>
                <td colspan="2" align="center">
                	<c:if test="${livros != null}">
                		<c:if test="${funcionarios != null}">
                    		<input type="submit" id="salvar" value="Salvar" />
                    	</c:if>
                    </c:if>
                    <c:if test="${livros == null}">
                    	<h>Não há livro cadastrado no sistema. Favor cadastrar e tentar novamente.</h>
                    </c:if>
                    <c:if test="${funcionarios != null}">
                    	<h>Não há funcionario cadastrado no sistema. Favor cadastrar e tentar novamente.</h>
                    </c:if>
                </td>
            </tr>
        </table>
        </form>
    </div>   
    <script type="text/javascript" src = "../Javascript/FormVendasListaLivros.js"></script>
</body>



</html>