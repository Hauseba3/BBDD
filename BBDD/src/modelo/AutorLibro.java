/**
 * 
 */
package modelo;

import java.util.Objects;

/**
 * @author Jose
 *
 */
public class AutorLibro {
	
	private Libro libro;
	private Autor autor;
	
	public AutorLibro(Libro libro) {
		this.libro = libro;
	}

	public AutorLibro(Autor autor) {
		this.autor = autor;
	}

	public AutorLibro(Libro libro, Autor autor) {
		this.libro = libro;
		this.autor = autor;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, libro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutorLibro other = (AutorLibro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(libro, other.libro);
	}

	@Override
	public String toString() {
		return "AutorLibro [libro=" + libro + ", autor=" + autor + "]";
	}
	
	
	
	
	
}
