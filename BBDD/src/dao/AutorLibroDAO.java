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
import modelo.AutorLibro;
import modelo.Libro;

/**
 * @author Jose
 * clase que implemente un CRUD de la base de datos
 * create
 * read
 * update
 * delete
 */
public class AutorLibroDAO {
	
	private static ConexionBD conexion;
	
	public AutorLibroDAO() {
		this.conexion = new ConexionBD();
	}
	
	public static int añadirAutorLibro(int idAutor, String isbn) {
		//CONEXION CON LA BBDD
		Connection con = conexion.getConexion();
		
		PreparedStatement consulta = null;
		int resultado = 0;
				
		try {
			consulta = con.prepareStatement("INSERT INTO autorlibro (idAutor, isbn)" + " VALUES (?,?) ");
			//pasamos los parametros
			consulta.setInt(1, idAutor);
			consulta.setString(2, isbn);
					
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

}
