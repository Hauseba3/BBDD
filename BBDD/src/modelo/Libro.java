/**
 * 
 */
package modelo;

import java.util.Objects;

/**
 * @author Jose
 *
 */
public class Libro {
	
	private String isbn;
	private String titulo;
	private int codEditorial;
	private int año;
	private int numPags;
	private double precio;
	private int cantidad;
	private double precioCD;
	
	public Libro() {
		this.isbn ="";
		this.titulo ="";
	}
	
	public Libro(String isbn, String titulo, int codEditorial, int año, int numPags, double precio, int cantidad,
			double precioCD) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.codEditorial = codEditorial;
		this.año = año;
		this.numPags = numPags;
		this.precio = precio;
		this.cantidad = cantidad;
		this.precioCD = precioCD;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getCodEditorial() {
		return codEditorial;
	}
	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public int getNumPags() {
		return numPags;
	}
	public void setNumPags(int numPags) {
		this.numPags = numPags;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioCD() {
		return precioCD;
	}
	public void setPrecioCD(double precioCD) {
		this.precioCD = precioCD;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", codEditorial=" + codEditorial + ", año=" + año
				+ ", numPags=" + numPags + ", precio=" + precio + ", cantidad=" + cantidad + ", precioCD=" + precioCD
				+ "]";
	}
	
	

}
