package elyland.refactoring;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection getConnection();
}
