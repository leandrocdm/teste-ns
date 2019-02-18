package br.com.teste.ns.utils;

public class Core implements Stream {
	
	private char [] entrada;
	private int indice;
	private int [] contador;
	
	public Core(String entrada) {
		this.entrada = entrada.toCharArray();
		this.indice = 0;
		contador = new int[5];
	}
	

	public char procuraPrimeiraVogal(char[] array) {
		for (int i = 0;i < array.length;i++) {
			switch (Character.toLowerCase(array[i])) {
			case 'a': 
				if (contador[0] == 1)
					return array[i];
				break;
			case 'e':
				if (contador[1] == 1)
					return array[i];
				break;
			case 'i':
				if (contador[2] == 1)
					return array[i];
				break;
			case 'o':
				if (contador[3] == 1)
					return array[i];
				break;
			case 'u':
				if (contador[4] == 1)
					return array[i];
				break;
			default: break;
			}
		}
		return '0';
	}
	
	public void getNext() {
		if (indice < (entrada.length))
			indice++;
	}

	public boolean hasNext() {
		return indice < (entrada.length);
	}
	
	
	public char charAt(Integer indice) {
		return entrada[indice];
	}
	
	public char firstChar(Stream input) {
		return entrada[0];
	}
	
	public boolean isVogal(Character c, boolean testecontador) {
		switch (Character.toLowerCase(c)) {
		case 'a': { 
			if (testecontador) contador[0]++;
			return true;
		}
		case 'e': {
			if (testecontador) contador[1]++;
			return true;
		}
		case 'i': {
			if (testecontador) contador[2]++;
			return true;
		}
		case 'o': {
			if (testecontador) contador[3]++;
			return true;
		}
		case 'u': {
			if (testecontador) contador[4]++;
			return true;
		}
		default: return false;
		}
	}
	
}
