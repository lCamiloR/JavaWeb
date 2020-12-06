<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
    <link href="../css/mystyle.css" rel="stylesheet" type="text/css"/>
	<meta charset="ISO-8859-1">
    <title>CRUD Web Livraria</title>
</head>
<body>
    <h1>Gerenciamento de Venda</h1>
    <div>
        <nav class="navbar navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Crud Web Livraria</a>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Livros/new">Adicionar novo livro</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Livros">Listar todos os livros</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Funcionarios/new">Adicionar novo funcionario</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Funcionarios">Listar todos os funcionarios</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Vendas/new">Adicionar novo venda</a>
            </span>
            <span class="navbar-text btn-dark">
                <a href="/CrudWebLivraria/Vendas">Listar todos os venda</a>
            </span>
        </nav>
    </div>
    
    <div class="container centro">
        <c:if test="${venda != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${venda == null}">
            <form action="insert" method="post">
        </c:if>
        <table class="table table-borderless">
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
                    	<h>N�o h� livro cadastrado no sistema. Favor cadastrar e tentar novamente.</h>
                    </c:if>
                    <c:if test="${funcionarios != null}">
                    	<h>N�o h� funcionario cadastrado no sistema. Favor cadastrar e tentar novamente.</h>
                    </c:if>
                </td>
            </tr>
        </table>
        </form>
    </div>   
    <script type="text/javascript" src = "../Javascript/FormVendasListaLivros.js"></script>
</body>



</html>