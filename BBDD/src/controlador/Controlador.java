/**
 * 
 */
package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexion.FuncionesBD;
import dao.AutorDAO;
import dao.AutorLibroDAO;
import dao.EditorialDAO;
import dao.LibroDAO;
import modelo.Autor;
import modelo.Editorial;
import modelo.Libro;
import pruebas.VentanaActualizarEditorial;
import pruebas.VentanaAutores;
import pruebas.VentanaAñadirAutores;
import pruebas.VentanaAñadirEditoriales;
import pruebas.VentanaEditoriales;
import pruebas.VentanaInsertarLibro;
import pruebas.VentanaLibros;
import pruebas.VentanaPrincipal;

/**
 * @author Jose
 *
 */
public class Controlador {
	//Ventanas del sistema
	private VentanaPrincipal ventanaPrincipal;
	private VentanaEditoriales ventanaEditorial;
	private VentanaLibros ventanaLibro;
	private VentanaAutores ventanaAutor;
	private VentanaAñadirAutores ventanaAñadirAutor;
	private VentanaAñadirEditoriales ventanaAñadirEditorial;
	private VentanaActualizarEditorial ventanaActualizarEditorial;
	private VentanaInsertarLibro ventanaInsertarLibro;
	
	//objetos DAO o CRUD de la BBDD
	private EditorialDAO editorialDAO;
	private AutorDAO autorDAO;
	private AutorLibroDAO autorLibroDAO;
	private LibroDAO libroDAO;
	
	public Controlador () {
		//crea las ventanas de la aplicacion
		ventanaPrincipal = new VentanaPrincipal();
		ventanaEditorial = new VentanaEditoriales();
		ventanaLibro = new VentanaLibros();
		ventanaAutor = new VentanaAutores();
		ventanaAñadirAutor = new VentanaAñadirAutores();
		ventanaAñadirEditorial = new VentanaAñadirEditoriales();
		ventanaActualizarEditorial = new VentanaActualizarEditorial();
		ventanaInsertarLibro = new VentanaInsertarLibro();
		
		//dar acceso al controlador desde las vistas
		ventanaPrincipal.setControlador(this);
		ventanaEditorial.setControlador(this);
		ventanaLibro.setControlador(this);
		ventanaAutor.setControlador(this);
		ventanaAñadirAutor.setControlador(this);
		ventanaAñadirEditorial.setControlador(this);
		ventanaActualizarEditorial.setControlador(this);
		ventanaInsertarLibro.setControlador(this);
		
		//creamos los objetos DAO
		editorialDAO = new EditorialDAO();
		autorDAO = new AutorDAO();
		libroDAO = new LibroDAO();
		autorLibroDAO = new AutorLibroDAO();
		
	}
	
	
	public void iniciarPrograma() {
		ventanaPrincipal.setVisible(true);

	}
	//FUNCIONES INSERTAR LIBRO
	public void mostrarVentanaInsertarLibro() {
		// CONSULTAR LAS EDITORIALES
		ArrayList<Editorial> listaEditoriales = editorialDAO.mostrarEditoriales();
		//CONSULTAR LOS AUTORES
		ArrayList<Autor> listaAutores = autorDAO.mostrarAutores();
		//PASAR LA LISTA DE EDITORIALES
		ventanaInsertarLibro.setEditoriales(listaEditoriales);
		//PASAR LA LISTA DE AUTORES
		ventanaInsertarLibro.setAutores(listaAutores);
		ventanaInsertarLibro.setVisible(true);
	}
	
	public void insertarLibro(Libro l, ArrayList<Autor> listaAutoresSeleccionados) {
		//INSERTAMOS EL LIBRO
		libroDAO.añadirLibro(l);
		
		for (Autor autor : listaAutoresSeleccionados) {
			autorLibroDAO.añadirAutorLibro(autor.getIdautor(), l.getIsbn());
		}
		
		ventanaInsertarLibro.setVisible(false);
	}
	
	//FUNCIONES DE EDITORIAL
	public void mostrarVentanaEditorial () {
		ventanaAñadirEditorial.setVisible(true);
	}
	
	public void mostrarEditoriales() {
		ArrayList<Editorial> listaEditoriales = EditorialDAO.mostrarEditoriales();
		ventanaEditorial.setListaEditoriales(listaEditoriales);
		ventanaEditorial.setVisible(true);
	}
	
	public void insertarEditorial (Editorial ed) {
		int resultado = EditorialDAO.añadirEditorial(ed);
		if(resultado==0) {
			JOptionPane.showMessageDialog(null, "Error no se ha podido insertar!");
		} else {
			JOptionPane.showMessageDialog(null, "Editorial insertada correctamente!");
			ventanaAñadirEditorial.setVisible(false);
		}
	}
	
	public void actualizarEditoriales(Editorial ed) {
		int resultado = EditorialDAO.actualizarEditorial(ed);
		if(resultado==0) {
			JOptionPane.showMessageDialog(null, "Error no se ha podido actualizar!");
		} else {
			JOptionPane.showMessageDialog(null, "Editorial actualizada correctamente!");
			ventanaEditorial.setVisible(false);
		}
	}
	
	public void borrarEditoriales(Editorial ed) {
		int resultado = EditorialDAO.borrarEditorial(ed);
		if(resultado==0) {
			JOptionPane.showMessageDialog(null, "Error no se ha podido borrar!");
		} else {
			JOptionPane.showMessageDialog(null, "Editorial borrada correctamente!");
			ventanaEditorial.setVisible(false);
		}
	}
	
	public void mostrarActualizarEditorial(int codEditorial) {
		
		Editorial ed = EditorialDAO.mostrarEditoriales(codEditorial);
		ventanaActualizarEditorial.setEditorial(ed);
		ventanaActualizarEditorial.setVisible(true);
		
	}
	
	//FUNCIONES DE LIBROS
	public void mostrarLibros() {
		ArrayList<Libro> listaLibros = FuncionesBD.mostrarLibros();
		ventanaLibro.setListaLibros(listaLibros);
		ventanaLibro.setVisible(true);
	}
	
	public int borrarLibro(String isbn) {
		 return FuncionesBD.borrarLibro(isbn);
	}
	
	//FUNCIONES DE AUTOR
	public void mostrarAutores() {
		ArrayList<Autor> listaAutores = FuncionesBD.mostrarAutores();
		ventanaAutor.setListaAutores(listaAutores);
		ventanaAutor.setVisible(true);
	}
	
	public void insertarAutor(Autor a) {
		int resultado = FuncionesBD.añadirAutor(a);
		if(resultado==0) {
			JOptionPane.showMessageDialog(ventanaAñadirAutor, "Error no se ha podido insertar!");
		} else {
			JOptionPane.showMessageDialog(ventanaAñadirAutor, "Autor insertado correctamente!");
			ventanaAñadirAutor.setVisible(false);
		}
	}
	
	public void mostrarVentanaAutor() {
		ventanaAñadirAutor.setVisible(true);
	}

	
	
	

}
