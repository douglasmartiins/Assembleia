package com.sicredi.assembleia.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.sicredi.assembleia.controller.GenericController;
import com.sicredi.assembleia.entidades.Pauta;
import com.sicredi.assembleia.entidades.VoRetornoCpf;
import com.sicredi.assembleia.entidades.Voto;
import com.sicredi.assembleia.erros.UrlNaoEncontradaException;
import com.sicredi.assembleia.repository.PautaRepository;
import com.sicredi.assembleia.repository.VotoRepository;


@Service
public class PautaService extends GenericController{

	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private GeradorSequencialID geradorSequencialID;

	Logger logger = Logger.getLogger(PautaService.class.getName());

	
	public Pauta salvarPauta(Pauta pauta) {
		Date data = new Date(System.currentTimeMillis());
		pauta.setId(geradorSequencialID.generateSequence(Pauta.PAUTA_SEQ));
		pauta.setDataCadastro(data);
		return pautaRepository.save(pauta);
	}

	public List<Pauta> buscar() {
		List<Pauta> lista = pautaRepository.findAll();
		if(lista==null)
			throw new UrlNaoEncontradaException(URL_VALIDA_CPF);
		return lista;
	}

	public String salvarVoto(Voto voto) {

			if (validarCpf(voto.getCpf())) {
				if (buscarVotoPorCpf(voto.getIdPauta(), voto.getCpf()))
					return ("Você já votou para essa pauta!");

				voto.setId(geradorSequencialID.generateSequence(Pauta.PAUTA_SEQ));
				votoRepository.save(voto);
			} else {
				return ("Esse Cpf não pode votar");
			}
		// }
		return "Obrigado por votar!";
	}

	public String resultadoPauta(String idPauta) {
		int sim = 0;
		int nao = 0;
		 Pauta validandoPauta = pautaRepository.findById(idPauta);
		 if(validandoPauta !=null) {
			 return "Pauta Inexistente!";
		 }	
		 List<Voto> votos = votoRepository.findAll();
		 for (Voto voto : votos) {
			 if (voto.getVotoCooperado() == 1) { // SIM = 1
				 	sim += 1;
			 	} else { // NÃO
			 		nao+= 1;
			 	}
		}
		return "Total para SIM: " + sim + " Total para NÃO: " + nao;
	}

	public Boolean validarCpf(String cpf) {
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Chamada da Url: " + URL_VALIDA_CPF + "Time: " + dataEhoraFormatada());
		try {	
			 VoRetornoCpf retorno = restTemplate.getForObject(URL_VALIDA_CPF + cpf, VoRetornoCpf.class);
			 if (retorno != null) {
				 if (retorno.getStatus().contains("ABLE_TO_VOTE")){
					 return true;
				 }else if(retorno.getStatus().contains("UNABLE_TO_VOTE")){
					 return false;
				 }
			 }
		}catch (UrlNaoEncontradaException e) {
			
		}
		
		return false;
	}

	public Boolean buscarVotoPorCpf(String idPauta, String cpf) {
		logger.info("Verificando se o CPF " + cpf + " votou");
		List<Voto> retorno = votoRepository.get(idPauta, cpf);
		if (retorno.isEmpty()) {
			return false;
		}

		return true;
	}
	
	public Date dataEhoraFormatada() {
		Date data = new Date(System.currentTimeMillis());
		return data;
	}

}
