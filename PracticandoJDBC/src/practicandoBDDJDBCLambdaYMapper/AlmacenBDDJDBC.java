package practicandoBDDJDBCLambdaYMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AlmacenBDDJDBC <T>{
	private static String URLBDD = "jdbc:mysql://localhost:3306/jdbc_practica";
	private String contrasenia;
	private String usuario;
	private Mapper<T> mapper;
	private Nombrador<T> nombrador;
	
	
	public AlmacenBDDJDBC(String URLBDD ,String contrasenia, String usuario, Mapper<T> mapper,
			Nombrador<T> nombrador) {
		super();
		this.URLBDD = URLBDD;
		this.nombrador = nombrador;
		this.mapper = mapper;
		try (Connection conexion = DriverManager.getConnection(URLBDD, contrasenia, usuario)){
			
		} catch (SQLException e) {
			System.err.println("Error al conectar en la base de datos");
		}
	}

	
	
	
	
	
}
