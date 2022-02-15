/**
 * 
 */
package pruebas;

import java.util.ArrayList;

import conexion.FuncionesBD;
import modelo.Editorial;
import modelo.Libro;

/**
 * @author Jose
 *
 */
public class PruebaConexion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Editorial> lista = FuncionesBD.mostrarEditoriales();
		ArrayList<Libro> listaLibros = FuncionesBD.mostrarLibros();
		
		System.out.println("Muestra la tabla editoriales por pantalla: ");
		
		for (Editorial editorial : lista) {
			System.out.println(editorial);
		}
		
		System.out.println("Muestra la tabla libros por pantalla: ");
		
		for (Libro libro : listaLibros) {
			System.out.println(libro);
		}
		
		

	}

}
