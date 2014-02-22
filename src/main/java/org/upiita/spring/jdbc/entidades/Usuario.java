package org.upiita.spring.jdbc.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "usuario_id")
	private Integer usuarioId;

	@Column(name = "password")
	private String password;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		StringBuilder builder;

		builder = new StringBuilder();
		builder.append("[");
		builder.append("usuarioId:").append(this.usuarioId).append(", ");
		builder.append("email:").append(this.email).append(", ");
		builder.append("password:").append(this.password).append(", ");
		builder.append("nombre:").append(this.nombre);

		builder.append("]");

		return builder.toString();
	}

}
