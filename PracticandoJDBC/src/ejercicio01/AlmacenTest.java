package ejercicio01;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AlmacenTest {

	@Test
	void test() {
		AlmacenJDBC<Coche> almacenCoches = AlmacenJDBC.<Coche>builder()
				.bddUrl("jdbc:mysql://localhost:3306/jdbc_practica")
				.user("root")
				.password("1234")
				.mapper(new CocheMapper())
				.clase(Coche.class)
				.build();

		ArrayList<Coche> coches = (ArrayList<Coche>) almacenCoches.getAll();
		System.out.println(coches);
	}

}
