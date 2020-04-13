package com.example.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="clientes") //Se modifica el nombre de la clase para que no se llame igual en BD
public class Cliente implements Serializable{
	
	/*Cuado se guardan objetos en la peticion http es recomendable utilizar serializable*/
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_cliente") //Se puede personalizar el nombre de la identidad en la BD
	@NotBlank
	@Size(min=4, max=12)
	private String nombre;
	@NotBlank
	private String apellido;
	@NotBlank
	@Email
	private String email;
	
	/*Se agrega temporal para indicar el formato de fecha con el cual se van a guardar los datos en la 
	 * BD. Hora y fecha, solo fecha, etc*/
	@Temporal(TemporalType.DATE) 
	/*Se tiene que usar este patron para que corresponda al mismo en BD*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name ="create_at")
	@NotNull
	@Past
	private Date createAt;
	
	/*
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
