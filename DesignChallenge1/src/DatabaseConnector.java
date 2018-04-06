import java.sql.Connection;

public interface DatabaseConnector {

	public abstract Connection getConnection();
	
}
