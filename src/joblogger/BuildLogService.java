package joblogger;

interface BuildLogService {

  String buildFinalLogMessage(String messageText, boolean logError, boolean logWarning,
      boolean logMessage);

  int buildDataBaseMessageType(boolean logError, boolean logWarning, boolean logMessage);
}
