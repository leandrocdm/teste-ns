package br.com.servicocampanha.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.servicocampanha.model.Campanha;
import br.com.servicocampanha.repository.CampanhaRepository;


@Service
public class CampanhaService {
	
	@Autowired
	private CampanhaRepository campanhaRepository;

	@Transactional
	public Campanha save(final Campanha campanha){
		List<Campanha> listaCampanhas = this.pesquisarVigentes();
		List<Campanha> listaAtualizada = this.corrigirData(listaCampanhas, campanha);
		listaAtualizada.add(campanha);
		for (Campanha c : listaAtualizada) {
			this.campanhaRepository.save(c);
		}
		return campanha;
	}

	@Transactional
	public void delete(final Long id) {
		this.campanhaRepository.delete(id);
	}

	public Campanha findById(final Long id) {
		return campanhaRepository.findOne(id);
	}

	public List<Campanha> findAll(){
		return (List<Campanha>) this.campanhaRepository.findAll();
	}

	public List<Campanha> pesquisarVigentes(){
		List<Campanha> listaTotal = (List<Campanha>) this.campanhaRepository.findAll();
		List<Campanha> listaVigentes = new ArrayList<Campanha>();
		
		for (Campanha campanha : listaTotal) {
			if (this.campanhasVigente(campanha)) {
				listaVigentes.add(campanha);
			}
		}
		return listaVigentes;
	}
	
	public List<Campanha> pesquisarVigentesPorTime(Long codigoTime) {
		return this.campanhaRepository.findByIdTimeCoracao(codigoTime).stream()
				.filter(this::campanhasVigente).collect(Collectors.toList());
	}
	
	public boolean campanhasVigente(Campanha campanha){
		Calendar dataAtual = Calendar.getInstance();
		if (campanha.getDataInicio().before(dataAtual) && campanha.getDataFim().after(dataAtual)) {
			return true;
		} else{
			return false;
		}
	}
	
	private List<Campanha> corrigirData(List<Campanha> listaCampanha, Campanha campanha) {
		List<Campanha> lista = listaCampanha;
		List<String> listaDatas = new ArrayList<String>();
		List<Campanha> listaAtualizada = new ArrayList<Campanha>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dataCampanha = sdf.format(campanha.getDataFim().getTime());
		if (!lista.isEmpty()) {
			for (Campanha c : lista) {
				listaDatas.add(sdf.format(c.getDataFim().getTime()));
			}

			for (Campanha c : lista) {
				listaDatas.remove(sdf.format(c.getDataFim().getTime()));
				Calendar data = c.getDataFim();
				data.add(Calendar.DATE, +1);
				c.setDataFim(data);

				listaDatas.add(sdf.format(c.getDataFim().getTime()));
				listaAtualizada.add(c);
			}
			if (listaDatas.contains(dataCampanha)) {
				corrigirData(listaAtualizada, campanha);
			}
		}
		return lista;
	}
}
