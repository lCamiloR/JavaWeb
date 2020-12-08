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
    <h2>
        <a href="/CrudWebLivraria/crud/Livros?operacao=new">Adicionar novo livro</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/crud/Livros">Listar todos os livros</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/crud/Funcionarios?operacao=new">Adicionar novo funcionario</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/crud/Funcionarios">Listar todos os funcionarios</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/crud/Vendas?operacao=new">Adicionar novo venda</a>
        &nbsp;&nbsp;
        <a href="/CrudWebLivraria/crud/Vendas">Listar todos os venda</a>
        &nbsp;&nbsp;
         
    </h2>
    <div align="center">
        <c:if test="${venda != null}">
            <form action="/CrudWebLivraria/crud/Vendas?operacao=update" method="post">
        </c:if>
        <c:if test="${venda == null}">
            <form action="/CrudWebLivraria/crud/Vendas?operacao=insert" method="post">
        </c:if>
        
        <c:if test="${msgErro != null}">
        	<caption>
          		<c:out value='${msgErro}' />
           </caption>
        </c:if>
        
        <table border="1">
            <caption>
                    <c:if test="${venda != null}">
                        Editar Venda
                    </c:if>
                    <c:if test="${venda == null}">
                        Adicionar nova venda
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
                <th>Descontos: </th>
                <td>
                    <input type="number" step=0.1 id="descontos" name="descontos" size="45"
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
		            <button type="button" id = "btAdicionarLivro">Adicionar Livro</button>
	            </td>
	        </tr>
            <tr>
                <td colspan="2" align="center">
                	<c:if test="${livros != null}">
                		<c:if test="${funcionarios != null}">
                			<c:if test="${venda == null}">
                    			<input type="submit" id="operacao" name="operacao" value="insert" />
                   			</c:if>
                   			<c:if test="${venda != null}">
                    			<input type="submit" id="operacao" name="operacao" value="update" />
                   			</c:if>
                    	</c:if>
                    </c:if>
                    <c:if test="${livros == null}">
                    	<h>Não há livro cadastrado no sistema. Favor cadastrar e tentar novamente.</h>
                    </c:if>
                    <c:if test="${funcionarios == null}">
                    	<h>Não há funcionario cadastrado no sistema. Favor cadastrar e tentar novamente.</h>
                    </c:if>
                </td>
            </tr>
        </table>
        </form>
    </div>  
    <!-- type="text/javascript" src = "../Javascript/FormVendasListaLivros.js" -->> 
    <script >

    document.getElementById("btAdicionarLivro").addEventListener("click", function (e){
    	
    	    e.preventDefault();
    	    
    	    var livroSelect = document.getElementById("livrosSelect");
    	    var livroNome = livroSelect.options[livroSelect.selectedIndex].innerHTML;
    	    var livroId = livroSelect.value;
    	    var num =  document.getElementById("quantia").value;
    	    
    	    var table = document.getElementById("livrosTabela");
    	    
    	    var row = table.insertRow(-1);
    	    
    	    var cell1 = row.insertCell(0);
    	    var cell2 = row.insertCell(1);
    	    var cell3 = row.insertCell(2);
    	    
    	    var inputLivro = document.createElement("input");
    	    inputLivro.type = "text";
    	    inputLivro.value = livroNome;
    	    inputLivro.disabled = true;
    	    
    	    var inputQuantia = document.createElement("input");
    	    inputQuantia.type = "number";
    	    inputQuantia.name = "quantia";
    	    inputQuantia.value = num;
    	    
    	    var inputId = document.createElement("input");
    	    inputId.type = "hidden";
    	    inputId.name = "livro";
    	    inputId.value = livroId;

    		var indexSelect = document.createElement("input");
    	    indexSelect.type = "hidden";
    	    indexSelect.value = livroSelect.selectedIndex;

    		var buttonDelete = document.createElement("button");
    		buttonDelete.innerHTML = "Deletar";
    		buttonDelete.addEventListener("click", function (x){
    	    	x.preventDefault();

    			var cell = x.target.parentElement;
    			var rowIndex = cell.parentElement.rowIndex;
    			var indexSelect = cell.children[1].value;
    			
    			var table = document.getElementById("livrosTabela");
    			
    			var livroSelect = document.getElementById("livrosSelect");
    			var op = livroSelect.options[indexSelect];
    			
    			op.disabled = false;
    			table.deleteRow(rowIndex);
    			
    		});
    	    
    	    cell1.appendChild(inputLivro);
    	    cell2.appendChild(inputQuantia);
    	    cell3.appendChild(inputId);
    		cell3.appendChild(indexSelect);
    		cell3.appendChild(buttonDelete);
    		
    		livroSelect.options[livroSelect.selectedIndex].disabled = true;
    		livroSelect.selectedIndex = -1;
    	}
    );



    
    </script>
</body>



</html>