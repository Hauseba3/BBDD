/**
 * 
 */
package modelo;

import java.util.Objects;

/**
 * @author Jose
 *
 */
public class Autor {
	
	private int idautor;
	private String nombre;
	private String apellidos;
	
	public Autor() {
		this.nombre = "";
		this.apellidos = "";
	}
	
	public Autor(int idautor, String nombre, String apellidos) {
		super();
		this.idautor = idautor;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	public int getIdautor() {
		return idautor;
	}
	public void setIdautor(int idautor) {
		this.idautor = idautor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idautor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return idautor == other.idautor;
	}

	@Override
	public String toString() {
		return this.apellidos + ", " + this.nombre;
	}
	
	

}
