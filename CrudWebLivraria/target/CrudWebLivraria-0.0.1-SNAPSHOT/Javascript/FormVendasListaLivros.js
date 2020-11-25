function atualizarPreco(table) {
	var soma = 0;
	
	for(var i = 0; i < table.rows.lenght; i++){
		soma = soma + row.children[1].firstChild.value;
	}
	
	document.getElementById("valorTotal").value = soma;
}

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
			atualizarPreco(table);
			
		});
	    
	    cell1.appendChild(inputLivro);
	    cell2.appendChild(inputQuantia);
	    cell3.appendChild(inputId);
		cell3.appendChild(indexSelect);
		cell3.appendChild(buttonDelete);
		
		livroSelect.options[livroSelect.selectedIndex].disabled = true;
		livroSelect.selectedIndex = -1;
		atualizarPreco(table);
	}
);


