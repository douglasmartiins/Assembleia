package com.sicredi.assembleia.entidades;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;



@Document(collection = "pauta")
public class Pauta {
	
	@Transient 
	public  static  final  String  PAUTA_SEQ  =  "users_sequence" ;
	
	@Id
	private long id;
	private long central;
	private long sucursal;
	private String descricaoPauta;
	private Date dataCadastro;
	private Date dataExpiracao;
	
	
	
	public Pauta() {
	}
	public Pauta(String descricaoPauta) {
		this.descricaoPauta = descricaoPauta;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCentral() {
		return central;
	}
	public void setCentral(long central) {
		this.central = central;
	}
	public long getSucursal() {
		return sucursal;
	}
	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}
	public String getDescricaoPauta() {
		return descricaoPauta;
	}
	public void setDescricaoPauta(String descricaoPauta) {
		this.descricaoPauta = descricaoPauta;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataExpiracao() {
		return dataExpiracao;
	}
	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
}
