package crudweblivraria.negocio;

import crudweblivraria.interfaces.IValidador;
import crudweblivraria.model.domain.EntidadeDominio;
import crudweblivraria.model.domain.Livro;

public class ValidadorIsbn implements IValidador {

	public String validarCampo(EntidadeDominio ent) {
		
		Livro l = (Livro) ent;
		String campo = l.getIsbn();
		
		// length must be 10 
        int n = campo.length(); 
        if (n != 10) 
            return "ISBN deve ter comprimento 10.\n"; 
  
        // Computing weighted sum 
        // of first 9 digits 
        int sum = 0; 
        for (int i = 0; i < 9; i++)  
        { 
            int digit = campo.charAt(i) - '0'; 
            if (0 > digit || 9 < digit) 
                return "Não é um ISBN válido."; 
            sum += (digit * (10 - i)); 
        } 
  
        // Checking last digit. 
        char last = campo.charAt(9); 
        if (last != 'X' && (last < '0' ||  
                            last > '9')) 
            return "Digito de verificação inválido.\n"; 
  
        // If last digit is 'X', add 10  
        // to sum, else add its value 
        sum += ((last == 'X') ? 10 : (last - '0')); 
  
        // Return true if weighted sum  
        // of digits is divisible by 11. 
        if(sum % 11 == 0) return "";
        else return "Não é um ISBN válido.";
	}

}
