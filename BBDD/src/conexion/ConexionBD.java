/**
 * 
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jose
 *
 */
public class ConexionBD {

	private static final String database = "biblioteca";
	private static final String usuario = "biblioteca";
	private static final String contraseña = "pitufo10";
	private static final String url = "jdbc:mysql://localhost/" + database;
	private  Connection conexion=null;
	
	
	public Connection getConexion() {
		
		if(conexion != null) {
			return conexion;
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //REGISTRA EL DRIVER DE MYSQL
			
			conexion = DriverManager.getConnection(url, usuario, contraseña); //HACE LA CONEXION
			System.out.println("Conexion a biblioteca correcta!");
			
		} catch (ClassNotFoundException e) { // para el driver
			System.out.println("Driver no registrado");
		} catch (SQLException e) { //para lo demas
			System.out.println("Error SQLException: "+e.getMessage());
		}
		
		return conexion;
	}
	
	public void desconectar() {
		
		try {
			conexion.close();
			conexion = null;
		} catch (SQLException e) {
			System.out.println("No se puede cerrar la conexion " + e.getMessage());
		}
		
	}

}
