package joblogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import static joblogger.util.Constants.*;

public class DataBaseServiceImpl implements DataBaseService {

  Connection connection;

  @Override
  public void createConnection(Map dbParams) throws SQLException {

    Properties connectionProps = new Properties();
    connectionProps.put(USER_KEY, dbParams.get(USER_VALUE));
    connectionProps.put(PASSWORD_KEY, dbParams.get(PASSWORD_VALUE));

    connection = DriverManager.getConnection(generateConnectionString(dbParams), connectionProps);
  }

  @Override
  public Statement createStatement() {
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return statement;
  }

  @Override
  public void closeConnection(){
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private String generateConnectionString(Map dbParams) {
    return STRING_JDBC + dbParams.get(STRING_DBMS) + CONNECTION_SLASHES + dbParams.get(SERVER_NAME)
        + POINTS + dbParams.get(PORT_NUMBER) + SLASH;
  }

}
