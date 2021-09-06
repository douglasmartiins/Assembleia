package com.sicredi.assembleia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sicredi.assembleia.entidades.Pauta;
import com.sicredi.assembleia.entidades.Voto;
import com.sicredi.assembleia.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaservice;

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<Pauta> salvarPauta(@RequestBody Pauta pauta) {
		pauta = pautaservice.salvarPauta(pauta);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pauta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<?> buscar() {
		List<Pauta> lista = pautaservice.buscar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}	
	
	@RequestMapping(value= "/votar",method = RequestMethod.POST)
	private String salvarVoto(@RequestBody Voto voto){
		return	pautaservice.salvarVoto(voto);
		  
	}
	
	@RequestMapping(value = "/validarCpf", method = RequestMethod.GET)
	private Boolean validarCpf(@RequestBody Voto voto) {
		return pautaservice.validarCpf(voto.getCpf());
	}
	
	@RequestMapping(value = "/resultadoPauta", method = RequestMethod.GET)
	private String resultadoPauta(@RequestBody Voto voto) {
		return pautaservice.resultadoPauta(voto.getIdPauta());
	}
	
}
