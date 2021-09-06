package com.sicredi.assembleia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sicredi.assembleia.entidades.Pauta;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, Long> {

	public Pauta findById(String idPauta);

}
