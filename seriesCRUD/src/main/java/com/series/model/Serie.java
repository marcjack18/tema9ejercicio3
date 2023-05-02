package com.series.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="serie")
public class Serie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idserie")
	int id;
	
	@Column(name="nombre")
	String nombre;
	
	@Column(name="temporadas")
	int temporadas;
	
	@Column(name="capitulos")
	int capitulos;
	
	public Serie() {
		super();
	}

	public Serie(String nombre, int temporadas, int capitulos) {
		super();
		this.nombre = nombre;
		this.temporadas = temporadas;
		this.capitulos = capitulos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}

	public int getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}
	
	

}
