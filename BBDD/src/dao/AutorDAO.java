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
import modelo.Editorial;
import modelo.Libro;

/**
 * @author Jose
 * clase que implemente un CRUD de la base de datos
 * create
 * read
 * update
 * delete
 */
public class AutorDAO {
	
	private static ConexionBD conexion;
	
	public AutorDAO() {
		this.conexion = new ConexionBD();
	}
	
	public static ArrayList<Autor> mostrarAutores() {
			
			//CONEXION CON LA BBDD
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
					
					Autor a = new Autor(idAutor, nombre , apellidos);
					lista.add(a);
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

	public Autor mostrarAutores(int idAutor) {
		
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
		
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		
		Autor a = null;
		
		try {
			consulta = con.prepareStatement("select * from autores" + " where idAutor = ?");
			consulta.setInt(1, idAutor);
			resultado = consulta.executeQuery();
			
			if(resultado.next()) {
	
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				
				a = new Autor(idAutor, nombre , apellidos);
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
		return a;
	}
	
	public static ArrayList<Autor> mostrarAutores(Libro l) {
		
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
		
		Statement consulta = null;
		ResultSet resultado = null;
		
		ArrayList<Autor> lista = new ArrayList<Autor>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select a.idAutor, nombre, apellidos from autores a INNER JOIN autorlibro al ON a.idAutor = al.idAutor" +
			" where isbn = ?");
			
			//recorre todas las final que devuelve la consulta
			while(resultado.next()) {
				int  idAutor = resultado.getInt("idAutor");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				
				Autor a = new Autor(idAutor, nombre, apellidos);
				lista.add(a);
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

	public static int actualizarAutor(Autor a) {
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
				
		PreparedStatement consulta = null;
		int resultado = 0;
				
		try {
			consulta = con.prepareStatement("UPDATE autores " + "SET " + "nombre = ?, apellidos = ?" + " WHERE idAutor = ?");
			//pasamos los parametros
			consulta.setString(1, a.getNombre());
			consulta.setString(2, a.getApellidos());
			consulta.setInt(3, a.getIdautor());
					
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
	
	public static int borrarAutor(Autor a) {
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
				
		PreparedStatement consulta = null;
		int resultado = 0;
				
		try {
			consulta = con.prepareStatement("DELETE FROM autores"+ " WHERE idAutor = ?");
			//pasamos los parametros
			consulta.setInt(1, a.getIdautor());
					
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
