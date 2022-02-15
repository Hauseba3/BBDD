/**
 * 
 */
package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Autor;
import modelo.Editorial;
import modelo.Libro;


/**
 * @author Jose
 *
 */
public class FuncionesBD {
	
	public static ArrayList<Editorial> mostrarEditoriales() {
		
		//CONEXION CON LA BBDD
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.getConexion();
		
		Statement consulta = null;
		ResultSet resultado = null;
		
		ArrayList<Editorial> lista = new ArrayList<Editorial>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from editoriales");
			
			//recorre todas las final que devuelve la consulta
			while(resultado.next()) {
				int  codEditorial = resultado.getInt("codEditorial");
				String nombre = resultado.getString("nombre");
				int año = resultado.getInt("año");
				
				Editorial ed = new Editorial(codEditorial, nombre , año);
				lista.add(ed);
				
				//System.out.printf("CodEditorial: %d Nombre: %s Año: %d\n", codEditorial, nombre, año);
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
	
	public static ArrayList<Libro> mostrarLibros() {
		
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from libros");
			
			while(resultado.next()) {
				String isbn = resultado.getString("isbn");
				String titulo = resultado.getString("título");
				int codEditorial = resultado.getShort("codEditorial");
				int año = resultado.getInt("año");
				int numPags = resultado.getInt("num_pags");
				double precio = resultado.getDouble("precio");
				int cantidad = resultado.getInt("cantidad");
				double precioCD = resultado.getDouble("precioCD");
				
				Libro lib = new Libro(isbn, titulo, codEditorial, año, numPags, precio, cantidad, precioCD);
				listaLibros.add(lib);
				
				/*System.out.printf("ISBN: %s Título: %s CodEditorial: %d Año: %d NumPags: %d Precio: %.2f Cantidad: %d PrecioCD: %.2f\n", 
						isbn, titulo, codEditorial, año, numPags, precio, cantidad, precioCD);*/
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
		return listaLibros;
		
	}
	
	public static ArrayList<Autor> mostrarAutores() {
		//CONEXION CON LA BBDD
				ConexionBD conexion = new ConexionBD();
				Connection con = conexion.getConexion();
				
				Statement consulta = null;
				ResultSet resultado = null;
				
				ArrayList<Autor> lista = new ArrayList<Autor>();
				
				try {
					consulta = con.createStatement();
					resultado = consulta.executeQuery("select * from autores");
					
					//recorre todas las final que devuelve la consulta
					while(resultado.next()) {
						int  idAutor = resultado.getInt("idAutor");
						String nombre = resultado.getString("nombre");
						String apellidos = resultado.getString("apellidos");
						
						Autor au = new Autor(idAutor, nombre , apellidos);
						lista.add(au);
						
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
	
	public static int añadirAutor(Autor a) {
		//CONEXION CON LA BBDD
				ConexionBD conexion = new ConexionBD();
				Connection con = conexion.getConexion();
				
				PreparedStatement consulta = null;
				int resultado = 0;
				
				try {
					consulta = con.prepareStatement("INSERT INTO autores (nombre, apellidos)" + " VALUES (?,?) ");
					//pasamos los parametros
					consulta.setString(1, a.getNombre());
					consulta.setString(2, a.getApellidos());
					
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
	
	public static int añadirEditorial(Editorial ed) {
		//CONEXION CON LA BBDD
				ConexionBD conexion = new ConexionBD();
				Connection con = conexion.getConexion();
				
				PreparedStatement consulta = null;
				int resultado = 0;
				
				try {
					consulta = con.prepareStatement("INSERT INTO editoriales (nombre, año)" + " VALUES (?,?) ");
					//pasamos los parametros
					consulta.setString(1, ed.getNombre());
					consulta.setInt(2, ed.getAño());
					
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
	
	public static int borrarLibro(String isbn) {
		//CONEXION CON LA BBDD
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.getConexion();
		
		PreparedStatement consulta = null;
		int resultado = 0;
		
		try {
			consulta = con.prepareStatement("DELETE FROM libros " + " WHERE isbn = ?");
			//pasamos los parametros
			consulta.setString(1, isbn);
			
			resultado = consulta.executeUpdate();

			
		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado " + e.getMessage());
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
	
	public static void librosPrecio(double valor) {
		
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from libros where precio > " + valor);
			
			while(resultado.next()) {
				String isbn = resultado.getString("isbn");
				String titulo = resultado.getString("título");
				int codEditorial = resultado.getShort("codEditorial");
				int año = resultado.getInt("año");
				int numPags = resultado.getInt("num_pags");
				double precio = resultado.getDouble("precio");
				int cantidad = resultado.getInt("cantidad");
				double precioCD = resultado.getDouble("precioCD");
				
				System.out.printf("ISBN: %s Título: %s CodEditorial: %d Año: %d NumPags: %d Precio: %.2f Cantidad: %d PrecioCD: %.2f\n", 
						isbn, titulo, codEditorial, año, numPags, precio, cantidad, precioCD);
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
		
		
	}

}
