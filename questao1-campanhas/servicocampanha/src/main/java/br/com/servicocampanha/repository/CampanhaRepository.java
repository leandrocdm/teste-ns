package br.com.servicocampanha.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.servicocampanha.model.Campanha;

@Repository
public interface CampanhaRepository extends CrudRepository<Campanha, Long>{

		List<Campanha> findByIdTimeCoracao(Long codigoTime);

}
