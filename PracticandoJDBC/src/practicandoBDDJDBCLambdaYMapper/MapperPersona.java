package practicandoBDDJDBCLambdaYMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MapperPersona implements Mapper<Persona> {

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
