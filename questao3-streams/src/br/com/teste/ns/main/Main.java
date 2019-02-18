package br.com.teste.ns.main;
import br.com.teste.ns.utils.Core;

public class Main{

	public static void main(String[] args) {
		String str = "aAbBABacafe";
		try {
			char resultado = acharCaracter(str);
			if (resultado == '0')
				throw new Exception("Erro!");
			System.out.println("Resultado: " + resultado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public static char acharCaracter(String str) {
		Core stream = new Core(str);
		char [] primeiras = new char[str.length()];
		int contador = 0;
		while (stream.hasNext()) {
			if (stream.isVogal(stream.charAt(contador), true))
				if (contador > 0 && !stream.isVogal(stream.charAt(contador - 1), false))
					if (contador > 1 && stream.isVogal(stream.charAt(contador - 2), false))
						primeiras[contador] = stream.charAt(contador);
			stream.getNext();
			contador++;
		}
		return stream.procuraPrimeiraVogal(primeiras);
	}
}
