package solucionBDDLambdaYMapperClaude;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MapperPersonaClaude implements MapperClaude<Persona> { // antes: Mapper<T> (no compilaba)

	@Override
	public Optional<Persona> map(ResultSet resultset) {
		try {
			return Optional.of(new Persona(
					resultset.getInt("id"),
					resultset.getString("nombre"),
					resultset.getString("apellidos")));
		} catch (SQLException e) {
			System.err.println("Fallo al traer la consulta de una persona");
		}

		return Optional.empty();
	}

}
