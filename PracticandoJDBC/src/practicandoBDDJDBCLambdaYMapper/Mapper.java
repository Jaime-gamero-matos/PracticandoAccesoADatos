package practicandoBDDJDBCLambdaYMapper;

import java.sql.ResultSet;
import java.util.Optional;

@FunctionalInterface
public interface Mapper<T> {
	Optional<T> map(ResultSet resultset);
}
