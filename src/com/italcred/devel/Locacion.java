package com.italcred.devel;

public class Locacion {

	private String departamento;
	private String localidad;
	private String calle;
	
	public Locacion(String departamento, String localidad, String calle){
		this.departamento = departamento;
		this.localidad = localidad;
		this.calle = calle;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getCalle() {
		return calle;
	}

}
