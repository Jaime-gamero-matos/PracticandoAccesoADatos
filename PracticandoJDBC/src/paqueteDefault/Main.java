package paqueteDefault;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		// vamos a utilizar bastante esta estructura de control que no es un try catch
		// normal es un try with, lo que hace que cuando usemos alguna clase que sea
		// Closeable
		// es decir que necesita ser cerrada, el try with se encargará de cerrarla
		// automaticamente cuando terminemos
		try (Connection conexionBD = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_practica", "root","1234");
				Statement sentenciaSelectAll = conexionBD.createStatement();
				ResultSet resultadoSelectAll = sentenciaSelectAll.executeQuery("SELECT * FROM usuarios;");) {
			String nombreDB = conexionBD.getCatalog();
			System.out.println("¡La conexión a la base de datos " + nombreDB + " ha sido exitosa!");
			// Statement y Resultset tambien son closeables
			while (resultadoSelectAll.next()) {
				int idUsuario = resultadoSelectAll.getInt("id");
				String nombreUsuario = resultadoSelectAll.getString("nombre");
				String emailUsuario = resultadoSelectAll.getString("email");
				int edadUsuario = resultadoSelectAll.getInt("edad");
				System.out.println("ID: " + idUsuario + " Nombre: " + nombreUsuario + " Email: " + emailUsuario
						+ " Edad: " + edadUsuario);
			}
			String sentenciaUsuariosQueContienenO = "SELECT * FROM usuarios WHERE nombre LIKE ?";
			try (PreparedStatement nombresQueContienenO = conexionBD.prepareStatement(sentenciaUsuariosQueContienenO)){
				nombresQueContienenO.setString(0, sentenciaUsuariosQueContienenO);
			} catch (SQLException excepcionSentenciaPreparad) {
				System.out.println("la sentenciaPreparada ha fallado");
			}
		} catch (SQLException falloConexionSQL) {
			System.out.println("La conexión a la base de datos ha fallado");
		}

	}
}
