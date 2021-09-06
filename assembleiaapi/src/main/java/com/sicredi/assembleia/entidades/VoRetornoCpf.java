package com.sicredi.assembleia.entidades;

import java.io.Serializable;

public class VoRetornoCpf implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
