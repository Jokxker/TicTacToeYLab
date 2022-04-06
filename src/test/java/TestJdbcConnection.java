import com.aleks.repository.PlayerRepository;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJdbcConnection {
    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        try(Connection connection = new PlayerRepository().connection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }
}
