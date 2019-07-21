package joblogger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public interface DataBaseService {

  void createConnection(Map dbParams) throws SQLException;

  Statement createStatement();

  void closeConnection();
}
