package br.com.servicocampanha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientResponseException;

import br.com.servicocampanha.model.Campanha;
import br.com.servicocampanha.service.CampanhaService;

@Controller
public class CampanhaController {
	
	@Autowired
	private CampanhaService campanhaService;	
	
	@RequestMapping(value = "/campanha", method = RequestMethod.GET)
	public ResponseEntity<?> listarCampanhasValidas(HttpServletRequest request) {
		try {
			return new ResponseEntity<List<Campanha>>(new ArrayList<Campanha>(campanhaService.findAll()), HttpStatus.OK);
		} catch (Exception e) {
			throw new RestClientResponseException(e.getMessage(), 500, null, null, new byte[0], null);
		}
	}
	
	@PostMapping(value = "/campanha/inserir", consumes = "application/json")
	public ResponseEntity<?> criarCampanha(@RequestBody Campanha campanha, HttpServletRequest request) {
		try {
			campanhaService.save(campanha);
			return new ResponseEntity<>("Campanha inserida com sucesso.", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/campanha/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id, HttpServletRequest request) {
		try{
			return new ResponseEntity<Campanha>(campanhaService.findById(id), HttpStatus.OK);		
		}catch (Exception e) {
			throw new RestClientResponseException(e.getMessage(), 500, null, null, new byte[0], null);
		}
	}

	@RequestMapping(value = "/campanha/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletarCampanha(@PathVariable("id") Long id, HttpServletRequest request) {
		try {
			campanhaService.delete(id);
			return new ResponseEntity<>("Campanha excluída com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		}
	}	
}