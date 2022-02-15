/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Autor;
import modelo.Libro;

/**
 * @author Jose
 * clase que implemente un CRUD de la base de datos
 * create
 * read
 * update
 * delete
 */
public class LibroDAO {
	
	private static ConexionBD conexion;
	
	public LibroDAO() {
		this.conexion = new ConexionBD();
	}
	
	public static ArrayList<Libro> mostrarLibros() {
			
			//CONEXION CON LA BBDD
			Connection con = conexion.getConexion();
			
			Statement consulta = null;
			ResultSet resultado = null;
			
			ArrayList<Libro> lista = new ArrayList<Libro>();
			
			try {
				consulta = con.createStatement();
				resultado = consulta.executeQuery("select * from libros");
				
				//recorre todas las final que devuelve la consulta
				while(resultado.next()) {
					String isbn = resultado.getString("isbn");
					String titulo = resultado.getString("título");
					int  codEditorial = resultado.getInt("codEditorial");
					int año = resultado.getInt("año");
					int numPags = resultado.getInt("num_pags");
					double precio = resultado.getDouble("precio");
					int cantidad = resultado.getInt("cantidad");
					double precioCD = resultado.getDouble("precioCD");
					
					Libro l = new Libro(isbn, titulo, codEditorial, año, numPags, precio, cantidad, precioCD);
					lista.add(l);
				}
				
			} catch (SQLException e) {
				System.out.println("Error al realizar la consulta " + e.getMessage());
			} finally {
				try {
					resultado.close();
					consulta.close();
					conexion.desconectar();
				} catch (SQLException e) {
					System.out.println("Error al liberar recursos " + e.getMessage());
				}
			}
			return lista;
		}

	public Libro mostrarLibros(String isbn) {
		
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
		
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		
		Libro l = null;
		
		try {
			consulta = con.prepareStatement("select * from libros" + " where isbn = ?");
			consulta.setString(1, isbn);
			resultado = consulta.executeQuery();
			
			if(resultado.next()) {
	
				String titulo = resultado.getString("título");
				int  codEditorial = resultado.getInt("codEditorial");
				int año = resultado.getInt("año");
				int numPags = resultado.getInt("num_pags");
				double precio = resultado.getDouble("precio");
				int cantidad = resultado.getInt("cantidad");
				double precioCD = resultado.getDouble("precioCD");
				
				l = new Libro(isbn, titulo, codEditorial, año, numPags, precio, cantidad, precioCD);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta " + e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos " + e.getMessage());
			}
		}
		return l;
	}
	
	public static ArrayList<Libro> mostrarLibros(Autor a) {
		
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
		
		Statement consulta = null;
		ResultSet resultado = null;
		
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select l.* FROM libros l INNER JOIN autorlibro al ON l.isbn = al.isbn WHERE idAutor = ?");
			
			//recorre todas las final que devuelve la consulta
			while(resultado.next()) {
				
				String isbn = resultado.getString("isbn");
				String titulo = resultado.getString("título");
				int  codEditorial = resultado.getInt("codEditorial");
				int año = resultado.getInt("año");
				int numPags = resultado.getInt("num_pags");
				double precio = resultado.getDouble("precio");
				int cantidad = resultado.getInt("cantidad");
				double precioCD = resultado.getDouble("precioCD");
				
				Libro l = new Libro(isbn, titulo, codEditorial, año, numPags, precio, cantidad, precioCD);
				lista.add(l);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta " + e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos " + e.getMessage());
			}
		}
		return lista;
	}

	public static int añadirLibro(Libro l) {
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
		
		PreparedStatement consulta = null;
		int resultado = 0;
				
		try {
			consulta = con.prepareStatement("INSERT INTO libros (isbn, título, codEditorial, año, num_pags, precio, cantidad, precioCD)" 
											+ " VALUES (?,?,?,?,?,?,?,?) ");
			//pasamos los parametros
			consulta.setString(1, l.getIsbn());
			consulta.setString(2, l.getTitulo());
			consulta.setInt(3, l.getCodEditorial());
			consulta.setInt(4, l.getAño());
			consulta.setInt(5, l.getNumPags());
			consulta.setDouble(6, l.getPrecio());
			consulta.setInt(7, l.getCantidad());
			consulta.setDouble(8, l.getPrecioCD());
					
			resultado = consulta.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta " + e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos " + e.getMessage());
			}
		}
		return resultado;
	}

	public static int actualizarLibro(Libro l) {
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
				
		PreparedStatement consulta = null;
		int resultado = 0;
				
		try {
			consulta = con.prepareStatement("UPDATE libros " + "SET " + "título = ?, codEditorial = ?, año = ?, num_pags = ?,"
					+ "precio = ?, cantidad = ?, precioCD = ?" + " WHERE isbn = ?");
			//pasamos los parametros
			consulta.setString(1, l.getTitulo());
			consulta.setInt(2, l.getCodEditorial());
			consulta.setInt(3, l.getAño());
			consulta.setInt(4, l.getNumPags());
			consulta.setDouble(5, l.getPrecio());
			consulta.setInt(6, l.getCantidad());
			consulta.setDouble(7, l.getPrecioCD());
			consulta.setString(8, l.getIsbn());
					
			resultado = consulta.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("Error al actualizar " + e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos " + e.getMessage());
			}
		}
		return resultado;
	}
	
	public static int borrarLibro(Libro l) {
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
				
		PreparedStatement consulta = null;
		int resultado = 0;
				
		try {
			consulta = con.prepareStatement("DELETE FROM libros"+ " WHERE isbn = ?");
			//pasamos los parametros
			consulta.setString(1, l.getIsbn());
					
			resultado = consulta.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al borrar " + e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos " + e.getMessage());
			}
		}
		return resultado;
	}

}
