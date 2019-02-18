package br.com.teste.ns.test;
import org.junit.Assert;
import org.junit.Test;

import br.com.teste.ns.main.Main;

public class Teste {
	@Test
	public void teste() {
		testar("aAbBABacafe", 'e');
		testar("zKpgSIISat", 'a');
	}
	
	public void testar(String string, char expected) {
		char resultado = Main.acharCaracter(string);
		Assert.assertEquals(expected, resultado);
	}
}
