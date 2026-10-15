package solucionBDDLambdaYMapperClaude;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlmacenBDDJDBCClaude {

	private final String url;
	private final String usuario;
	private final String password;

	public AlmacenBDDJDBCClaude(String url, String usuario, String password) {
		this.url = url;
		this.usuario = usuario;
		this.password = password;
	}

	private Connection conectar() throws SQLException {
		return DriverManager.getConnection(url, usuario, password);
	}

	/**
	 * Ejecuta cualquier SELECT y convierte cada fila con el Mapper recibido
	 * (puede pasarse una clase o una lambda). Los parametros sustituyen a los '?'.
	 */
	public <T> List<T> consultar(String sql, MapperClaude<T> mapper, Object... parametros) {
		List<T> resultado = new ArrayList<>();

		try (Connection con = conectar();
				PreparedStatement ps = con.prepareStatement(sql)) {

			for (int i = 0; i < parametros.length; i++) {
				ps.setObject(i + 1, parametros[i]);
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Optional<T> fila = mapper.map(rs);
					fila.ifPresent(resultado::add);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error ejecutando la consulta: " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * SELECT * FROM tabla. El nombre de la tabla no se puede pasar como
	 * parametro '?', asi que se valida para evitar inyeccion SQL.
	 */
	public <T> List<T> consultarTodo(String tabla, MapperClaude<T> mapper) {
		if (tabla == null || !tabla.matches("[A-Za-z_][A-Za-z0-9_]*")) {
			throw new IllegalArgumentException("Nombre de tabla no valido: " + tabla);
		}
		return consultar("SELECT * FROM " + tabla, mapper);
	}

	/** Atajo concreto para personas. */
	public List<Persona> consultarTodasLasPersonas() {
		return consultarTodo("persona", new MapperPersonaClaude());
	}
}
