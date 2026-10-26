package ejercicio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AlmacenJDBC<T> {
	private String bddUrl;
	private String user;
	private String password;	
	private Connection conexion = null;
	private Mapper<T> mapper;
	private Class<T> clase;
	
	
	public AlmacenJDBC(String bddUrl,String user,String password, Connection conexion, Mapper<T> mapper, Class<T> clase) {
		super();
		this.bddUrl = bddUrl;
		this.user = user;
		this.password = password;
		this.conexion = conexion;
		this.mapper = mapper;
		this.clase = clase;
		
		
		try {
			conexion = DriverManager.getConnection(bddUrl, user, password);
			
		} catch (SQLException exc) {
			System.err.println(exc);
		}
	}
	
	

	public AlmacenJDBC(Builder<T> builder) {
		this.bddUrl = builder.bddUrl;
		this.user = builder.user;
		this.password = builder.password;
		this.mapper = builder.mapper;
		this.clase = builder.clase;
		
		Connection conexion = builder.conexion;
		
		try {
			conexion = DriverManager.getConnection(bddUrl, user, password);
		} catch (SQLException exc) {
			System.err.println(exc);
		}
		this.conexion = conexion;
	}



	public List<T> getAll(){
		ArrayList<T> listaObjetos = new ArrayList<T>();
		try (Statement consulta = conexion.createStatement();
				ResultSet resultadoConsulta = consulta.executeQuery("SELECT * FROM " + clase.getSimpleName());){
			while (resultadoConsulta.next()) {
				mapper.map(resultadoConsulta).ifPresent(listaObjetos::add);
			}
		} catch (SQLException exc) {
			System.err.println(exc);
		}
		return listaObjetos;
	}
	
	
public static <T> Builder<T> builder() {
	return new Builder<>();
}

public static class Builder<T> { 
	private String bddUrl;
	private String user;
	private String password;
	private Connection conexion = null;
	private Mapper<T> mapper;
	private Class<T> clase;
	
	public Builder<T> bddUrl(String bddUrl) {
		this.bddUrl = bddUrl;
		return this;
	}

	public Builder<T> user(String user) {
		this.user = user;
		return this;
	}

	public Builder<T> password(String password) {
		this.password = password;
		return this;
	}

	public Builder<T> mapper(Mapper<T> mapper) {
		this.mapper = mapper;
		return this;
	}

	public Builder<T> clase(Class<T> clase) {
		this.clase = clase;
		return this;
	}

	public AlmacenJDBC<T> build() {
		return new AlmacenJDBC<>(this);
	}
	

}
}