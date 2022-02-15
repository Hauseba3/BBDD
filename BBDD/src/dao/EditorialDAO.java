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
import modelo.Editorial;

/**
 * @author Jose
 * clase que implemente un CRUD de la base de datos
 * create
 * read
 * update
 * delete
 */
public class EditorialDAO {
	
	private static ConexionBD conexion;
	
	public EditorialDAO() {
		this.conexion = new ConexionBD();
	}
	
	public static ArrayList<Editorial> mostrarEditoriales() {
			
			//CONEXION CON LA BBDD
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

	public static Editorial mostrarEditoriales(int codEditorial) {
		
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
		
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		
		Editorial ed = null;
		
		try {
			consulta = con.prepareStatement("select * from editoriales" + " where codEditorial = ?");
			consulta.setInt(1, codEditorial);
			resultado = consulta.executeQuery();
			
			if(resultado.next()) {
	
				String nombre = resultado.getString("nombre");
				int año = resultado.getInt("año");
				
				ed = new Editorial(codEditorial, nombre , año);

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
		return ed;
	}

	public static int añadirEditorial(Editorial ed) {
		//CONEXION CON LA BBDD
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

	public static int actualizarEditorial(Editorial ed) {
		//CONEXION CON LA BBDD
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.getConexion();
				
		PreparedStatement consulta = null;
		int resultado = 0;
				
		try {
			consulta = con.prepareStatement("UPDATE editoriales " + "SET " + "nombre = ?, año = ?" + " WHERE codEditorial = ?");
			//pasamos los parametros
			consulta.setString(1, ed.getNombre());
			consulta.setInt(2, ed.getAño());
			consulta.setInt(3, ed.getCodEditorial());
					
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
	
	public static int borrarEditorial(Editorial ed) {
		//CONEXION CON LA BBDD
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.getConexion();
				
		PreparedStatement consulta = null;
		int resultado = 0;
				
		try {
			consulta = con.prepareStatement("DELETE FROM editoriales"+ " WHERE codEditorial = ?");
			//pasamos los parametros
			consulta.setInt(1, ed.getCodEditorial());
					
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
