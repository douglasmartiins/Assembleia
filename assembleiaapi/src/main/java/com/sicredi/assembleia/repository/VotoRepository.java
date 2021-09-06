package com.sicredi.assembleia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sicredi.assembleia.entidades.Voto;

@RequestMapping
public interface VotoRepository extends MongoRepository<Voto, String> {
@Query("{'idPauta' : :#{#idPauta}, 'cpf' : :#{#cpf} }")
 List <Voto> get(@Param("idPauta") String idPauta, @Param("cpf") String cpf);

//List<Voto> findAll();

}
