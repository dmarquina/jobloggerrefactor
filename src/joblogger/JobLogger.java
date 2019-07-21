package joblogger;

import java.util.Map;
import java.util.logging.Logger;

public class JobLogger {

  private static boolean logToFile;
  private static boolean logToConsole;
  private static boolean logToDatabase;

  private static boolean logMessage;
  private static boolean logWarning;
  private static boolean logError;

  private static Map dbParams;
  private static Logger logger;

  private ValidateService validateService;
  private BuildLogService buildLogService;
  private LogService logService;

  public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
      boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap,
      ValidateService validateService, BuildLogService buildLogService, LogService logService) {
    logger = Logger.getLogger("MyLog");
    logError = logErrorParam;
    logMessage = logMessageParam;
    logWarning = logWarningParam;

    logToFile = logToFileParam;
    logToConsole = logToConsoleParam;
    logToDatabase = logToDatabaseParam;

    dbParams = dbParamsMap;

    this.validateService = validateService;
    this.buildLogService = buildLogService;
    this.logService = logService;
  }

  public void executeJobLogger(String messageText) throws Exception {

    validateService.validateEntryValues(messageText, logToConsole, logToFile, logToDatabase,
                                        logError, logMessage, logWarning);

    String finalMessage =
        buildLogService.buildFinalLogMessage(messageText, logError, logWarning, logMessage);

    if (logToFile) {
      logService.logToFile(finalMessage, dbParams, logger);
    }
    if (logToConsole) {
      logService.logToConsole(finalMessage, logger);
    }
    if (logToDatabase) {
      int messageType =
          buildLogService.buildDataBaseMessageType(logError, logWarning, logMessage);
      logService.logToDataBase(messageText, messageType, dbParams);
    }
  }
}