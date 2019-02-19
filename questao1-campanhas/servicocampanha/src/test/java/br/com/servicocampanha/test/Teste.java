package br.com.servicocampanha.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;

public class Teste {
	
	private URL url;
	
	@Test
	public void teste() {
		setCampanha("{\"nomeCampanha\":\"Campanha 1\", \"idTimeCoracao\": 1, \"dataInicio\":\"08-05-2017\", \"dataVigencia\":\"08-06-2017\"}");
		setCampanha("{\"nomeCampanha\":\"Campanha 2\", \"idTimeCoracao\": 2, \"dataInicio\":\"08-05-2017\", \"dataVigencia\":\"08-06-2017\"}");
		setCampanha("{\"nomeCampanha\":\"Campanha 3\", \"idTimeCoracao\": 3, \"dataInicio\":\"08-05-2017\", \"dataVigencia\":\"08-06-2017\"}");
		getCampanhas();
	}
	
	public void getCampanhas() {
		try {
			url = new URL("http://localhost:8080/campanha");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(RequestMethod.GET.name());
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) 
				throw new RuntimeException("Failed: HTTP error code: "
						+ conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String output;
			System.out.println("Campanhas:\n");
			while ((output = br.readLine()) != null) 
				System.out.println(output+"\n");
			
			conn.disconnect();	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setCampanha(String json) {
		try {
			url = new URL("http://localhost:8080/campanha/inserir");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(RequestMethod.POST.name());
			conn.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
				throw new RuntimeException("Failed: HTTP error code: "
						+ conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String output;
			System.out.println("Criar/Atualizar Campanha:\n");
			while((output = br.readLine()) != null)
				System.out.println(output+"\n");
			
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
