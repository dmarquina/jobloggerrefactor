package joblogger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

interface LogService {

  void logToFile(String finalMsg, Map dbParams, Logger logger) throws IOException;

  void logToConsole(String finalMsg, Logger logger);

  void logToDataBase(String messageText, int messageType, Map dbParams) throws SQLException;
}
