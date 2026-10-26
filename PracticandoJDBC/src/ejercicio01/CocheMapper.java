package ejercicio01;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CocheMapper implements Mapper<Coche>{
	@Override
	public Optional<Coche> map(ResultSet resultado) {
		try {
			return Optional.of(new Coche(resultado.getString("matricula"),
					resultado.getString("marca"),
					resultado.getNString("modelo")));
		} catch (SQLException exc) {
			System.err.println(exc);
		}
		return Optional.empty();
	}
}
