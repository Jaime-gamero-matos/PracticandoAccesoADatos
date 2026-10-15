package solucionBDDLambdaYMapperClaude;

import java.sql.ResultSet;
import java.util.Optional;

@FunctionalInterface
public interface MapperClaude<T> {
	Optional<T> map(ResultSet resultset);
}
