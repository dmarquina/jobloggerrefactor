package joblogger;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogServiceImpl implements LogService {

  private DataBaseService dataBaseService;

  public LogServiceImpl(DataBaseService dataBaseService) {
    this.dataBaseService = dataBaseService;
  }

  @Override
  public void logToFile(String finalMessage, Map dbParams, Logger logger) throws IOException {
    FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
    logger.addHandler(fh);
    logger.log(Level.INFO, finalMessage);
  }

  @Override
  public void logToConsole(String finalMessage, Logger logger) {
    ConsoleHandler ch = new ConsoleHandler();
    logger.addHandler(ch);
    logger.log(Level.INFO, finalMessage);
  }

  @Override
  public void logToDataBase(String messageText, int messageType, Map dbParams) throws SQLException {
    dataBaseService.createConnection(dbParams);
    Statement stmt = dataBaseService.createStatement();
    stmt.executeUpdate("insert into Log_Values('" + messageText + "', " + messageType + ")");
    dataBaseService.closeConnection();
  }
}
