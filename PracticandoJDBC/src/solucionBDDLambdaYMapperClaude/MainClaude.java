package solucionBDDLambdaYMapperClaude;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainClaude {

	public static void main(String[] args) {
		// Ajusta url, usuario y password a tu base de datos
		AlmacenBDDJDBCClaude almacen = new AlmacenBDDJDBCClaude(
				"jdbc:mysql://localhost:3306/mi_bdd", "root", "");

		// 1) Con la clase mapper
		List<Persona> personas = almacen.consultarTodasLasPersonas();
		personas.forEach(System.out::println);

		// 2) Con una lambda (por eso Mapper es @FunctionalInterface)
		List<Persona> personas2 = almacen.consultarTodo("persona", rs -> {
			try {
				return Optional.of(new Persona(
						rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos")));
			} catch (SQLException e) {
				return Optional.empty();
			}
		});
		personas2.forEach(System.out::println);
	}
}
