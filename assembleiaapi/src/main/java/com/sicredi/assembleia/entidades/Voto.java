package com.sicredi.assembleia.entidades;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

@AllArgsConstructor
@Document  
public class Voto {
	
	@Transient 
	public  static  final  String  VOTO_SEQ  =  "users_sequence" ;
	
	@Id
	private long id;
	@NonNull
	private String idPauta;
	@NonNull
	private int votoCooperado;
	@NonNull
	private String cpf;
	@NonNull
	private String uf;
	@NonNull
	private Date dataVoto;

	public Voto() {
	}

	public Voto(String idPauta, String cpf) {
		this.idPauta = idPauta;
		this.cpf	 = cpf;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(String idPauta) {
		this.idPauta = idPauta;
	}

	public int getVotoCooperado() {
		return votoCooperado;
	}

	public void setVotoCooperado(int votoCooperado) {
		this.votoCooperado = votoCooperado;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Date getDataVoto() {
		return dataVoto;
	}

	public void setDataVoto(Date dataVoto) {
		this.dataVoto = dataVoto;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", idPauta=" + idPauta + ", votoCooperado=" + votoCooperado + ", cpf=" + cpf + ", uf="
				+ uf + ", dataVoto=" + dataVoto + "]";
	}
	
	
}
